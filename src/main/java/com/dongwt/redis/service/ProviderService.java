package com.dongwt.redis.service;

import java.util.List;

import com.dongwt.redis.api.response.Response;
import com.dongwt.redis.entity.QueueParams;
import com.dongwt.redis.entity.internal.JdWSWSVoucher;

public interface ProviderService {

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

    /**
     * 
     * 功能描述:获取处理的数据
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     董纹陶:   2016年12月14日      新建
     * </pre>
     *
     * @return
     */
    List<QueueParams> getHandleList();

    /**
     * 
     * 功能描述:入队
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     董纹陶:   2016年12月14日      新建
     * </pre>
     *
     * @param jdWSWSVoucher
     */
    Response<String> lPush(JdWSWSVoucher jdWSWSVoucher);

}
