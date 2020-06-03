package com.rojae.blog.application.configuration;

import static com.rojae.blog.application.configuration.SocialType.*;

import com.rojae.blog.application.utility.QuickSignInAdapter;
import com.rojae.blog.domain.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.security.AuthenticationNameUserIdSource;

// https://stackoverflow.com/questions/19525380/difference-between-role-and-grantedauthority-in-spring-security
// https://parandol.tistory.com/14

// Resource Server
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/**/write*", "/**/edit*", "/**/delete*").hasRole(Role.USER.name())
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll()
                .and()
                    .oauth2Login()
                    .loginPage("/user/login")
                    .defaultSuccessUrl("/loginSuccess")
                    .failureUrl("/loginFailure")

                .and().headers().frameOptions().sameOrigin()
                .and().csrf().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/user/login"));
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("인증 중....");
        auth.inMemoryAuthentication()
                .withUser("email")
                .password("{noop}1111")
                .roles("MANAGER");
    }
}