package cn.sevenlion.logistics.back.basic.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @author: qimeiwen
 * @create: 2021-10-08
 */
public class BasicUtils {

    public static Snowflake snowflake = IdUtil.createSnowflake(1,1);

    /**
     * 生成唯一主键
     * @return
     */
    public static String getSerialCode() {
        return String.valueOf(snowflake.nextId());
    }
}
