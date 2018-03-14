package com.djt.cbs.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djt.cbs.system.entity.SysMenu;
import com.djt.cbs.system.service.SystemService;
import com.djt.cbs.web.form.MenuItem;
import com.djt.cbs.web.form.MenuItemForm;
import com.djt.cbs.web.form.MenuModuleForm;
import com.djt.cbs.web.util.HttpJsonResult;
import com.djt.cbs.web.util.WebUtil;
import com.djt.common.ServiceResult;

@Controller
@RequestMapping("/api/sys/menu")
public class MenuController {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(MenuController.class);

    @Resource(name = "systemService")
    SystemService                          systemService;

    @RequestMapping(value = { "/find/my" }, method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<List<MenuModuleForm>> loadUserMenu(HttpServletRequest request) {
        HttpJsonResult<List<MenuModuleForm>> result = new HttpJsonResult<List<MenuModuleForm>>();
        try {
            //TODO: 取登陆用户信息
            ServiceResult<List<SysMenu>> menuResult = systemService.loadUserMenu(WebUtil
                .sessionId(request));
            if (!menuResult.getSuccess()) {
                result.setMessage(menuResult.getMessage());
                return result;
            }
            List<MenuModuleForm> form = new ArrayList<MenuModuleForm>(
                menuResult.getResult() == null ? 0 : menuResult.getResult().size());
            for (SysMenu module : menuResult.getResult()) {
                if (module.getChildren() == null || module.getChildren().isEmpty())
                    continue;
                MenuModuleForm mm = new MenuModuleForm();
                mm.setMmid(module.getMenuId());
                mm.setTitle(module.getMenuName());
                mm.setItems(new ArrayList<MenuItemForm>(module.getChildren().size()));
                for (SysMenu menuItem : module.getChildren()) {
                    MenuItemForm mi = new MenuItemForm();
                    mi.setMiid(menuItem.getMenuId());
                    mi.setTitle(menuItem.getMenuName());
                    mi.setType(menuItem.getMenuItemType());
                    mi.setData(menuItem.getMenuItemData());
                    mm.getItems().add(mi);
                }
                form.add(mm);
            }
            result.setData(form);
        } catch (Exception e) {
            log.error("加载用户菜单异常", e);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = { "/find/menutree" }, method = { RequestMethod.GET })
    public @ResponseBody
    MenuItem findMenuTree() {
        //HttpJsonResult<MenuTreeRoot> result = new HttpJsonResult<MenuTreeRoot>();
        MenuItem root = new MenuItem();
        try {
            ServiceResult<List<SysMenu>> menuResult = systemService.findMenuTree();
            if (!menuResult.getSuccess()) {
                //result.setMessage(menuResult.getMessage());
                return root;
            }
            root.setName("ROOT");
            root.setStatus(1);
            root.setMdata("");
            root.setPid(0);
            root.setMid(0);
            root.setExpanded(true);
            root.setLeaf(false);
            root.setMtype("root");
            root.setChildren(this.buildMenuItem(menuResult.getResult()));
            //result.setData(root);
        } catch (Exception e) {
            log.error("加载用户菜单异常", e);
            //result.setMessage(e.getMessage());
        }

        return root;
    }

    private List<MenuItem> buildMenuItem(List<SysMenu> menus) {
        if (menus == null || menus.isEmpty())
            return null;
        List<MenuItem> result = new ArrayList<MenuItem>();
        for (SysMenu menu : menus) {
            MenuItem mi = new MenuItem();
            mi.setMid(menu.getMenuId());
            mi.setPid(menu.getParentId());
            mi.setName(menu.getMenuName());
            mi.setMtype(menu.getMenuItemType());
            mi.setMdata(menu.getMenuItemData());
            mi.setStatus(menu.getStatus());
            mi.setOrderBy(menu.getOrderBy());
            mi.setUpdateTime(menu.getUpdateTime());
            mi.setUpdateUser(menu.getUpdateUser());

            if (menu.getChildren() != null && !menu.getChildren().isEmpty()) {
                mi.setChildren(this.buildMenuItem(menu.getChildren()));
                mi.setLeaf(false);
                mi.setExpanded(true);
            } else {
                mi.setLeaf(true);
                mi.setExpanded(false);
            }

            if (SysMenu.MENU_MODULE.equals(menu.getMenuItemType()))
                mi.setIconCls("mtree-m");
            else
                mi.setIconCls("mtree-i");
            result.add(mi);
        }
        return result;
    }

    @RequestMapping(value = { "/update" }, method = { RequestMethod.POST })
    public @ResponseBody
    HttpJsonResult<Boolean> updateUser(MenuItem mi) {
        try {
            //TODO: 取当前操作用户ID
            SysMenu menu = new SysMenu();
            menu.setMenuId(mi.getMid());
            menu.setParentId(mi.getPid());
            menu.setMenuItemData(mi.getMdata());
            menu.setMenuItemType(mi.getMtype());
            menu.setMenuName(mi.getName());
            menu.setOrderBy(mi.getOrderBy());
            menu.setStatus(mi.getStatus());
            ServiceResult<Boolean> result = this.systemService.updateMenu(menu);
            if (!result.getSuccess())
                return new HttpJsonResult<Boolean>(result.getMessage());
            return new HttpJsonResult<Boolean>(result.getResult());
        } catch (Exception e) {
            log.error("更新菜单异常", e);
            return new HttpJsonResult<Boolean>(e.getMessage());
        }
    }

    @RequestMapping(value = { "/create" }, method = { RequestMethod.POST })
    public @ResponseBody
    HttpJsonResult<Boolean> createUser(MenuItem mi) {
        try {
            //TODO: 取当前操作用户ID
            SysMenu menu = new SysMenu();
            menu.setMenuItemData(mi.getMdata());
            menu.setMenuItemType(mi.getMtype());
            menu.setMenuName(mi.getName());
            menu.setOrderBy(mi.getOrderBy());
            menu.setParentId(mi.getPid());
            menu.setStatus(mi.getStatus());
            ServiceResult<Integer> srvResult = this.systemService.createMenu(menu);
            if (!srvResult.getSuccess())
                return new HttpJsonResult<Boolean>(srvResult.getMessage());
            return new HttpJsonResult<Boolean>(true);
        } catch (Exception e) {
            log.error("创建菜单异常", e);
            return new HttpJsonResult<Boolean>(e.getMessage());
        }
    }
}