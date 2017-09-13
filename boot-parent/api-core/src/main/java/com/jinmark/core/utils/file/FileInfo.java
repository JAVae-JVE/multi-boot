package com.jinmark.core.utils.file;
/**
 * 
 * @author QC
 * @ClassName FileInfo
 * @Description TODO(上传的文件信息) 
 * @date 2017年9月12日上午10:27:03
 */
public class FileInfo {
	/**
	 * 数据库存放文件名-带后缀
	 */
	private String dbFileName;
	/**
	 * 原文件名
	 */
	private String fileName;
	/**
	 * 文件的MimeType
	 */
	private String fileMimeType;
	
	
	
	public FileInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileInfo(String dbFileName, String fileName, String fileMimeType) {
		super();
		this.dbFileName = dbFileName;
		this.fileName = fileName;
		this.fileMimeType = fileMimeType;
	}
	public String getDbFileName() {
		return dbFileName;
	}
	public void setDbFileName(String dbFileName) {
		this.dbFileName = dbFileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileMimeType() {
		return fileMimeType;
	}
	public void setFileMimeType(String fileMimeType) {
		this.fileMimeType = fileMimeType;
	}
	
}
