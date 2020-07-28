//package com.rojae.blog.test;
//
//import com.rojae.blog.infrastructure.dao.PostDao;
//import com.rojae.blog.infrastructure.dto.UserDto;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.annotation.Resource;
//import java.time.LocalDateTime;
//
//@SpringBootTest
//@Component
//@RunWith(SpringJUnit4ClassRunner.class)
//public class BootTest {
//
//    @Resource(name="userService")
//    private UserService userService;
//
//    private PostDao postDao;
//
//    //@Resource(name = "userService")
//    //private static UserService userService;
//
//    @Test
//    public void Test() {
//
//        System.out.println("-------- TEST ---------");
//
//        System.out.println("START TEST");
//
//        LocalDateTime now = LocalDateTime.of(2019, 12, 6, 0, 0, 0);
//        LocalDateTime now2 = LocalDateTime.of(2019, 12, 6, 0, 0, 0);
//
//        UserDto userDto = UserDto.builder()
//                .userName("rojae")
//                .userEmail("Email@gmail.com")
//                //.accessToken("ACCESS_TOKEN")
//               //.createdDate(now)
//                //.modifiedDate(now2)
//                .build();
//
//        System.out.println(userDto.getId());
//        System.out.println(userDto.getUserName());
//        System.out.println(userDto.getUserEmail());
//     //   System.out.println(userDto.getAccessToken());
//    //    System.out.println(userDto.getCreatedDate());
//     //   System.out.println(userDto.getModifiedDate());
//        //userDao.save(new User(userDto.getId(), userDto.getUserName(), userDto.getUserEmail(), userDto.getAccessToken()));
//
//      //  userService.saveUser(userDto);
//
//        System.out.println("END");
//    }
//
//}