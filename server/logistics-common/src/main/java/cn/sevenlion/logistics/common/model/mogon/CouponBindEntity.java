package cn.sevenlion.logistics.common.model.mogon;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Data
public class CouponBindEntity {

    private static final String TABLE_NAME = "couponBind%d";

    private static final Integer HASH_COUNT = 9;

    public static String buildTableName(String userCode) {
        return String.format(TABLE_NAME, (userCode.hashCode() & 0x7FFFFFFF) % HASH_COUNT);
    }

    /**
     * 用户code
     */
    private String userCode;

    /**
     * 卡券code
     */
    private String couponCode;

    /**
     * 批次code
     */
    private String batchCode;

    /**
     * 卡券类型
     */
    private Integer type;

    /**
     * 卡券使用范围
     */
    private Integer scope;

    /**
     * 卡券值
     */
    private Integer value;

    /**
     * 最低使用限制
     */
    private Integer minimum;


    /**
     * 卡券状态
     * CouponStatus
     */
    private Integer status;

    /**
     * 有效天数
     */
    private Integer days;

    /**
     * 绑定时间
     */
    private LocalDateTime bindTime;

    /**
     * 使用开始时间
     */
    private LocalDate startTime;

    /**
     * 使用过期时间
     */
    private LocalDate expireTime;

    /**
     * 使用时间
     */
    private LocalDateTime useTime;

    /**
     * 描述
     */
    private String description;
}
