package com.djt.cbs.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djt.cbs.system.entity.PermissionOwner;
import com.djt.cbs.system.entity.PermissionResource;
import com.djt.cbs.system.entity.SysPermission;
import com.djt.cbs.system.service.SystemService;
import com.djt.cbs.web.form.PermissionResourceForm;
import com.djt.cbs.web.util.HttpJsonResult;
import com.djt.common.PagerInfo;
import com.djt.common.ServiceResult;
import com.djt.common.util.StringUtil;

@Controller
@RequestMapping("/api/sys/permission")
public class PermissionController {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(PermissionController.class);
    @Resource(name = "systemService")
    SystemService                          systemService;

    @RequestMapping(value = { "/find-owner" }, method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<List<PermissionOwner>> findOwner(@RequestParam(required = true) Integer type,
                                                    @RequestParam(required = false) String name,
                                                    @RequestParam(required = false) Integer status,
                                                    @RequestParam(required = false) Integer pi,
                                                    @RequestParam(required = false) Integer ps) {
        HttpJsonResult<List<PermissionOwner>> result = new HttpJsonResult<List<PermissionOwner>>();
        try {
            PagerInfo pager = null;
            if (pi != null && pi > 0 && ps != null && ps > 0)
                pager = new PagerInfo(ps, pi);
            ServiceResult<List<PermissionOwner>> ownerResult = systemService.findPermissionOwner(
                type, name, status, pager);
            if (ownerResult.getPager() != null)
                result.setTotalCount(ownerResult.getPager().getRowsCount());
            result.setData(ownerResult.getResult());
        } catch (Exception e) {
            log.error("查询权限所有者列表异常", e);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = { "/find-resource-tree" }, method = { RequestMethod.GET })
    public @ResponseBody
    PermissionResourceForm findResource(@RequestParam(required = true) Integer ownerId,
                                        @RequestParam(required = true) Integer ownerType,
                                        @RequestParam(required = false) String name) {
        PermissionResourceForm root = new PermissionResourceForm();
        root.setExpanded(true);
        root.setLeaf(false);
        root.setName("root");
        root.setRid("");
        root.setRtype(0);
        try {
            ServiceResult<List<PermissionResource>> ownerResult = systemService
                .findPermissionResourceTree(ownerId, ownerType, name);
            if (!ownerResult.getSuccess()) {
                return root;
            }
            root.setChildren(this.buildResourceTree(ownerResult.getResult()));
        } catch (Exception e) {
            log.error("查询权限资源列表异常", e);
        }

        return root;
    }

    private List<PermissionResource> buildResourceTree(List<PermissionResource> resources) {
        if (resources == null || resources.isEmpty())
            return null;
        List<PermissionResource> result = new ArrayList<PermissionResource>(resources.size());
        for (PermissionResource res : resources) {
            PermissionResourceForm form = new PermissionResourceForm();
            form.setRid(res.getRid());
            form.setName(res.getName());
            form.setRtype(res.getRtype());
            form.setStatus(res.getStatus());
            form.setChecked(res.getChecked());

            if (res.getChildren() != null && !res.getChildren().isEmpty()) {
                form.setChildren(this.buildResourceTree(res.getChildren()));
                form.setExpanded(true);
                form.setLeaf(false);
            } else {
                form.setExpanded(false);
                form.setLeaf(true);
            }
            if (res.getRtype() == SysPermission.RESOURCE_MENU)
                form.setIconCls("mtree-i");
            else if (res.getRtype() == SysPermission.RESOURCE_ACTION)
                form.setIconCls("mtree-act");
            else
                form.setIconCls("mtree-m");

            result.add(form);
        }
        return result;
    }

    @RequestMapping(value = { "/assign" }, method = { RequestMethod.POST })
    public @ResponseBody
    HttpJsonResult<Boolean> assign(@RequestParam(required = true) Integer ownerId,
                                   @RequestParam(required = true) Integer ownerType,
                                   @RequestParam(required = true) String ids) {
        HttpJsonResult<Boolean> result = new HttpJsonResult<Boolean>();
        try {
            ServiceResult<Boolean> srvResult = this.systemService.assignPermission(ownerId,
                ownerType, StringUtil.isEmpty(ids) ? null : ids.split(","));
            if (!srvResult.getSuccess()) {
                result.setMessage(srvResult.getMessage());
            }
        } catch (Exception e) {
            log.error("[sys][perm]为用户分配权限失败", e);
            result.setMessage("为用户分配权限失败：" + e.getMessage());
        }

        return result;
    }
}