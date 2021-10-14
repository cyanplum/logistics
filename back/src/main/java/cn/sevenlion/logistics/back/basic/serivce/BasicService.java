package cn.sevenlion.logistics.back.basic.serivce;

import cn.sevenlion.logistics.back.basic.model.bo.BasicBo;
import cn.sevenlion.logistics.back.basic.model.entity.BasicEntity;
import cn.sevenlion.logistics.back.basic.model.vo.BasicVo;
import cn.sevenlion.logistics.back.basic.web.ConvertUtils;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author: qimeiwen
 * @create: 2021-09-30
 */
public interface BasicService<Entity extends BasicEntity> extends IService<Entity> {

    BasicVo entity2Vo(Entity entity);

    Entity bo2Entity(BasicBo bo);
}
