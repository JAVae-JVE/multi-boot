package com.jinmark.sys.service.system;

import java.util.List;

import com.jinmark.core.bean.Response;
import com.jinmark.sys.domain.Industry;

/**
 * 
 * @author QC
 * @ClassName IndustryServiceI
 * @Description TODO(行业Service) 
 * @date 2018年2月28日下午4:34:05
 */
public interface IndustryServiceI {
	/**
	 * 
	 * @Title indusList
	 * @Description TODO(获取行业列表) 
	 * @return
	 * @return List<Industry>  返回类型 
	 * @throws
	 */
	List<Industry> indusList();
	/**
	 * 
	 * @Title getIndustry
	 * @Description TODO(根据行业id获取单个行业) 
	 * @param id
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response getIndustry(String id);
	/**
	 * 
	 * @Title deleteIndustry
	 * @Description TODO(删除某个行业) 
	 * @param id
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response deleteIndustry(String id);
	/**
	 * 
	 * @Title findParent
	 * @Description TODO(获取父行业) 
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response findParent();
	/**
	 * 
	 * @Title saveIndustry
	 * @Description TODO(保存行业) 
	 * @param industry
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response saveIndustry(Industry industry);

}
