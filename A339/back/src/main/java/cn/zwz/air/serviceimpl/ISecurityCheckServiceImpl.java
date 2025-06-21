package cn.zwz.air.serviceimpl;

import cn.zwz.air.mapper.SecurityCheckMapper;
import cn.zwz.air.entity.SecurityCheck;
import cn.zwz.air.service.ISecurityCheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安检 服务层接口实现
 * @author 
 * 
 */
@Slf4j
@Service
@Transactional
public class ISecurityCheckServiceImpl extends ServiceImpl<SecurityCheckMapper, SecurityCheck> implements ISecurityCheckService {

    @Autowired
    private SecurityCheckMapper securityCheckMapper;
}