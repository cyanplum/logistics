package cn.sevenlion.logistics.business.server.service.impl;

import cn.sevenlion.logistics.business.common.model.entity.AddressEntity;
import cn.sevenlion.logistics.business.server.mapper.AddressMapper;
import cn.sevenlion.logistics.business.server.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地址表 服务实现类
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-11
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressEntity> implements IAddressService {

}
