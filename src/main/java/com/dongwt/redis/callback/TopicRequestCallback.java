package com.dongwt.redis.callback;

import com.dongwt.redis.api.response.TopicResponse;

public interface TopicRequestCallback {
    
    /**
     * 
     * 功能描述:响应事件
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     董纹陶:   2016年12月14日      新建
     * </pre>
     *
     * @param response
     */
    void onResponseEvent(TopicResponse response);

}
