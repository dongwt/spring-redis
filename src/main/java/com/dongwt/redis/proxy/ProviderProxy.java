package com.dongwt.redis.proxy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;

import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.api.response.TopicResponse;
import com.dongwt.redis.handle.TopicRequestHandle;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class ProviderProxy<T> extends BaseProxy<T> {

    private static final long serialVersionUID = 1L;

    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    /**
     * 主题处理器列表
     */
    private ConcurrentHashMap<String, TopicRequestHandle<T>> topicHandleMap = new ConcurrentHashMap<String, TopicRequestHandle<T>>();

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
    public void lPush(TopicRequest<T> request, TopicRequestHandle<T> handle) {
        String topickey = projectName + request.getUUID();
        byte[] queuekey = keySerializer.serialize(projectName);
        byte[] value = requestSerializer.serialize(request);

        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                //入队
                connection.lPush(queuekey, value);
                //将回调放入缓存
                topicHandleMap.put(topickey, handle);
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
                    byte[] channels = keySerializer.serialize(projectName);

                    @Override
                    public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                        connection.subscribe(new MessageListener() {
                            @Override
                            public void onMessage(Message message, byte[] pattern) {
                                String channel = keySerializer.deserialize(message.getChannel());
                                TopicResponse<T> response = responseSerializer.deserialize(message.getBody());
                                //执行回调
                                topicHandleMap.get(channel + response.getUUID()).callBack(response);
                            }
                        }, channels);
                        return true;
                    }
                });
            }
        });

    }

}
