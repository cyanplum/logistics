package cn.sevenlion.logistics.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    public static <T> Page<T> buildPage(IPage page, List<T> t) {
        Page<T> result = new Page<>();
        result.setTotal(page.getTotal());
        result.setRecords(t);
        result.setCurrent(page.getCurrent());
        result.setPages(page.getPages());
        result.setSize(page.getSize());
        return result;
    }

    public static <T> Page<T> buildPage(List<T> list, long count, int pn, int size) {
        Page<T> page = new Page<>();
        page.setTotal(count);
        page.setCurrent(pn);
        page.setSize(size);
        page.setRecords(list);
        if (count % size == 0) {
            page.setPages(count / size);
        } else {
            page.setPages((count / size) + 1);
        }
        return page;
    }
}
