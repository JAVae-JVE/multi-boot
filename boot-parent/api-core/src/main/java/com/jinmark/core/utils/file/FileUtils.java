package com.jinmark.core.utils.file;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.jinmark.core.Constants;
import com.jinmark.core.bean.Response;
import com.jinmark.core.utils.StrUtils;

/**
 * 上传文件和下载文件 QC 2016年12月21日 下午2:17:37
 */
public class FileUtils {

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            文件
	 * @param saveDir
	 *            保存目录
	 * @param fileType
	 *            允许的文件类型，格式如下“-image/jpeg-image/gif-”前后都以逗号相隔
	 * @param fileSize
	 *            文件大小，单位字节，最大为20971520(20MB)字节
	 * @return Response
	 */
	public static Response<FileInfo> fileUpload(MultipartFile file, String saveDir,
			String fileType, Long fileSize) {
		Response<FileInfo> res = null;
		if (file != null && StringUtils.isNotEmpty(saveDir)) {
			try {
				String fileMimeType = getMimeType(file.getBytes());
				if (StringUtils.isNotEmpty(fileType)) {
					if (!checkFormat(file.getBytes(), fileType)) {
						// 上传文件类型不允许
						res = new Response<FileInfo>(false, "不允许的文件类型");
						return res;
					}
				}
				if (fileSize != null) {
					if (fileSize < file.getSize()) {
						// 上传文件过大
						res = new Response<FileInfo>(false, "文件不得超过"
								+ (fileSize / (1024 * 1024)) + "MB");
						return res;
					}
				}
				String fileName = file.getOriginalFilename();

				if (StrUtils.findSpecialCharacters(fileName)) {
					res = new Response<FileInfo>(false, "文件名不能包含特殊字符");
					return res;
				}

				File dir = new File(saveDir);
				if (!dir.exists()) {
					dir.mkdirs();
				}
 				
				String dbFileName = UUID.randomUUID().toString()
						.replace("-", "")
						+ fileName.substring(fileName.lastIndexOf("."));
				StringBuffer path = new StringBuffer(saveDir);
				path.append(File.separator).append(dbFileName);

				File localFile = new File(path.toString());
				file.transferTo(localFile);
																			// 为空时传入null,
																			// height
																			// 为空时传入null
				// 上传成功后，把保存到数据库的文件相对路径:原文件名:文件的MIME类型以逗号隔开返回
				res = new Response<FileInfo>(true, "上传成功", new FileInfo(dbFileName, fileName, fileMimeType));

			} catch (Exception e) {
				e.printStackTrace();
				res = new Response<FileInfo>(false, "上传失败");
			}
		} else {
			res = new Response<FileInfo>(false, "上传失败");
		}
		return res;
	}

	/**
	 * 下载单个文件
	 * 
	 * @param fileName
	 *            原文件名
	 * @param filePath
	 *            文件路径
	 * @param response
	 *            response对象
	 */
	public static void fileDownload(String fileName, String filePath,
			HttpServletResponse response) {
		File file = new File(filePath);
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.addHeader("Content-Length", String.valueOf(file.length()));
		try {
			response.addHeader("Content-Disposition", "attachment;fileName="
					+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		InputStream inputStream = null;
		OutputStream os = null;

		try {
			inputStream = new FileInputStream(file);
			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 批量打包
	 * 
	 * @param downFiles
	 *            需要打包下载的文件对象集合
	 * @param response
	 *            response对象
	 * @return DownFile 打包成功的文件对象
	 */
	public static DownFile fileBatch(List<DownFile> downFiles,
			HttpServletResponse response) {
		// 在服务器端创建打包下载的临时文件目录
		try {

			String tempFilePath = Constants.ZIPFILEPATH;
			File fileDir = new File(tempFilePath);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			// 在服务器端创建打包下载的临时文件
			String fileName = UUID.randomUUID().toString().replace("-", "")
					+ ".zip";
			File tempFile = new File(fileDir, fileName);
			if (!tempFile.exists()) {
				tempFile.createNewFile();
			}

			FileOutputStream outputStream = new FileOutputStream(tempFilePath
					+ File.separator + fileName);
			// 压缩流
			ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream,
					Charset.forName("UTF-8"));
			// 把文件列表打包
			zipFiles(downFiles, zipOutputStream);
			zipOutputStream.close();
			outputStream.close();
			/*
			 * //下载压缩文件 FileUtils.fileDownload(tempFile.getName(),
			 * tempFile.getAbsolutePath(), response); //下载完成后删除临时zip文件
			 * FileUtils.deleteFile(tempFile);
			 */
			return new DownFile(fileName, fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 压缩文件列表中的文件
	 * 
	 * @param files
	 *            文件列表
	 * @param outputStream
	 *            输出流
	 * @throws IOException
	 *             异常
	 * @throws ServletException
	 *             异常
	 */
	public static void zipFiles(List<DownFile> files,
			ZipOutputStream outputStream) throws IOException, ServletException {
		try {
			for (DownFile file : files) {
				writeZipFile(file, outputStream);
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 将文件写入到zip文件中
	 * 
	 * @param downFile
	 *            文件
	 * @param outputstream
	 *            输出流
	 * @throws IOException
	 *             异常
	 * @throws ServletException
	 *             异常
	 */
	public static void writeZipFile(DownFile downFile,
			ZipOutputStream outputstream) throws IOException, ServletException {
		try {
			File inputFile = new File(Constants.FILEPATH + File.separator
					+ downFile.getFilePath());
			if (inputFile.exists()) {
				if (inputFile.isFile()) {
					FileInputStream inStream = new FileInputStream(inputFile);
					BufferedInputStream bInStream = new BufferedInputStream(
							inStream);
					ZipEntry entry = new ZipEntry(downFile.getFileName());
					outputstream.putNextEntry(entry);

					final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
					long streamTotal = 0; // 接受流的容量
					int streamNum = 0; // 流需要分开的数量
					int leaveByte = 0; // 文件剩下的字符数
					byte[] inOutbyte; // byte数组接受文件的数据

					streamTotal = bInStream.available(); // 通过available方法取得流的最大字符数
					streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
					leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量

					if (streamNum > 0) {
						for (int j = 0; j < streamNum; ++j) {
							inOutbyte = new byte[MAX_BYTE];
							// 读入流,保存在byte数组
							bInStream.read(inOutbyte, 0, MAX_BYTE);
							outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
						}
					}
					// 写出剩下的流数据
					inOutbyte = new byte[leaveByte];
					bInStream.read(inOutbyte, 0, leaveByte);
					outputstream.write(inOutbyte);
					outputstream.closeEntry(); // Closes the current ZIP entry
					// and positions the stream for
					// writing the next entry
					bInStream.close(); // 关闭
					inStream.close();
				}
			} else {
				throw new ServletException("文件不存在！");
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 删除本地服务器指定目录下所有东西（文件、文件夹）
	 * 
	 * @param file
	 *            文件
	 * @return boolean
	 */
	public static boolean deleteDir(File file) {
		try {
			if (file.exists() && file.isDirectory()) {
				File[] files = file.listFiles();
				for (File f : files) {
					if (f.isDirectory()) {
						deleteDir(f);
					} else {
						deleteFile(f);
					}
				}
			}
			file.delete();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 删除指定文件
	 * 
	 * @param file
	 *            文件
	 */
	public static void deleteFile(File file) {
		if (file.exists() && file.isFile()) {
			file.delete();
		}
	}

	/**
	 * 得到目录下的文件
	 * 
	 * @param file
	 *            下面全是文件
	 * @return int
	 */
	public static int getFilesCount(File file) {
		return file.listFiles().length;
	}

	/**
	 * 得到目录下最后一次被修改的文件
	 * 
	 * @param file
	 *            文件
	 * @return File
	 */
	public static File getLastModified(File file) {
		File[] files = file.listFiles();
		File max = files[0];
		for (int i = 1; i < files.length; i++) {
			if (files[i].lastModified() > max.lastModified())
				max = files[i];
		}
		return max;
	}

	/**
	 * 判断文件类型是否满足允许类型
	 * 
	 * @param data
	 *            上传的文件字节数组
	 * @param allowedTypes
	 *            允许上传的文件类型
	 * @return boolean
	 */
	public static boolean checkFormat(byte[] data, String allowedTypes) {
		String filetype = getMimeType(data);
		if (allowedTypes.indexOf(filetype) != -1) {
			return true;
		}
		return false;
	}

	/**
	 * 获取文件的MIME类型
	 * 
	 * @param data
	 *            上传的文件字节数组
	 * @return String
	 */
	public static String getMimeType(byte[] data) {
		String mimeType = "--";
		MagicMatch match = null;
		try {
			match = Magic.getMagicMatch(data);
			mimeType = "-" + match.getMimeType().toLowerCase() + "-";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mimeType;
	}
	
	
	/**
	 * 案例等比例压缩图片+图片截图
	 * @param file
	 * @param saveDir 源文件保存目录
	 * @param fileType 文件类型
	 * @param fileSize 源文件大小
	 * @param width 缩放后宽
	 * @param height 缩放后高
	 * @param size 压缩图片文件大小
	 * @return Response
	 */
	public static Response<FileInfo> thumbnailFile(MultipartFile file, String saveDir,
			String fileType, Long fileSize, int width, int height) {
		//上传原图
		Response<FileInfo> res = fileUpload(file, saveDir, fileType, fileSize);
		
		File fromPic = new File(saveDir + File.separator + res.getResult().getDbFileName());
		BufferedImage image;
		try {
			image = ImageIO.read(fromPic);
			Builder<BufferedImage> builder = null;  
			//原图宽 
			int imageWidth = image.getWidth(); 
			//原图宽高
			int imageHeitht = image.getHeight();
			
			//1.PC详情+微信
			File caseDetail = new File(Constants.FILEPATH_CASEDETAIL);
			if (!caseDetail.exists()) {
				caseDetail.mkdirs();
			}
			if(imageWidth <= 740) {
				//原图
				Thumbnails.of(image).outputQuality(0.25f).size(imageWidth,imageHeitht).toFile(new File(caseDetail, res.getResult().getDbFileName()));
			}else {
				Thumbnails.of(image).outputQuality(0.25f).size(740, (int)((float)740*imageHeitht/imageWidth)).toFile(new File(caseDetail, res.getResult().getDbFileName()));
			}
			
			
			//2.列表+封面
			File caseList = new File(Constants.FILEPATH_CASELIST);
			if (!caseList.exists()) {
				caseList.mkdirs();
			}
			if ((float)width / height != (float)imageWidth / imageHeitht) {  
			    if (imageWidth > imageHeitht) {  
			        image = Thumbnails.of(fromPic).height(height).asBufferedImage();  
			    } else {  
			        image = Thumbnails.of(fromPic).width(width).asBufferedImage();  
			    }  
			    builder = Thumbnails.of(image).outputQuality(0.25f).size(width, height).sourceRegion(Positions.CENTER, width, height);  
			} else {  
			    builder = Thumbnails.of(image).outputQuality(0.25f).size(width, height);  
			}  
			builder.toFile(new File(caseList, res.getResult().getDbFileName()));  
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
