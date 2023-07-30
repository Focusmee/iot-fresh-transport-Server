package com.example.iotfreshtransportserver.controller;

import com.alibaba.fastjson.JSON;
import com.example.iotfreshtransportserver.domain.ResponseResult;
import com.example.iotfreshtransportserver.domain.command.PublishCommand;
import com.example.iotfreshtransportserver.domain.dto.PublishCommandDto;
import com.example.iotfreshtransportserver.domain.vo.*;
import com.example.iotfreshtransportserver.domain.vo.section.PowerCommandVo;
import com.example.iotfreshtransportserver.domain.vo.section.SyncCommandVo;
import com.example.iotfreshtransportserver.domain.vo.section.ThresholdCommandVo;
import com.example.iotfreshtransportserver.domain.vo.section.TimeCommandVo;
import com.example.iotfreshtransportserver.mqtt.MqttMessageService;
import com.example.iotfreshtransportserver.service.PublishCommandService;
import com.example.iotfreshtransportserver.utils.BeanCopyUtils;
import com.example.iotfreshtransportserver.utils.DateUtils;
import javafx.util.Pair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/publish")
public class PublishController {

    private final MqttMessageService mqttMessageService;

    private final PublishCommandService publishCommandService;
    private static List<Pair<String, Class>> topicAndMessage ;

    @Autowired
    public PublishController(MqttMessageService mqttMessageService, PublishCommandService publishCommandService, List<Pair<String, Class>> topicAndMessage) {
        this.mqttMessageService = mqttMessageService;
        this.publishCommandService = publishCommandService;
        this.topicAndMessage = topicAndMessage;
        topicAndMessage.add(new Pair<String, Class>("mqtt/control/sync", SyncCommandVo.class));
        topicAndMessage.add(new Pair<String, Class>("mqtt/control/threshold", ThresholdCommandVo.class));
        topicAndMessage.add(new Pair<String, Class>("mqtt/control/power", PowerCommandVo.class));
        topicAndMessage.add(new Pair<String, Class>("mqtt/control/time", TimeCommandVo.class));
    }


    /**
     * mqtt发布
     * 注意这里不能直接@RequstBody PublishCommandDto publishCommandDto 前端传的有float和long不能直接转换注入到参数中
     * @param jsonString json字符串
     * @return {@link ResponseResult}
     */
    @PostMapping("/parameters")
    public ResponseResult publishMqtt(@RequestBody String jsonString) {
        System.out.println(jsonString);
        PublishCommandDto publishCommandDto = JSON.parseObject(jsonString, PublishCommandDto.class);
        PublishCommand publishCommand = new PublishCommand();
        BeanUtils.copyProperties(publishCommandDto,publishCommand);
        publishCommand.setTime(LocalDateTime.now());
        publishCommand.setTBegin(DateUtils.longToLocalDateTime(publishCommandDto.getTBegin()));
        publishCommand.setTEnd(DateUtils.longToLocalDateTime(publishCommandDto.getTEnd()));
        //格式化一下
//        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        PublishCommandVo publishCommandVo = new PublishCommandVo();
        BeanUtils.copyProperties(publishCommandDto,publishCommandVo);
        publishCommandVo.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        publishCommandVo.setTBegin(publishCommand.getTBegin().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        publishCommandVo.setTEnd(publishCommand.getTEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        //分四部分发送
        for (Pair<String, Class> stringCommandVoPair : topicAndMessage) {
            CommandVo o = (CommandVo)BeanCopyUtils.copyBean(publishCommandVo, stringCommandVoPair.getValue());
            mqttMessageService.publish(stringCommandVoPair.getKey(), o);
        }
        publishCommandService.save(publishCommand);//保存命令
        return ResponseResult.okResult();
    }


    @GetMapping("/parameters/{vid}")
    public ResponseResult getControlParameters(@PathVariable Integer vid) {
        PublishCommand byId = publishCommandService.getNewByVid(vid);
        PublishCommandDto publishCommandDto = BeanCopyUtils.copyBean(byId, PublishCommandDto.class);
        publishCommandDto.setTBegin(byId.getTBegin().toEpochSecond(ZoneOffset.of("+8")));
        publishCommandDto.setTEnd(byId.getTEnd().toEpochSecond(ZoneOffset.of("+8")));
        return ResponseResult.okResult(publishCommandDto);
    }
}
