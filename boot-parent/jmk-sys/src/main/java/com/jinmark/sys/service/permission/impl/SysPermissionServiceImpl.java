package com.jinmark.sys.service.permission.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jinmark.core.Constants.ResType;
import com.jinmark.core.bean.Response;
import com.jinmark.core.bean.Selected;
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

	@Override
	public Response findParent(String menuType) {
		Response res = new Response();
		if(ResType.MENU.getValue().equals(menuType)) {//菜单
			List<Selected> menus = new ArrayList<Selected>();
			menus.add(new Selected("-1", "|—根菜单", false));
			
			List<SysPermission> list = permissionRepository.findByAvailableAndParentIdIsNullOrderByPriorityAsc(true);
			if(list != null && list.size() > 0) {
				for (SysPermission sp : list) {
					menus.add(new Selected(sp.getId(), "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|—" + sp.getName(), false));
					List<SysPermission> children = permissionRepository.findByAvailableAndParentIdOrderByPriorityAsc(true, sp.getId());
					if(children != null && children.size() > 0) {
						for (SysPermission child : children) {
							menus.add(new Selected(child.getId(), "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|—" + child.getName(), false, true));
						}
					}
				}
			}
			res.setSuccess(true);
			res.setResult(menus);
		}else if(ResType.BUTTON.getValue().equals(menuType)) {
			List<Selected> menus = new ArrayList<Selected>();
			List<SysPermission> list = permissionRepository.findSecondLevelMenu(true, ResType.BUTTON.getValue());
			if(list != null && list.size() > 0) {
				for (SysPermission sp : list) {
					menus.add(new Selected(sp.getId(), "|—" + sp.getName(), false));
					List<SysPermission> children = permissionRepository.findByAvailableAndParentIdOrderByPriorityAsc(true, sp.getId());
					if(children != null && children.size() > 0) {
						for (SysPermission child : children) {
							menus.add(new Selected(child.getId(), "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|—" + child.getName(), false, true));
						}
					}
				}
			}
			res.setSuccess(true);
			res.setResult(menus);
		}else {
			res.setMsg("无效数据");
		}
		return res;
	}

}
