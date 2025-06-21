package cn.zwz.air.serviceimpl;

import cn.zwz.air.mapper.CheckInMapper;
import cn.zwz.air.entity.CheckIn;
import cn.zwz.air.service.ICheckInService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 登机手续 服务层接口实现
 * @author
 *
 */
@Slf4j
@Service
@Transactional
public class ICheckInServiceImpl extends ServiceImpl<CheckInMapper, CheckIn> implements ICheckInService {

    @Autowired
    private CheckInMapper checkInMapper;
}