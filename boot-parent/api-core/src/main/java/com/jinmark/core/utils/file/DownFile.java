package com.jinmark.core.utils.file;
/**
 * 
 * QC
 * 2016年12月21日 下午2:17:06
 */
public class DownFile {
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
