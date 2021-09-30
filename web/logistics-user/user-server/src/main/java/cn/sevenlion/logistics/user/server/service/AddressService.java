package cn.sevenlion.logistics.user.server.service;

import cn.sevenlion.logistics.common.model.entity.user.AddressEntity;
import cn.sevenlion.logistics.user.server.model.bo.AddressBo;
import cn.sevenlion.logistics.user.server.model.query.AddressQueryModel;
import cn.sevenlion.logistics.user.server.model.vo.AddressVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author: qimeiwen
 * @create: 2021-09-14
 */
public interface AddressService extends IService<AddressEntity> {

    /**
     * 查询地址列表
     * @param userCode
     * @param queryModel
     * @return
     */
    Page<AddressVo> findPage(String userCode, AddressQueryModel queryModel);

    /**
     * 根据地址code查询该用户地址
     * @param loginIdAsString
     * @param serialCode
     * @return
     */
    AddressVo selectByCode(String loginIdAsString, String serialCode);


    /**
     * 修改地址
     * @param userCodd
     * @param serialCode
     * @param bo
     * @return
     */
    boolean update(String userCode, String serialCode, AddressBo bo);

    /**
     * 新增地址
     * @param userCode
     * @param bo
     * @return
     */
    boolean insert(String userCode, AddressBo bo);

    /**
     * 删除地址
     * @param userCode
     * @param serialCode
     * @return
     */
    boolean delete(String userCode, String serialCode);
}
