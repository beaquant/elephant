package com.djt.cbs.system.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.djt.cbs.system.entity.SysAccessLog;

public interface SysAccessLogDao {
    int create(SysAccessLog accessLog);

    List<SysAccessLog> find(@Param("startTime") Date startTime, @Param("endTime") Date endTime,
                            @Param("clientIp") String clientIp, @Param("visitUrl") String visitUrl,
                            @Param("userName") String userName,
                            @Param("sessionId") String sessionId, @Param("start") Integer start,
                            @Param("size") Integer size);

    Integer findCount(@Param("startTime") Date startTime, @Param("endTime") Date endTime,
                      @Param("clientIp") String clientIp, @Param("visitUrl") String visitUrl,
                      @Param("userName") String userName, @Param("sessionId") String sessionId);
}