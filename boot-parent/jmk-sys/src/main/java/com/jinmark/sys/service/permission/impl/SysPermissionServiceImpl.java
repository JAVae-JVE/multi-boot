package com.jinmark.sys.service.permission.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.jinmark.sys.domain.SysPermission;
import com.jinmark.sys.repository.SysPermissionRepository;
import com.jinmark.sys.service.permission.SysPermissionServiceI;

@Service
@Transactional(readOnly = true)
public class SysPermissionServiceImpl implements SysPermissionServiceI {

	@Autowired
	private SysPermissionRepository permissionRepository;
	
	@Override
	public Set<String> findPermissions(Set<String> menuIds) {
		 Set<String> permissions = new HashSet<String>();
		 List<SysPermission> pr = permissionRepository.findAll(menuIds);
		 if(null != pr && pr.size() > 0) {
			 for (SysPermission sysPermission : pr) {
				 if(sysPermission != null && !StringUtils.isEmpty(sysPermission.getPermission())) {
		               permissions.add(sysPermission.getPermission());
		         }
			}
		 }
	     return permissions;
	}

	@Override
	public List<SysPermission> findSysPermissions() {
		List<SysPermission> list = permissionRepository.findByAvailableAndParentIdIsNullOrderByPriorityAsc(true);
		if(list != null && list.size() > 0) {
			for (SysPermission sysPermission : list) {
				findPermChildren(sysPermission);
			}
		}
		return list;
	}

	private void findPermChildren(SysPermission sp) {
		List<SysPermission> children = permissionRepository.findByAvailableAndParentIdOrderByPriorityAsc(true, sp.getId());
		if(children != null && children.size() > 0) {
			sp.setChildren(children);
			for (SysPermission sysPermission : children) {
				findPermChildren(sysPermission);
			}
		}
	}

}
