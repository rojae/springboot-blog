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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
public class OAuth2Controller {
    @Resource(name = "userService")
    UserService userService;

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

  /*
        Cookie setCookie = new Cookie("enrolled", "Authencated_User");
        response.addCookie(setCookie);
*/
        return "blog";
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "loginFailure";
    }

    @GetMapping("/loginPreview")
    public String loginDo(Model model, @CookieValue(value = "accessToken", defaultValue = "") String logined, HttpServletResponse response, HttpServletRequest request){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("@"+logined);

        // 익명의 유저
        if(auth.getPrincipal() == "anonymousUser")  // 익명의 유저인 경우에는 로그인 페이지로 이동
            return "redirect:/connect/login";
        else if(logined.length() != 0)              // 이미 로그인을 했다면 메인 페이지로 이동, 로그인 성공 핸들러는 작동
            return "redirect:/blog";
        else {                                      // 사용자 정보를 가져와 디비처리
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
            String name = (String) userDetails.getAttribute("name");
            String email = (String) userDetails.getAttribute("email");

            System.out.println(identi);
            System.out.println(name);
            System.out.println(email);
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
            // JWT로 구현하면 좋지 않을까?
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
}