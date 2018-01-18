package com.jinmark.sys.service.permission.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jinmark.core.bean.Pages;
import com.jinmark.core.bean.Response;
import com.jinmark.sys.config.shiro.PasswordHelper;
import com.jinmark.sys.domain.SysRole;
import com.jinmark.sys.domain.SysUser;
import com.jinmark.sys.domain.SysUserRole;
import com.jinmark.sys.repository.SysUserRepository;
import com.jinmark.sys.repository.SysUserRoleRepository;
import com.jinmark.sys.service.CommonServiceI;
import com.jinmark.sys.service.permission.SysRoleServiceI;
import com.jinmark.sys.service.permission.SysUserServiceI;
import com.jinmark.sys.vo.permission.QueryUserRequest;
import com.jinmark.sys.vo.permission.UserRequest;
import com.jinmark.sys.vo.permission.UserResponse;

import strman.Strman;

@Service
public class SysUserServiceImpl implements SysUserServiceI {
	
	@Autowired
	private SysUserRepository userRepository;
	@Autowired
	private SysRoleServiceI roleService;
	@Autowired
	private SysUserRoleRepository userRoleRepository;
	@Autowired
	private CommonServiceI commonService;

	@Override
	public SysUser findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public Set<String> findRoles(String username) {
		SysUser user = findByUsername(username);
		if(user == null) {
            return Collections.emptySet();
        }
		Set<String> roleIds = findRoleIdsByUser(user);
		return roleService.findRoles(roleIds.toArray(new String[0]));
	}

	/**
	 * 通过用户获取角色id集合
	 * @param user
	 * @return Set<Long>
	 */
	private Set<String> findRoleIdsByUser(SysUser user) {
		Set<SysUserRole> ur = user.getSysUserRoles();
		Set<String> roleIds = new HashSet<String>();
		for (SysUserRole sysUserRole : ur) {
			roleIds.add(sysUserRole.getSysRole().getId());
		}
		return roleIds;
	}

	@Override
	public Set<String> findPermissions(String username) {
		SysUser user =findByUsername(username);
        if(user == null) {
            return Collections.emptySet();
        }
        Set<String> roleIds = findRoleIdsByUser(user);
		return roleService.findPerssions(roleIds.toArray(new String[0]));
	}

	@Override
	public List<SysUser> queryUserList(QueryUserRequest queryUserRequest, Pages pages) {
		Pageable pageable = new PageRequest(pages.getPage() - 1, pages.getSize(), new Sort(Direction.DESC, "createtime"));
		if(StringUtils.isBlank(queryUserRequest.getName())) {
			queryUserRequest.setName("%%");
		}
		return userRepository.findUserList(queryUserRequest, pageable);
	}


	@Transactional
	@Override
	public Response saveSysUser(UserRequest userRequest) {
		Response res = new Response();
		Response validateUsernameRes = commonService.validateUsername(userRequest.getId(), userRequest.getUsername());
		Response validateMobileRes = commonService.validateMobile(userRequest.getId(), userRequest.getMobile());
		if(!validateUsernameRes.isSuccess()) {
			return validateUsernameRes;
		}
		if(!validateMobileRes.isSuccess()) {
			return validateMobileRes;
		}
		
		if(StringUtils.isBlank(userRequest.getId())) {//新增
			SysUser user = new SysUser();
			user.setUsername(userRequest.getUsername());
			user.setPassword("12345678");
			user.setMobile(userRequest.getMobile());
			user.setName(userRequest.getName());
			user.setLocked(false);
			user.setCreatetime(new Date());
			PasswordHelper.encryptPassword(user);
			//保存用户
			userRepository.save(user);
			//保存用户角色
			SysUserRole sur = new SysUserRole(new SysUser(user.getId()), new SysRole(userRequest.getRoleId()));
			userRoleRepository.save(sur);
			res.setSuccess(true);
			res.setMsg("新增成功");
		} else {//修改
			SysUser user = new SysUser();
			user.setId(userRequest.getId());
			user.setMobile(userRequest.getMobile());
			user.setName(userRequest.getName());
			//修改用户
			userRepository.updateSysUser(user);
			//修改角色
			userRoleRepository.deleteSysUserRole(userRequest.getId());
			userRoleRepository.save(new SysUserRole(new SysUser(userRequest.getId()), new SysRole(userRequest.getRoleId())));
			res.setSuccess(true);
			res.setMsg("编辑成功");
		}
		
		return res;
	}

	@Override
	public Response getSysUser(String id) {
		Response res = new Response();
		res.setSuccess(true);
		SysUser user = userRepository.findOne(id);
		List<SysUserRole> ur = new ArrayList<>(user.getSysUserRoles());
		UserResponse userResponse = new UserResponse(user.getId(), user.getName(),
				user.getUsername(), user.isLocked(), user.getMobile(), ur.size() == 0 ? "" : ur.get(0).getSysRole().getId());
		res.setResult(userResponse);
		return res;
	}

	@Transactional
	@Override
	public Response deleteSysUser(List<String> ids) {
		Response res = new Response();
		if(null == ids || ids.size() <= 0) {
			res.setMsg("删除失败");
			return res;
		}
		
		//删除用户-角色关联
		String [] array = ids.toArray(new String[]{});
		String userIds = Strman.join(array, ",");
		userRoleRepository.deleteSysUserRole(userIds);
		
		//删除用户
		userRepository.deleteByIdIn(ids);
		res.setSuccess(true);
		res.setMsg("删除成功");
		return res;
	}

	
}
