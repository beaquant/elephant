package com.djt.cbs.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.djt.cbs.system.entity.SysPermission;

public interface SysPermissionDao {
    List<SysPermission> find(@Param("ownerType") Integer ownerType,
                             @Param("ownerIds") List<Integer> ownerIds,
                             @Param("resType") Integer resType,
                             @Param("resIds") List<Integer> resIds);

    List<Integer> findResourceIdsByOwnerId(@Param("ownerType") int ownerType,
                                           @Param("ownerId") int ownerId,
                                           @Param("resType") int resourceType);

    List<Integer> findResourceIdsByOwnerIds(@Param("ownerType") int ownerType,
                                            @Param("ownerIds") List<Integer> ownerIds,
                                            @Param("resType") int resourceType);

    int update(SysPermission perm);

    void create(SysPermission perm);
}