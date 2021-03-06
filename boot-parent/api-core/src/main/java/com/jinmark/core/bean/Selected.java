package com.jinmark.core.bean;

import java.util.List;

/**
 * 
 * @author QC
 * @ClassName Selected
 * @Description TODO(下拉|复选|单选列表item项) 
 * @date 2017年9月28日上午11:31:11
 */
public class Selected {
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 显示名称
	 */
	private String name;
	/**
	 * 是否已选中
	 */
	private boolean selected;
	/**
	 * 是否禁用
	 */
	private boolean disabled;
	
	/**
	 * 图标class
	 */
	private String iconClass;
	
	private List<Selected> children;
	
	public Selected() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Selected(String id, String name, boolean selected) {
		super();
		this.id = id;
		this.name = name;
		this.selected = selected;
	}

	
	
	public Selected(String id, String name, boolean selected, String iconClass) {
		super();
		this.id = id;
		this.name = name;
		this.selected = selected;
		this.iconClass = iconClass;
	}

	public Selected(String id, String name, boolean selected, boolean disabled) {
		this(id, name, selected);
		this.disabled = disabled;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public List<Selected> getChildren() {
		return children;
	}

	public void setChildren(List<Selected> children) {
		this.children = children;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	
	
}
