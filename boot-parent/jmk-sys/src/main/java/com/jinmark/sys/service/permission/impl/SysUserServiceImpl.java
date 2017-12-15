package com.jinmark.sys.service.permission.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import com.jinmark.core.bean.Pages;
import com.jinmark.sys.domain.SysUser;
import com.jinmark.sys.domain.SysUserRole;
import com.jinmark.sys.repository.SysUserRepository;
import com.jinmark.sys.service.permission.SysRoleServiceI;
import com.jinmark.sys.service.permission.SysUserServiceI;
import com.jinmark.sys.vo.permission.QueryUserRequest;

@Service
public class SysUserServiceImpl implements SysUserServiceI {
	
	@Autowired
	private SysUserRepository userRepository;
	@Autowired
	private SysRoleServiceI roleService;


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

	@Override
	public SysUser getUserById(String userId) {
		return userRepository.findOne(userId);
	}

	/*@Override
	public List<SysPermission> findMenus(String username) {
		SysUser user =findByUsername(username);
        if(user == null) {
            return Collections.emptyList();
        }
        
        Set<String> roleIds = findRoleIdsByUser(user);
		return roleService.findMenus(roleIds.toArray(new String[0]));
	}*/

	/*@Transactional(readOnly = false)
	@Override
	public Response saveUser(String username, String password) {
		Response out = null;*/
		/*try {*/
			/*if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
				out = new Response(false, "账号或");
			}else{*/
				
				/*SysUser u = sysUserDao.get(new SysUser(in.getSysUser().getUsername()));
				if(u != null) {
					out.setResult(new Result("300", "账号已存在"));
					return out;
				}
				if(!isUniquePhone(in.getSysUser().getPhoneNum())) {
					out.setResult(new Result("300", "手机号码已存在"));
					return out;
				}
				//加密
				PasswordHelper.encryptPassword(in.getSysUser());
				in.getSysUser().setLocked("false");
				in.getSysUser().setCreatetime(new Date());
				sysUserDao.save(in.getSysUser());
				
				if(StringUtils.isNotEmpty(in.getRoleIds())) {
					String[] ids = in.getRoleIds().split(",");
					for (String id : ids) {
						userToRoleDao.save(new SysUserToRole(in.getSysUser(), new SysRole(id)));
					}
				}
				out.setResult(new Result("200", MessageUtils.getValue("MSG0000")));
			}
			*/
		/*} catch (Exception e) {
			e.printStackTrace();
			out.setResult(new Result("300", MessageUtils.getValue("MSG0001")));
		}*/
		/*	SysUser user = new SysUser();
			user.setCreatetime(new Date());
			user.setLocked(false);
			user.setMobile("13699454591");
			user.setName("秦川");
			user.setUsername("admin");
			user.setPassword("mlxlx+qhjnw");
			PasswordHelper.encryptPassword(user);
			userRepository.save(user);
		return null;
	}*/

	/*private boolean isUniquePhone(String phoneNum) {
		SysUser user = new SysUser();
		user.setPhoneNum(phoneNum);
		SysUser u = sysUserDao.get(user);
		if(u == null) {
			return true;
		}
		return false;
	}*/

	/*@Override
	public SysUserOut queryUsers(SysUserIn in, Pages pages) {
		SysUserOut out = new SysUserOut();
		try {
			PageBean pageBean = new PageBean(pages.getPageCurrent(), pages.getPageSize());
			if(in != null && in.getSysUser() != null && StringUtils.isNotEmpty(in.getSysUser().getRealName())) {
				Like[] likes = {new Like("realName", in.getSysUser().getRealName())};
				in.getSysUser().setRealName(null);
				out.setSysUsers(sysUserDao.find(in.getSysUser(), pageBean, null, likes, null));
				Long count = sysUserDao.count(new SysUser(), likes);
				pages.setTotal(count == null ? 0 : count.intValue());
				int totalPage = (pages.getTotal() + (pages.getPageSize() - 1)) / pages.getPageSize();
				pages.setTotalPage(totalPage);
			}else {
				out.setSysUsers(sysUserDao.find(new SysUser(), pageBean));
				Long count = sysUserDao.count(new SysUser());
				pages.setTotal(count == null ? 0 : count.intValue());
				int totalPage = (pages.getTotal() + (pages.getPageSize() - 1)) / pages.getPageSize();
				pages.setTotalPage(totalPage);
			}
			if(out.getSysUsers() != null && out.getSysUsers().size() > 0) {
				for (SysUser u : out.getSysUsers()) {
					List<SysRole> list = userToRoleDao.findRolesByUser(new SysUserToRole(u));
					if(list != null && list.size() > 0) {
						for (SysRole sysRole : list) {
							if(Constants.ADMIN.equals(sysRole.getRoleFlag())) {
								u.setIsAdmin(true);
								break;
							}
						}
					}
					u.setRoles(list);
				}
			}
			out.setPages(pages);
			out.setResult(new Result("200", MessageUtils.getValue("MSG0000")));
		} catch (Exception e) {
			e.printStackTrace();
			out.setResult(new Result("300", MessageUtils.getValue("MSG0001")));
		}
		return out;
	}

	@Override
	public SysUserOut getUser(SysUserIn in) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysUserOut editUserPage(SysUserIn in) {
		SysUserOut out = new SysUserOut();
		try {
			if(in != null && in.getSysUser() != null && StringUtils.isNotEmpty(in.getSysUser().getId())) {
				out.setUser(sysUserDao.get(in.getSysUser()));
				List<SysRole> list = roleService.findAllRoles();
				
				//以有角色
				List<SysRole> roles = userToRoleDao.findRolesByUser(new SysUserToRole(in.getSysUser()));
				if(roles != null && roles.size() > 0) {
					for (SysRole sysRole : list) {
						roles.contains(sysRole);
						sysRole.setSelected(true);
					}
				}
				out.setCurrentRoles(roles);
				out.setRoles(list);
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
	
	@Transactional(readOnly = false)
	@Override
	public SysUserOut editUser(SysUserIn in) {
		SysUserOut out = new SysUserOut();
		try {
			if(in != null && in.getSysUser() != null && StringUtils.isNotEmpty(in.getSysUser().getId())) {
				if(StringUtils.isEmpty(in.getSysUser().getRealName())) {
					out.setResult(new Result("300", "员工姓名必须"));
					return out;
				}
				if(StringUtils.isEmpty(in.getSysUser().getPhoneNum())) {
					out.setResult(new Result("300", "手机号码必须"));
					return out;
				}
				sysUserDao.updateUserInfo(in.getSysUser());
				//授权
				if(StringUtils.isNotEmpty(in.getRoleIds())) {
					addRolesForUser(in.getSysUser(), in.getRoleIds());
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
	
	*//**
	 * 为用户添加角色
	 * @Description: TODO
	 * @param user
	 * @param roleIds
	 * @return void  
	 * @throws
	 * @author qinchuan
	 * @date 2016-9-8 下午3:22:11
	 *//*
	private void addRolesForUser(SysUser user, String roleIds) {
		deleteRolesForUser(user);
		String[] ids = roleIds.split(",");
		for (String id : ids) {
			userToRoleDao.save(new SysUserToRole(user, new SysRole(id)));
		}
	}

	@Transactional(readOnly = false)
	@Override
	public SysUserOut deleteUsers(SysUserIn in) {
		SysUserOut out = new SysUserOut();
		
		try {
			
			if(in != null && StringUtils.isNotEmpty(in.getUserIds())) {
				String[] idArray = in.getUserIds().split(",");
				for (String id : idArray) {
					SysUser user = new SysUser();
					user.setId(id);
					deleteRolesForUser(user);
					sysUserDao.delete(user);
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
	
	
	*//**
	 * 删除用户所有角色
	 * @Description: TODO
	 * @param user
	 * @return void  
	 * @throws
	 * @author qinchuan
	 * @date 2016-9-8 下午2:46:44
	 *//*
	private void deleteRolesForUser(SysUser user) {
		userToRoleDao.deleteRolesForUser(new SysUserToRole(user));
	}

	@Override
	public SysUserOut addUserPage() {
		SysUserOut out = new SysUserOut();
		try {
			out.setRoles(roleService.findAllRoles());
			out.setResult(new Result("200", MessageUtils.getValue("MSG0000")));
		} catch (Exception e) {
			e.printStackTrace();
			out.setResult(new Result("300", MessageUtils.getValue("MSG0001")));
		}
		return out;
	}

	@Transactional(readOnly = false)
	@Override
	public SysUserOut changePassword(SysUserIn in) {
		SysUserOut out = new SysUserOut();
		try {
			if(in != null && in.getSysUser() != null && StringUtils.isNotEmpty(in.getSysUser().getId())) {
				if(StringUtils.isEmpty(in.getSysUser().getPassword())) {
					out.setResult(new Result("300", "旧密码必填"));
					return out;
				}
				if(StringUtils.isEmpty(in.getSysUser().getNewPassword())) {
					out.setResult(new Result("300", "新密码必填"));
					return out;
				}
				
				if(StringUtils.isEmpty(in.getSysUser().getSureNewPassword())) {
					out.setResult(new Result("300", "确认新密码必填"));
					return out;
				}
				
				if(!in.getSysUser().getNewPassword().equals(in.getSysUser().getSureNewPassword())) {
					out.setResult(new Result("300", "新密码和确认新密码不一致"));
					return out;
				}
				
				SysUser user = new SysUser();
				user.setId(in.getSysUser().getId());
				SysUser u = sysUserDao.get(user);
				
				if(!isOldPassword(in.getSysUser().getPassword(), u)) {
					out.setResult(new Result("300", "旧密码错误"));
					return out;
				}
				
				SysUser newUser = new SysUser(u.getUsername());
				newUser.setId(u.getId());
				newUser.setPassword(in.getSysUser().getNewPassword());
				PasswordHelper.encryptPassword(newUser);
				sysUserDao.changePassword(newUser);
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

	private boolean isOldPassword(String password, SysUser user) {
		SysUser u = new SysUser();
		u.setPassword(password);
		u.setUsername(user.getUsername());
		u.setSalt(user.getSalt());
		//加密后的旧密码
		String oldPassword = PasswordHelper.encryptPasswordValidation(u);
		if(oldPassword.equals(user.getPassword())) {
			return true;
		}
		return false;
	}*/
}
