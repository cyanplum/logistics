package cn.sevenlion.logistics.security.provider;

import cn.sevenlion.logistics.security.enums.ColumnFieldEnum;
import cn.sevenlion.logistics.security.processor.TableFieldProcessor;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/21 3:32 下午
 */
public class TableFieldProviderFactory {

    public static ArrayListMultimap<ColumnFieldEnum, TableFieldProcessor> tableFieldProcessorMap = ArrayListMultimap.create();

    public static List<TableFieldProcessor> getProcessorByType(ColumnFieldEnum columnFieldEnum) {
        if (tableFieldProcessorMap.size() == 0) {
            return Lists.newArrayList();
        }
        return tableFieldProcessorMap.get(columnFieldEnum);
    }

    public static void registerProcessor(ColumnFieldEnum columnFieldEnum, TableFieldProcessor processor) {
        tableFieldProcessorMap.put(columnFieldEnum, processor);
    }

    public TableFieldProviderFactory() {
    }
}
