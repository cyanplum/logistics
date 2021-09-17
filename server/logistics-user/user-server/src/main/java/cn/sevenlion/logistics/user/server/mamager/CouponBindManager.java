package cn.sevenlion.logistics.user.server.mamager;

import cn.sevenlion.logistics.common.model.enums.CouponStatus;
import cn.sevenlion.logistics.common.model.mogon.CouponBindEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Component
public class CouponBindManager {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Long selectCountByStatus(String userCode, Integer code) {
        // TODO: 2021/9/17 远程调用查询
        Criteria criteria = new Criteria();
        criteria.and("user_code").is(userCode)
                .and("status").is(code);
        Query query = new Query(criteria);
        long couponCont = mongoTemplate.count(query, CouponBindEntity.class, CouponBindEntity.buildTableName(userCode));
        return couponCont;
    }
}
