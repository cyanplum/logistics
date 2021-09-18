package cn.sevenlion.logistics.common.consts;

/**
 * @author: qimeiwen
 * @create: 2021-09-16
 * redis常量
 */
public class RedisConst {

    public static final Long LOCK_TIME = 10L;

    /**
     * 地址全量信息map key-地址code value-地址内容
     */
    public static final String ADDRESS_INFO_MAP = "ADDRESS_INFO";

    /**
     * 用户地址缓存数据 key-ADDRESS_USER_用户code zset为排序好的地址code
     */
    public static final String ADDRESS_USER_ZSET = "ADDRESS_USER_%s";

    /**
     * 会员等级定义 map<等级code, 等级信息>
     */
    public static final String GROWTH_LEVEL_MAP = "GROWTH_LEVEL";

    /**
     * 绑定卡券Lock key
     */
    public static final String COUPON_BIND_KEY = "COUPON_BIND_%s";

    /**
     * 卡券信息map<serialCode, CouponEntity>
     */
    public static final String COUPON_MAP = "COUPON_MAP";

}
