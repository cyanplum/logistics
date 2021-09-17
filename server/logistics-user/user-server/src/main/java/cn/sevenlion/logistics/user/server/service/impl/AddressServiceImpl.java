package cn.sevenlion.logistics.user.server.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.logistics.common.exception.BaseException;
import cn.sevenlion.logistics.user.server.mamager.AddressManager;
import cn.sevenlion.logistics.common.mapper.user.AddressMapper;
import cn.sevenlion.logistics.common.model.entity.user.AddressEntity;
import cn.sevenlion.logistics.common.util.PageUtil;
import cn.sevenlion.logistics.common.util.SerialCodeUtils;
import cn.sevenlion.logistics.user.server.model.bo.AddressBo;
import cn.sevenlion.logistics.user.server.model.query.AddressQueryModel;
import cn.sevenlion.logistics.user.server.model.vo.AddressVo;
import cn.sevenlion.logistics.user.server.service.AddressService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: qimeiwen
 * @create: 2021-09-14
 */
@Slf4j
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressEntity> implements AddressService {

    @Autowired
    private AddressManager addressManager;

    @Override
    public Page<AddressVo> findPage(String userCode, AddressQueryModel queryModel) {
        Page<AddressEntity> addressEntityPage = addressManager.selectPage(queryModel.getPn(), queryModel.getSize(), userCode, queryModel.getUsername(), queryModel.getPhone());
        if (addressEntityPage.getRecords().isEmpty()) {
            return new Page<AddressVo>();
        }
        List<AddressVo> result = addressEntityPage.getRecords().stream().map(it -> {
            AddressVo vo = new AddressVo();
            BeanUtils.copyProperties(it, vo);
            return vo;
        }).collect(Collectors.toList());
        Page<AddressVo> page = PageUtil.buildPage(addressEntityPage, result);
        return page;
    }

    @Override
    public AddressVo selectByCode(String userCode, String serialCode) {
        AddressEntity addressEntity = addressManager.selectById(userCode, serialCode);
        if (ObjectUtil.isNull(addressEntity)) {
            throw new BaseException("地址不存在！");
        }
        AddressVo addressVo = new AddressVo();
        BeanUtils.copyProperties(addressEntity, addressVo);
        return addressVo;
    }

    @Override
    public boolean update(String userCode, String serialCode, AddressBo bo) {
        AddressEntity addressEntity = getById(serialCode);
        if (ObjectUtil.isNull(addressEntity) || !addressEntity.getUserCode().equals(userCode)) {
            throw new BaseException("修改失败！");
        }
        BeanUtils.copyProperties(bo, addressEntity);
        addressEntity.setSerialCode(serialCode);
        return updateById(addressEntity);
    }

    @Override
    public boolean insert(String userCode, AddressBo bo) {
        AddressEntity addressEntity = new AddressEntity();
        BeanUtils.copyProperties(bo, addressEntity);
        addressEntity.setUserCode(userCode);
        addressEntity.setSerialCode(SerialCodeUtils.generateSerialCode());
        return save(addressEntity);
    }

    @Override
    public boolean delete(String userCode, String serialCode) {
        AddressEntity addressEntity = getById(serialCode);
        if (ObjectUtil.isNull(addressEntity) || !addressEntity.getUserCode().equals(userCode)) {
            throw new BaseException("删除失败！");
        }
        return removeById(serialCode);
    }
}
