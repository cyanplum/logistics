package cn.sevenlion.logistics.security.reposiroty;


import cn.sevenlion.logistics.security.enums.ValidateCodeType;
import cn.sevenlion.logistics.security.model.ValidateCode;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 12:08 下午
 */
public interface ValidateCodeRepository {


    public void save(ValidateCodeType type, String key, ValidateCode code);

    public ValidateCode get(ValidateCodeType type, String key);

    public void remove(ValidateCodeType type, String key);
}
