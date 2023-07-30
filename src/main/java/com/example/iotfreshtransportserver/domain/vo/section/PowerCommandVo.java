package com.example.iotfreshtransportserver.domain.vo.section;

import com.example.iotfreshtransportserver.domain.vo.CommandVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerCommandVo extends CommandVo {
    private Integer device_type;
    private Float Bright;
    private Float SpeedM1;
    private Float SpeedM2;
}
