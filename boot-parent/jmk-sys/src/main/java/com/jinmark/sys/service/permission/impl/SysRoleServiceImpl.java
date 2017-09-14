package com.jinmark.sys.service.permission.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinmark.core.Constants.ResType;
import com.jinmark.sys.domain.SysPermission;
import com.jinmark.sys.domain.SysRole;
import com.jinmark.sys.domain.SysRolePermission;
import com.jinmark.sys.repository.SysRolePermissionRepository;
import com.jinmark.sys.repository.SysRoleRepository;
import com.jinmark.sys.service.permission.SysPermissionServiceI;
import com.jinmark.sys.service.permission.SysRoleServiceI;

@Service
@Transactional(readOnly = true)
public class SysRoleServiceImpl implements SysRoleServiceI {

	@Autowired
	private SysRoleRepository roleRepository;
	/*@Autowired
	private SysUserToRoleDaoI userToRoleDao;//用户角色Dao*/
	@Autowired
	private SysRolePermissionRepository rolePermissionRepository;//角色菜单Dao
	@Autowired
	private SysPermissionServiceI permissionService;//菜单Service
	
	@Override
	public Set<String> findRoles(String... roleIds) {
		Set<String> roles = new HashSet<String>();
		List<SysRole> r = roleRepository.findAll(Arrays.asList(roleIds));
        if(null != r && r.size() > 0) {
        	for (SysRole sysRole : r) {
        		roles.add(sysRole.getRoleFlag());
			}
        }
        return roles;
	}

	@Override
	public Set<String> findPerssions(String[] roleIds) {
		Set<String> menuIds = new HashSet<String>();
		List<SysRole> r = roleRepository.findAll(Arrays.asList(roleIds));
		if(null != r && r.size() > 0) {
			for (SysRole sysRole : r) {
				Set<SysRolePermission> rp = sysRole.getSysRolePermissions();
				for (SysRolePermission sysRolePermission : rp) {
					menuIds.add(sysRolePermission.getSysPermission().getId());
				}
			}
		}
        return permissionService.findPermissions(menuIds);
	}

	@Override
	public List<SysPermission> findMenus(String[] roleIds) {
		List<SysPermission> rootMenus = new ArrayList<SysPermission>();
		List<SysPermission> menus = new ArrayList<SysPermission>();
		List<SysRole> r = roleRepository.findAll(Arrays.asList(roleIds));
		if(null != r && r.size() > 0) {
			for (SysRole sysRole : r) {
				Set<SysRolePermission> rp = sysRole.getSysRolePermissions();
				for (SysRolePermission sysRolePermission : rp) {
					if(ResType.MENU.getInfo().equals(sysRolePermission.getSysPermission().getResourceType())) {
						menus.add(sysRolePermission.getSysPermission());
						if(StringUtils.isBlank(sysRolePermission.getSysPermission().getParentId())) {
							rootMenus.add(sysRolePermission.getSysPermission());
						}
					}
				}
			}
		}
		
		for (SysPermission rm : rootMenus) {
			List<SysPermission> children = new ArrayList<SysPermission>();
			for (SysPermission m : menus) {
				if(rm.getId().equals(m.getParentId())) {
					children.add(m);
				}
			}
			
			rm.setChildren(children);
		}
		return rootMenus;
	}

	/*@Override
	public SysRole findOne(String roleId) {
		return roleRepository.findOne(roleId);
	}

	@Override
	public List<SysRole> findAllRoles() {
		return roleRepository.findByAvailable(true);
	}*/

	/*@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public SysRoleOut queryRoles(SysRoleIn in, Pages pages) {
		SysRoleOut out = new SysRoleOut();
		try {
			//PageBean pageBean = new PageBean(pages.getPageCurrent(), pages.getPageSize());
			if(in != null && in.getRole() != null && StringUtils.isNotEmpty(in.getRole().getRoleName())) {
				Like[] likes = {new Like("roleName", in.getRole().getRoleName())};
				in.getRole().setRoleName(null);
				
				out.setRoles(roleDao.find(in.getRole(), null, null, likes, null));
				Long count = roleDao.count(new SysRole(), likes);
				pages.setTotal(count == null ? 0 : count.intValue());
			}else {
				out.setRoles(roleDao.find(new SysRole()));
				Long count = roleDao.count(new SysRole());
				pages.setTotal(count == null ? 0 : count.intValue());
			}
			out.setCurrentRoles(userToRoleDao.findRolesByUser(new SysUserToRole(in.getUser())));
			out.setPages(pages);
			out.setResult(new Result("200", MessageUtils.getValue("MSG0000")));
		} catch (Exception e) {
			e.printStackTrace();
			out.setResult(new Result("300", MessageUtils.getValue("MSG0001")));
		}
		return out;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public SysRoleOut addRolePage() {
		SysRoleOut out = new SysRoleOut();
		try {
			out.setMenus(menuService.findAll());
			out.setResult(new Result("200", MessageUtils.getValue("MSG0000")));
		} catch (Exception e) {
			e.printStackTrace();
			out.setResult(new Result("300", MessageUtils.getValue("MSG0001")));
		}
		return out;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public SysRoleOut saveRole(SysRoleIn in) {
		SysRoleOut out = new SysRoleOut();
		try {
			if(in != null && in.getRole() != null) {
				if(StringUtils.isEmpty(in.getRole().getRoleName())) {
					out.setResult(new Result("300", "角色名称必填"));
					return out;
				}
				
				if(StringUtils.isEmpty(in.getRole().getRoleFlag())) {
					out.setResult(new Result("300", "角色标识符必填"));
					return out;
				}
				
				SysRole role1 = new SysRole();
				role1.setRoleName(in.getRole().getRoleName());
				SysRole role2 = new SysRole();
				role2.setRoleFlag(in.getRole().getRoleFlag());
				
				
				SysRole r1 = roleDao.get(role1);
				SysRole r2 = roleDao.get(role2);
				if(r1 != null){
					out.setResult(new Result("300", "角色名称已存在"));
					return out;
				}
				if(r2 != null){
					out.setResult(new Result("300", "角色标识符已存在"));
					return out;
				}
				
				in.getRole().setAvailable(1);
				in.getRole().setCreatetime(new Date());
				roleDao.save(in.getRole());
				if(StringUtils.isNotEmpty(in.getMenuIds())) {
					String[] menuArray = in.getMenuIds().split(",");
					for (String menuId : menuArray) {
						roleToMenuDao.save(new SysRoleToMenu(new SysRole(in.getRole().getId()), new SysMenu(menuId)));
					}
				}
				out.setResult(new Result("200", MessageUtils.getValue("MSG0000")));
			}else {
				out.setResult(new Result("300", MessageUtils.getValue("MSG0001")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.setResult(new Result("300", MessageUtils.getValue("MSG0001")));
		}
		return out;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public SysRoleOut editRolePage(SysRoleIn in) {
		SysRoleOut out = new SysRoleOut();
		try {
			if(in != null && in.getRole() != null && StringUtils.isNotEmpty(in.getRole().getId())) {
				out.setRole(roleDao.get(new SysRole(in.getRole().getId())));
				List<SysMenu> list = menuService.findAll();
				
				//以有菜单
				List<SysMenu> menus = roleToMenuDao.findMenusByRole(new SysRoleToMenu(new SysRole(in.getRole().getId())));
				if(menus != null && menus.size() > 0) {
					for (SysMenu menu : list) {
						if(menus.contains(menu)){
							menu.setSelected(true);
						}
						if(menu.getChildren() != null && menu.getChildren().size() > 0) {
							for (SysMenu child : menu.getChildren()) {
								if(menus.contains(child)) {
									child.setSelected(true);
								}
							}
						}
					}
				}
				out.setMenus(list);
				out.setResult(new Result("200", MessageUtils.getValue("MSG0000")));
			}else {
				out.setResult(new Result("300", MessageUtils.getValue("MSG0001")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			out.setResult(new Result("300", MessageUtils.getValue("MSG0001")));
		}
		return out;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public SysRoleOut editRole(SysRoleIn in) {
		SysRoleOut out = new SysRoleOut();
		try {
			if(in != null && in.getRole() != null && StringUtils.isNotEmpty(in.getRole().getId())) {
				//roleDao.updateObject(in.getRole());
				//授权
				if(StringUtils.isNotEmpty(in.getMenuIds())) {
					//1.删除已有“角色-菜单”
					roleToMenuDao.deleteRoleToMenuByRole(new SysRoleToMenu(in.getRole()));
					//2.保存现有“角色-菜单”
					String[] idArray = in.getMenuIds().split(",");
					for (String menuId : idArray) {
						roleToMenuDao.save(new SysRoleToMenu(new SysRole(in.getRole().getId()), new SysMenu(menuId)));
					}
				}
				out.setResult(new Result("200", MessageUtils.getValue("MSG0000")));
			}else {
				out.setResult(new Result("300", MessageUtils.getValue("MSG0001")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.setResult(new Result("300", MessageUtils.getValue("MSG0001")));
		}
		return out;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public SysRoleOut deleteRoles(SysRoleIn in) {
		SysRoleOut out = new SysRoleOut();
		
		try {
			
			if(in != null && StringUtils.isNotEmpty(in.getRoleIds())) {
				String[] idArray = in.getRoleIds().split(",");
				for (String id : idArray) {
					//1.删除用户-角色
					userToRoleDao.deleteUserToRoleByRole(new SysUserToRole(new SysRole(id)));
					//2.删除角色-菜单
					roleToMenuDao.deleteRoleToMenuByRole(new SysRoleToMenu(new SysRole(id)));
					//3.删除角色
					roleDao.delete(new SysRole(id));
				}
				out.setResult(new Result("200", MessageUtils.getValue("MSG0000")));
			}else {
				out.setResult(new Result("300", MessageUtils.getValue("MSG0001")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.setResult(new Result("300", MessageUtils.getValue("MSG0001")));
		}
		return out;
	}*/

}
