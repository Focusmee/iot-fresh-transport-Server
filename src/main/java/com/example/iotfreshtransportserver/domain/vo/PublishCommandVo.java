package com.example.iotfreshtransportserver.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishCommandVo {

    private Integer cabinId;
    private Integer isSyncTime;

    private Float TinDH;
    private Float TinDL;
    private Float TG;
    private Float LXD;

    private Integer device_type;
    private Float Bright;
    private Float SpeedM1;
    private Float SpeedM2;

    private String TBegin;
    private String TEnd;
    private String time;


}