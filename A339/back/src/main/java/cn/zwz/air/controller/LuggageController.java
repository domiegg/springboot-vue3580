package cn.zwz.air.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.service.IUserService;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.air.entity.Luggage;
import cn.zwz.air.service.ILuggageService;
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
@Api(tags = "行李管理接口")
@RequestMapping("/zwz/luggage")
@Transactional
public class LuggageController {

    @Autowired
    private ILuggageService iLuggageService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条行李")
    public Result<Luggage> get(@RequestParam String id){
        return new ResultUtil<Luggage>().setData(iLuggageService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部行李个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iLuggageService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部行李")
    public Result<List<Luggage>> getAll(){
        return new ResultUtil<List<Luggage>>().setData(iLuggageService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询行李")
    public Result<IPage<Luggage>> getByPage(@ModelAttribute Luggage luggage ,@ModelAttribute PageVo page){
        QueryWrapper<Luggage> qw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        QueryWrapper<User> userQw = new QueryWrapper<>();
        userQw.eq("id",currUser.getId());
        userQw.inSql("id","SELECT user_id FROM a_user_role WHERE del_flag = 0 AND role_id = '1536606659751841799'");
        if(iUserService.count(userQw) < 1L) {
            qw.eq("user_id",currUser.getId());
        }
        if(!ZwzNullUtils.isNull(luggage.getUserName())) {
            qw.like("user_name",luggage.getUserName());
        }
        if(!ZwzNullUtils.isNull(luggage.getContent())) {
            qw.like("content",luggage.getContent());
        }
        IPage<Luggage> data = iLuggageService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Luggage>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改行李")
    public Result<Luggage> saveOrUpdate(Luggage luggage){
        if(iLuggageService.saveOrUpdate(luggage)){
            return new ResultUtil<Luggage>().setData(luggage);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增行李")
    public Result<Luggage> insert(Luggage luggage){
        User user = iUserService.getById(luggage.getUserId());
        if(user == null) {
            return ResultUtil.error("乘客不存在");
        }
        luggage.setUserName(user.getNickname());
        iLuggageService.saveOrUpdate(luggage);
        return new ResultUtil<Luggage>().setData(luggage);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑行李")
    public Result<Luggage> update(Luggage luggage){
        User user = iUserService.getById(luggage.getUserId());
        if(user == null) {
            return ResultUtil.error("乘客不存在");
        }
        luggage.setUserName(user.getNickname());
        iLuggageService.saveOrUpdate(luggage);
        return new ResultUtil<Luggage>().setData(luggage);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除行李")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iLuggageService.removeById(id);
        }
        return ResultUtil.success();
    }
}
