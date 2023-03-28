package com.qiaose.controller;

import com.qiaose.entity.security.User;
import com.qiaose.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashMap;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index() {
        return "welcome";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/welcome")
    public String welcome(Model model, Authentication authentication) {
        String username = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        User principal = (User) authentication.getPrincipal();

        log.info("{},{},{}",username,authorities,principal);
        model.addAttribute("username", username);
        model.addAttribute("roles", principal.getRoles());
        model.addAttribute("authorities",authorities);
        return "welcome";
    }

}
