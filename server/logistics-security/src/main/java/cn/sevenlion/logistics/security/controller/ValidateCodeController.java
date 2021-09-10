package cn.sevenlion.logistics.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.sevenlion.logistics.security.reposiroty.image.ImageValidateCodeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 2:31 下午
 */
@RequestMapping
@RestController
public class ValidateCodeController {

    @Autowired
    private ImageValidateCodeService imageValidateCodeService;

    @GetMapping("/image")
    public void image(@RequestParam(value = "deviceId") String deviceId, HttpServletRequest request, HttpServletResponse response) {
        imageValidateCodeService.generate(deviceId,response);
    }
}
