package manager;

import cn.sevenlion.logistics.common.model.entity.user.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@Slf4j
@Component
public class UserManager {


    public UserEntity selectUserById(String userCode) {
        // TODO: 2021/9/17 远程调用查询积分
        return null;
    }
}
