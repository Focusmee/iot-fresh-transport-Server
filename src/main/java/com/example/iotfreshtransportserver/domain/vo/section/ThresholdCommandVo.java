package com.example.iotfreshtransportserver.domain.vo.section;

import com.example.iotfreshtransportserver.domain.vo.CommandVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThresholdCommandVo extends CommandVo {
    private Float TinDH;
    private Float TinDL;
    private Float TG;
    private Float LXD;
}
