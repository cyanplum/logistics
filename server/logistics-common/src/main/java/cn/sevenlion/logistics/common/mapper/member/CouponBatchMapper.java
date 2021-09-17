package cn.sevenlion.logistics.common.mapper.member;

import cn.sevenlion.logistics.common.model.entity.member.CouponBatchEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cyanplum
 * @since 2021-09-17
 */
public interface CouponBatchMapper extends BaseMapper<CouponBatchEntity> {

    /**
     * 加锁查询卡券库存
     *
     * @param couponCode
     * @param batchCode
     * @return
     */
    CouponBatchEntity selectCouponBatchForUpdate(@Param("couponCode") String couponCode, @Param("batchCode") String batchCode);

    /**
     * 扣减库存
     * @param batchCode
     * @param count
     * @return
     */
    int cutStock(@Param("batchCode") String batchCode, @Param("count") int count);
}
