package cn.sevenlion.logistics.back.basic.serivce.impl;

import cn.sevenlion.logistics.back.basic.model.bo.BasicBo;
import cn.sevenlion.logistics.back.basic.model.entity.BasicEntity;
import cn.sevenlion.logistics.back.basic.model.vo.BasicVo;
import cn.sevenlion.logistics.back.basic.serivce.BasicService;
import cn.sevenlion.logistics.back.basic.web.ConvertUtils;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author: qimeiwen
 * @create: 2021-09-30
 */
public abstract class BasicServiceImpl<Entity extends BasicEntity, Mapper extends BaseMapper<Entity>, Vo extends BasicVo> extends ServiceImpl<Mapper, Entity> implements BasicService<Entity>{

    @Override
    public BasicVo entity2Vo(Entity entity) {
        return (BasicVo) ConvertUtils.convert(entity, getVoClazz());
    }

    @Override
    public Entity bo2Entity(BasicBo bo) {
        return ConvertUtils.convert(bo, entityClass);
    }

    protected Class<Vo> getVoClazz() {
        return (Class<Vo>) ReflectionKit.getSuperClassGenericType(this.getClass(), 2);
    }
}
