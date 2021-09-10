package cn.sevenlion.logistics.security.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
public class UrlMatcherRegistry {

    private List<String> matchUrls;

    private List<String> ignoreUrls;

    public UrlMatcherRegistry() {
        matchUrls = new ArrayList<>();
        ignoreUrls = new ArrayList<>();
    }

    public UrlMatcherRegistry(List<String> matchUrls, List<String> ignoreUrls) {
        this.matchUrls = matchUrls;
        this.ignoreUrls = ignoreUrls;
    }

    public List<String> getMatchUrls() {
        return matchUrls;
    }

    public void setMatchUrls(List<String> matchUrls) {
        this.matchUrls = matchUrls;
    }

    public List<String> getIgnoreUrls() {
        return ignoreUrls;
    }

    public void setIgnoreUrls(List<String> ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }

    public UrlMatcherRegistry matchUrl(String... url) {
        Collections.addAll(this.matchUrls, url);
        return this;
    }

    public UrlMatcherRegistry ignoreUrl(String ... url) {
        Collections.addAll(this.ignoreUrls, url);
        return this;
    }
}
