package cn.zwz.air.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.air.entity.SecurityIncidents;
import cn.zwz.air.service.ISecurityIncidentsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 
 * 
 */
@Slf4j
@RestController
@Api(tags = "安全事件管理接口")
@RequestMapping("/zwz/securityIncidents")
@Transactional
public class SecurityIncidentsController {

    @Autowired
    private ISecurityIncidentsService iSecurityIncidentsService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条安全事件")
    public Result<SecurityIncidents> get(@RequestParam String id){
        return new ResultUtil<SecurityIncidents>().setData(iSecurityIncidentsService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部安全事件个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iSecurityIncidentsService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部安全事件")
    public Result<List<SecurityIncidents>> getAll(){
        return new ResultUtil<List<SecurityIncidents>>().setData(iSecurityIncidentsService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询安全事件")
    public Result<IPage<SecurityIncidents>> getByPage(@ModelAttribute SecurityIncidents securityIncidents ,@ModelAttribute PageVo page){
        QueryWrapper<SecurityIncidents> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(securityIncidents.getTitle())) {
            qw.like("title",securityIncidents.getTitle());
        }
        if(!ZwzNullUtils.isNull(securityIncidents.getContent())) {
            qw.like("content",securityIncidents.getContent());
        }
        IPage<SecurityIncidents> data = iSecurityIncidentsService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<SecurityIncidents>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改安全事件")
    public Result<SecurityIncidents> saveOrUpdate(SecurityIncidents securityIncidents){
        if(iSecurityIncidentsService.saveOrUpdate(securityIncidents)){
            return new ResultUtil<SecurityIncidents>().setData(securityIncidents);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增安全事件")
    public Result<SecurityIncidents> insert(SecurityIncidents securityIncidents){
        iSecurityIncidentsService.saveOrUpdate(securityIncidents);
        return new ResultUtil<SecurityIncidents>().setData(securityIncidents);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑安全事件")
    public Result<SecurityIncidents> update(SecurityIncidents securityIncidents){
        iSecurityIncidentsService.saveOrUpdate(securityIncidents);
        return new ResultUtil<SecurityIncidents>().setData(securityIncidents);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除安全事件")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iSecurityIncidentsService.removeById(id);
        }
        return ResultUtil.success();
    }
}
