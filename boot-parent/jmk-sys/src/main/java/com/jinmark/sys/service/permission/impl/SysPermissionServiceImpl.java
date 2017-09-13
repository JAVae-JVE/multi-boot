package com.jinmark.sys.service.permission.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.shiro.authz.permission.WildcardPermission;
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
	public List<SysPermission> findMenus(Set<String> permissions) {
		List<SysPermission> allMenus = findAllMenus();
        List<SysPermission> menus = new ArrayList<SysPermission>();
        for(SysPermission menu : allMenus) {
            /*if(StringUtils.isEmpty(menu.getParentMenu())) {
                continue;
            }
            if(menu.getType() != Constants.ResourceType.menu.getInfo()) {
                continue;
            }*/
            if(!hasPermission(permissions, menu)) {
                continue;
            }
            menus.add(menu);
        }
        return menus;
	}
	/**
	 * 是否有权限
	 * @Description: TODO
	 * @param permissions
	 * @param menu
	 * @return
	 * @return boolean  
	 * @throws
	 * @author qinchuan
	 * @date 2016-9-7 下午7:14:44
	 */
	private boolean hasPermission(Set<String> permissions, SysPermission menu) {
        if(StringUtils.isEmpty(menu.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(menu.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }


	@Override
	public SysPermission findOne(String menuId) {
		return permissionRepository.findOne(menuId);
	}

	/*@Override
	public List<SysPermission> findAllRoot() {
		SysPermission menu = new SysPermission();
		menu.setAvailable(1);
		menu.setParentMenu("0");
		List<SysMenu> parentMenus = menuDao.find(menu);
		for (SysMenu sysMenu : parentMenus) {
			SysMenu m = new SysMenu();
			m.setParentMenu(sysMenu.getId());
			sysMenu.setChildren(menuDao.find(m));
		}
		return parentMenus;
	}*/

	@Override
	public List<SysPermission> findAllMenus() {
		return permissionRepository.findByAvailable(true);
	}

}
