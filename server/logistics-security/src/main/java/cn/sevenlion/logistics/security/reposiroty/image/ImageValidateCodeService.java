package cn.sevenlion.logistics.security.reposiroty.image;

import cn.sevenlion.logistics.security.enums.ValidateCodeType;
import cn.sevenlion.logistics.security.model.ValidateCode;
import cn.sevenlion.logistics.security.model.image.ImageValidateCode;
import cn.sevenlion.logistics.security.reposiroty.AbstractValidateCodeService;
import cn.sevenlion.logistics.security.reposiroty.ValidateCodeGenerator;
import cn.sevenlion.logistics.security.reposiroty.ValidateCodeRepository;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 2:15 下午
 */
public class ImageValidateCodeService extends AbstractValidateCodeService {


    private ValidateCodeGenerator generator;

    private ValidateCodeRepository repository;

    public ImageValidateCodeService(ValidateCodeGenerator generator, ValidateCodeRepository repository) {
        super(generator,repository, ValidateCodeType.image);
        this.generator = generator;
        this.repository = repository;
    }

    public ImageValidateCodeService() {
    }

    @Override
    protected void send(HttpServletResponse response, ValidateCode validateCode) {
        if (validateCode instanceof ImageValidateCode) {
            try {
                ImageIO.write(((ImageValidateCode) validateCode).getImage(),"JPEG", response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
