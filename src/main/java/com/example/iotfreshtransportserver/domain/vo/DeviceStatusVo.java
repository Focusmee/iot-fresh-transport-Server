package com.example.iotfreshtransportserver.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceStatusVo {
    private Integer id;


    private Integer cabinId;

    private Integer vstatus;
    private LocalDateTime time;
    private String description;
}
