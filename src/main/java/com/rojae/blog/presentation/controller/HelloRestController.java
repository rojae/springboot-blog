package com.rojae.blog.presentation.controller;

import java.util.List;

import com.rojae.blog.domain.model.entity.Post;
import com.rojae.blog.infrastructure.dao.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rojae.blog.domain.model.entity.Hello;
import com.rojae.blog.infrastructure.dao.HelloDao;

@Controller
public class HelloRestController {

    @Autowired
    private HelloDao helloDao;

    @Autowired
    private PostDao postDao;

    @RequestMapping("/hello")
    public String index(Model model) {
        model.addAttribute("name", "SpringBlog from rojae");
        return "hello";
    }

    @RequestMapping("/blog")
    public String blog(Model model) {
        return "blog";
    }

    @RequestMapping(value = "/blog/index.html", method = RequestMethod.GET)
    public String index2(Model model) {
        List<Post> postList = postDao.findAll();
        model.addAttribute("postList", postList);
        return "blog";
    }

    @RequestMapping("/post")
    public String post(Model model) {
        return "post";
    }

    @RequestMapping("/add")
    public Hello add(Hello hello) {

        Hello helloData = helloDao.save(hello);

        return helloData;
    }

    @RequestMapping("/list")
    public List<Hello> list(Model model) {

        List<Hello> helloList = helloDao.findAll();

        return helloList;
    }

    @RequestMapping("/")
    public String index() {
        return "helloworld!";
    }
}