package cn.sevenlion.logistics.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: qimeiwen
 * @create: 2021-09-17
 */
@SpringBootApplication
@MapperScan("cn.sevenlion.logistics.**.mapper")
@ComponentScan("cn.sevenlion.logistics")
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }
}
