package com.example.iotfreshtransportserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.iotfreshtransportserver.domain.entity.TemperatureInfo;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.List;


/**
 * (TemperatureInfo)表服务接口
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
public interface TemperatureInfoService extends IService<TemperatureInfo> {

    void updateTemperatureInfo(String vid, double tin, double tout, double tinDH, double tinDL, double tg);

    List<TemperatureInfo> getTemperatureInfoByVID(String vid);

    List<TemperatureInfo> getNewList(Integer vid,Integer size);

    List<TemperatureInfo> getTemperatureInfoByTime(String vid, LocalDateTime start, LocalDateTime end);
}

