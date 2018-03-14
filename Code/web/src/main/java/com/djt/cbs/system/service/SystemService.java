package com.djt.cbs.system.service;

import java.util.Date;
import java.util.List;

import com.djt.cbs.system.entity.PermissionOwner;
import com.djt.cbs.system.entity.PermissionResource;
import com.djt.cbs.system.entity.SysAccessLog;
import com.djt.cbs.system.entity.SysAction;
import com.djt.cbs.system.entity.SysMenu;
import com.djt.cbs.system.entity.SysPermission;
import com.djt.cbs.system.entity.SysRole;
import com.djt.cbs.system.entity.SysSession;
import com.djt.cbs.system.entity.SysUser;
import com.djt.common.BusinessException;
import com.djt.common.PagerInfo;
import com.djt.common.ServiceResult;

public interface SystemService {
    /**
     * 加载用户有权限的菜单项。
     * @param sessionId 当前登陆认证后的回话ID。
     * @return
     */
    ServiceResult<List<SysMenu>> loadUserMenu(String sessionId);

    /**
     * 加载树状结构的菜单。
     * @return
     */
    ServiceResult<List<SysMenu>> findMenuTree();

    ServiceResult<Integer> createMenu(SysMenu menu);

    ServiceResult<Boolean> updateMenu(SysMenu menu);

    /**
     * 根据条件查询用户对象列表。
     * @param name 用户姓名，模糊查询
     * @param loginId 登录ID，模糊查询。
     * @param status 状态。查询指定状态的用户，为null时不会对用户状态进行查询。
     * @param pager 分页信息，为null时不会进行分页处理，将返回所有记录。客户端必须正确设置好每页记录数{@link PagerInfo#getPageSize()}和
     * 第几页{@link PagerInfo#getPageIndex()}。
     * @return 返回符合条件的用户对象列表，按照登录账号排序，符合条件的总记录数在{@link ServiceResult#getPager()}中。
     * <p>发生系统异常时（{@link ServiceResult#isSuccess()}为false）{@link ServiceResult#getResult(Object)}为null。
     */
    ServiceResult<List<SysUser>> findUser(String name, String loginId, Integer status,
                                          PagerInfo pager);

    /**
     * 根据用户ID取单个用户对象。
     * @param userId 用户ID。
     * @return 用户ID无效、用户不存在时，返回null；否则返回用户对象。
     */
    ServiceResult<SysUser> getUserById(Integer userId);

    /**
     * 更新后台管理的用户资料。
     * @param operaterId 操作用户ID。
     * @param user 要更新的用户对象。
     * @return 更新成功返回true，否则返回false。
     */
    ServiceResult<Boolean> updateUser(SysUser user, int optUserId);

    /**
     * 创建一个新的后台管理用户。
     * @param user {@link SysUser}对象。用户名称{@link SysUser#getUserName()}、登录账号{@link SysUser#getLoginId()}
     * 、登录密码{@link SysUser#getPassword()}不能为空。
     * @return 注册成功返回新创建的用户ID，失败时返回的用户ID为0。
     */
    ServiceResult<Integer> createUser(SysUser user, int optUserId);

    /**
     * 用户登陆。
     * @param loginId 账号。
     * @param password 密码。
     * @param sessionId 登陆后的会话ID。
     * @return 登陆成功，返回登陆用户对象；登陆失败将抛出{@link BusinessException}
     */
    ServiceResult<SysUser> userLogin(String loginId, String password, String sessionId);

    /**
     * 用户登陆。
     * @param loginId 账号。
     * @param password 密码。
     * @param sessionId 登陆后的会话ID。
     * @param clientIp 客户端IP
     * @return 登陆成功，返回登陆用户对象；登陆失败将抛出{@link BusinessException}
     */
    ServiceResult<SysUser> userLoginByClientIp(String loginId, String password, String sessionId,
                                               String clientIp);

    /**
     * 用户退出登陆。
     * @param sessionId 登陆后的会话ID。
     * @return
     */
    ServiceResult<Boolean> userLogout(String sessionId);

    ServiceResult<List<SysRole>> findRole(String name, Integer status, PagerInfo pager);

    ServiceResult<SysRole> getRoleById(Integer roleId);

    ServiceResult<Boolean> updateRole(SysRole role, int optUserId);

    ServiceResult<Integer> createRole(SysRole role, int optUserId);

    /**
     * 查找已经分配给用户的角色列表。
     * 不管角色是否禁用、删除，均会返回。只会返回角色ID、角色名称、角色状态，其他字段不会返回。
     * @param userId
     * @return
     */
    ServiceResult<List<SysRole>> findUserRoleAssigned(Integer userId);

    /**
     * 查找尚未分配给用户的角色列表。
     * 只会返回有效状态的角色对象，只返回角色ID、角色名称，其他字段不会返回。
     * @param userId
     * @return
     */
    ServiceResult<List<SysRole>> findUserRoleUnAssigned(Integer userId);

    /**
     * 给用户分配角色。
     * @param userId
     * @param roleIds
     * @return
     */
    ServiceResult<Boolean> assignUserRole(Integer userId, List<Integer> roleIds);

    /**
     * 给用户取消分配角色。
     * @param userId
     * @param roleIds
     * @return
     */
    ServiceResult<Boolean> unassignUserRole(Integer userId, List<Integer> roleIds);

    /**
     * 查询操作列表
     * @param actKey 操作代码，全模糊匹配
     * @param actName 操作名称，全模糊匹配
     * @param remark 备注，全模糊匹配
     * @param status 状态，精确匹配
     * @param pager 分页信息
     * @return
     */
    ServiceResult<List<SysAction>> findAction(String actKey, String actName, String remark,
                                              Integer status, String menuName, PagerInfo pager);

    ServiceResult<SysAction> getActionById(int actId);

    ServiceResult<Boolean> updateAction(SysAction action, int optUserId);

    ServiceResult<Integer> createAction(SysAction action, int optUserId);

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
    ServiceResult<List<PermissionOwner>> findPermissionOwner(Integer type, String name,
                                                             Integer status, PagerInfo pager);

    /**
     * 查询所有的权限资源数据。
     * 1. 模块、菜单、操作将以树状结构返回；
     * 2. 已经分配给权限所有者的资源，{@link PermissionResource#getChecked()}值将设置为true，否则设置范围false；
     * 3. 会返回所有状态的资源（包括有效、禁用、逻辑删除的）；
     * @param ownerId 权限所有者ID，已经分配给该所有者的资源会设置{@link PermissionResource#getChecked()}值。
     * @param ownerType 权限所有者类型，参考{@link SysPermission#OWNER_ROLE}、{@link SysPermission#OWNER_USER}。
     * @param name 查询条件：资源名称，全模糊匹配。
     * @return
     */
    ServiceResult<List<PermissionResource>> findPermissionResourceTree(Integer ownerId,
                                                                       Integer ownerType,
                                                                       String name);

    /**
     * 分配权限。
     * @param ownerId 权限所有者ID。
     * @param ownerType 权限所有者类型。参考{@link SysPermission#OWNER_ROLE}、{@link SysPermission#OWNER_USER}。
     * @param resourceIds 资源ID。
     * 资源ID格式规范：
     * 1. 菜单模块：mdl-资源ID；
     * 2. 菜单项：itm-资源ID；
     * 3. 系统操作：act-资源ID；
     */
    ServiceResult<Boolean> assignPermission(Integer ownerId, Integer ownerType, String[] resourceIds);

    /**
     * URI访问权限校验。
     * @param sessionId 会话ID。
     * @param requestUri 当前请求的URI地址。
     * @return
     */
    ServiceResult<SysSession> checkPermission(String sessionId, String requestUri);

    /**
     * URI访问权限校验。
     * @param sessionId 会话ID。
     * @param requestUri 当前请求的URI地址。
     * @param clientIp 客户端IP
     * @return
     */
    ServiceResult<SysSession> checkPermissionByClientIp(String sessionId, String requestUri,
                                                        String clientIp);

    /**
     * action key访问权限校验。
     * 为实现页面按钮级权限的临时简单方案
     * @param sessionId 会话ID。
     * @param actKey 操作key值。
     * @return ServiceResult<Boolean>
     */
    ServiceResult<Boolean> checkPermissionBySessionIdAndActKey(String sessionId, String actKey);

    /**
     * 创建访问日志。
     * @param accessLog
     * @return
     */
    ServiceResult<Boolean> createAccessLog(SysAccessLog accessLog);

    /**
     * 查询系统日志。
     * @param startTime 开始时间。
     * @param endTime 结束时间。
     * @param userName 用户名。
     * @param visitUrl 访问地址。
     * @param clientIp 客户端IP。
     * @param sessionId 会话ID。
     * @param pager 分页信息。
     * @return
     */
    ServiceResult<List<SysAccessLog>> findAccessLog(Date startTime, Date endTime, String userName,
                                                    String visitUrl, String clientIp,
                                                    String sessionId, PagerInfo pager);

    /**
     * 修改用户密码。
     * @param userId 用户ID。
     * @param oldPassword 旧密码。
     * @param newPassword 新密码。
     * @return
     */
    ServiceResult<Boolean> changePwd(Integer userId, String oldPassword, String newPassword);
}
