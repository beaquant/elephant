package com.djt.cbs.system.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;

import com.djt.cbs.system.dao.SysAccessLogDao;
import com.djt.cbs.system.dao.SysActionDao;
import com.djt.cbs.system.dao.SysMenuDao;
import com.djt.cbs.system.dao.SysPermissionDao;
import com.djt.cbs.system.dao.SysRoleDao;
import com.djt.cbs.system.dao.SysSessionDao;
import com.djt.cbs.system.dao.SysUserDao;
import com.djt.cbs.system.entity.PermissionOwner;
import com.djt.cbs.system.entity.PermissionResource;
import com.djt.cbs.system.entity.SysAccessLog;
import com.djt.cbs.system.entity.SysAction;
import com.djt.cbs.system.entity.SysMenu;
import com.djt.cbs.system.entity.SysPermission;
import com.djt.cbs.system.entity.SysRole;
import com.djt.cbs.system.entity.SysSession;
import com.djt.cbs.system.entity.SysUser;
import com.djt.common.ArgumentException;
import com.djt.common.BusinessException;
import com.djt.common.PagerInfo;
import com.djt.common.util.ConvertUtil;
import com.djt.common.util.DateUtil;
import com.djt.common.util.EncryptUtil;
import com.djt.common.util.StringUtil;

public class SystemModel {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(SystemModel.class);

    private SysUserDao                     userDao;
    private SysRoleDao                     roleDao;
    private SysMenuDao                     menuDao;
    private SysActionDao                   actionDao;
    private SysPermissionDao               permissionDao;
    private SysSessionDao                  sessionDao;
    private SysAccessLogDao                accessLogDao;

    public void setUserDao(SysUserDao value) {
        this.userDao = value;
    }

    public void setRoleDao(SysRoleDao value) {
        this.roleDao = value;
    }

    public void setMenuDao(SysMenuDao value) {
        this.menuDao = value;
    }

    public void setActionDao(SysActionDao value) {
        this.actionDao = value;
    }

    public void setPermissionDao(SysPermissionDao value) {
        this.permissionDao = value;
    }

    public void setAccessLogDao(SysAccessLogDao value) {
        this.accessLogDao = value;
    }

    public void setSessionDao(SysSessionDao value) {
        this.sessionDao = value;
    }

    //============================================================================
    //菜单
    //============================================================================
    /**
     * 获取用户有权限的菜单。
     * 用于：登陆后左侧菜单区域显示。
     * @param userId
     * @return
     */
    public List<SysMenu> loadUserMenu(String sessionId) {
        Assert.notNull(userDao, "Property 'userDao' is required.");
        Assert.notNull(menuDao, "Property 'menuDao' is required.");
        Assert.notNull(this.permissionDao, "Property 'permissionDao' is required.");
        Assert.notNull(this.sessionDao, "Property 'sessionDao' is required.");

        SysSession session = this.sessionDao.get(sessionId);
        if (session == null)
            throw new BusinessException("您尚未登陆，请先登陆再执行该操作");

        List<Integer> menuItemIds = new ArrayList<Integer>();
        //用户自己有权限的菜单ID
        List<Integer> idsFromUser = this.permissionDao.findResourceIdsByOwnerId(
            SysPermission.OWNER_USER, session.getUserId(), SysPermission.RESOURCE_MENU);
        if (idsFromUser != null) {
            for (Integer id : idsFromUser)
                menuItemIds.add(id);
        }
        //用户所属的角色有权限的菜单ID
        List<Integer> roleIds = this.userDao.findUserRoleIds(session.getUserId());
        if (roleIds != null && roleIds.size() > 0) {
            List<Integer> idsFromRole = this.permissionDao.findResourceIdsByOwnerIds(
                SysPermission.OWNER_ROLE, roleIds, SysPermission.RESOURCE_MENU);
            if (idsFromRole != null) {
                for (Integer id : idsFromRole)
                    menuItemIds.add(id);
            }
        }

        if (menuItemIds == null || menuItemIds.isEmpty())
            return null;

        List<SysMenu> menuItems = this.menuDao.loadMenus(menuItemIds);
        //干掉禁用状态的，或者无效的功能菜单
        for (int i = 0; menuItems != null && i < menuItems.size();) {
            if (menuItems.get(i).getStatus() != 1 //禁用状态的
                //目前功能菜单只支持2级，权限维护只针对菜单项（第2级），不针对菜单模块（第1级）
                || menuItems.get(i).getParentId() <= 0
                || (!SysMenu.MENU_ITEM_URL.equals(menuItems.get(i).getMenuItemType()) && !SysMenu.MENU_ITEM_APP
                    .equals(menuItems.get(i).getMenuItemType()))) {
                if (log.isInfoEnabled())
                    log.info("[sys][menu][user-" + session.getUserId() + "]菜单"
                             + menuItems.get(i).getMenuName() + "被移除。status="
                             + menuItems.get(i).getStatus() + ", type="
                             + menuItems.get(i).getMenuItemType() + ", parent="
                             + menuItems.get(i).getParentId());
                menuItems.remove(i);
                continue;
            }
            i++;
        }

        //取菜单项的父级模块
        List<Integer> parentIds = new ArrayList<Integer>();
        for (SysMenu mi : menuItems)
            if (!parentIds.contains(mi.getParentId()))
                parentIds.add(mi.getParentId());
        List<SysMenu> parentItems = this.menuDao.loadMenus(parentIds);
        for (int i = 0; parentItems != null && i < parentItems.size();) {
            //父级模块被禁用了
            if (parentItems.get(i).getStatus() != 1) {
                parentItems.remove(i);
                continue;
            }
            //父级模块有效
            for (SysMenu mi : menuItems)
                if (mi.getParentId().equals(parentItems.get(i).getMenuId())) {
                    parentItems.get(i).addChildren(mi);
                }
            i++;
        }

        return parentItems;
    }

    /**
     * 以树状结构方式返回所有菜单。
     * 用于：菜单管理界面；创建、修改系统操作时的弹出菜单选择框。
     * @return
     */
    public List<SysMenu> findAllMenuTree() {
        Assert.notNull(this.menuDao, "Property 'menuDao' is required.");

        List<SysMenu> allMenus = this.menuDao.findAllMenus();
        if (allMenus == null || allMenus.isEmpty())
            return new ArrayList<SysMenu>(0);

        return this.buildMenuTree(allMenus, 0);
    }

    private List<SysMenu> buildMenuTree(List<SysMenu> allMenus, int parentId) {
        if (allMenus == null || allMenus.isEmpty())
            return null;
        List<SysMenu> result = new ArrayList<SysMenu>();
        for (SysMenu menu : allMenus) {
            if (menu.getParentId().equals(parentId)) {
                result.add(menu);
                menu.setChildren(this.buildMenuTree(allMenus, menu.getMenuId()));
            }
        }
        return result.isEmpty() ? null : result;
    }

    /**
     * 创建菜单。
     * @param menu
     * @return
     */
    public Integer createMenu(SysMenu menu) {
        if (menu == null)
            return 0;
        if (StringUtil.isEmpty(menu.getMenuName()))
            throw new BusinessException("名称不能为空");
        if (!SysMenu.MENU_MODULE.equals(menu.getMenuItemType())
            && !SysMenu.MENU_ITEM_APP.equals(menu.getMenuItemType())
            && !SysMenu.MENU_ITEM_URL.equals(menu.getMenuItemType())) {
            throw new BusinessException("无效的菜单类型，必须为mdl、app、url之一");
        }
        if (!SysMenu.MENU_MODULE.equals(menu.getMenuItemType())) {
            if ((menu.getParentId() == null || menu.getParentId() <= 0))
                throw new BusinessException("菜单项必须选择菜单模块ID");
            if (StringUtil.isEmpty(menu.getMenuItemData()))
                throw new BusinessException("菜单项地址不能为空");
        } else {
            menu.setParentId(0);
            menu.setMenuItemData("");
        }
        if (menu.getOrderBy() == null || menu.getOrderBy() < 0)
            menu.setOrderBy(0);
        if (menu.getStatus() == null)
            menu.setStatus(1);

        menu.setCreateTime(new Date(System.currentTimeMillis()));
        menu.setCreateUser("admin");
        menu.setUpdateUser("admin");

        Assert.notNull(this.menuDao, "Property 'menuDao' is required.");

        this.dbConstrains(menu);
        this.menuDao.create(menu);
        return menu.getMenuId();
    }

    /**
     * 更新菜单。
     * @param menu
     * @return
     */
    public Boolean updateMenu(SysMenu menu) {
        if (menu == null)
            return false;
        if (StringUtil.isEmpty(menu.getMenuName()))
            throw new BusinessException("名称不能为空");
        if (!SysMenu.MENU_MODULE.equals(menu.getMenuItemType())
            && !SysMenu.MENU_ITEM_APP.equals(menu.getMenuItemType())
            && !SysMenu.MENU_ITEM_URL.equals(menu.getMenuItemType())) {
            throw new BusinessException("无效的菜单类型，必须为mdl、app、url之一");
        }
        if (!SysMenu.MENU_MODULE.equals(menu.getMenuItemType())) {
            if (StringUtil.isEmpty(menu.getMenuItemData()))
                throw new BusinessException("菜单项地址不能为空");
            if (menu.getMenuId() == null || menu.getMenuId() <= 0)
                throw new BusinessException("更新菜单项时无效的菜单ID：" + menu.getMenuId());
        } else {
            menu.setMenuItemData("");
            menu.setParentId(0);
        }
        if (menu.getOrderBy() == null || menu.getOrderBy() < 0)
            menu.setOrderBy(0);
        if (menu.getStatus() == null)
            menu.setStatus(1);

        menu.setUpdateUser("admin");

        Assert.notNull(this.menuDao, "Property 'menuDao' is required.");

        this.dbConstrains(menu);
        return this.menuDao.update(menu) > 0;
    }

    private void dbConstrains(SysMenu a) {
        a.setMenuName(StringUtil.dbSafeString(a.getMenuName(), false, 40));
        a.setMenuItemData(StringUtil.dbSafeString(a.getMenuItemData(), false, 500));
        a.setCreateUser(StringUtil.dbSafeString(a.getCreateUser(), false, 40));
        a.setUpdateUser(StringUtil.dbSafeString(a.getUpdateUser(), false, 40));
    }

    //============================================================================
    //用户
    //============================================================================
    /**
     * 查询用户。
     * 用于：用户管理界面。
     * @param name 查询条件：用户姓名，全模糊匹配。
     * @param loginId 查询条件：登陆账号，全模糊匹配。
     * @param status 查询条件：状态，相等匹配，为null时不使用该查询条件。
     * @param pager 分页信息：为null时不进行分页，不为null时，将按照分页信息返回当前页数据，并将总记录数设置到
     *  {@link PagerInfo#getRowsCount()}中。
     * @return
     */
    public List<SysUser> findUser(String name, String loginId, Integer status, PagerInfo pager) {
        Assert.notNull(userDao, "Property 'userDao' is required.");

        // Dao方法参数
        if (StringUtil.isEmpty(name, true))
            name = null;
        else
            name = "%" + name + "%";
        if (StringUtil.isEmpty(loginId, true))
            loginId = null;
        else
            loginId = "%" + loginId + "%";
        Integer start = null, size = null;
        if (pager != null) {
            start = pager.getStart();
            size = pager.getPageSize();
        }

        try {
            // 查总记录数
            if (pager != null)
                pager.setRowsCount(userDao.findCount(name, loginId, status));

            // 查用户
            return userDao.find(name, loginId, status, start, size);
        } catch (Exception e) {
            log.error("[sys][user]无法查询用户列表", e);
            throw new BusinessException("数据库错误，无法查询用户列表：" + e.getMessage());
        }
    }

    public SysUser getUser(Integer userId) {
        Assert.notNull(userDao, "Property 'userDao' is required.");
        if (userId == null) {
            throw new ArgumentException("用户ID不能为空！");
        }
        try {
            return userDao.get(userId);
        } catch (Exception e) {
            log.error("[sys][user]无法读取用户资料, id=" + userId, e);
            throw new BusinessException("数据库错误，无法读取用户信息：，" + e.getMessage());
        }
    }

    public Boolean updateUser(SysUser user, int optUserId) {
        Assert.notNull(userDao, "Property 'userDao' is required.");
        if (user == null)
            throw new ArgumentException("无法更新用户资料，被更新的用户对象为空");
        if (user.getUserId() <= 0)
            throw new BusinessException("无法更新用户资料，无效的用户ID:" + user.getUserId());
        if (StringUtil.isEmpty(user.getUserName()) || StringUtil.isEmpty(user.getLoginId()))
            throw new BusinessException("无法更新用户资料，用户名和登录账号不能为空");
        if (user.getStatus() == null)
            throw new BusinessException("无法更新用户资料，用户状态不能为空");
        // 更新时不检查密码是否为空。
        // 密码加密存储，更新时用以下逻辑确定是否需要更新密码：
        // 密码为null，不更新；不为null，则重新进行加密并更新密码。
        if (user.getPassword() != null) {
            user.setPassword(EncryptUtil.md5(user.getPassword()));
        }

        //TODO: 获取当前登陆用户信息
        user.setUpdateUser("admin");

        try {
            this.dbConstrains(user);
            return userDao.update(user) > 0;
        } catch (Exception e) {
            log.error(
                "[sys][user]无法更新用户资料, id=" + user.getUserId() + ", name=" + user.getUserName(), e);
            throw new BusinessException("数据库错误，无法更新用户资料：" + e.getMessage());
        }
    }

    public Integer createUser(SysUser user, int optUserId) {
        //数据校验
        Assert.notNull(userDao, "Property 'userDao' is required.");
        if (user == null)
            throw new ArgumentException("无法创建用户，要创建的用户对象为空");
        if (StringUtil.isEmpty(user.getUserName()))
            throw new ArgumentException("无法创建用户，用户名不能为空");
        if (StringUtil.isEmpty(user.getLoginId(), true))
            throw new ArgumentException("无法创建用户，登录账号不能为空");
        if (StringUtil.isEmpty(user.getPassword()))
            throw new ArgumentException("无法创建用户，登录密码不能为空");

        //默认值
        //TODO: 读取当前登陆用户信息
        if (user.getStatus() == null)
            user.setStatus(1);
        user.setCreateUser("admin");
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setUpdateUser("admin");

        //密码加密存储
        user.setPassword(EncryptUtil.md5(user.getPassword()));

        try {
            this.dbConstrains(user);
            userDao.create(user);

            return user.getUserId();
        } catch (Exception e) {
            log.error(
                "[sys][user]无法创建用户, name=" + user.getUserName() + ", login=" + user.getLoginId(), e);
            throw new BusinessException("数据库错误，无法创建用户：" + e.getMessage());
        }
    }

    private void dbConstrains(SysUser a) {
        a.setUserName(StringUtil.dbSafeString(a.getUserName(), false, 25));
        a.setLoginId(StringUtil.dbSafeString(a.getLoginId(), false, 40));
        a.setMobile(StringUtil.dbSafeString(a.getMobile(), false, 15));
        a.setPhone(StringUtil.dbSafeString(a.getPhone(), false, 20));
        a.setEmail(StringUtil.dbSafeString(a.getEmail(), false, 50));
        a.setCreateUser(StringUtil.dbSafeString(a.getCreateUser(), false, 40));
        a.setUpdateUser(StringUtil.dbSafeString(a.getUpdateUser(), false, 40));
    }

    /**
     * 用户登陆。
     * @param loginId 账号。
     * @param password 密码。
     * @param clientIp 客户端IP
     * @return 如果登陆成功，返回登陆的用户对象。如果失败将抛出异常{@link BusinessException}
     */
    public SysUser userLogin(String loginId, String password, String sessionId, String clientIp) {
        if (StringUtil.isEmpty(loginId) || StringUtil.isEmpty(password))
            throw new BusinessException("账号和密码不能为空");
        Assert.notNull(this.userDao, "Property 'userDao' is required.");
        Assert.notNull(this.sessionDao, "Property 'sessionDao' is required.");

        SysUser user = this.userDao.getByLoginId(loginId);
        if (user == null)
            throw new BusinessException("账号" + loginId + "不存在");
        if (!user.getStatus().equals(1))
            throw new BusinessException("账号" + loginId + "已经被禁用，请与系统管理员联系");
        if (!user.getPassword().equals(EncryptUtil.md5(password)))
            throw new BusinessException("密码错误");

        //记录登陆session
        SysSession session = new SysSession();
        session.setSessionId(sessionId);
        session.setUserId(user.getUserId());
        session.setUserName(user.getUserName());
        session.setLoginTime(new Date(System.currentTimeMillis()));
        session.setLastAccessTime(new Date(System.currentTimeMillis()));
        if (clientIp == null) {
            clientIp = "";
        }
        session.setLoginIp(clientIp);
        this.sessionDao.create(session);

        return user;
    }

    /**
     * 用户退出登陆。
     * @param sessionId
     */
    public boolean userLogout(String sessionId) {
        try {
            if (StringUtil.isEmpty(sessionId))
                return false;
            return this.sessionDao.delete(sessionId) > 0;
        } catch (Exception e) {
            log.warn("[sys][login] 退出登陆错误", e);
            return false;
        }
    }

    //============================================================================
    //角色
    //============================================================================
    public List<SysRole> findRole(String name, Integer status, PagerInfo pager) {
        Assert.notNull(roleDao, "Property 'roleDao' is required.");

        // Dao方法参数
        if (name != null)
            name = "%" + name + "%";
        Integer start = null, size = null;
        if (pager != null) {
            start = pager.getStart();
            size = pager.getPageSize();
        }

        try {
            // 查总记录数
            if (pager != null)
                pager.setRowsCount(roleDao.findCount(name, status));

            // 查角色
            return roleDao.find(name, status, start, size);
        } catch (Exception e) {
            log.error("[sys][role]无法查询角色列表", e);
            throw new BusinessException("数据库错误，无法查询角色列表：" + e.getMessage());
        }
    }

    public SysRole getRole(Integer roleId) {
        Assert.notNull(roleDao, "Property 'roleDao' is required.");
        if (roleId == null) {
            throw new ArgumentException("角色ID不能为空！");
        }
        try {
            return roleDao.get(roleId);
        } catch (Exception e) {
            log.error("[sys][role]无法读取角色资料, id=" + roleId, e);
            throw new BusinessException("数据库错误，无法读取角色信息：，" + e.getMessage());
        }
    }

    public Boolean updateRole(SysRole role, int optUserId) {
        Assert.notNull(roleDao, "Property 'roleDao' is required.");
        if (role == null)
            throw new ArgumentException("无法更新角色资料，被更新的角色对象为空");
        if (role.getRoleId() == null || role.getRoleId() <= 0)
            throw new BusinessException("无法更新角色资料，无效的角色ID:" + role.getRoleId());
        if (StringUtil.isEmpty(role.getRoleName()))
            throw new BusinessException("无法更新角色资料，角色名不能为空");
        if (role.getStatus() == null)
            throw new BusinessException("无法更新角色资料，角色状态不能为空");

        //TODO: 获取当前登陆用户信息
        role.setUpdateUser("admin");

        try {
            this.dbConstrains(role);
            return roleDao.update(role) > 0;
        } catch (Exception e) {
            log.error(
                "[sys][role]无法更新角色资料, id=" + role.getRoleId() + ", name=" + role.getRoleName(), e);
            throw new BusinessException("数据库错误，无法更新角色资料：" + e.getMessage());
        }
    }

    public Integer createRole(SysRole role, int optUserId) {
        //数据校验
        Assert.notNull(roleDao, "Property 'roleDao' is required.");
        if (role == null)
            throw new ArgumentException("无法创建角色，要创建的角色对象为空");
        if (StringUtil.isEmpty(role.getRoleName()))
            throw new ArgumentException("无法创建角色，角色名不能为空");

        //默认值
        //TODO: 读取当前登陆用户信息
        if (role.getStatus() == null)
            role.setStatus(1);
        role.setCreateUser("admin");
        role.setCreateTime(new Date(System.currentTimeMillis()));
        role.setUpdateUser("admin");

        try {
            this.dbConstrains(role);
            roleDao.create(role);

            return role.getRoleId();
        } catch (Exception e) {
            log.error("[sys][role]无法创建角色, name=" + role.getRoleName(), e);
            throw new BusinessException("数据库错误，无法创建角色：" + e.getMessage());
        }
    }

    private void dbConstrains(SysRole a) {
        a.setRoleName(StringUtil.dbSafeString(a.getRoleName(), false, 30));
        a.setRemark(StringUtil.dbSafeString(a.getRemark(), false, 100));
        a.setCreateUser(StringUtil.dbSafeString(a.getCreateUser(), false, 40));
        a.setUpdateUser(StringUtil.dbSafeString(a.getUpdateUser(), false, 40));
    }

    /**
     * 查询已经分配给用户的角色列表。
     * 用于：为用户分配角色的弹出框；
     * @param userId
     * @return
     */
    public List<SysRole> findUserRoleAssigned(Integer userId) {
        Assert.notNull(roleDao, "Property 'roleDao' is required.");
        if (userId == null || userId <= 0)
            throw new BusinessException("无效的用户ID");
        return this.roleDao.findUserRoleAssigned(userId);
    }

    /**
     * 查询尚未分配给用户的系统角色列表。
     * 用于：为用户分配角色的弹出框；
     * @param userId
     * @return
     */
    public List<SysRole> findUserRoleUnAssigned(Integer userId) {
        Assert.notNull(roleDao, "Property 'roleDao' is required.");
        if (userId == null || userId <= 0)
            throw new BusinessException("无效的用户ID");
        return this.roleDao.findUserRoleUnAssigned(userId);
    }

    /**
     * 为用户分配角色。
     * 用于：为用户分配角色的弹出框；
     * @param userId 用户ID。
     * @param roleIds 需要为用户分配的角色ID列表；
     * @param optUser 
     * @return
     */
    public Boolean assignUserRole(Integer userId, List<Integer> roleIds, String optUser) {
        Assert.notNull(roleDao, "Property 'roleDao' is required.");
        if (userId == null || userId <= 0)
            throw new BusinessException("无效的用户ID，无法为用户分配角色");
        if (roleIds == null || roleIds.isEmpty()) {
            log.info("[sys][role] 没有为用户分配角色，传入的角色ID为空，userId=" + userId + ", optUser=" + optUser);
            return false;
        }
        return this.roleDao.assignUserRoles(userId, roleIds, optUser) > 0;
    }

    /**
     * 取消已经分配给用户的角色。
     * 用于：为用户分配角色的弹出框；
     * @param userId 用户ID。
     * @param roleIds 需要取消的角色ID列表。
     * @param optUser
     * @return
     */
    public Boolean unassignUserRole(Integer userId, List<Integer> roleIds, String optUser) {
        Assert.notNull(roleDao, "Property 'roleDao' is required.");
        if (userId == null || userId <= 0)
            throw new BusinessException("无效的用户ID，无法为用户取消分配角色");
        if (roleIds == null || roleIds.isEmpty()) {
            log.info("[sys][role] 没有为用户取消分配角色，传入的角色ID为空，userId=" + userId + ", optUser=" + optUser);
            return false;
        }
        for (Integer roleId : roleIds) {
            this.roleDao.unassignUserRole(userId, roleId, optUser);
        }
        return true;
    }

    //============================================================================
    //操作
    //============================================================================
    public List<SysAction> findAction(String key, String name, String remark, Integer status,
                                      String menuName, PagerInfo pager) {
        Assert.notNull(this.actionDao, "Property 'actionDao' is required.");

        int start = 0, size = 0;
        if (pager != null) {
            start = pager.getStart();
            size = pager.getPageSize();
        }

        if (key != null && !StringUtil.isEmpty(key))
            key = "%" + StringUtil.trim(key, ' ', '%') + "%";
        else
            key = null;
        if (name != null && !StringUtil.isEmpty(name))
            name = "%" + StringUtil.trim(name, ' ', '%') + "%";
        else
            name = null;
        if (remark != null && !StringUtil.isEmpty(remark))
            remark = "%" + StringUtil.trim(remark, ' ', '%') + "%";
        else
            remark = null;
        if (menuName != null && !StringUtil.isEmpty(menuName))
            menuName = "%" + StringUtil.trim(menuName, ' ', '%') + "%";
        else
            menuName = null;

        if (pager != null)
            pager.setRowsCount(this.actionDao.findCount(key, name, remark, status, menuName, start,
                size));

        return this.actionDao.find(key, name, remark, status, menuName, start, size);
    }

    public SysAction getAction(Integer actionId) {
        Assert.notNull(actionDao, "Property 'actionDao' is required.");
        if (actionId == null || actionId <= 0) {
            return null;
        }
        return this.actionDao.get(actionId);
    }

    public Boolean updateAction(SysAction action, int optUserId) {
        Assert.notNull(actionDao, "Property 'actionDao' is required.");
        if (action == null)
            throw new ArgumentException("被更新的操作对象为空");
        if (action.getActId() <= 0)
            throw new BusinessException("无效的操作ID:" + action.getActId());
        if (StringUtil.isEmpty(action.getActKey()) || StringUtil.isEmpty(action.getActName()))
            throw new BusinessException("操作key和名称不能为空");

        if (action.getStatus() == null)
            action.setStatus(1);
        action.setUrl(this.fixUri(action.getUrl()));
        this.dbConstrains(action);
        return actionDao.update(action) > 0;
    }

    public Integer createAction(SysAction action, int optUserId) {
        //数据校验
        Assert.notNull(actionDao, "Property 'actionDao' is required.");
        if (action == null)
            throw new ArgumentException("要创建的操作对象为空");
        if (StringUtil.isEmpty(action.getActKey()))
            throw new ArgumentException("操作key不能为空");
        if (StringUtil.isEmpty(action.getActName()))
            throw new ArgumentException("操作名称不能为空");

        //默认值
        if (action.getStatus() == null)
            action.setStatus(1);
        action.setUrl(this.fixUri(action.getUrl()));

        this.dbConstrains(action);
        this.actionDao.create(action);
        return action.getActId();
    }

    private void dbConstrains(SysAction a) {
        a.setActKey(StringUtil.dbSafeString(a.getActKey(), false, 30));
        a.setActName(StringUtil.dbSafeString(a.getActName(), false, 30));
        a.setUrl(StringUtil.dbSafeString(a.getUrl(), false, 200));
        a.setRemark(StringUtil.dbSafeString(a.getRemark(), false, 100));
    }

    private String fixUri(String uri) {
        if (StringUtil.isEmpty(uri))
            return uri;
        return "/" + StringUtil.trim(uri.replace('\\', '/'), ' ', '/', '\\', '?', '&');
    }

    //============================================================================
    //权限
    //============================================================================
    private List<SysPermission> findPermission(Integer ownerType, Integer ownerId, Integer resType,
                                               Integer resId) {
        Assert.notNull(this.permissionDao, "Property 'permissionDao' is required.");
        List<Integer> ownerIds = null;
        if (ownerId != null && ownerId > 0) {
            ownerIds = new ArrayList<Integer>(1);
            ownerIds.add(ownerId);
        }
        List<Integer> resIds = null;
        if (resId != null && resId > 0) {
            resIds = new ArrayList<Integer>(1);
            resIds.add(resId);
        }
        return this.permissionDao.find(ownerType, ownerIds, resType, resIds);
    }

    private List<SysPermission> findPermission(Integer ownerType, List<Integer> ownerIds,
                                               Integer resType, List<Integer> resIds) {
        Assert.notNull(this.permissionDao, "Property 'permissionDao' is required.");
        return this.permissionDao.find(ownerType, ownerIds, resType, resIds);
    }

    /**
     * 查询权限所有者列表。
     * 用于：权限管理界面；
     * @param type 权限所有者类型，相等匹配，不允许为null。参考{@link SysPermission#OWNER_ROLE}、{@link SysPermission#OWNER_USER}。
     * @param name 查询条件：权限所有者名称，全模糊查询。
     * @param status 查询条件：权限所有者状态，相等匹配，为null时不使用该查询条件。
     * @param pager 分页信息：为null时不进行分页，不为null时，将按照分页信息返回当前页数据，并将总记录数设置到
     *  {@link PagerInfo#getRowsCount()}中。
     * @return
     */
    public List<PermissionOwner> findPermissionOwner(Integer type, String name, Integer status,
                                                     PagerInfo pager) {
        List<PermissionOwner> result = null;

        if (type == null)
            throw new BusinessException("必须指定权限所有者类型");

        if (type == SysPermission.OWNER_USER) {
            List<SysUser> users = this.findUser(name, null, status, pager);
            if (users != null) {
                result = new ArrayList<PermissionOwner>(users.size());
                for (SysUser user : users) {
                    PermissionOwner owner = new PermissionOwner();
                    owner.setType(SysPermission.OWNER_USER);
                    owner.setId(user.getUserId());
                    owner.setName(user.getUserName());
                    owner.setStatus(user.getStatus());
                    result.add(owner);
                }
            }
        }

        if (type == SysPermission.OWNER_ROLE) {
            List<SysRole> roles = this.findRole(name, status, pager);
            if (roles != null) {
                result = new ArrayList<PermissionOwner>(roles.size());
                for (SysRole role : roles) {
                    PermissionOwner owner = new PermissionOwner();
                    owner.setType(SysPermission.OWNER_ROLE);
                    owner.setId(role.getRoleId());
                    owner.setName(role.getRoleName());
                    owner.setStatus(role.getStatus());
                    result.add(owner);
                }
            }
        }

        return result;
    }

    /**
     * 查询所有的权限资源数据。
     * 1. 模块、菜单、操作将以树状结构返回；
     * 2. 已经分配给权限所有者的资源，{@link PermissionResource#getChecked()}值将设置为true，否则设置范围false；
     * 3. 会返回所有状态的资源（包括有效、禁用、逻辑删除的）；
     * @param userId 用户ID，已经分配给该用户的资源会设置{@link PermissionResource#getChecked()}值。
     * @param name 查询条件：资源名称，全模糊匹配。
     * @return
     */
    public List<PermissionResource> findPermissionResourceTree(Integer ownerId, Integer ownerType,
                                                               String name) {
        //取所有菜单（模块+菜单项，树状结构）
        List<SysMenu> menus = this.findAllMenuTree();
        if (menus == null || menus.isEmpty())
            return new ArrayList<PermissionResource>(0);
        //取所有操作
        List<SysAction> actions = this.findAction(null, name, null, null, null, null);
        //构造PermissionResource树状结构
        List<PermissionResource> result = this.buildPermissionResourceTree(menus, actions);
        //处理name查询条件
        this.filterPermissionResourceTree(result, name);
        //标记已经分配给用户的资源
        this.processUserPermissionResourceAssignment(result, ownerId, ownerType);
        return result;
    }

    private void processUserPermissionResourceAssignment(List<PermissionResource> resources,
                                                         Integer ownerId, Integer ownerType) {
        Assert.notNull(this.permissionDao, "Property 'permissionDao' is required.");
        List<SysPermission> perms = this.findPermission(ownerType, ownerId, null, null);
        if (perms == null || perms.isEmpty())
            return;

        Map<String, SysPermission> permMap = this.toMap(perms);
        for (PermissionResource menuModule : resources) {
            //第一层：菜单模块
            menuModule.setChecked(null);
            if (menuModule.getChildren() == null || menuModule.getChildren().isEmpty()) {
                continue;
            }
            for (PermissionResource menuItem : menuModule.getChildren()) {
                //第二层：菜单项
                SysPermission permMenuItem = permMap.get(menuItem.getRid());
                if (permMenuItem != null
                    && SysPermission.RESOURCE_MENU == permMenuItem.getResType()) {
                    menuItem.setChecked(true);
                }
                if (menuItem.getChildren() == null || menuItem.getChildren().isEmpty())
                    continue;
                for (PermissionResource action : menuItem.getChildren()) {
                    //第三层：系统操作
                    SysPermission permAction = permMap.get(action.getRid());
                    if (permAction != null
                        && SysPermission.RESOURCE_ACTION == permAction.getResType()) {
                        action.setChecked(true);
                    }
                }
            }
        }
    }

    private Map<String, SysPermission> toMap(List<SysPermission> perms) {
        if (perms == null || perms.isEmpty())
            return new HashMap<String, SysPermission>(0);
        Map<String, SysPermission> result = new HashMap<String, SysPermission>(perms.size());
        for (SysPermission perm : perms) {
            result.put(this.buildPermissionResourceKey(perm), perm);
        }
        return result;
    }

    private void filterPermissionResourceTree(List<PermissionResource> resources, String name) {
        for (int i = 0; i < resources.size();) {
            //第一层：菜单模块
            PermissionResource menuModule = resources.get(i);
            //模块下面没有任何菜单项，移除
            if (menuModule.getChildren() == null || menuModule.getChildren().isEmpty()) {
                resources.remove(i);
                continue;
            }
            //没有输入名称查询条件值，无须对菜单项、系统操作进行过滤
            if (StringUtil.isEmpty(name, true)) {
                i++;
                continue;
            }
            for (int j = 0; j < menuModule.getChildren().size();) {
                //第二层：菜单项
                PermissionResource menuItem = menuModule.getChildren().get(j);
                //菜单项名称符合查询条件
                if (menuItem.getName().trim().toLowerCase().indexOf(name.trim().toLowerCase()) >= 0) {
                    j++;
                    continue;
                }
                //菜单项名称不符合查询条件，则看菜单项下面是否有系统操作符合名称查询条件，有则保留菜单项，无则移除菜单项
                if (menuItem.getChildren() == null || menuItem.getChildren().isEmpty()) {
                    menuModule.getChildren().remove(j);
                    continue;
                }
                j++;
            }
            if (menuModule.getChildren() == null || menuModule.getChildren().isEmpty()) {
                resources.remove(i);
                continue;
            }
            i++;
        }
    }

    private List<PermissionResource> buildPermissionResourceTree(List<SysMenu> menus,
                                                                 List<SysAction> actions) {
        if (menus == null)
            return null;
        List<PermissionResource> result = new ArrayList<PermissionResource>(menus.size());
        for (SysMenu menu : menus) {
            //构造权限资源对象
            PermissionResource res = new PermissionResource();
            if (SysMenu.MENU_ITEM_APP.equals(menu.getMenuItemType())
                || SysMenu.MENU_ITEM_URL.equals(menu.getMenuItemType())) {
                res.setRtype(SysPermission.RESOURCE_MENU);
                res.setRid("itm-" + menu.getMenuId());
            } else {
                res.setRtype(0);
                res.setRid("mdl-" + menu.getMenuId());
            }
            res.setName(menu.getMenuName());
            res.setChecked(false);
            res.setStatus(menu.getStatus());

            //处理菜单模块、菜单项父子关系
            if (menu.getChildren() != null)
                res.setChildren(this.buildPermissionResourceTree(menu.getChildren(), actions));

            //将操作列表添加到菜单项资源上
            if (res.getRtype() == SysPermission.RESOURCE_MENU && actions != null
                && !actions.isEmpty()) {
                List<PermissionResource> actionResources = new ArrayList<PermissionResource>();
                for (SysAction action : actions) {
                    if (!action.getFnId().equals(menu.getMenuId()))
                        continue;
                    PermissionResource actRes = new PermissionResource();
                    actRes.setRtype(SysPermission.RESOURCE_ACTION);
                    actRes.setRid("act-" + action.getActId());
                    actRes.setName(action.getActName());
                    actRes.setChecked(false);
                    actRes.setStatus(action.getStatus());
                    actionResources.add(actRes);
                }
                if (!actionResources.isEmpty())
                    res.setChildren(actionResources);
            }

            result.add(res);
        }
        return result;
    }

    /**
     * 分配权限。
     * @param ownerId 权限所有者ID。
     * @param ownerType 权限所有者类型。参考{@link SysPermission#OWNER_ROLE}、{@link SysPermission#OWNER_USER}。
     * @param resourceIds 资源ID列表。
     * 资源ID格式规范：
     * 1. 菜单模块：mdl-资源ID；
     * 2. 菜单项：itm-资源ID；
     * 3. 系统操作：act-资源ID；
     */
    public void assignPermission(Integer ownerId, Integer ownerType, String[] resourceIds) {
        //部分数据校验
        if (ownerId == null || ownerId <= 0)
            throw new BusinessException("无效的权限所有者ID：" + ownerId);
        if (ownerType == null || ownerType <= 0)
            throw new BusinessException("无效的权限所有者类型：" + ownerType);
        Set<String> ids = new HashSet<String>(resourceIds == null ? 0 : resourceIds.length);
        if (resourceIds != null)
            for (String id : resourceIds)
                ids.add(id);

        Assert.notNull(this.permissionDao, "Property 'permissionDao' is required.");

        //已经分配给 该权限所有者 的资源
        List<SysPermission> assignedList = this.findPermission(ownerType, ownerId, null, null);
        Map<String, SysPermission> assignedMap = new HashMap<String, SysPermission>(
            assignedList == null ? 0 : assignedList.size());
        for (SysPermission perm : assignedList) {
            String id = this.buildPermissionResourceKey(perm);
            if (StringUtil.isEmpty(id)) {
                log.warn("[sys][perm] 无法识别的权限类型：" + perm.getResType());
                continue;
            }
            assignedMap.put(id, perm);
        }

        //更新所有者的资源权限
        for (String id : ids) {
            //资源已经分配给这个所有者，无需进行处理
            if (assignedMap.containsKey(id)) {
                assignedMap.remove(id);
                continue;
            }
            //资源尚未分配给这个所有者，添加
            String[] tokens = id.split("-");
            if (tokens == null || tokens.length != 2) {
                throw new BusinessException("无效的资源ID：" + id);
            }
            Integer resId = ConvertUtil.toInt(tokens[1], null);
            if (resId == null || resId <= 0) {
                throw new BusinessException("无效资源ID：" + id);
            }
            Integer resType = null;
            if ("act".equals(tokens[0]))
                resType = SysPermission.RESOURCE_ACTION;
            else if ("itm".equals(tokens[0]))
                resType = SysPermission.RESOURCE_MENU;
            else {
                if (log.isInfoEnabled())
                    log.info("[sys][perm]未执行权限分配操作：owner:" + ownerId + ", type:" + ownerType
                             + ", resource:" + id);
                continue;
            }

            SysPermission perm = new SysPermission();
            perm.setOwnerId(ownerId);
            perm.setOwnerType(ownerType);
            perm.setResId(resId);
            perm.setResType(resType);
            perm.setStartTime(new Date(System.currentTimeMillis()));
            perm.setEndTime(DateUtil.getDate(9999, 12, 31));
            //TODO: 传递当前操作用户
            perm.setUpdateUser("admin");
            try {
                this.permissionDao.create(perm);
            } catch (Exception e) {
                log.error("[sys][perm] 添加权限时数据库错误", e);
                throw new BusinessException("数据库错误，权限修改失败：" + e.getMessage());
            }
        }
        //assignedMap中剩余的资源不再分配给该所有者
        for (Map.Entry<String, SysPermission> entry : assignedMap.entrySet()) {
            SysPermission perm = entry.getValue();
            perm.setEndTime(DateUtil.add(DateUtil.currentDateTime(), Calendar.SECOND, -1));
            //TODO: 
            perm.setUpdateUser("admin");
            try {
                this.permissionDao.update(perm);
            } catch (Exception e) {
                log.error("[sys][perm] 删除权限时数据库错误", e);
                throw new BusinessException("数据库错误，权限修改失败：" + e.getMessage());
            }
        }
    }

    private String buildPermissionResourceKey(SysPermission perm) {
        if (perm == null)
            return null;
        if (perm.getResType() == SysPermission.RESOURCE_MENU)
            return "itm-" + perm.getResId();
        else if (perm.getResType() == SysPermission.RESOURCE_ACTION)
            return "act-" + perm.getResId();
        else
            return null;
    }

    public SysSession checkPermission(String sessionId, String requestUri, String clientIp) {
        if (StringUtil.isEmpty(sessionId))
            throw new BusinessException("会话ID为空，请重新登陆系统或者与系统管理员联系");
        if (StringUtil.isEmpty(requestUri))
            throw new BusinessException("请求地址为空，无法进行权限校验");

        Assert.notNull(this.sessionDao, "Property 'sessionDao' is required.");
        Assert.notNull(this.actionDao, "Property 'actionDao' is required.");

        //获取会话对象
        SysSession session = null;
        try {
            session = this.sessionDao.get(sessionId);
        } catch (Exception e) {
            log.error("[sys][perm]读取会话数据失败：sessionId:" + sessionId + ", uri:" + requestUri, e);
            throw new BusinessException("系统错误，读取会话状态失败");
        }
        if (session == null) {
            log.error("[sys][perm]会话数据不存在：sessionId:" + sessionId + ", uri:" + requestUri);
            throw new BusinessException("无效的会话ID，请重新登陆系统或者与系统管理员联系");
        }

        //Session60分钟后过期
        Calendar c1 = Calendar.getInstance();
        c1.setTime(session.getLastAccessTime());
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        long time1 = c1.getTimeInMillis() / 1000;
        long time2 = c2.getTimeInMillis() / 1000;
        long diff = (time2 - time1) / 60;//相隔分钟
        //log.info("空闲时间:" + diff);
        if (diff > 60) {
            throw new BusinessException("空闲时间超过60分钟，请重新登陆系统。");
        }
        if (!StringUtil.isEmpty(clientIp)) {///FIXME 兼容ebs，将来要去掉非空判断
            //由于新办公室ip经常变换，顾先去掉这个验证
            //            //ip异常，需要重新登录
            //            if (!session.getLoginIp().equalsIgnoreCase(clientIp)) {
            //                throw new BusinessException("IP异常，请重新登陆系统。");
            //            }
        }
        //更新最后访问时间
        try {
            session.setLastAccessTime(new Date(System.currentTimeMillis()));
            this.sessionDao.update(session);
        } catch (Exception e) {
            log.warn("[sys][perm]更新会话的最后访问时间失败", e);
            //非关键错误，继续检查访问权限
        }
        //根据uri取系统操作
        SysAction action = null;
        try {
            action = this.actionDao.findByUrl(requestUri + "%");
        } catch (Exception e) {
            log.error("[sys][perm]无法读取系统操作", e);
            //读取系统操作失败，禁止执行该操作
            throw new BusinessException("系统错误，读取系统操作失败");
        }
        if (action == null)
            //没有为该访问地址配置权限，允许用户执行该操作
            return session;
        //检查用户权限：注意，这里不再判断用户状态，用户如果禁用、逻辑删除，登录时应当失败。
        try {
            List<SysPermission> perms = this.findPermission(SysPermission.OWNER_USER,
                session.getUserId(), SysPermission.RESOURCE_ACTION, action.getActId());
            //用户有权限访问
            if (perms != null && !perms.isEmpty())
                return session;
        } catch (Exception e) {
            log.error("[sys][perm]查找用户访问权限失败：userId:" + session.getUserId() + ", actionId:"
                      + action.getActId(), e);
            throw new BusinessException("系统错误，读取用户权限失败");
        }
        //检查角色权限
        try {
            List<Integer> roleIds = this.userDao.findUserRoleIds(session.getUserId());
            //用户没有分配任何角色，无权限访问
            if (roleIds == null || roleIds.isEmpty())
                return null;
            List<Integer> resIds = new ArrayList<Integer>(1);
            resIds.add(action.getActId());
            List<SysPermission> perms = this.findPermission(SysPermission.OWNER_ROLE, roleIds,
                SysPermission.RESOURCE_ACTION, resIds);
            if (perms != null && !perms.isEmpty())
                return session;
        } catch (Exception e) {
            log.error("[sys][perm]查找用户角色访问权限失败：userId:" + session.getUserId() + ", actionId:"
                      + action.getActId(), e);
            throw new BusinessException("系统错误，读取用户角色权限失败");
        }
        return null;
    }

    public Boolean checkPermissionBySessionIdAndActKey(String sessionId, String actKey) {
        if (StringUtil.isEmpty(sessionId))
            throw new BusinessException("会话ID为空，请重新登陆系统或者与系统管理员联系");
        if (StringUtil.isEmpty(actKey))
            throw new BusinessException("操作key为空，无法进行权限校验");

        Assert.notNull(this.sessionDao, "Property 'sessionDao' is required.");
        Assert.notNull(this.actionDao, "Property 'actionDao' is required.");

        //获取会话对象
        SysSession session = null;
        try {
            session = this.sessionDao.get(sessionId);
        } catch (Exception e) {
            log.error("[sys][perm]读取会话数据失败：sessionId:" + sessionId + ", actKey:" + actKey, e);
            throw new BusinessException("系统错误，读取会话状态失败");
        }
        if (session == null) {
            log.error("[sys][perm]会话数据不存在：sessionId:" + sessionId + ", actKey:" + actKey);
            throw new BusinessException("无效的会话ID，请重新登陆系统或者与系统管理员联系");
        }
        //更新最后访问时间
        try {
            session.setLastAccessTime(new Date(System.currentTimeMillis()));
            this.sessionDao.update(session);
        } catch (Exception e) {
            log.warn("[sys][perm]更新会话的最后访问时间失败", e);
            //非关键错误，继续检查访问权限
        }
        //根据uri取系统操作
        SysAction action = null;
        try {
            action = this.actionDao.findByActKey(actKey);
        } catch (Exception e) {
            log.error("[sys][perm]无法读取系统操作", e);
            //读取系统操作失败，禁止执行该操作
            throw new BusinessException("系统错误，读取系统操作失败");
        }
        if (action == null)
            //没有为该访问地址配置权限，允许用户执行该操作
            return true;
        //检查用户权限：注意，这里不再判断用户状态，用户如果禁用、逻辑删除，登录时应当失败。
        try {
            List<SysPermission> perms = this.findPermission(SysPermission.OWNER_USER,
                session.getUserId(), SysPermission.RESOURCE_ACTION, action.getActId());
            //用户有权限访问
            if (perms != null && !perms.isEmpty())
                return true;
        } catch (Exception e) {
            log.error("[sys][perm]查找用户访问权限失败：userId:" + session.getUserId() + ", actionId:"
                      + action.getActId(), e);
            throw new BusinessException("系统错误，读取用户权限失败");
        }
        //检查角色权限
        try {
            List<Integer> roleIds = this.userDao.findUserRoleIds(session.getUserId());
            //用户没有分配任何角色，无权限访问
            if (roleIds == null || roleIds.isEmpty())
                return null;
            List<Integer> resIds = new ArrayList<Integer>(1);
            resIds.add(action.getActId());
            List<SysPermission> perms = this.findPermission(SysPermission.OWNER_ROLE, roleIds,
                SysPermission.RESOURCE_ACTION, resIds);
            if (perms != null && !perms.isEmpty())
                return true;
        } catch (Exception e) {
            log.error("[sys][perm]查找用户角色访问权限失败：userId:" + session.getUserId() + ", actionId:"
                      + action.getActId(), e);
            throw new BusinessException("系统错误，读取用户角色权限失败");
        }
        return null;
    }

    /**
     * 保存系统操作日志。
     * @param accessLog
     */
    public void createAccessLog(SysAccessLog accessLog) {
        Assert.notNull(this.accessLogDao, "Property 'accessLogDao' is required.");

        accessLog.setLogTime(new Date(System.currentTimeMillis()));
        if (accessLog.getUserId() == null)
            accessLog.setUserId(0);
        if (accessLog.getLogType() == null)
            accessLog.setLogType(0);
        if (accessLog.getSuccess() == null)
            accessLog.setSuccess(1);
        accessLog.setUserName(StringUtil.dbSafeString(accessLog.getUserName(), false, 40));
        accessLog.setClientIp(StringUtil.dbSafeString(accessLog.getClientIp(), false, 15));
        accessLog.setServerIp(StringUtil.dbSafeString(accessLog.getServerIp(), false, 15));
        accessLog.setRefererUrl(StringUtil.dbSafeString(accessLog.getRefererUrl(), false, 300));
        accessLog.setVisitUrl(StringUtil.dbSafeString(accessLog.getVisitUrl(), false, 300));
        accessLog.setClientToken(StringUtil.dbSafeString(accessLog.getClientToken(), false, 40));
        accessLog.setSessionId(StringUtil.dbSafeString(accessLog.getSessionId(), false, 40));
        accessLog.setParamValue(StringUtil.dbSafeString(accessLog.getParamValue(), false, 500));
        accessLog.setCookieValue(StringUtil.dbSafeString(accessLog.getCookieValue(), false, 500));
        accessLog.setAgent(StringUtil.dbSafeString(accessLog.getAgent(), false, 200));

        this.accessLogDao.create(accessLog);
    }

    public List<SysAccessLog> findAccessLog(Date startTime, Date endTime, String clientIp,
                                            String userName, String visitUrl, String sessionId,
                                            PagerInfo pager) {
        Assert.notNull(this.accessLogDao, "Property 'accessLogDao' is required.");

        // Dao方法参数
        if (endTime == null)
            endTime = DateUtil.currentDateTime();
        if (startTime == null)
            startTime = DateUtil.add(endTime, Calendar.DAY_OF_MONTH, -3);
        if (StringUtil.isEmpty(clientIp, true))
            clientIp = null;
        if (StringUtil.isEmpty(userName, true))
            userName = null;
        if (StringUtil.isEmpty(visitUrl, true))
            visitUrl = null;
        else
            visitUrl = "%" + visitUrl + "%";
        if (StringUtil.isEmpty(sessionId, true))
            sessionId = null;
        Integer start = null, size = null;
        if (pager != null) {
            start = pager.getStart();
            size = pager.getPageSize();
        } else {
            start = 0;
            size = 100;
        }

        try {
            // 查总记录数
            if (pager != null)
                pager.setRowsCount(this.accessLogDao.findCount(startTime, endTime, clientIp,
                    visitUrl, userName, sessionId));

            // 查用户
            return this.accessLogDao.find(startTime, endTime, clientIp, visitUrl, userName,
                sessionId, start, size);
        } catch (Exception e) {
            log.error("[sys][log]无法查询系统日志", e);
            throw new BusinessException("数据库错误，无法查询系统日志：" + e.getMessage());
        }
    }

    /**
     * 修改用户密码。
     * @param userId 账号id。
     * @param oldPassword 旧密码。
     * @param newPassword 新密码。
     * @return 如果修改成功，返回true。如果失败将抛出异常{@link BusinessException}
     */
    public Boolean changePwd(Integer userId, String oldPassword, String newPassword) {
        if (userId == 0 || StringUtil.isEmpty(oldPassword) || StringUtil.isEmpty(newPassword))
            throw new BusinessException("用户ID及新旧密码不能为空。");
        if (oldPassword.equals(newPassword))
            throw new BusinessException("新旧密码不能相同。");
        Assert.notNull(this.userDao, "Property 'userDao' is required.");

        SysUser user = this.userDao.get(userId);
        if (user == null)
            throw new BusinessException("用户不存在。");
        if (!user.getPassword().equals(EncryptUtil.md5(oldPassword)))
            throw new BusinessException("旧密码不正确，请重新输入。");

        return this.userDao.updatePasswordByUserId(userId, EncryptUtil.md5(newPassword)) == 1;
    }
}