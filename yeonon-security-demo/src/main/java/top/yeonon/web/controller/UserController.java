package top.yeonon.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import top.yeonon.dto.User;
import top.yeonon.dto.UserCondition;
import top.yeonon.exception.UserServiceException;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/regist")
    public void register(User user, HttpServletRequest request) {
        //注册用户
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }


    @GetMapping
    @JsonView(User.UserSampleView.class)
    public List<User> query(UserCondition condition,
                            @PageableDefault(size = 10, page = 2, sort = "username,asc") Pageable pageable) {
        System.out.println(pageable);
        List<User> userList = new ArrayList<>();
        System.out.println(condition);
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    @GetMapping("{id}")
    @JsonView(User.UserDetailView.class)
    public User getUserInfo(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("yeonon");
        user.setPassword("124563");
        System.out.println("getUserInfo 开始");
        return user;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {

//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
//        }

        user.setId(2);
        System.out.println(user);
        System.out.println(user.getBirthday());
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user);
        return user;
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("delete " + id);
    }

}
