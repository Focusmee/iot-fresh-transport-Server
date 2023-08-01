package com.example.iotfreshtransportserver.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.core.annotation.AliasFor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceivedDataDto {
    Integer cabinId;
    @JsonProperty("VID")
    Integer vid;

    @JsonProperty("PID")
    Integer pid;
    @JsonProperty("Tin")
    Double tin;
    @JsonProperty("Tout")
    Double tout;
    @JsonProperty("LXin")
    Double lxin;
    @JsonProperty("VStatus")
    Integer vstatus;
    @JsonProperty("Time")
    LocalDateTime time;
}
