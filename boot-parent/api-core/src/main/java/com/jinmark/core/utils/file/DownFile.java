package com.jinmark.core.utils.file;
/**
 * 
 * @author QC
 * @ClassName DownFile
 * @Description TODO(下载文件对象) 
 * @date 2017年9月12日上午10:47:59
 */
public class DownFile {
	/**
	 * 文件主键
	 */
	private String id;
	/**
	 * 原文件名
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String filePath;
	
	

	public DownFile() {
		super();
	}

	

	public DownFile(String fileName, String filePath) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
	}



	public DownFile(String id, String fileName, String filePath) {
		this(fileName, filePath);
		this.id = id;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
