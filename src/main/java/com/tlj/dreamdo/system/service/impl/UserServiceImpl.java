package com.tlj.dreamdo.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tlj.dreamdo.system.mapper.UserMapper;
import com.tlj.dreamdo.system.model.UserModel;
import com.tlj.dreamdo.system.service.UserService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author TianXin
 * @since 2019-04-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements UserService {

}
