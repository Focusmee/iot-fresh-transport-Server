package com.example.iotfreshtransportserver.controller;

import com.example.iotfreshtransportserver.domain.ResponseResult;
import com.example.iotfreshtransportserver.domain.command.RemoteControlCommand;
import com.example.iotfreshtransportserver.mqtt.MqttMessageService;
import com.example.iotfreshtransportserver.service.RemoteControlCommandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device/command")
public class CommandController {

    private final RemoteControlCommandService remoteControlCommandService;

    private final MqttMessageService mqttMessageService;

    private static String topic ;

    public CommandController(RemoteControlCommandService remoteControlCommandService, MqttMessageService mqttMessageService) {
        this.remoteControlCommandService = remoteControlCommandService;
        this.mqttMessageService = mqttMessageService;
        this.topic = "mqtt/control/command";
    }

    /**
     * 列表命令
     *
     * @return {@link ResponseResult}
     */
    @GetMapping("/list")
    public ResponseResult listCommand() {
        return ResponseResult.okResult(remoteControlCommandService.list());
    }

    /**
     * 发送命令
     *
     * @param remoteControlCommand 远程控制命令
     * @return {@link ResponseResult}
     *//*发送指令到MQTT服务器上*/
    @PostMapping("/send")
    public ResponseResult sendCommand(@RequestBody RemoteControlCommand remoteControlCommand) {
        mqttMessageService.publish(topic,remoteControlCommand);
        return ResponseResult.okResult();
    }

    /**
     * 添加命令
     *
     * @param remoteControlCommand 远程控制命令
     * @return {@link ResponseResult}
     */
    @PostMapping("/add")
    public ResponseResult addCommand(@RequestBody RemoteControlCommand remoteControlCommand) {
        remoteControlCommandService.save(remoteControlCommand);
        return ResponseResult.okResult();
    }

    /**
     * 删除命令
     *
     * @param id id
     * @return {@link ResponseResult}
     */
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteCommand(@PathVariable("id") Integer id) {
        remoteControlCommandService.removeById(id);
        return ResponseResult.okResult();
    }

    @PutMapping("/update")
    public ResponseResult updateCommand(@RequestBody RemoteControlCommand remoteControlCommand) {
        remoteControlCommandService.updateById(remoteControlCommand);
        return ResponseResult.okResult();
    }
}
