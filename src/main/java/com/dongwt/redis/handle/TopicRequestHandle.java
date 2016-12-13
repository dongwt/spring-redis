package com.dongwt.redis.handle;

import java.io.Serializable;

import com.dongwt.redis.api.response.TopicResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class TopicRequestHandle<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * 功能描述:回调函数
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     董纹陶:   2016年12月12日      新建
     * </pre>
     *
     * @param response
     */
    public abstract void callBack(TopicResponse<T> response);
}