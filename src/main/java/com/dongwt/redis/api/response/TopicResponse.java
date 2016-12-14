package com.dongwt.redis.api.response;

import java.io.Serializable;

import com.dongwt.redis.entity.QueueParams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String UUID;

    private QueueParams body;

}
