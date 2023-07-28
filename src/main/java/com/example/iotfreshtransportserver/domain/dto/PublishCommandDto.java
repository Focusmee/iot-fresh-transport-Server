package com.example.iotfreshtransportserver.domain.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishCommandDto {
    @JsonIgnore
    private Integer id;
    private Integer cabinId;
    private Integer device_type;
    private Integer command_type;
    private Float TinDH;
    private Float TinDL;
    private Float TG;
    private Float LXD;
    private Float Bright;
    private Float SpeedM1;
    private Float SpeedM2;
    private Long TBegin;
    private Long TEnd;
    private Integer isSyncTime;
}