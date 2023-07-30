package com.example.iotfreshtransportserver.controller;

import com.example.iotfreshtransportserver.domain.ResponseResult;
import com.example.iotfreshtransportserver.domain.entity.*;
import com.example.iotfreshtransportserver.service.*;
import javafx.util.Pair;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@RestController
@RequestMapping("/api/transport")
public class TransportController {

    @Autowired
    private TransportCabinService transportCabinService;

    @Autowired
    private TemperatureInfoService temperatureInfoService;

    @Autowired
    private LightInfoService lightInfoService;

    @Autowired
    private DeviceControlService deviceControlService;

    @Autowired
    private DeviceStatusService deviceStatusService;

    /**
     * 获得运输舱列表
     *
     * @return {@link ResponseResult} 所有储运厢信息
     */
    @GetMapping("/list")
    public ResponseResult getTransportCabinList() {
        List<TransportCabin> transportCabinList = transportCabinService.list();
        return ResponseResult.okResult(transportCabinList);
    }

    // 在此处添加各种请求处理方法
    /**
     * 获取储运厢信息
     * @param vid 储运厢编号
     * @return 储运厢信息
     */
    @GetMapping("/{vid}")
    public ResponseResult<TransportCabin> getTransportCabin(@PathVariable("vid") Integer vid) {
        List<TransportCabin> transportCabins = transportCabinService.getTransportCabinByVID(vid);
        return ResponseResult.okResult(transportCabins);
    }

    /**
     * 添加储运厢信息
     * @param vid 储运厢编号
     * @param pid 物品识别号
     */
    @PostMapping("/{vid}")
    public ResponseResult<Void> addTransportCabin(@PathVariable("vid") Integer vid, @RequestParam("pid") Integer pid) {
        transportCabinService.addTransportCabin(vid, pid);
        return ResponseResult.okResult();
    }

    /**
     * 更新储运厢信息
     * @param vid 储运厢编号
     * @param pid 物品识别号
     */
    @PutMapping("/{vid}")
    public ResponseResult<Void> updateTransportCabin(@PathVariable("vid") Integer vid, @RequestParam("pid") Integer pid) {
        transportCabinService.updateTransportCabin(vid, pid);
        return ResponseResult.okResult();
    }

    /**
     * 删除储运厢信息
     * @param vid 储运厢编号
     */
    @DeleteMapping("/{vid}")
    public ResponseResult<Void> deleteTransportCabin(@PathVariable("vid") Integer vid) {
        transportCabinService.deleteTransportCabinByVID(vid);
        return ResponseResult.okResult();
    }

    /**
     * 获取储运厢温度信息
     * @param vid 储运厢编号
     * @return 储运厢温度信息
     */
    @GetMapping("/{vid}/temperature")
    public ResponseResult<TemperatureInfo> getTemperatureInfo(@PathVariable("vid") String vid) {
        List<TemperatureInfo> temperatureInfoByVID = temperatureInfoService.getTemperatureInfoByVID(vid);
        return ResponseResult.okResult(temperatureInfoByVID);
    }

    @GetMapping("/{vid}/temperature/{start}/{end}")
    public ResponseResult<TemperatureInfo> getTemperatureInfoByTime(@PathVariable("vid") String vid,
                                                                    @PathVariable("start") Long start,
                                                                    @PathVariable("end") Long end) {
        Instant instant1 = Instant.ofEpochMilli(start);
        Instant instant2 = Instant.ofEpochMilli(end);
        LocalDateTime startTime =  LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());
        LocalDateTime endTime =  LocalDateTime.ofInstant(instant2, ZoneId.systemDefault());
        if (startTime.isAfter(endTime)) {
            LocalDateTime temp = startTime;
            startTime = endTime;
            endTime = temp;
        }
        List<TemperatureInfo> temperatureInfoByVID = temperatureInfoService.getTemperatureInfoByTime(vid, startTime, endTime);
        return ResponseResult.okResult(temperatureInfoByVID);
    }

    /**
     * 更新储运厢温度信息
     * @param vid 储运厢编号
     * @param tin 储运厢内部实时温度
     * @param tout 储运厢外部实时温度
     * @param tinDH 储运厢内部控制设定的上限温度
     * @param tinDL 储运厢内部控制设定的下限温度
     * @param tg 储运厢内、厢外节能控温设定的可利用温差
     */
    @PutMapping("/{vid}/temperature")
    public ResponseResult<Void> updateTemperatureInfo(@PathVariable("vid") String vid,
                                                      @RequestParam("tin") double tin,
                                                      @RequestParam("tout") double tout,
                                                      @RequestParam("tinDH") double tinDH,
                                                      @RequestParam("tinDL") double tinDL,
                                                      @RequestParam("tg") double tg) {
        temperatureInfoService.updateTemperatureInfo(vid, tin, tout, tinDH, tinDL, tg);
        return ResponseResult.okResult();
    }

    /**
     * 获取储运厢光照信息
     * @param vid 储运厢编号
     * @return 储运厢光照信息
     */
    @GetMapping("/{vid}/light")
    public ResponseResult getLightInfo(@PathVariable("vid") String vid) {
        List<LightInfo> lightInfoByVID = lightInfoService.getLightInfoByVID(vid);
        return ResponseResult.okResult(lightInfoByVID);
    }

    /**
     * 获得光信息
     *
     * @param vid   从视频
     * @param start 开始
     * @param end   结束
     * @return {@link ResponseResult}
     */
    @GetMapping("/{vid}/light/{start}/{end}")
    public ResponseResult getLightInfo(@PathVariable("vid") String vid, @PathVariable("start") Long start, @PathVariable("end") Long end) {
        Pair<LocalDateTime, LocalDateTime> localDateTimeLocalDateTimePair = startAndEnd(start, end);
        LocalDateTime startTime = localDateTimeLocalDateTimePair.getKey();
        LocalDateTime endTime = localDateTimeLocalDateTimePair.getValue();
        List<LightInfo> lightInfoByVID = lightInfoService.getLightInfoByTime(vid, startTime, endTime);
        return ResponseResult.okResult(lightInfoByVID);
    }

    /**
     * 更新储运厢光照信息
     * @param vid 储运厢编号
     * @param lxin 储运厢内实时光照
     * @param lxd 储运厢内设定的光照阈值
     * @param tbegin 储运厢光照控制时间段的起始时间
     * @param tend 储运厢光照控制时间段的终止时间
     */
    @PutMapping("/{vid}/light")
    public ResponseResult<Void> updateLightInfo(@PathVariable("vid") String vid,
                                                @RequestParam("lxin") double lxin,
                                                @RequestParam("lxd") double lxd,
                                                @RequestParam("tbegin") String tbegin,
                                                @RequestParam("tend") String tend) {
        lightInfoService.updateLightInfo(vid, lxin, lxd, tbegin, tend);
        return ResponseResult.okResult();
    }

    /**
     * 获取储运厢设备控制信息
     * @param vid 储运厢编号
     * @return 储运厢设备控制信息
     */
    @GetMapping("/{vid}/control")
    public ResponseResult<DeviceControl> getDeviceControl(@PathVariable("vid") String vid) {
        DeviceControl deviceControl = deviceControlService.getDeviceControlByVID(vid);
        return ResponseResult.okResult(deviceControl);
    }


    /**
     * 更新储运厢设备控制信息
     * @param vid 储运厢编号
     * @param bright 发光器件发光功率控制值
     * @param speedM1 压缩机速度控制值
     * @param speedM2 风机速度控制值
     */
    @PutMapping("/{vid}/control")
    public ResponseResult<Void> updateDeviceControl(@PathVariable("vid") String vid,
                                                    @RequestParam("bright") double bright,
                                                    @RequestParam("speedM1") double speedM1,
                                                    @RequestParam("speedM2") double speedM2) {
        deviceControlService.updateDeviceControl(vid, bright, speedM1, speedM2);
        return ResponseResult.okResult();
    }

    /**
     * 获取储运厢设备状态信息
     * @param vid 储运厢编号
     * @return 储运厢设备状态信息
     */
    @GetMapping("/{vid}/status")
    public ResponseResult<DeviceStatus> getDeviceStatus(@PathVariable("vid") String vid) {
        DeviceStatus deviceStatus = deviceStatusService.getDeviceStatusByVID(vid);
        return ResponseResult.okResult(deviceStatus);
    }

    private Pair<LocalDateTime,LocalDateTime> startAndEnd(Long start, Long end) {
        Instant instant1 = Instant.ofEpochMilli(start);
        Instant instant2 = Instant.ofEpochMilli(end);
        LocalDateTime startTime =  LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());
        LocalDateTime endTime =  LocalDateTime.ofInstant(instant2, ZoneId.systemDefault());
        if (startTime.isAfter(endTime)) {
            LocalDateTime temp = startTime;
            startTime = endTime;
            endTime = temp;
        }
        return new Pair<>(startTime,endTime);
    }
}
