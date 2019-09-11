package com.tlj.dreamdo.system.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tlj.dreamdo.common.controller.BaseController;
import com.tlj.dreamdo.common.factory.PageFactory;
import com.tlj.dreamdo.common.util.R;
import com.tlj.dreamdo.system.enums.StatusEnum;
import com.tlj.dreamdo.system.model.AdminModel;
import com.tlj.dreamdo.system.model.UserModel;
import com.tlj.dreamdo.system.service.UserService;
import com.tlj.dreamdo.system.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

/**
* 系统管理
* @author tanleijin
* @date 2019/9/11 14:40
*/
@Api("系统管理")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
@RestController
public class AdminManageController extends BaseController {

    
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminManageController.class);



    @Autowired
    private UserService userService;


    @ApiOperation("用户列表搜索")
    @GetMapping("/user/page")
    public Object userPage(@RequestParam(defaultValue="1")int currPage , @RequestParam(defaultValue="10") int pageSize,
                               @RequestParam(required=false)String username, @RequestParam(required=false)String source) {
        LOGGER.info("search condtion ,username: {} ,source: {} " , username , source);
        //1.分页
        IPage<UserModel> page = super.defaultPage(UserModel.class);
        //2.业务查询条件
        LambdaQueryWrapper<UserModel> wapper = new LambdaQueryWrapper<UserModel>();
        if(StringUtils.isNotBlank(username)  ) {
            wapper.like(UserModel::getUsername, username);
        }
        if(StringUtils.isNotBlank(source )) {
            wapper.eq(UserModel::getSource, source);
        }
        wapper.orderByDesc(UserModel::getCreateTime);
        
        IPage<UserModel> userPage = userService.page(page ,wapper);
        //3.格式化
/*        List<UserVo> userVos = userPage.getRecords().stream().map(u -> {
            UserVo userVo = new UserVo(u);
            userVo.setFansCount( socialService.selectFansCount(u.getId()));
            userVo.setArticleCount(articleService.selectByUser(u.getId(), null));
            return userVo;
        }).collect(Collectors.toList());
        IPage<UserVo> userVoPage = new Page<>();
        BeanUtils.copyProperties(userPage, userVoPage);
        userVoPage.setRecords(userVos);*/

        return super.packForBT(userPage);
    }
    
    @ApiOperation("用户冻结或解冻")
    @PutMapping("/user/freeze/{id}")
    public R userFreeze(@PathVariable Integer id , StatusEnum statusEnum) {
        Assert.notNull(id, "id is null");
        LOGGER.info("useriId : {} , statusEnum: {}" , id ,statusEnum);
        UserModel userModel = new UserModel(id);
        userModel.setState(statusEnum);
        userService.updateById(userModel);
        return R.ok();
    }
    
}
