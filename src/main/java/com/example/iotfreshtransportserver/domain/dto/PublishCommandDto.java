package com.example.iotfreshtransportserver.domain.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("tinDH")
    private Float TinDH;
    @JsonProperty("tinDL")
    private Float TinDL;
    @JsonProperty("tg")
    private Float TG;
    @JsonProperty("lxd")
    private Float LXD;
    @JsonProperty("bright")
    private Float Bright;
    @JsonProperty("speedM1")
    private Float SpeedM1;
    @JsonProperty("speedM2")
    private Float SpeedM2;
    @JsonProperty("tbegin")
    private Long TBegin;
    @JsonProperty("tend")
    private Long TEnd;
    private Integer isSyncTime;
}