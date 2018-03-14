package com.djt.cbs.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djt.cbs.system.entity.SysRole;
import com.djt.cbs.system.service.SystemService;
import com.djt.cbs.web.util.HttpJsonResult;
import com.djt.common.PagerInfo;
import com.djt.common.ServiceResult;
import com.djt.common.util.ConvertUtil;
import com.djt.common.util.StringUtil;

/**
 * 系统管理功能
 */
@Controller
@RequestMapping("/api/sys/role")
public class RoleController {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(RoleController.class);
    @Resource(name = "systemService")
    SystemService                          systemService;

    @RequestMapping(value = { "/find" }, method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<List<SysRole>> findRole(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) Integer status,
                                           @RequestParam(required = false) Integer pi,
                                           @RequestParam(required = false) Integer ps) {
        HttpJsonResult<List<SysRole>> result = new HttpJsonResult<List<SysRole>>();
        try {
            PagerInfo pager = null;
            if (pi != null && pi > 0 && ps != null && ps > 0)
                pager = new PagerInfo(ps, pi);
            ServiceResult<List<SysRole>> roleResult = systemService.findRole(name, status, pager);
            if (roleResult.getPager() != null)
                result.setTotalCount(roleResult.getPager().getRowsCount());
            result.setData(roleResult.getResult());
        } catch (Exception e) {
            log.error("查询角色列表异常", e);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    private HttpJsonResult<SysRole> internalGetRole(Integer roleId) {
        try {
            //取用户资料
            ServiceResult<SysRole> serviceResult = systemService.getRoleById(roleId);
            if (!serviceResult.getSuccess())
                return new HttpJsonResult<SysRole>(serviceResult.getMessage());

            return new HttpJsonResult<SysRole>(serviceResult.getResult());
        } catch (Exception e) {
            log.error("获取角色异常:", e);
            return new HttpJsonResult<SysRole>(e.getMessage());
        }
    }

    @RequestMapping(value = { "/get/{roleId}" }, method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<SysRole> getRole(@PathVariable Integer roleId) {
        return this.internalGetRole(roleId);
    }

    @RequestMapping(value = { "/update" }, method = { RequestMethod.POST })
    public @ResponseBody
    HttpJsonResult<Boolean> updateRole(SysRole role) {
        try {
            //TODO: 取当前操作用户ID
            ServiceResult<Boolean> result = this.systemService.updateRole(role, 15);
            if (!result.getSuccess())
                return new HttpJsonResult<Boolean>(result.getMessage());
            return new HttpJsonResult<Boolean>(result.getResult());
        } catch (Exception e) {
            log.error("更新角色异常", e);
            return new HttpJsonResult<Boolean>(e.getMessage());
        }
    }

    @RequestMapping(value = { "/create" }, method = { RequestMethod.POST })
    public @ResponseBody
    HttpJsonResult<SysRole> createRole(SysRole role) {
        try {
            //TODO: 取当前操作用户ID
            ServiceResult<Integer> result = this.systemService.createRole(role, 12);
            if (!result.getSuccess())
                return new HttpJsonResult<SysRole>(result.getMessage());
            return this.internalGetRole(result.getResult());
        } catch (Exception e) {
            log.error("创建角色异常", e);
            return new HttpJsonResult<SysRole>(e.getMessage());
        }
    }

    @RequestMapping(value = { "/user/assigned" }, method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<List<SysRole>> findUserRoleAssigned(@RequestParam(required = true) Integer userId) {
        HttpJsonResult<List<SysRole>> result = new HttpJsonResult<List<SysRole>>();
        try {
            ServiceResult<List<SysRole>> roleResult = systemService.findUserRoleAssigned(userId);
            result.setData(roleResult.getResult());
        } catch (Exception e) {
            log.error("查询用户已分配角色列表异常", e);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = { "/user/unassigned" }, method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<List<SysRole>> findUserRoleUnAssigned(@RequestParam(required = true) Integer userId) {
        HttpJsonResult<List<SysRole>> result = new HttpJsonResult<List<SysRole>>();
        try {
            ServiceResult<List<SysRole>> roleResult = systemService.findUserRoleUnAssigned(userId);
            result.setData(roleResult.getResult());
        } catch (Exception e) {
            log.error("查询用户未分配角色列表异常", e);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = { "/user/assign" }, method = { RequestMethod.POST })
    public @ResponseBody
    HttpJsonResult<Boolean> assignUserRole(@RequestParam(required = true) Integer userId,
                                           @RequestParam(required = true) String roleIds) {
        HttpJsonResult<Boolean> result = new HttpJsonResult<Boolean>();
        try {
            List<Integer> roleList = new ArrayList<Integer>();
            if (StringUtil.isEmpty(roleIds, true)) {
                result.setMessage("角色ID列表为空");
                return result;
            }
            for (String id : roleIds.split(",")) {
                Integer value = ConvertUtil.toInt(id, 0);
                if (value == null || value <= 0)
                    continue;
                roleList.add(value);
            }
            if (roleList.isEmpty()) {
                result.setMessage("无效的角色ID列表：" + roleIds);
                return result;
            }
            ServiceResult<Boolean> assignResult = systemService.assignUserRole(userId, roleList);
            if (assignResult.getSuccess())
                result.setData(assignResult.getResult());
            else
                result.setMessage(assignResult.getMessage());
        } catch (Exception e) {
            log.error("分配用户角色异常", e);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = { "/user/unassign" }, method = { RequestMethod.POST })
    public @ResponseBody
    HttpJsonResult<Boolean> unassignUserRole(@RequestParam(required = true) Integer userId,
                                             @RequestParam(required = true) String roleIds) {
        HttpJsonResult<Boolean> result = new HttpJsonResult<Boolean>();
        try {
            List<Integer> roleList = new ArrayList<Integer>();
            if (StringUtil.isEmpty(roleIds, true)) {
                result.setMessage("角色ID列表为空");
                return result;
            }
            for (String id : roleIds.split(",")) {
                Integer value = ConvertUtil.toInt(id, 0);
                if (value == null || value <= 0)
                    continue;
                roleList.add(value);
            }
            if (roleList.isEmpty()) {
                result.setMessage("无效的角色ID列表：" + roleIds);
                return result;
            }
            ServiceResult<Boolean> assignResult = systemService.unassignUserRole(userId, roleList);
            if (assignResult.getSuccess())
                result.setData(assignResult.getResult());
            else
                result.setMessage(assignResult.getMessage());
        } catch (Exception e) {
            log.error("取消分配用户角色异常", e);
            result.setMessage(e.getMessage());
        }

        return result;
    }
}