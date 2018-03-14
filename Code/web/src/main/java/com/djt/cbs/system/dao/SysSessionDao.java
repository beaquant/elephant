package com.djt.cbs.system.dao;

import com.djt.cbs.system.entity.SysSession;

public interface SysSessionDao {
    int create(SysSession session);

    int update(SysSession session);

    SysSession get(String sessionId);

    int delete(String sessionId);
}