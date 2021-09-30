package cn.sevenlion.logistics.back.basic.serivce.impl;

import cn.sevenlion.logistics.back.basic.model.bo.BasicBo;
import cn.sevenlion.logistics.back.basic.model.entity.BasicEntity;
import cn.sevenlion.logistics.back.basic.model.query.BasicQueryModel;
import cn.sevenlion.logistics.back.basic.model.vo.BasicVo;
import cn.sevenlion.logistics.back.basic.serivce.BasicService;
import cn.sevenlion.logistics.back.basic.web.ConvertUtils;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: qimeiwen
 * @create: 2021-09-30
 */
public abstract class BasicServiceImpl<Entity extends BasicEntity, Mapper extends BaseMapper<Entity>> implements BasicService {

    @Autowired
    Mapper mapper;

    ConvertUtils<Entity> convertUtils = new ConvertUtils<Entity>();


    @Override
    public IPage<BasicVo> selectPage(BasicQueryModel queryModel) {
        return mapper.selectPage(new Page(queryModel.getPn(), queryModel.getPageSize()), queryModel.queryWrapper());
    }

    @Override
    public int save(BasicBo bo) {
//        Entity entity = covertUtils.getClazz();
//        BeanUtils.copyProperties(bo, entity);
//        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
//        entity.setSerialCode(String.valueOf(snowflake.nextId()));
//        return mapper.insert(entity);
        return 0;
    }

    @Override
    public BasicVo selectById(String serialCode) {
        return null;
    }

    @Override
    public int updateById(String serialCode, BasicBo bo) {
        return 0;
    }

    @Override
    public int deleteById(String serialCode) {
        return 0;
    }

    @Override
    public BasicEntity selectEntity(String serialCode) {
        return null;
    }
}
