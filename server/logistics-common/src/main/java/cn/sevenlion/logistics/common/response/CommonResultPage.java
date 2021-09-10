package cn.sevenlion.logistics.common.response;//package org.uppower.sevenlion.common.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/3/6 11:09 下午
 */
@Data
@NoArgsConstructor
@SuppressWarnings("unchecked")
public class CommonResultPage<T> {

    private long code;

    private String msg;

    private List<T> data;

    private boolean success;

    private long page;

    private long total;

    public CommonResultPage(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResultPage(List<T> data) {
        this.data = data;
    }

    public CommonResultPage(long code, String msg, List<T> data, boolean success, long page, long total) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.success = success;
        this.page = page;
        this.total = total;
    }

    public static <T> CommonResultPage success(long code, String msg, List<T> data, long page, long total) {
        return new CommonResultPage(code, msg, data, true, page, total);
    }

    public static <T> CommonResultPage<T> success(String msg, List<T> data,long page,long total) {
        return new CommonResultPage(ResultCode.SUCCESS.getCode(), msg, data,true, page, total);
    }

    public static <T> CommonResultPage<T> success(List<T> data,long page,long total) {
        return new CommonResultPage(ResultCode.SUCCESS.getCode(), "成功", data,true,page,total);
    }
    public static <T> CommonResultPage<T> success(){
        return new CommonResultPage(ResultCode.SUCCESS.getCode(), "成功",null,true,0L,0L);
    }

    public static <T> CommonResultPage<T> failed(long code, String msg, List<T> data) {
        return new CommonResultPage(code, msg, data, false, 0L, 0L);
    }

    public static <T> CommonResultPage<T> failed(String msg, List<T> data) {
        return new CommonResultPage(ResultCode.FAILED.getCode(), msg, data, false,  0L, 0L);
    }

    public static <T> CommonResultPage<T> failed(long code, String msg) {
        return new CommonResultPage(code, msg, null,false, 0L, 0L);
    }

    public static <T> CommonResultPage<T> failed(List<T> data) {
        return new CommonResultPage(ResultCode.FAILED.getCode(), "失败", data, false, 0L, 0L);
    }

    public static <T> CommonResultPage<T> failMsg(String msg) {
        return new CommonResultPage(ResultCode.FAILED.getCode(), msg, null, false, 0L, 0L);
    }

    public static <T> CommonResultPage<T> failMsg(String msg, List<T> data) {
        return new CommonResultPage(ResultCode.FAILED.getCode(), msg, data, false, 0L, 0L);
    }
}
