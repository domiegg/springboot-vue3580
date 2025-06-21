package cn.zwz.air.serviceimpl;

import cn.zwz.air.mapper.LuggageMapper;
import cn.zwz.air.entity.Luggage;
import cn.zwz.air.service.ILuggageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 行李 服务层接口实现
 * @author 
 * 
 */
@Slf4j
@Service
@Transactional
public class ILuggageServiceImpl extends ServiceImpl<LuggageMapper, Luggage> implements ILuggageService {

    @Autowired
    private LuggageMapper luggageMapper;
}