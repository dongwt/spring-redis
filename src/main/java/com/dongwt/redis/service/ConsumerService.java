package com.dongwt.redis.service;

import java.util.List;

import com.dongwt.redis.entity.QueueParams;

/**
 * 
 * Function: 队列参数服务
 *
 * @author   董纹陶
 * @Date	 2016年12月14日		下午4:01:08
 *
 * @see
 */
public interface ConsumerService {
    
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
    
    
    /**
     * 
     * 功能描述:更新
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     董纹陶:   2016年12月14日      新建
     * </pre>
     *
     * @param queueParams
     */
    void  update(QueueParams queueParams);
    
    
    
    

}
