package com.example.bbs.shiro;

import com.example.bbs.mapper.user.bean.UserEntity;
import com.example.bbs.service.basic.UserService;
import com.example.bbs.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwt = (JwtToken) token;
        log.info("jwt----------------->{}", jwt);
        String userId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();
        UserEntity user = userService.getById(userId);
        if(user == null) {
            throw new UnknownAccountException("账户不存在！");
        }
        //AccountProfile profile = new AccountProfile();
        //BeanUtil.copyProperties(user, profile);
        //log.info("profile----------------->{}", user.toString());
        return new SimpleAuthenticationInfo(user, jwt.getCredentials(), getName());
    }
}
