package cn.zwz.air.serviceimpl;

import cn.zwz.air.mapper.SecurityIncidentsMapper;
import cn.zwz.air.entity.SecurityIncidents;
import cn.zwz.air.service.ISecurityIncidentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安全事件 服务层接口实现
 * @author 
 * 
 */
@Slf4j
@Service
@Transactional
public class ISecurityIncidentsServiceImpl extends ServiceImpl<SecurityIncidentsMapper, SecurityIncidents> implements ISecurityIncidentsService {

    @Autowired
    private SecurityIncidentsMapper securityIncidentsMapper;
}