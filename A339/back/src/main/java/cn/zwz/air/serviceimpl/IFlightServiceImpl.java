package cn.zwz.air.serviceimpl;

import cn.zwz.air.mapper.FlightMapper;
import cn.zwz.air.entity.Flight;
import cn.zwz.air.service.IFlightService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 航班 服务层接口实现
 * @author 
 * 
 */
@Slf4j
@Service
@Transactional
public class IFlightServiceImpl extends ServiceImpl<FlightMapper, Flight> implements IFlightService {

    @Autowired
    private FlightMapper flightMapper;
}