package cn.sevenlion.logistics.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/9/12 10:00 下午
 */
public class PageUtil<T> {

    public static <T> Page<T> buildPage(Page page, List<T> t) {
        Page<T> result = new Page<>();
        result.setTotal(page.getTotal());
        result.setRecords(t);
        result.setCurrent(page.getCurrent());
        result.setPages(page.getPages());
        result.setSize(page.getSize());
        return result;
    }
}
