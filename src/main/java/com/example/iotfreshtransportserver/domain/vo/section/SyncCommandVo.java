package com.example.iotfreshtransportserver.domain.vo.section;

import com.example.iotfreshtransportserver.domain.vo.CommandVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyncCommandVo extends CommandVo {
    private Integer isSyncTime;
    private Integer mode;
}
