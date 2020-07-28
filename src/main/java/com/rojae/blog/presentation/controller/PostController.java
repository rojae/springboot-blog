package com.rojae.blog.presentation.controller;

import com.rojae.blog.application.utility.ObjectUtils;
import com.rojae.blog.domain.model.entity.Post;
import com.rojae.blog.domain.model.entity.User;
import com.rojae.blog.infrastructure.dao.PostDao;

import java.time.LocalDateTime;
import java.util.List;

import com.rojae.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostDao postDao;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String form(Post post) {
        return "form";
    }

    // post 클래스에서 검사한 유효성 검사를
    // binding Result를 통해서 에러 처리
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(@CookieValue(value = "greeting", defaultValue = "") String userName,
                        @CookieValue(value = "mail", defaultValue = "") String userEmail,
                        @CookieValue(value = "accessToken", defaultValue = "") String accessToken,
                        @Valid Post post, BindingResult bindingResult) {

        User user = userService.getUser(accessToken);

        if (bindingResult.hasErrors()) {
            return "form";
        }

        post.setRegDate(LocalDateTime.now());
        post.setUser(user);
        return "redirect:/post/" + postDao.save(post).getId();
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<Post> postList = postDao.findAll();
        model.addAttribute("postList", postList);
        return "blog";
    }

    @RequestMapping("/{id}")
    public String view(Model model, @PathVariable int id) {
        Post post = postDao.findById(id).orElse(null);
        model.addAttribute("post", post);
        return "post";
    }

    @RequestMapping("/{id}/delete")
    public String delete(@CookieValue(value = "accessToken", defaultValue = "") String accessToken,
                         @PathVariable int id) throws IllegalArgumentException{
            Long uid = userService.getInfo(accessToken).getId();
        try {

            if (!ObjectUtils.isEmpty(postDao.findOneByUserIdAndId(id, uid))) {
                postDao.deleteById(id);
                return "redirect:/post/list";
            } else
                return "invalid";
        }
        catch (Exception e){
            return "redirect:/invalid";
        }
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editor(Model model, @PathVariable int id) {
        Post post = postDao.findById(id).orElse(null);
        model.addAttribute("post", post);
        return "form";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String edit(@CookieValue(value = "accessToken", defaultValue = "") String accessToken,
                       @PathVariable int id, @Valid Post post, BindingResult bindingResult) throws IllegalArgumentException{
        Long uid = userService.getInfo(accessToken).getId();

        if (bindingResult.hasErrors())
            return "form";

        try{
            if(!ObjectUtils.isEmpty(postDao.findOneByUserIdAndId(id, uid))) {
                return "redirect:/post/" + postDao.save(post).getId();
            }
            else
                return "invalid";
        }catch (Exception e){
            return "redirect:/invalid";
        }

    }


}