package com.djt.cbs.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.djt.cbs.system.entity.SysMenu;

public interface SysMenuDao {
    List<SysMenu> loadMenus(@Param("ids") List<Integer> ids);

    List<SysMenu> findAllMenus();

    int create(SysMenu menu);

    int update(SysMenu menu);
}