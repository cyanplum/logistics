package cn.sevenlion.logistics.user.server.manager;

import cn.sevenlion.logistics.user.common.model.entity.AddressEntity;
import cn.sevenlion.logistics.user.server.mapper.AddressMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/12 9:23 下午
 */
@Component
public class AddressManager {

    @Autowired
    AddressMapper addressMapper;

    public Page<AddressEntity> selectPage(Integer pn, Integer size, String userCode, String username, String phone) {
        Page<AddressEntity> addressEntityPage = addressMapper.selectPage(new Page<AddressEntity>(pn, size),
                new QueryWrapper<AddressEntity>().lambda()
                        .eq(AddressEntity::getUserCode, userCode)
                        .likeRight(StringUtils.isNotBlank(username), AddressEntity::getName, username)
                        .likeRight(StringUtils.isNotBlank(phone), AddressEntity::getPhone, phone)
                        .orderByDesc(AddressEntity::getCreateTime));
        return addressEntityPage;
    }
}
