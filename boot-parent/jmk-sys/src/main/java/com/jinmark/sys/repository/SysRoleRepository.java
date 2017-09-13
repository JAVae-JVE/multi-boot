package com.jinmark.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinmark.sys.domain.SysRole;

public interface SysRoleRepository extends JpaRepository<SysRole, String> {
	/**
	 * 根据available获取角色
	 * @param available
	 * @return
	 * @return List<SysRole>
	 */
	List<SysRole> findByAvailable(boolean available);
}
