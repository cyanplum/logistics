package cn.sevenlion.logistics.security.common;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/20 9:43 上午
 */
public interface UrlStrategyMatcher {

    /**
     * 添加路径
     * @param urlMatcherRegistry
     */
    public void handleUrl(UrlMatcherRegistry urlMatcherRegistry);
}
