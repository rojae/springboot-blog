package com.rojae.blog.presentation.controller;

import com.rojae.blog.infrastructure.dto.UserDto;
import com.rojae.blog.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class OAuth2Controller {
    @Resource(name = "userService")
    UserService userService;

    @GetMapping({"", "/"})
    public String getAuthorizationMessage() {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, HttpServletResponse response, HttpServletRequest request){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 익명의 유저
        if(auth.getPrincipal() == "anonymousUser")
            return "connect/login";
        else {
            Object authInfo = auth.getDetails();
            DefaultOAuth2User userDetails = (DefaultOAuth2User) auth.getPrincipal();

            System.out.println("======= AUTH INFO ======= ");

            System.out.println("-- SESSION --");
            System.out.println(authInfo);

            System.out.println("-- SCOPE --");
            System.out.println(userDetails.getAuthorities());

            System.out.println("-- DETAIL INFO --");

            String access_token = (String) userDetails.getAttribute("access_token");
            String identi = (String) userDetails.getAttribute("id");

            System.out.println(identi);
            System.out.println((String) userDetails.getAttribute("name"));
            System.out.println((String) userDetails.getAttribute("email"));
            System.out.println(access_token);

            // 데이터베이스 저장 부분
            try {
                System.out.println("======= DB STORE ======= ");

                UserDto userDto
                        = UserDto.builder()
                        .userId(userDetails.getAttribute("id"))
                        .userName(userDetails.getAttribute("name"))
                        .userEmail(userDetails.getAttribute("email"))
                        .accessToken(userDetails.getAttribute("access_token"))
                        .build();

                System.out.println(userDto.getId());
                System.out.println(userDto.getUserName());
                System.out.println(userDto.getUserId());
                System.out.println(userDto.getUserEmail());
                System.out.println(userDto.getAccessToken());
                System.out.println(userDto.getCreatedDate());
                System.out.println(userDto.getModifiedDate());

                // Service -> Repository || -> Entity -> DB
                userService.saveUser(userDto);

            } catch (Exception e) {
                System.out.println("------- FAIL -------");
                e.printStackTrace();
            }

            // 쿠키를 저장하자
            // 1. 토큰
            // 2. 식별자
            // 세션 서비스를 만들어야 하지 않을까?
            // JWY로 구현하면 좋지 않을까?
            ArrayList<Cookie> setCookie = new ArrayList<>();
            setCookie.add(new Cookie("accessToken", access_token));
            setCookie.add(new Cookie("identifier", identi));

            for (Cookie cookie : setCookie) response.addCookie(cookie);

            model.addAttribute("message", "accessToken" + (String) userDetails.getAttribute("access_token"));

            return "/";
        }
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "loginFailure";
    }
}