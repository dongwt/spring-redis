package com.dongwt.redis.handle;

import com.dongwt.redis.api.request.TopicRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class TopicResponseHandle<T>{


    /**
     * 
     * 功能描述:回调函数
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     董纹陶:   2016年12月12日      新建
     * </pre>
     *
     * @param request
     */
    public abstract void callBack(TopicRequest<T> request);
}
