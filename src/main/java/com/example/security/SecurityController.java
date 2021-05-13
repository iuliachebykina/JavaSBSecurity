package com.example.security;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/public/api/test")
    public String publicApi() {
        return getNameAndRoles();
    }

    @GetMapping("/admin/api/test")
    public String adminApi() {
        return getNameAndRoles();
    }

    @GetMapping("/support/api/test")
    public String supportApi() {
        return getNameAndRoles();
    }

    private String getNameAndRoles() {
        var context = SecurityContextHolder.getContext().getAuthentication();
        var name = context.getName();
        var r = context.getAuthorities().toString();
        var roles = r.substring(1, r.length() - 1);
        return String.format("Name: %s \t Role: %s", name, roles);
    }

}
