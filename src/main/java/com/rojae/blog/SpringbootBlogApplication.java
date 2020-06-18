package com.rojae.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@PropertySource({
        "classpath:application-oauth.properties",
        "classpath:application-ssh.properties"
})
public class SpringbootBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogApplication.class, args);
    }

}

