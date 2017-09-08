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
	protected int pageSize = 10;
	/**
	 * 当前页
	 */
	protected int pageCurrent = 1;
	/**
	 * 总条数
	 */
	protected int total;
	/**
	 * 总页数
	 */
	protected int totalPage;
	
	public Pages() {
		super();
	}
	public Pages(int pageSize, int pageCurrent, int total, int totalPage) {
		super();
		this.pageSize = pageSize;
		this.pageCurrent = pageCurrent;
		this.total = total;
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
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
