package com.dongwt.redis.dao.read;

import java.util.List;

import com.dongwt.redis.entity.QueueParams;

public interface ConsumerReadMapper {
    
    /**
     * 
     * 功能描述:获取未处理的数据
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     董纹陶:   2016年12月14日      新建
     * </pre>
     *
     * @return
     */
    List<QueueParams> getUnHandleList();
    
}
