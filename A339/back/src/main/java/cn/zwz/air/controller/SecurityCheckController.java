package cn.zwz.air.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.service.IUserService;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.air.entity.SecurityCheck;
import cn.zwz.air.service.ISecurityCheckService;
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
@Api(tags = "安检管理接口")
@RequestMapping("/zwz/securityCheck")
@Transactional
public class SecurityCheckController {

    @Autowired
    private ISecurityCheckService iSecurityCheckService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条安检")
    public Result<SecurityCheck> get(@RequestParam String id){
        return new ResultUtil<SecurityCheck>().setData(iSecurityCheckService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部安检个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iSecurityCheckService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部安检")
    public Result<List<SecurityCheck>> getAll(){
        return new ResultUtil<List<SecurityCheck>>().setData(iSecurityCheckService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询安检")
    public Result<IPage<SecurityCheck>> getByPage(@ModelAttribute SecurityCheck securityCheck ,@ModelAttribute PageVo page){
        QueryWrapper<SecurityCheck> qw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        QueryWrapper<User> userQw = new QueryWrapper<>();
        userQw.eq("id",currUser.getId());
        userQw.inSql("id","SELECT user_id FROM a_user_role WHERE del_flag = 0 AND role_id = '1536606659751841799'");
        if(iUserService.count(userQw) < 1L) {
            qw.eq("user_id",currUser.getId());
        }
        if(!ZwzNullUtils.isNull(securityCheck.getUserName())) {
            qw.like("user_name",securityCheck.getUserName());
        }
        if(!ZwzNullUtils.isNull(securityCheck.getContent2())) {
            qw.like("content2",securityCheck.getContent2());
        }
        IPage<SecurityCheck> data = iSecurityCheckService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<SecurityCheck>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改安检")
    public Result<SecurityCheck> saveOrUpdate(SecurityCheck securityCheck){
        if(iSecurityCheckService.saveOrUpdate(securityCheck)){
            return new ResultUtil<SecurityCheck>().setData(securityCheck);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增安检")
    public Result<SecurityCheck> insert(SecurityCheck securityCheck){
        User user = iUserService.getById(securityCheck.getUserId());
        if(user == null) {
            return ResultUtil.error("乘客不存在");
        }
        securityCheck.setUserName(user.getNickname());
        iSecurityCheckService.saveOrUpdate(securityCheck);
        return new ResultUtil<SecurityCheck>().setData(securityCheck);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑安检")
    public Result<SecurityCheck> update(SecurityCheck securityCheck){
        User user = iUserService.getById(securityCheck.getUserId());
        if(user == null) {
            return ResultUtil.error("乘客不存在");
        }
        securityCheck.setUserName(user.getNickname());
        iSecurityCheckService.saveOrUpdate(securityCheck);
        return new ResultUtil<SecurityCheck>().setData(securityCheck);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除安检")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iSecurityCheckService.removeById(id);
        }
        return ResultUtil.success();
    }
}
