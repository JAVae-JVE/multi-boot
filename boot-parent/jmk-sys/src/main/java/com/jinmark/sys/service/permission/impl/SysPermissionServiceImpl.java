package com.jinmark.sys.service.permission.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinmark.core.Constants.ResType;
import com.jinmark.core.bean.Response;
import com.jinmark.core.bean.Selected;
import com.jinmark.sys.domain.SysPermission;
import com.jinmark.sys.repository.SysPermissionRepository;
import com.jinmark.sys.repository.SysRolePermissionRepository;
import com.jinmark.sys.service.permission.SysPermissionServiceI;

@Service
public class SysPermissionServiceImpl implements SysPermissionServiceI {

	@Autowired
	private SysPermissionRepository permissionRepository;
	@Autowired
	private SysRolePermissionRepository rolePermissionRepository;
	
	@Override
	public Set<String> findPermissions(Set<String> menuIds) {
		 Set<String> permissions = new HashSet<String>();
		 List<SysPermission> pr = permissionRepository.findAll(menuIds);
		 if(null != pr && pr.size() > 0) {
			 for (SysPermission sysPermission : pr) {
				 if(sysPermission != null && StringUtils.isNotBlank(sysPermission.getPermission())) {
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
	/**
	 * 
	 * @Title findPermChildren
	 * @Description TODO(递归获取子孙菜单) 
	 * @param sp
	 * @return void  返回类型 
	 * @throws
	 */
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
			List<SysPermission> list = permissionRepository.findByAvailableAndParentIdIsNullOrderByPriorityAsc(true);
			if(list != null && list.size() > 0) {
				for (SysPermission sp : list) {
					menus.add(new Selected(sp.getId(), "|—" + sp.getName(), false, true));
					List<SysPermission> children = permissionRepository.findByAvailableAndParentIdOrderByPriorityAsc(true, sp.getId());
					if(children != null && children.size() > 0) {
						for (SysPermission child : children) {
							menus.add(new Selected(child.getId(), "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|—" + child.getName(), false));
							List<SysPermission> children2 = permissionRepository.findByAvailableAndParentIdOrderByPriorityAsc(true, child.getId());
							for (SysPermission child2 : children2) {
								menus.add(new Selected(child2.getId(), "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|—" + child2.getName(), false, true));
							}
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

	@Transactional
	@Override
	public Response saveSysPermission(SysPermission sysPermission) {
		Response res = new Response();
		sysPermission.setParentId("-1".equals(sysPermission.getParentId()) ? null : sysPermission.getParentId());
		if(StringUtils.isNotBlank(sysPermission.getId())) {//编辑
			permissionRepository.updateSysPermission(sysPermission);
			res.setSuccess(true);
			res.setMsg("编辑成功");
		} else {//新增
			sysPermission.setAvailable(true);
			sysPermission.setCreatetime(new Date());
			permissionRepository.save(sysPermission);
			res.setSuccess(true);
			res.setMsg("新增成功");
		}
		return res;
	}

	@Override
	public List<SysPermission> findUserMenus(String userId) {
		List<SysPermission> root = permissionRepository.findUserParentMenus(userId);
		if(root != null && root.size() > 0) {
			for (SysPermission sysPermission : root) {
				findUserChildrenMenus(sysPermission, userId);
			}
		}
		return root;
	}
	
	/**
	 * 
	 * @Title findUserChildrenMenus
	 * @Description TODO(递归获取用户子孙菜单) 
	 * @param sp
	 * @param userId
	 * @return void  返回类型 
	 * @throws
	 */
	private void findUserChildrenMenus(SysPermission sp, String userId) {
		List<SysPermission> children = permissionRepository.findUserChildrenMenus(userId, sp.getId());
		if(children != null && children.size() > 0) {
			sp.setChildren(children);
			for (SysPermission sysPermission : children) {
				findUserChildrenMenus(sysPermission, userId);
			}
		}
	}

	@Override
	public Response getSysPermission(String id) {
		Response res = new Response();
		if(StringUtils.isBlank(id)) {
			res.setMsg("数据加载失败");
		}else {
			res.setSuccess(true);
			res.setResult(permissionRepository.getById(id));
		}
		return res;
	}

	@Transactional
	@Override
	public Response deleteSysPermission(String id) {
		Response res = new Response();
		if(StringUtils.isBlank(id)) {
			res.setMsg("删除失败");
		}else {
			List<SysPermission> children = permissionRepository.findByAvailableAndParentIdOrderByPriorityAsc(true, id);
			if(children != null && children.size() > 0) {//有子菜单
				res.setMsg("删除失败，请先删除子菜单");
			} else {
				//删除角色-菜单记录
				rolePermissionRepository.deleteByPermId(id);
				//删除菜单
				permissionRepository.delete(new SysPermission(id));
				res.setSuccess(true);
				res.setMsg("删除成功");
			}
			
		}
		return res;
	}

}
