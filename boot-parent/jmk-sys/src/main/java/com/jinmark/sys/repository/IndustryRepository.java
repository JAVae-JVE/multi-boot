package com.jinmark.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jinmark.sys.domain.Industry;

public interface IndustryRepository extends JpaRepository<Industry, String>{
	/**
	 * 
	 * @Title findByParentIdIsNullOrderByIndusSortAsc
	 * @Description TODO(获取根行业) 
	 * @return
	 * @return List<Industry>  返回类型 
	 * @throws
	 */
	List<Industry> findByParentIdIsNullOrderByIndusSortAsc();
	/**
	 * 
	 * @Title findByParentIdOrderByIndusSortAsc
	 * @Description TODO(根据父行业id获取子孙行业) 
	 * @param parentId
	 * @return
	 * @return List<Industry>  返回类型 
	 * @throws
	 */
	List<Industry> findByParentIdOrderByIndusSortAsc(String parentId);
	/**
	 * 
	 * @Title getById
	 * @Description TODO(根据id获取行业对象) 
	 * @param id
	 * @return
	 * @return Industry  返回类型 
	 * @throws
	 */
	Industry getById(String id);
	
	@Modifying
	@Query("update Industry o set o.indusName = :#{#industry.indusName}, o.indusSort = :#{#industry.indusSort}, o.gmtModified = :#{#industry.gmtModified}, o.parentId = :#{#industry.parentId} where o.id = :#{#industry.id}")
	void updateIndustry(@Param("industry") Industry industry);
}
