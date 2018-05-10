package top.sillyfan.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sillyfan.security.dto.User;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {


    @GetMapping("/user")
    public List<User> query(String name) {
        return Arrays.asList(new User("tom", "123"), new User("Jim", "456"));
    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails u) {
        return u;
    }

}
