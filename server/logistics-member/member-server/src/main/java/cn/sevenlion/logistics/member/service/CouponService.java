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
     * 查询卡券列表
     * @param userCode
     * @param queryModel
     * @return
     */
    Page<CouponVo> selectCouponPage(String userCode, CouponQueryModel queryModel);

    /**
     * 查询卡券详情
     * @param userCode
     * @param serialCode
     * @return
     */
    CouponVo selectCouponById(String userCode, String serialCode);

    /**
     * 领取卡券
     * @param userCode
     * @param bo
     * @return
     */
    boolean receiveCoupon(String userCode, CouponBindBo bo);
}
