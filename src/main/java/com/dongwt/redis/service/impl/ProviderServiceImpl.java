package com.dongwt.redis.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.dao.read.ProviderReadMapper;
import com.dongwt.redis.dao.write.ProviderWriteMapper;
import com.dongwt.redis.entity.QueueParams;
import com.dongwt.redis.entity.internal.JdWSWSVoucher;
import com.dongwt.redis.proxy.ProviderProxy;
import com.dongwt.redis.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderProxy providerProxy;

    @Value("${redis.projectName}")
    private String projectName;

    @Autowired
    private ProviderReadMapper providerReadMapper;

    @Autowired
    private ProviderWriteMapper providerWriteMapper;

    @Override
    public void save(QueueParams queueParams) {
        providerWriteMapper.save(queueParams);
    }

    @Override
    public List<QueueParams> getHandleList() {
        return providerReadMapper.getHandleList();
    }

    /**
     * 
     * 功能描述:参数转换
     *
     * <pre>
     * Modify Reason:(修改原因,不需覆盖，直接追加.)
     *     董纹陶:   2016年12月14日      新建
     * </pre>
     *
     * @param jdWSWSVoucher
     * @return
     */
    private QueueParams transformParams(JdWSWSVoucher jdWSWSVoucher) {
        QueueParams queueParams = new QueueParams();

        queueParams.setProjectName(providerProxy.getProjectName());
        queueParams.setVoucherNumber(jdWSWSVoucher.getTableHead().getVoucherNumber());
        queueParams.setOrigin(jdWSWSVoucher.getOrigin());
        queueParams.setRequestBody(JSONObject.toJSONString(jdWSWSVoucher));

        return queueParams;
    }

    @Override
    public void lPush(JdWSWSVoucher jdWSWSVoucher) {

        //参数转换
        QueueParams queueParams = transformParams(jdWSWSVoucher);

        //保存
        save(queueParams);

        //入队
        TopicRequest request = new TopicRequest(UUID.randomUUID().toString(), queueParams);
        providerProxy.lPush(request);

    }

}
