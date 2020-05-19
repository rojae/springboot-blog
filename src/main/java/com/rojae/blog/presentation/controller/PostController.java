package com.rojae.blog.presentation.controller;

import com.rojae.blog.domain.model.entity.Post;
import com.rojae.blog.infrastructure.dao.PostDao;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
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

    @Autowired
    private ConnectionRepository connectionRepository;

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String form(Post post) {
        return "form";
    }

    // post 클래스에서 검사한 유효성 검사를
    // binding Result를 통해서 에러 처리
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(@Valid Post post, BindingResult bindingResult) {
        User user = getConnect();
        if (bindingResult.hasErrors()) {
            return "form";
        }
        post.setRegDate(LocalDateTime.now());
        post.setUserId(user.getProviderUserId());
        post.setName(user.getDisplayName());
        return "redirect:/post/" + postDao.save(post).getId();
    }

    @RequestMapping("/list")
    public String list(Model model) {
        User user = getConnect();
        model.addAttribute("user", user);
        return "blog";
    }

    @RequestMapping("/{id}")
    public String view(Model model, @PathVariable int id) {
        User user = getConnect();
        model.addAttribute("user", user);
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
        User user = getConnect();

        if (bindingResult.hasErrors()) {
            return "form";
        }

        Post oldPost = postDao.findById(post.getId()).orElse(null);
        if (oldPost.getUserId().equals(user.getProviderUserId())) {
            oldPost.setTitle(post.getTitle());
            oldPost.setSubtitle(post.getSubtitle());
            oldPost.setContent(post.getContent());
            return "redirect:/post/" + postDao.save(oldPost).getId();
        }

        return "form";
    }

    private User getConnect() {
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        if (connection == null) {
            return null;
        }
        System.out.println("@@");
        ConnectionData data = connection.createData();
        return new User(data.getProviderUserId(), data.getDisplayName());
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        String providerUserId;
        String displayName;
    }
}