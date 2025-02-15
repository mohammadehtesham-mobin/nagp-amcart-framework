package com.nagp.amcart.security.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.nagp.amcart.security.constant.SecurityConstant.COGNITO_GROUPS_CLAIMS;
import static com.nagp.amcart.security.constant.SecurityConstant.ROLE_PREFIX;

public class CognitoAuthorityConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        List<String> cognitoGroups = jwt.getClaimAsStringList(COGNITO_GROUPS_CLAIMS);
        if (cognitoGroups == null || cognitoGroups.isEmpty()) {
            return Collections.emptyList();
        }

        return cognitoGroups.stream()
                .map(group -> new SimpleGrantedAuthority(ROLE_PREFIX + group.toUpperCase()))
                .collect(Collectors.toList());
    }
}
