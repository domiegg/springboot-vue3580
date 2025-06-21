package cn.zwz.air.serviceimpl;

import cn.zwz.air.mapper.NaturalResourcesMapper;
import cn.zwz.air.entity.NaturalResources;
import cn.zwz.air.service.INaturalResourcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资源 服务层接口实现
 * @author
 *
 */
@Slf4j
@Service
@Transactional
public class INaturalResourcesServiceImpl extends ServiceImpl<NaturalResourcesMapper, NaturalResources> implements INaturalResourcesService {

    @Autowired
    private NaturalResourcesMapper naturalResourcesMapper;
}