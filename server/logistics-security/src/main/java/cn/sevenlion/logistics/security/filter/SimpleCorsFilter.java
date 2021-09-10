package cn.sevenlion.logistics.security.filter;//package org.uppower.alibaba_demo.security.filter;
//
//import org.uppower.alibaba_demo.security.common.UrlMatcherRegistry;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author create by:
// * *      ____        ___  ___       __          __
// * *    /  _  \     /   |/   |      | |        / /
// * *   | | | |     / /|   /| |     | |  __   / /
// * *  | | | |     / / |__/ | |    | | /  | / /
// * * | |_| |_    / /       | |   | |/   |/ /
// * * \_______|  /_/        |_|  |___/|___/
// * @date 2021/5/17 11:04 上午
// */
//public class SimpleCorsFilter extends OncePerRequestFilter {
//
//    private AntPathMatcher requestMatcher = new AntPathMatcher();
//
//    @Autowired
//    private UrlMatcherRegistry urlMatcherRegistry;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        String origin = httpServletRequest.getRemoteHost() + ":" + httpServletRequest.getRemotePort();
//        if (checkUrl(httpServletRequest)) {
//
//        }
//    }
//
//    public boolean checkUrl(HttpServletRequest request) {
//        urlMatcherRegistry.
//    }
//}
