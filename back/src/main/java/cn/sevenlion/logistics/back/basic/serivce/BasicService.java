package cn.sevenlion.logistics.back.basic.serivce;

import cn.sevenlion.logistics.back.basic.model.bo.BasicBo;
import cn.sevenlion.logistics.back.basic.model.entity.BasicEntity;
import cn.sevenlion.logistics.back.basic.model.query.BasicQueryModel;
import cn.sevenlion.logistics.back.basic.model.vo.BasicVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author: qimeiwen
 * @create: 2021-09-30
 */
public interface BasicService {

    /**
     * 分页查询
     * @param queryModel
     * @return
     */
    IPage<BasicVo> selectPage(BasicQueryModel queryModel);

    /**
     * 新增
     * @param bo
     * @return
     */
    int save(BasicBo bo);

    /**
     * 根据主键查询
     * @param serialCode
     * @return
     */
    BasicVo selectById(String serialCode);

    /**
     * 根据主键修改
     * @param serialCode
     * @param bo
     * @return
     */
    int updateById(String serialCode, BasicBo bo);

    /**
     * 根据主键删除
     * @param serialCode
     * @return
     */
    int deleteById(String serialCode);

    /**
     * 根据主键查询实体
     * @param serialCode
     * @return
     */
    BasicEntity selectEntity(String serialCode);


}
