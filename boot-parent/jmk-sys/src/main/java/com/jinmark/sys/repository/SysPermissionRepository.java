package com.jinmark.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinmark.sys.domain.SysPermission;

public interface SysPermissionRepository extends JpaRepository<SysPermission, String> {
	/**
	 * 通过available相等查询
	 * @param available
	 * @return List
	 */
	List<SysPermission> findByAvailable(boolean available);
}
