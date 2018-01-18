package com.jinmark.sys.service.permission.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinmark.core.bean.Response;
import com.jinmark.core.bean.Selected;
import com.jinmark.sys.domain.SysRole;
import com.jinmark.sys.domain.SysRolePermission;
import com.jinmark.sys.repository.SysRolePermissionRepository;
import com.jinmark.sys.repository.SysRoleRepository;
import com.jinmark.sys.repository.SysUserRoleRepository;
import com.jinmark.sys.service.permission.SysPermissionServiceI;
import com.jinmark.sys.service.permission.SysRoleServiceI;

@Service
public class SysRoleServiceImpl implements SysRoleServiceI {

	@Autowired
	private SysRoleRepository roleRepository;
	@Autowired
	private SysPermissionServiceI permissionService;//菜单Service
	@Autowired
	private SysUserRoleRepository userRoleRepository;
	@Autowired
	private SysRolePermissionRepository rolePermissionRepository;
	
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
	public List<Selected> findRoles() {
		List<SysRole> list =  roleRepository.findByAvailable(true);
		List<Selected> roles = new ArrayList<Selected>();
		if(list != null && list.size() > 0) {
			for (SysRole sysRole : list) {
				roles.add(new Selected(sysRole.getId(), sysRole.getRoleName(), false));
			}
		}
		return roles;
	}

	@Override
	public List<SysRole> findRoleList() {
		return roleRepository.findByAvailable(true);
	}

	@Transactional
	@Override
	public Response saveRole(SysRole role) {
		Response res = new Response();
		if(StringUtils.isNotBlank(role.getId())) {//编辑
			roleRepository.updateRole(role);
			res.setSuccess(true);
			res.setMsg("编辑成功");
		} else {//新增
			role.setAvailable(true);
			role.setCreatetime(new Date());
			roleRepository.save(role);
			res.setSuccess(true);
			res.setMsg("新增成功");
		}
		return res;
	}

	@Override
	public Response getRole(String id) {
		Response res = new Response();
		if(StringUtils.isBlank(id)) {
			res.setMsg("数据加载失败");
		}else {
			res.setSuccess(true);
			SysRole re = roleRepository.getById(id);
			SysRole ta = new SysRole();
			ta.setId(re.getId());
			ta.setRoleName(re.getRoleName());
			ta.setRoleFlag(re.getRoleFlag());
			ta.setDescription(re.getDescription());
			res.setResult(ta);
		}
		return res;
	}

	@Transactional
	@Override
	public Response deleteRole(String id) {
		Response res = new Response();
		if(StringUtils.isBlank(id)) {
			res.setMsg("删除失败");
		} else {
			//删除用户-角色
			userRoleRepository.deleteSysUserRoleByRoleId(id);
			//删除角色-权限
			rolePermissionRepository.deleteByRoleId(id);
			//删除角色
			roleRepository.delete(id);
			res.setSuccess(true);
			res.setMsg("删除成功");
		}
		return res;
	}

	
}
