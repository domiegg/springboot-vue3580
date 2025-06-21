package cn.zwz.air.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.air.entity.NaturalResources;
import cn.zwz.air.service.INaturalResourcesService;
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
@Api(tags = "资源管理接口")
@RequestMapping("/zwz/naturalResources")
@Transactional
public class NaturalResourcesController {

    @Autowired
    private INaturalResourcesService iNaturalResourcesService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条资源")
    public Result<NaturalResources> get(@RequestParam String id){
        return new ResultUtil<NaturalResources>().setData(iNaturalResourcesService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部资源个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iNaturalResourcesService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部资源")
    public Result<List<NaturalResources>> getAll(){
        return new ResultUtil<List<NaturalResources>>().setData(iNaturalResourcesService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询资源")
    public Result<IPage<NaturalResources>> getByPage(@ModelAttribute NaturalResources naturalResources ,@ModelAttribute PageVo page){
        QueryWrapper<NaturalResources> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(naturalResources.getTitle())) {
            qw.like("title",naturalResources.getTitle());
        }
        if(!ZwzNullUtils.isNull(naturalResources.getContent())) {
            qw.like("content",naturalResources.getContent());
        }
        IPage<NaturalResources> data = iNaturalResourcesService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<NaturalResources>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改资源")
    public Result<NaturalResources> saveOrUpdate(NaturalResources naturalResources){
        if(iNaturalResourcesService.saveOrUpdate(naturalResources)){
            return new ResultUtil<NaturalResources>().setData(naturalResources);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增资源")
    public Result<NaturalResources> insert(NaturalResources naturalResources){
        iNaturalResourcesService.saveOrUpdate(naturalResources);
        return new ResultUtil<NaturalResources>().setData(naturalResources);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑资源")
    public Result<NaturalResources> update(NaturalResources naturalResources){
        iNaturalResourcesService.saveOrUpdate(naturalResources);
        return new ResultUtil<NaturalResources>().setData(naturalResources);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除资源")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iNaturalResourcesService.removeById(id);
        }
        return ResultUtil.success();
    }
}
