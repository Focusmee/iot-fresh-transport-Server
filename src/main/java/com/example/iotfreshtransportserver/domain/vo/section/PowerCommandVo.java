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
    private Integer Bright;
    private Float SpeedM1;
    private Integer air;
    private Float SpeedM2;
}
