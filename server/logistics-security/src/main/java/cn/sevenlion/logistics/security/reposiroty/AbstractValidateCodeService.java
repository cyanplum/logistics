package cn.sevenlion.logistics.security.reposiroty;

import cn.sevenlion.logistics.security.enums.ValidateCodeType;
import cn.sevenlion.logistics.security.exceptions.SevenlionException;
import cn.sevenlion.logistics.security.model.ValidateCode;

import javax.servlet.http.HttpServletResponse;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 12:43 下午
 */
public abstract class AbstractValidateCodeService {

    private ValidateCodeGenerator generator;

    private ValidateCodeRepository repository;

    private ValidateCodeType type;

    public AbstractValidateCodeService(ValidateCodeGenerator generator, ValidateCodeRepository repository, ValidateCodeType type) {
        this.generator = generator;
        this.repository = repository;
        this.type = type;
    }

    public AbstractValidateCodeService() {
    }

    public void generate(String deviceId, HttpServletResponse response) {
        ValidateCode validateCode = generator.generate(deviceId);
        //保存
        repository.save(type,deviceId,validateCode);
        //发送
        send(response, validateCode);
    }

    public void validate(String code, String deviceId) {
        ValidateCode validateCode = repository.get(type, deviceId);

        if (code == null) {
            throw new SevenlionException("验证码不能为空");
        }
        if (validateCode == null) {
            throw new SevenlionException("验证码不存在");
        }
        if (validateCode.isExpired()) {
            throw new SevenlionException("验证码过期");
        }
        if (!validateCode.getCode().toLowerCase().equals(code.toLowerCase())) {
            throw new SevenlionException("验证码不正确！");
        }
        repository.remove(type,deviceId);
    }

    protected abstract void send(HttpServletResponse response, ValidateCode validateCode);

}
