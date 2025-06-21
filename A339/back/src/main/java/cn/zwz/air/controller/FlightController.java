package cn.zwz.air.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.air.entity.Flight;
import cn.zwz.air.service.IFlightService;
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
@Api(tags = "航班管理接口")
@RequestMapping("/zwz/flight")
@Transactional
public class FlightController {

    @Autowired
    private IFlightService iFlightService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条航班")
    public Result<Flight> get(@RequestParam String id){
        return new ResultUtil<Flight>().setData(iFlightService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部航班个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iFlightService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部航班")
    public Result<List<Flight>> getAll(){
        return new ResultUtil<List<Flight>>().setData(iFlightService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询航班")
    public Result<IPage<Flight>> getByPage(@ModelAttribute Flight flight ,@ModelAttribute PageVo page){
        QueryWrapper<Flight> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(flight.getTitle())) {
            qw.like("title",flight.getTitle());
        }
        IPage<Flight> data = iFlightService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Flight>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改航班")
    public Result<Flight> saveOrUpdate(Flight flight){
        if(iFlightService.saveOrUpdate(flight)){
            return new ResultUtil<Flight>().setData(flight);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增航班")
    public Result<Flight> insert(Flight flight){
        iFlightService.saveOrUpdate(flight);
        return new ResultUtil<Flight>().setData(flight);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑航班")
    public Result<Flight> update(Flight flight){
        iFlightService.saveOrUpdate(flight);
        return new ResultUtil<Flight>().setData(flight);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除航班")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iFlightService.removeById(id);
        }
        return ResultUtil.success();
    }
}
