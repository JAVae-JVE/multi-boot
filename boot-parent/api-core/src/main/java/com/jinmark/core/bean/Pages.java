package com.jinmark.core.bean;
/**
 * 分页对象
 * QC
 * 2016年12月21日 下午1:53:30
 */
public class Pages {
	/**
	 * 页大小
	 */
	private int size = 10;
	/**
	 * 当前页
	 */
	private int page = 1;
	/**
	 * 总条数
	 */
	private int total;
	/**
	 * 总页数
	 */
	private int totalPage;
	
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
