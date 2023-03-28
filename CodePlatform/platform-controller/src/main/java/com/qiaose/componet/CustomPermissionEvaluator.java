package com.qiaose.componet;

import com.qiaose.entity.security.Authority;
import com.qiaose.entity.security.Role;
import com.qiaose.entity.security.User;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if(authentication == null || !(permission instanceof String)) {
            return false;
        }

        User user = (User) authentication.getPrincipal();
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();

        return hasPrivilege(user, targetType, permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if(authentication == null || !(permission instanceof String)) {
            return false;
        }
        User user = (User) authentication.getPrincipal();
        return hasPrivilege(user, targetType.toUpperCase(), permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(User user, String targetType, String permission) {
        for(Role role : user.getRoles()) {
            for(Authority p : role.getAuthorities()) {
                if( p.getName().equalsIgnoreCase(permission)) {
                    return true;
                }
            }
        }
        return false;
    }
}
