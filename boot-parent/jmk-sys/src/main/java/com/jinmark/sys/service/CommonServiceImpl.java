package com.jinmark.sys.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinmark.core.bean.Response;
import com.jinmark.core.utils.RegexUtil;
import com.jinmark.sys.domain.SysRole;
import com.jinmark.sys.domain.SysUser;
import com.jinmark.sys.repository.SysRoleRepository;
import com.jinmark.sys.repository.SysUserRepository;

/**
 * 
 * @author QC
 * @ClassName CommonServiceImpl
 * @Description TODO(公共的service实现) 
 * @date 2017年10月31日上午11:58:53
 */
@Service
public class CommonServiceImpl implements CommonServiceI {

	@Autowired
	private SysUserRepository userRepository;
	@Autowired
	private SysRoleRepository roleRepository;
	
	@Override
	public Response validateMobile(String userId, String mobile) {
		Response res = new Response();
		if(StringUtils.isBlank(mobile)) {
			res.setSuccess(true);
			return res;
		} else {//验证手机号格式
			if(!RegexUtil.isMobile(mobile)) {
				res.setSuccess(false);
				res.setMsg("输入正确格式的11位手机号");
				return res;
			}
		}
		SysUser user = userRepository.findByMobile(mobile);
		if(StringUtils.isBlank(userId)) {//新增
			if(user == null) {
				res.setSuccess(true);//验证通过
			}else {
				res.setSuccess(false);
				res.setMsg("手机号已存在");
			}
		} else {//修改
			if(user == null) {
				res.setSuccess(true);//验证通过
			}else {
				if(userId.equals(user.getId())) {
					res.setSuccess(true);
				}else {
					res.setSuccess(false);
					res.setMsg("手机号已存在");
				}
			}
		}
		return res;
	}

	@Override
	public Response validateUsername(String userId, String username) {
		Response res = new Response();
		if(StringUtils.isBlank(username) || username.length() < 4 || username.length() > 20) {
			res.setMsg("输入账号（4-20个字符）");
			return res;
		}
		
		SysUser user = userRepository.findByUsername(username);
		if(StringUtils.isBlank(userId)) {//新增
			if(user == null) {
				res.setSuccess(true);//验证通过
			}else {
				res.setSuccess(false);
				res.setMsg("账号已存在");
			}
		} else {//修改
			if(user == null) {
				res.setSuccess(true);//验证通过
			}else {
				if(userId.equals(user.getId())) {
					res.setSuccess(true);
				}else {
					res.setSuccess(false);
					res.setMsg("账号已存在");
				}
			}
		}
		return res;
	}

	@Override
	public Response validateRoleName(String roleId, String roleName) {
		Response res = new Response();
		if(StringUtils.isBlank(roleName) || roleName.length() < 2 || roleName.length() > 20) {
			res.setMsg("输入角色名称（2-20个字符）");
			return res;
		}
		
		SysRole role = roleRepository.findByRoleName(roleName);
		if(StringUtils.isBlank(roleId)) {//新增
			if(role == null) {
				res.setSuccess(true);//验证通过
			}else {
				res.setSuccess(false);
				res.setMsg("角色名称已存在");
			}
		} else {//修改
			if(role == null) {
				res.setSuccess(true);//验证通过
			}else {
				if(roleId.equals(role.getId())) {
					res.setSuccess(true);
				}else {
					res.setSuccess(false);
					res.setMsg("角色名称已存在");
				}
			}
		}
		return res;
	}

	@Override
	public Response validateRoleFlag(String roleId, String roleFlag) {
		Response res = new Response();
		if(StringUtils.isBlank(roleFlag) || roleFlag.length() < 2 || roleFlag.length() > 20) {
			res.setMsg("输入角色标识（2-20个字符）");
			return res;
		}
		
		SysRole role = roleRepository.findByRoleFlag(roleFlag);
		if(StringUtils.isBlank(roleId)) {//新增
			if(role == null) {
				res.setSuccess(true);//验证通过
			}else {
				res.setSuccess(false);
				res.setMsg("角色标识已存在");
			}
		} else {//修改
			if(role == null) {
				res.setSuccess(true);//验证通过
			}else {
				if(roleId.equals(role.getId())) {
					res.setSuccess(true);
				}else {
					res.setSuccess(false);
					res.setMsg("角色标识已存在");
				}
			}
		}
		return res;
	}

}
