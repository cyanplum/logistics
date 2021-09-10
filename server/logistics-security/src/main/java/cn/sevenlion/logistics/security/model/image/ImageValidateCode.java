package cn.sevenlion.logistics.security.model.image;

import cn.sevenlion.logistics.security.model.ValidateCode;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 12:23 下午
 */
public class ImageValidateCode extends ValidateCode implements Serializable {

    private static final long serialVersionUID = 1L;


    private BufferedImage image;


    public ImageValidateCode() {
    }

    public ImageValidateCode(String code, Integer expireIn, BufferedImage image) {
        super(code,expireIn);
        this.image = image;
    }

    @Override
    public String getCode() {
        return super.getCode();
    }

    @Override
    public void setCode(String code) {
        super.setCode(code);
    }

    @Override
    public Integer getExpireIn() {
        return super.getExpireIn();
    }

    @Override
    public void setExpireIn(Integer expireIn) {
        super.setExpireIn(expireIn);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }


}
