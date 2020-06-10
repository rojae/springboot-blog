package com.rojae.blog.presentation.controller;

import com.rojae.blog.domain.model.entity.Post;
import com.rojae.blog.infrastructure.dao.PostDao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostDao postDao;

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String form(Post post) {
        return "form";
    }

    // post 클래스에서 검사한 유효성 검사를
    // binding Result를 통해서 에러 처리
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(@Valid Post post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        post.setRegDate(LocalDateTime.now());
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
    public String delete(@PathVariable int id) {
        postDao.deleteById(id);
        return "redirect:/post/list";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editor(Model model, @PathVariable int id) {
        Post post = postDao.findById(id).orElse(null);
        model.addAttribute("post", post);
        return "form";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String edit(@Valid Post post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        return "redirect:/post/" + postDao.save(post).getId();
    }
}