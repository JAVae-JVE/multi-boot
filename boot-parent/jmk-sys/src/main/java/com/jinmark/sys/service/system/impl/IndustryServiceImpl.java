package com.jinmark.sys.service.system.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jinmark.core.bean.Response;
import com.jinmark.core.bean.Selected;
import com.jinmark.sys.domain.Industry;
import com.jinmark.sys.repository.IndustryRepository;
import com.jinmark.sys.service.system.IndustryServiceI;

/**
 * 
 * @author QC
 * @ClassName IndustryServiceImpl
 * @Description TODO(行业Service实现类) 
 * @date 2018年2月28日下午4:51:04
 */
@Service
public class IndustryServiceImpl implements IndustryServiceI {

	@Autowired
	private IndustryRepository industryRepository;
	
	@Override
	public List<Industry> indusList() {
		List<Industry> list = industryRepository.findByParentIdIsNullOrderByIndusSortAsc();
		if(list != null && list.size() > 0) {
			for (Industry indu : list) {
				findIndusChildren(indu);
			}
		}
		return list;
	}

	/**
	 * 
	 * @Title findIndusChildren
	 * @Description TODO(根据行业获取子行业) 
	 * @param indu
	 * @return void  返回类型 
	 * @throws
	 */
	private void findIndusChildren(Industry indu) {
		List<Industry> children = industryRepository.findByParentIdOrderByIndusSortAsc(indu.getId());
		if(children != null && children.size() > 0) {
			indu.setChildren(children);
			for (Industry child : children) {
				findIndusChildren(child);
			}
		}
		
	}

	@Override
	public Response getIndustry(String id) {
		Response res = new Response();
		if(StringUtils.isBlank(id)) {
			res.setMsg("数据加载失败");
		}else {
			res.setSuccess(true);
			res.setResult(industryRepository.getById(id));
		}
		return res;
	}

	@Override
	public Response deleteIndustry(String id) {
		Response res = new Response();
		if(StringUtils.isBlank(id)) {
			res.setMsg("删除失败");
		}else {
			List<Industry> children = industryRepository.findByParentIdOrderByIndusSortAsc(id);
			if(children != null && children.size() > 0) {//有子行业
				res.setMsg("删除失败，请先删除子行业");
			} else {
				industryRepository.delete(new Industry(id));
				res.setSuccess(true);
				res.setMsg("删除成功");
			}
			
		}
		return res;
	}

	@Override
	public Response findParent() {
		Response res = new Response();
		List<Selected> indus = new ArrayList<Selected>();
		indus.add(new Selected("-1", "|—根行业", false));
		
		List<Industry> list = industryRepository.findByParentIdIsNullOrderByIndusSortAsc();
		if(list != null && list.size() > 0) {
			for (Industry in : list) {
				indus.add(new Selected(in.getId(), "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|—" + in.getIndusName(), false));
				List<Industry> children = industryRepository.findByParentIdOrderByIndusSortAsc(in.getId());
				if(children != null && children.size() > 0) {
					for (Industry child : children) {
						indus.add(new Selected(child.getId(), "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|—" + child.getIndusName(), false, true));
					}
				}
			}
		}
		res.setSuccess(true);
		res.setResult(indus);
		return res;
	}

	@Transactional
	@Override
	public Response saveIndustry(Industry industry) {
		Response res = new Response();
		industry.setParentId("-1".equals(industry.getParentId()) ? null : industry.getParentId());
		if(StringUtils.isNotBlank(industry.getId())) {//编辑
			industryRepository.updateIndustry(industry);
			res.setSuccess(true);
			res.setMsg("编辑成功");
		} else {//新增
			industryRepository.save(industry);
			res.setSuccess(true);
			res.setMsg("新增成功");
		}
		return res;
	}

}
