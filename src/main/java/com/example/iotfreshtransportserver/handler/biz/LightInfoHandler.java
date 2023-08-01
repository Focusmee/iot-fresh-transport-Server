package com.example.iotfreshtransportserver.handler.biz;

import com.alibaba.fastjson.JSONObject;
import com.example.iotfreshtransportserver.domain.entity.LightInfo;
import com.example.iotfreshtransportserver.enums.TopicTypeEnum;
import com.example.iotfreshtransportserver.handler.TopicTypeHandler;
import com.example.iotfreshtransportserver.handler.anno.TopicTypeHandlerAnno;
import com.example.iotfreshtransportserver.service.LightInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@TopicTypeHandlerAnno(TopicTypeEnum.Light)
public class LightInfoHandler extends TopicTypeHandler {
    private final LightInfoService lightInfoService;

    @Autowired
    public LightInfoHandler(LightInfoService lightInfoService) {
        this.lightInfoService = lightInfoService;
    }


    @Override
    public void handler(String messageContent) {
        lightInfoService.save(JSONObject.parseObject(messageContent, LightInfo.class));
    }
}
