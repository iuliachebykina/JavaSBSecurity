package com.example.security;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.stream.Collectors;

@RestController
public class SecurityController {


    @PermitAll
    @GetMapping("/public/api/test")
    public AuthDTO publicApi() {
        return getNameAndRoles();
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/admin/api/test")
    public AuthDTO adminApi() {
        return getNameAndRoles();
    }

    @RolesAllowed("ROLE_SUPPORT")
    @GetMapping("/support/api/test")
    public AuthDTO supportApi() {
        return getNameAndRoles();
    }

    private AuthDTO getNameAndRoles() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var name = auth.getName();
        var roles = auth.getAuthorities().stream().map(Object::toString).collect(Collectors.toList());
        return new AuthDTO(name, roles);
    }

}
