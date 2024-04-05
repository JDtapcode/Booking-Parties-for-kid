package com.swp.birthdaybooking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum Role {
    UNAUTHORIZEGUEST(Set.of()),
    USER(Set.of(
            Permission.USER_READ,
            Permission.USER_CREATE,
            Permission.USER_UPDATE,
            Permission.USER_DELETE
    )),
    HOST(Set.of(
            Permission.USER_READ,
            Permission.USER_CREATE,
            Permission.USER_UPDATE,
            Permission.USER_DELETE,
            Permission.HOST_READ,
            Permission.HOST_CREATE,
            Permission.HOST_UPDATE,
            Permission.HOST_DELETE
    )),

    ADMIN(Set.of(
            Permission.USER_READ,
            Permission.USER_CREATE,
            Permission.USER_UPDATE,
            Permission.USER_DELETE,
            Permission.HOST_READ,
            Permission.HOST_CREATE,
            Permission.HOST_UPDATE,
            Permission.HOST_DELETE,
            Permission.ADMIN_READ,
            Permission.ADMIN_CREATE,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE
    ));

    @Getter
    private final Set<Permission> permission;

    public List<SimpleGrantedAuthority> getAuthorities() {
        System.out.println(getPermission());
        var authorities =
                getPermission().stream()
                        .map(permission1 -> new SimpleGrantedAuthority(permission1.getPermission()))
                        .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }


}
