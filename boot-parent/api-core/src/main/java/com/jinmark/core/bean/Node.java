package com.jinmark.core.bean;

import java.util.List;
/**
 * zTree节点树
 * QC
 * 2016年12月21日 下午1:51:21
 */
public class Node {
	/**
	 * 节点id
	 */
	private Integer id;
	/**
	 * 节点父id
	 */
	private Integer pid;
	/**
	 * 节点名称
	 */
	private String name;
	/**
	 * 子节点集合
	 */
	private List<Node> children;
	
	
	public Node(Integer id, Integer pid, String name, List<Node> children) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.children = children;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
	
}
