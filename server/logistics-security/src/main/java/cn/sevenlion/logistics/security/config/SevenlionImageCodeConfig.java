package cn.sevenlion.logistics.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cn.sevenlion.logistics.security.properties.ImageValidateCodeProperties;
import cn.sevenlion.logistics.security.reposiroty.ValidateCodeRepository;
import cn.sevenlion.logistics.security.reposiroty.image.ImageValidateCodeGenerator;
import cn.sevenlion.logistics.security.reposiroty.image.ImageValidateCodeService;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 12:31 下午
 */
@Configuration
public class SevenlionImageCodeConfig {

    @Autowired
    private ImageValidateCodeProperties properties;

    @Autowired
    private ValidateCodeRepository repository;

    @Autowired
    private ImageValidateCodeGenerator imageValidateCodeGenerator;

    @Bean
    @ConditionalOnMissingBean
    public ImageValidateCodeGenerator imageValidateCodeGenerator()  {
        return new ImageValidateCodeGenerator(properties);
    }

    @Bean
    public ImageValidateCodeProperties properties() {
        return new ImageValidateCodeProperties();
    }

    @Bean
    public ImageValidateCodeService imageValidateCodeService() {
        return new ImageValidateCodeService(imageValidateCodeGenerator,repository);
    }
}
