package com.jinmark.core.bean;
/**
 * 分页对象
 * QC
 * 2016年12月21日 下午1:53:30
 */
public class Pages {
	/**
	 * 每页大小
	 */
	private int size = 10;
	/**
	 * 当前页索引
	 */
	private int page = 0;
	public Pages() {
		super();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
}
