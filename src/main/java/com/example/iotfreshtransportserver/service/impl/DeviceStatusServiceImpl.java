package com.example.iotfreshtransportserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotfreshtransportserver.domain.ResponseResult;
import com.example.iotfreshtransportserver.domain.entity.DeviceControl;
import com.example.iotfreshtransportserver.domain.entity.DeviceStatus;
import com.example.iotfreshtransportserver.domain.entity.VstatusDescription;
import com.example.iotfreshtransportserver.domain.vo.DeviceStatusVo;
import com.example.iotfreshtransportserver.domain.vo.PageVo;
import com.example.iotfreshtransportserver.mapper.DeviceStatusMapper;
import com.example.iotfreshtransportserver.service.DeviceStatusService;
import com.example.iotfreshtransportserver.service.VstatusDescriptionService;
import com.example.iotfreshtransportserver.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * (DeviceStatus)表服务实现类
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
@Service("deviceStatusService")
public class DeviceStatusServiceImpl extends ServiceImpl<DeviceStatusMapper, DeviceStatus> implements DeviceStatusService {
    @Autowired
    private VstatusDescriptionService  vstatusDescriptionService;

    public DeviceStatus getNewDeviceStatusByVID(String vid) {
        QueryWrapper<DeviceStatus> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cabinId", vid);
        queryWrapper.orderByAsc("time");
        return getOne(queryWrapper);
    }

    /**
     * 得到了vid异常设备状态列表
     *
     * @param vid       从视频
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNum   页面num
     * @param pageSize  页面大小
     * @return {@link ResponseResult}<{@link PageVo}>
     */
    @Override
    public ResponseResult<PageVo> getAbnormalDeviceStatusListByVID(String vid, LocalDateTime startTime, LocalDateTime endTime, Integer pageNum, Integer pageSize) {
        QueryWrapper<DeviceStatus> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cabinId", vid);
        queryWrapper.between("time", startTime, endTime);
        queryWrapper.orderByDesc("time");
        queryWrapper.ne("vstatus", 0);
        Page<DeviceStatus> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        List<DeviceStatus> records = page.getRecords();
        List<DeviceStatusVo> deviceStatusVos = BeanCopyUtils.copyBeanList(records, DeviceStatusVo.class);
        for (DeviceStatusVo deviceStatusVo : deviceStatusVos) {
            String description = vstatusDescriptionService.getById(deviceStatusVo.getVstatus()).getDescription();
            deviceStatusVo.setDescription(description);
        }
        PageVo pageVo = new PageVo(deviceStatusVos, page.getTotal());
       return ResponseResult.okResult(pageVo);
    }

    @Override
    public void updateNormal(DeviceStatus deviceStatus) {
        QueryWrapper<DeviceStatus> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vstatus",0);
        update(deviceStatus, queryWrapper);
    }
}

/*说明:device_status只存一条状态正常的数据，其他数据都为异常数据，所以在查询异常的时候,排除那个正常数据*/
