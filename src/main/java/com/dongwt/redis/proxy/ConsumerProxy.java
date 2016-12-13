package com.dongwt.redis.proxy;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;

import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.api.response.TopicResponse;
import com.dongwt.redis.handle.TopicResponseHandle;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class ConsumerProxy<T> extends BaseProxy<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * 功能描述:TODO(描述这个方法的作用)
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     Administrator:   2016年12月12日      新建
     * </pre>
     *
     */
    public void bLPop(TopicResponseHandle<T> handle) {
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                boolean flag = true;
                while (flag) {
                    List<byte[]> values = connection.bLPop(120, keySerializer.serialize("projectName"));
                    
                    if(null == values){
                        return false;
                    }
                    
                    TopicRequest<T> request = requestSerializer.deserialize(values.get(1));
                    TopicResponse<T> response = new TopicResponse<T>(request.getUUID(), request.getBody());
                    
                    try {
                        Thread.sleep(1000*5);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //处理逻辑
                    handle.callBack(request);
                    //发布主题
                    connection.publish(keySerializer.serialize("projectName"), responseSerializer.serialize(response));
                }

                return true;
            }
        });
    }

}
