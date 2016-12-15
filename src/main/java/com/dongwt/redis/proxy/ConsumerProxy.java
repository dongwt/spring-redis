package com.dongwt.redis.proxy;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.api.response.TopicResponse;
import com.dongwt.redis.callback.impl.TopicHandleCallbackImpl;
import com.dongwt.redis.entity.QueueParams;
import com.dongwt.redis.service.ConsumerService;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class ConsumerProxy extends BaseProxy {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ConsumerService consumerService;

    @Autowired(required=false)
    private TopicHandleCallbackImpl topicHandleCallback;

    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    /**
     * 
     * 功能描述:拉取消息
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     董纹陶:   2016年12月12日      新建
     * </pre>
     *
     */
    public void bLPop() {

        executorService.submit(new Runnable() {

            @Override
            public void run() {

                redisTemplate.execute(new RedisCallback<Boolean>() {
                    @Override
                    public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                        boolean flag = true;
                        while (flag) {
                            List<byte[]> values = connection.bLPop(120, keySerializer.serialize(projectName));

                            if (null != values) {
                                TopicRequest request = requestSerializer.deserialize(values.get(1));
                                TopicResponse response = new TopicResponse(request.getUUID(), request.getBody());
                                //处理逻辑
                                topicHandleCallback.onRequestEvent(request);

                                //更新数据库
                                QueueParams queueParams = request.getBody();
                                queueParams.setHandleStatus(1);
                                queueParams.setResponseBody(JSONObject.toJSONString(response));
                                consumerService.update(queueParams);
                                //发布主题
                                connection.lPush(keySerializer.serialize(projectName + "CallBack"), responseSerializer.serialize(response));
                            }
                        }
                        return true;
                    }
                });

            }
        });

    }

}
