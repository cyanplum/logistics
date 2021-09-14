package cn.sevenlion.logistics.user.server.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.logistics.common.exception.BaseException;
import cn.sevenlion.logistics.common.util.PageUtil;
import cn.sevenlion.logistics.user.common.model.entity.AddressEntity;
import cn.sevenlion.logistics.user.common.model.entity.UserEntity;
import cn.sevenlion.logistics.user.server.manager.AddressManager;
import cn.sevenlion.logistics.user.server.manager.UserManager;
import cn.sevenlion.logistics.user.server.mapper.UserMapper;
import cn.sevenlion.logistics.user.server.model.bo.UserAuthBo;
import cn.sevenlion.logistics.user.server.model.query.AddressQueryModel;
import cn.sevenlion.logistics.user.server.model.query.UserAuthQueryModel;
import cn.sevenlion.logistics.user.server.model.vo.AddressVo;
import cn.sevenlion.logistics.user.server.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/10 10:27 下午
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserManager userManager;

    @Autowired
    private AddressManager addressManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserEntity auth(UserAuthQueryModel queryModel) {

        UserEntity userEntity = userMapper.selectOne(new QueryWrapper<UserEntity>().lambda()
                .eq(UserEntity::getUsername, queryModel.getUsername())
                .last("limit 1"));
        if (ObjectUtil.isNull(userEntity)) {
            throw new BaseException("用户名不存在！");
        }
        if (!passwordEncoder.matches(queryModel.getPassword(), userEntity.getPassword())) {
            throw new BaseException("密码不正确！");
        }
        return userEntity;
    }


}
