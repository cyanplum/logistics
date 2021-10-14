package cn.sevenlion.logistics.common.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.IdUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/12 4:26 下午
 */
public class SerialCodeUtils {

    public static DateTimeFormatter dayDf = DateTimeFormatter.ofPattern(DatePattern.PURE_DATE_PATTERN);

    public static DateTimeFormatter msDf = DateTimeFormatter.ofPattern(DatePattern.PURE_TIME_PATTERN);
    public static Random random = new Random();


    public static String generateSerialCode() {
        LocalDateTime now = LocalDateTime.now();
        String day = now.format(dayDf);
        String ms = now.format(msDf);
        int r = random.nextInt(Integer.valueOf(ms));
        r += 100000;
        return day + ms + r;
    }

    public static String generateSerialOrder() {
        LocalDateTime now = LocalDateTime.now();
        String day = now.format(dayDf);
        String ms = now.format(msDf);
        //自增订单号
        Long incr = RedisUtil.getIncr(day);
        String orderId = String.format("%05d", incr);
        return day + ms + orderId;
    }
}
