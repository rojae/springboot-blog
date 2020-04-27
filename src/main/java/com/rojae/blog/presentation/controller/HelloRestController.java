package com.rojae.blog.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rojae.blog.domain.model.entity.Hello;
import com.rojae.blog.infrastructure.dao.HelloDao;

@Controller
public class HelloRestController {

    @Autowired
    private HelloDao helloDao;

    @RequestMapping("/hello")
    public String index(Model model) {
        model.addAttribute("name", "SpringBlog from rojae");
        return "hello";
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