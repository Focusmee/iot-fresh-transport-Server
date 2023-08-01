package com.example.iotfreshtransportserver.domain.entity.command;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("publish_command")
public class PublishCommand {
    Integer id;
    Integer cabinId;
    Integer device_type;
    Float TinDH;
    Float TinDL;
    Float TG;
    Float LXD;
    Integer Bright;
    Float SpeedM1;
    Float SpeedM2;
    LocalDateTime TBegin;
    LocalDateTime TEnd;
    LocalDateTime time;
    Integer mode;
    Integer air;
    Integer isSyncTime; //是否同步时间
}
