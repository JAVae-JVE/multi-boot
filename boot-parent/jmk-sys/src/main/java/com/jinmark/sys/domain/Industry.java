package com.jinmark.sys.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "industry", uniqueConstraints = {@UniqueConstraint(columnNames = "indus_name") })
public class Industry implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9158702126839988543L;

	private String id;
	/**
	 * 行业名称
	 */
	private String indusName;
	/**
	 * 行业排序
	 */
	private Integer indusSort;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;
	/**
	 * 父id
	 */
	private String parentId;
	
	private List<Industry> children;
	
	
	
	public Industry() {
		super();
	}
	public Industry(String id) {
		super();
		this.id = id;
	}
	
	
	public Industry(String id, String indusName, Integer indusSort, Date gmtCreate, Date gmtModified, String parentId) {
		super();
		this.id = id;
		this.indusName = indusName;
		this.indusSort = indusSort;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
		this.parentId = parentId;
	}
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "indus_name", nullable = false, unique = true)
	public String getIndusName() {
		return indusName;
	}
	public void setIndusName(String indusName) {
		this.indusName = indusName;
	}
	@Column(name = "indus_sort")
	public Integer getIndusSort() {
		return indusSort;
	}
	public void setIndusSort(Integer indusSort) {
		this.indusSort = indusSort;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "gmt_create")
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "gmt_modified")
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	@Column(name = "parent_id")
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Transient
	public List<Industry> getChildren() {
		return children;
	}
	public void setChildren(List<Industry> children) {
		this.children = children;
	}
	
	
}
