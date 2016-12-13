package com.dongwt.redis.proxy;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public void bLPop(TopicResponseHandle<T> handle) {
        
        executorService.submit(new Runnable() {
            
            @Override
            public void run() {
                
                redisTemplate.execute(new RedisCallback<Boolean>() {
                    @Override
                    public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                        boolean flag = true;
                        while (flag) {
                            List<byte[]> values = connection.bLPop(120, keySerializer.serialize(projectName));
                            
                            if(null != values){
                                TopicRequest<T> request = requestSerializer.deserialize(values.get(1));
                                TopicResponse<T> response = new TopicResponse<T>(request.getUUID(), request.getBody());
                                //处理逻辑
                                handle.callBack(request);
                                //发布主题
                                connection.publish(keySerializer.serialize(projectName), responseSerializer.serialize(response));
                            }
                        }
                        return true;
                    }
                });
                
            }
        });
        
       
    }
    
    

}
