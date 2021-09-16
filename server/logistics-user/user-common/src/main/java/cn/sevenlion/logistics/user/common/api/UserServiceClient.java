package cn.sevenlion.logistics.user.common.api;

import cn.sevenlion.logistics.common.response.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: qimeiwen
 * @create: 2021-09-16
 */
@FeignClient
public interface UserServiceClient {

    @GetMapping
    public CommonResult selectUserByUserCode();
}
