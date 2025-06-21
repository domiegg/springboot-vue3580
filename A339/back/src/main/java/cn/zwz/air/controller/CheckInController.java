package cn.zwz.air.controller;

import cn.zwz.air.entity.Flight;
import cn.zwz.air.service.IFlightService;
import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.service.IUserService;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.air.entity.CheckIn;
import cn.zwz.air.service.ICheckInService;
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
@Api(tags = "登机手续管理接口")
@RequestMapping("/zwz/checkIn")
@Transactional
public class CheckInController {

    @Autowired
    private ICheckInService iCheckInService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IFlightService iFlightService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条登机手续")
    public Result<CheckIn> get(@RequestParam String id){
        return new ResultUtil<CheckIn>().setData(iCheckInService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部登机手续个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iCheckInService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部登机手续")
    public Result<List<CheckIn>> getAll(){
        return new ResultUtil<List<CheckIn>>().setData(iCheckInService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询登机手续")
    public Result<IPage<CheckIn>> getByPage(@ModelAttribute CheckIn checkIn ,@ModelAttribute PageVo page){
        QueryWrapper<CheckIn> qw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        QueryWrapper<User> userQw = new QueryWrapper<>();
        userQw.eq("id",currUser.getId());
        userQw.inSql("id","SELECT user_id FROM a_user_role WHERE del_flag = 0 AND role_id = '1536606659751841799'");
        if(iUserService.count(userQw) < 1L) {
            qw.eq("user_id",currUser.getId());
        }
        if(!ZwzNullUtils.isNull(checkIn.getUserName())) {
            qw.like("user_name",checkIn.getUserName());
        }
        if(!ZwzNullUtils.isNull(checkIn.getLuggage())) {
            qw.like("luggage",checkIn.getLuggage());
        }
        IPage<CheckIn> data = iCheckInService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<CheckIn>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改登机手续")
    public Result<CheckIn> saveOrUpdate(CheckIn checkIn){
        if(iCheckInService.saveOrUpdate(checkIn)){
            return new ResultUtil<CheckIn>().setData(checkIn);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增登机手续")
    public Result<CheckIn> insert(CheckIn checkIn){
        User user = iUserService.getById(checkIn.getUserId());
        if(user == null) {
            return ResultUtil.error("乘客不存在");
        }
        checkIn.setUserName(user.getNickname());
        QueryWrapper<Flight> qw = new QueryWrapper<>();
        qw.eq("title",checkIn.getLuggage());
        qw.last("limit 1");
        Flight flight = iFlightService.getOne(qw);
        if(flight == null) {
            return ResultUtil.error("航班不存在");
        }
        checkIn.setJi1(flight.getJi1());
        checkIn.setTime1(flight.getTime1());
        iCheckInService.saveOrUpdate(checkIn);
        return new ResultUtil<CheckIn>().setData(checkIn);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑登机手续")
    public Result<CheckIn> update(CheckIn checkIn){
        User user = iUserService.getById(checkIn.getUserId());
        if(user == null) {
            return ResultUtil.error("乘客不存在");
        }
        checkIn.setUserName(user.getNickname());
        QueryWrapper<Flight> qw = new QueryWrapper<>();
        qw.eq("title",checkIn.getLuggage());
        qw.last("limit 1");
        Flight flight = iFlightService.getOne(qw);
        if(flight == null) {
            return ResultUtil.error("航班不存在");
        }
        checkIn.setJi1(flight.getJi1());
        checkIn.setTime1(flight.getTime1());
        iCheckInService.saveOrUpdate(checkIn);
        return new ResultUtil<CheckIn>().setData(checkIn);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除登机手续")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iCheckInService.removeById(id);
        }
        return ResultUtil.success();
    }
}
