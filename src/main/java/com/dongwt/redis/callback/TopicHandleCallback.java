package com.dongwt.redis.callback;

import com.dongwt.redis.api.request.TopicRequest;

public interface TopicHandleCallback {
    
    /**
     * 
     * 功能描述:请求事件
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     董纹陶:   2016年12月14日      新建
     * </pre>
     *
     * @param request
     */
    void onRequestEvent(TopicRequest request);

}
