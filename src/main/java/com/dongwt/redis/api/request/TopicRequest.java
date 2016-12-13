package com.dongwt.redis.api.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicRequest<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目名
     */
    private String projectName;

    private String UUID;

    private T body;

    public String getQueueKey() {
        return projectName;
    }

    public String getTopicKey() {
        return projectName + UUID;
    }

}
