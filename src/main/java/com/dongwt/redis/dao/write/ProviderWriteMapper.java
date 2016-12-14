package com.dongwt.redis.dao.write;

import com.dongwt.redis.entity.QueueParams;

public interface ProviderWriteMapper {

    /**
     * 
     * 功能描述:保存
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     董纹陶:   2016年12月14日      新建
     * </pre>
     *
     * @param queueParams
     */
    void save(QueueParams queueParams);
}
