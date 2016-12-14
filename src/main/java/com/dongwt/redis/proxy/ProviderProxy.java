package com.dongwt.redis.proxy;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;

import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.api.response.TopicResponse;
import com.dongwt.redis.callback.TopicRequestCallback;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class ProviderProxy extends BaseProxy {

    private static final long serialVersionUID = 1L;

    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Autowired(required = false)
    private TopicRequestCallback callback;

    /**
     * 
     * 功能描述:入队
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     董纹陶:   2016年12月12日      新建
     * </pre>
     *
     * @param request
     * @param handle
     */
    public void lPush(TopicRequest request) {
        byte[] queuekey = keySerializer.serialize(projectName);
        byte[] value = requestSerializer.serialize(request);

        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                //入队
                connection.lPush(queuekey, value);
                return true;
            }
        });
    }

    /**
     * 
     * 功能描述:订阅
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     董纹陶:   2016年12月12日      新建
     * </pre>
     *
     */
    public synchronized void subscribe() {
        executorService.submit(new Runnable() {
            @Override
            public void run() {

                redisTemplate.execute(new RedisCallback<Boolean>() {
                    byte[] channels = keySerializer.serialize(projectName + "CallBack");

                    @Override
                    public Boolean doInRedis(RedisConnection connection) throws DataAccessException {

                        boolean flag = true;
                        while (flag) {
                            List<byte[]> values = connection.bLPop(120, channels);
                            if (null != values) {
                                TopicResponse response = responseSerializer.deserialize(values.get(1));
                                //执行回调
                                if (callback != null) {
                                    callback.onResponseEvent(response);
                                }
                            }
                        }
                        return true;
                    }
                });
            }
        });

    }

}
