package com.rojae.blog.presentation.controller;

import com.rojae.blog.infrastructure.dto.UserDto;
import com.rojae.blog.test.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Controller
public class OAuth2Controller {
    @Resource(name = "userService")
    UserService userService;

    LinkedHashMap<String, String> linkedHashMap, linkedHashMap2;

    @GetMapping({"", "/"})
    public String getAuthorizationMessage() {
        return "home";
    }


    @GetMapping("/loginPage")
    public String loginPage() {
        return "connect/login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@CookieValue(value = "greeting", defaultValue = "사용자") String name, @CookieValue(value = "mail", defaultValue = "???") String email, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<script>alert('"+name+"님, 계정이 등록 되었습니다!      이메일 : "+email+"'); </script>");

        out.flush();

        return "blog";
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "loginFailure";
    }

    @GetMapping("/loginPreview")
    public String loginDo(Model model, @CookieValue(value = "socialType", defaultValue = "") String socialType,
                          @CookieValue(value = "accessToken", defaultValue = "") String logined, HttpServletResponse response, HttpServletRequest request) {

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                Object authInfo = auth.getDetails();
                DefaultOAuth2User userDetails = (DefaultOAuth2User) auth.getPrincipal();

                String access_token = "";
                String identi = "";
                String name = "";
                String email = "";
                String thumbNail = "";

                System.out.println("@" + socialType);
                System.out.println(auth.getPrincipal());

                // 익명의 유저
                if (auth.getPrincipal() == "anonymousUser")  // 익명의 유저인 경우에는 로그인 페이지로 이동
                    return "redirect:/connect/login";
                else if (logined.length() != 0)              // 이미 로그인을 했다면 메인 페이지로 이동, 로그인 성공 핸들러는 작동
                    return "redirect:/blog";
                else if (socialType.equals("FACEBOOK")) {    // 개발 완료
                    access_token = (String) userDetails.getAttribute("access_token");
                    identi = (String) userDetails.getAttribute("id");
                    name = (String) userDetails.getAttribute("name");
                    email = (String) userDetails.getAttribute("email");
                }
                else if (socialType.equals("GOOGLE")) {     // 미개발
                    access_token = (String) userDetails.getAttribute("access_token");
                    identi = (String) userDetails.getAttribute("id");
                    name = (String) userDetails.getAttribute("name");
                    email = (String) userDetails.getAttribute("email");
                }
                else if (socialType.equals("KAKAO")) {      // 개발 중
                    access_token = (String) userDetails.getAttribute("access_token");
                    identi = (String) userDetails.getName();
                    linkedHashMap = userDetails.getAttribute("properties");             // kakao에서 가지고 있는 사용자의 profile 정보들
                    linkedHashMap2 = userDetails.getAttribute("kakao_account");             // kakao에서 가지고 있는 사용자의 profile 정보들

                    name = linkedHashMap.get("nickname");
                    thumbNail = linkedHashMap.get("profile_image");
                    email = linkedHashMap2.get("email");

                    System.out.println(identi);
                    System.out.println(name);
                    System.out.println(email);
                    System.out.println(thumbNail);
                }
                else if (socialType.equals("NAVER")) {      // 미개발
                    access_token = (String) userDetails.getAttribute("access_token");
                    identi = (String) userDetails.getAttribute("id");
                    name = (String) userDetails.getAttribute("name");
                    email = (String) userDetails.getAttribute("email");
                }

                // 데이터베이스 저장 부분
                try {
                    System.out.println("======= DB STORE ======= ");
                    UserDto userDto;

                    if(thumbNail.equals("")) {
                        userDto
                                = UserDto.builder()
                                .userId(identi)
                                .userName(name)
                                .userEmail(email)
                                .accessToken(access_token)
                                .socialType(socialType)
                                .build();
                    }
                    else{
                         userDto
                                = UserDto.builder()
                                .userId(identi)
                                .userName(name)
                                .userEmail(email)
                                .accessToken(access_token)
                                .socialType(socialType)
                                .thumbNail(thumbNail)
                                .build();
                    }

                    // Service -> Repository || -> Entity -> DB
                    userService.saveUser(userDto);

                } catch (Exception e) {
                    System.out.println("------- FAIL -------");
                    e.printStackTrace();
                }
                // 쿠키를 저장하자  1. 토큰  2. 식별자
                // 세션 서비스를 만들어야 하지 않을까?, JWT로 구현하면 좋지 않을까?
                ArrayList<Cookie> setCookie = new ArrayList<>();
                setCookie.add(new Cookie("accessToken", access_token));
                setCookie.add(new Cookie("identifier", identi));
                setCookie.add(new Cookie("greeting", name));
                setCookie.add(new Cookie("mail", email));

                for (Cookie cookie : setCookie) response.addCookie(cookie);

                model.addAttribute("message", "accessToken" + (String) userDetails.getAttribute("access_token"));

                return "redirect:/loginSuccess";
            }
}