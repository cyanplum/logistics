package cn.sevenlion.logistics.security.enums;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 12:16 下午
 */
public enum ValidateCodeType {

    image("IMAGE_CODE","imageValidateCodeService"),;

    private String prefix;
    private String serviceName;

    ValidateCodeType(String prefix, String serviceName) {
        this.prefix = prefix;
        this.serviceName = serviceName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
