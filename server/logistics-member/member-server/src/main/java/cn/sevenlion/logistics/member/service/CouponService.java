package cn.sevenlion.logistics.member.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.sevenlion.logistics.member.model.bo.CouponBindBo;
import cn.sevenlion.logistics.member.model.query.CouponQueryModel;
import cn.sevenlion.logistics.member.model.vo.CouponVo;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
public interface CouponService {
    /**
     * 查询用户绑定卡券列表
     * @param userCode
     * @param queryModel
     * @return
     */
    Page<CouponVo> selectBindCouponPageByUser(String userCode, CouponQueryModel queryModel);

    /**
     * 查询用户绑定卡券详情
     * @param userCode
     * @param serialCode
     * @return
     */
    CouponVo selectBindCouponByByUser(String userCode, String serialCode);

    /**
     * 领取卡券
     * @param userCode
     * @param bo
     * @return
     */
    boolean receiveCoupon(String userCode, CouponBindBo bo);

    /**
     * 查询卡券列表
     * @param queryModel
     * @return
     */
    Page<CouponVo> selectCouponPage(CouponQueryModel queryModel);

    /**
     * 查询根据卡券批次编号查询卡券
     * @param serialCode
     * @return
     */
    CouponVo selectCouponByBatchId(String serialCode);
}
