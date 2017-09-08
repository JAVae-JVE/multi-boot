package com.jinmark.core.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

/**
 * 文件分割
 * QC
 * 2017年2月28日 下午3:27:08
 */
public class SplitFile {
	/*
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件大小
	 */
	private long length;
	/**
	 * 分割后的存放目录
	 */
	private String destBlockPath;
	/**
	 * 块数
	 */
	private int size;
	/**
	 * 每块大小
	 */
	private long blockSize;
	/**
	 * 每块名称
	 */
	private List<String> blockPath;
	
	
	public SplitFile() {
		blockPath = new ArrayList<String>();
	}

	public SplitFile(String filePath, String destBlockPath) {
		this(filePath, destBlockPath, 1024);
	}
	
	public SplitFile(String filePath, String destBlockPath, long blockSize) {
		this();
		this.filePath = filePath;
		this.destBlockPath = destBlockPath;
		this.blockSize = blockSize;
		init();
	}
	
	/**
	 * 初始化操作 计算块数 确定文件名
	 * 
	 * @return void
	 */
	public void init() {
		File src = null;
		if(StringUtils.isBlank(this.filePath) || !(src = new File(this.filePath)).exists()) {
			return ;
		}
		if(src.isDirectory()) {
			return ;
		}
		//文件名
		this.fileName = src.getName();
		//计算块数 实际大小 每块大小
		this.length = src.length();
		//修正每块大小
		if(this.blockSize > this.length) {
			this.blockSize = this.length;
		}
		
		//确定块数
		this.size = (int)Math.ceil(this.length*1.0 / this.blockSize);
		
		initPathName();
	}
	
	/**
	 * 初始化分割文件的路径
	 * @param destPath
	 * @return void
	 */
	private void initPathName() {
		String fileSuffix = this.fileName.substring(this.fileName.lastIndexOf("."));
		for(int i = 0;i<size;i++) {
			this.blockPath.add(this.destBlockPath + File.separator + this.fileName.substring(0, this.fileName.lastIndexOf(".") - 1) + ".part" + i + fileSuffix);
		}
	}
	/**
	 * 文件的分割
	 * @return void
	 */
	public void split() {
		long beginPos = 0;//起始点
		long actualBlockSize = this.blockSize;//实际大小
		for(int i= 0;i<size;i++) {
			if(i == size-1) {
				actualBlockSize = this.length - beginPos;
			}
			splitDetail(i, beginPos, actualBlockSize);
			beginPos += actualBlockSize;
		}
	}
	/**
	 * 具体分割文件方法
	 * @param index 第几块
	 * @param beginPos 分割的起始位置
	 * @param actualBlockSize 实际块大小
	 * @return void
	 */
	private void splitDetail(int index, long beginPos, long actualBlockSize) {
		File src = new File(this.filePath);
		File dest = new File(this.blockPath.get(index));
		RandomAccessFile accessFile = null;//输入流
		BufferedOutputStream bos = null;//输出流
		try {
			accessFile = new RandomAccessFile(src, "r");
			bos = new BufferedOutputStream(new FileOutputStream(dest));
			//设置读取文件起始位置
			accessFile.seek(beginPos);
			byte[] flush = new byte[1024];
			int len = 0;
			while(-1 != (len = accessFile.read(flush))) {
				if(actualBlockSize - len >= 0) {//查看是否足够
					bos.write(flush, 0, len);
					actualBlockSize -= len;//剩余量
				}else {//写出最后一次剩余量
					bos.write(flush, 0, (int) actualBlockSize);
					break;
				}
				
			}
			bos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null != bos) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(null != accessFile) {
				try {
					accessFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 文件的合并
	 * @param destPath
	 * @return void
	 */
	public void mergeFile(String destPath) {
		File dest = new File(destPath);
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(dest, true));//追加
			BufferedInputStream bis = null;
			for(int i = 0; i<this.blockPath.size(); i++) {
				bis = new BufferedInputStream(new FileInputStream(new File(this.blockPath.get(i))));
				byte[] flush = new byte[1024];
				int len = 0;
				while(-1 != (len = bis.read(flush))) {
					bos.write(flush, 0, len);
				}
				bos.flush();
				if(null != bis) {
					bis.close();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != bos) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 文件的合并方法2
	 * @param destPath
	 * @return void
	 */
	public void mergeFile2(String destPath) {
		File dest = new File(destPath);
		BufferedOutputStream bos = null;
		//SequenceInputStream 表示其他输入流的逻辑串联。它从输入流的有序集合开始，并从第一个输入流开始读取，直到到达文件末尾，接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止。 
		SequenceInputStream sis = null;
		Vector<BufferedInputStream> v = new Vector<BufferedInputStream>();
		try {
			bos = new BufferedOutputStream(new FileOutputStream(dest, true));//追加
			
			for(int i = 0; i<this.blockPath.size(); i++) {
				v.add(new BufferedInputStream(new FileInputStream(new File(this.blockPath.get(i)))));
			}
			
			sis = new SequenceInputStream(v.elements());
			byte[] flush = new byte[1024];
			int len = 0;
			while(-1 != (len = sis.read(flush))) {
				bos.write(flush, 0, len);
			}
			bos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != sis) {
				try {
					sis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != bos) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SplitFile file = new SplitFile("D:\\tools\\apache-tomcat-6.0.44-windows-x64.zip", "D:\\tools", 1024*1024*2);
		System.out.println(file.size);
		file.split();
		file.mergeFile2("D:\\tools\\hhhh.zip");
	}
	
}
