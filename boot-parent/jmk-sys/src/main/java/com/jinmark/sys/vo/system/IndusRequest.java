package com.jinmark.sys.vo.system;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 
 * @author QC
 * @ClassName IndusRequest
 * @Description TODO(行业验证对象) 
 * @date 2018年3月27日下午3:44:23
 */
public class IndusRequest {
	/**
	 * 
	 */
	private String id;
	/**
	 * 行业名称
	 */
	@Length(min = 2, max = 20, message = "{indus.name}")
	private String indusName;
	
	/**
	 * 父级行业id
	 */
	@NotEmpty(message = "{indus.parentId}")
	private String parentId;
	/**
	 * 顺序
	 */
	private Integer indusSort;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIndusName() {
		return indusName;
	}
	public void setIndusName(String indusName) {
		this.indusName = indusName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getIndusSort() {
		return indusSort;
	}
	public void setIndusSort(Integer indusSort) {
		this.indusSort = indusSort;
	}
	
	
}
