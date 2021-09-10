package cn.sevenlion.logistics.security.reposiroty.image;

import cn.sevenlion.logistics.security.model.ValidateCode;
import cn.sevenlion.logistics.security.model.image.ImageValidateCode;
import cn.sevenlion.logistics.security.properties.ImageValidateCodeProperties;
import cn.sevenlion.logistics.security.reposiroty.ValidateCodeGenerator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 2:24 下午
 */
public class ImageValidateCodeGenerator implements ValidateCodeGenerator {

    private ImageValidateCodeProperties properties;

    public ImageValidateCodeGenerator(ImageValidateCodeProperties properties) {
        this.properties = properties;
    }

    public ImageValidateCodeGenerator() {
    }

    @Override
    public ValidateCode generate(String deviceId) {
        BufferedImage image = new BufferedImage(properties.getWeight(), properties.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, properties.getWeight(), properties.getHeight());
        Integer codeSize = properties.getHeight()*70/100;
        g.setFont(new Font("Times New Roman", Font.ITALIC,codeSize));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(properties.getWeight());
            int y = random.nextInt(properties.getHeight());
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, properties.getWeight()/5 * i + properties.getWeight()/8, (properties.getHeight()+codeSize)/2);
        }

        g.dispose();
        return new ImageValidateCode(sRand, 60, image);
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public ImageValidateCodeProperties getProperties() {
        return properties;
    }

    public void setProperties(ImageValidateCodeProperties properties) {
        this.properties = properties;
    }
}
