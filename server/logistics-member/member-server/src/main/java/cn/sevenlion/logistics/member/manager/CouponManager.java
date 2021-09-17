package manager;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.sevenlion.logistics.common.mapper.member.CouponBatchMapper;
import cn.sevenlion.logistics.common.mapper.member.CouponMapper;
import cn.sevenlion.logistics.common.model.entity.member.CouponBatchEntity;
import cn.sevenlion.logistics.common.model.entity.member.CouponEntity;
import cn.sevenlion.logistics.common.model.mogon.CouponBindEntity;
import cn.sevenlion.logistics.common.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Slf4j
@Component
public class CouponManager {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponBatchMapper couponBatchMapper;

    public Page<CouponBindEntity> selectCouponBindPage(Integer pn, Integer size, String userCode, Integer status) {
        Criteria criteria = new Criteria();
        if (ObjectUtil.isNotNull(status)) {
            criteria.and("status").is(status);
        }
        if (StrUtil.isNotBlank(userCode)) {
            criteria.is("userCode").is(userCode);
        }
        Query query = new Query(criteria);

        long count = mongoTemplate.count(query, CouponBindEntity.class, CouponBindEntity.buildTableName(userCode));
        query.skip(size * (pn - 1));
        query.limit(size);
        List<CouponBindEntity> couponBindEntityList = mongoTemplate.find(query, CouponBindEntity.class, CouponBindEntity.buildTableName(userCode));
        Page<CouponBindEntity> page = PageUtil.buildPage(couponBindEntityList, count, pn, size);
        return page;
    }

    public CouponBindEntity selectCouponBindById(String userCode, String serialCode) {
        Criteria criteria = new Criteria();
        if (ObjectUtil.isNotNull(serialCode)) {
            criteria.and("serialCode").is(serialCode);
        }
        if (StrUtil.isNotBlank(userCode)) {
            criteria.is("userCode").is(userCode);
        }
        Query query = new Query(criteria);
        CouponBindEntity couponBindEntity = mongoTemplate.findOne(query, CouponBindEntity.class, CouponBindEntity.buildTableName(userCode));
        return couponBindEntity;
    }

    public CouponEntity selectCouponById(String couponCode) {
        return couponMapper.selectById(couponCode);
    }


    public CouponBatchEntity selectCouponBatch(String couponCode, String batchCode) {
        LambdaQueryWrapper<CouponBatchEntity> wrapper = new QueryWrapper<CouponBatchEntity>().lambda()
                .eq(StrUtil.isNotBlank(couponCode), CouponBatchEntity::getCouponCode, couponCode)
                .eq(StrUtil.isNotBlank(batchCode), CouponBatchEntity::getSerialCode, batchCode)
                .last("limit 1");
        return couponBatchMapper.selectOne(wrapper);
    }
}
