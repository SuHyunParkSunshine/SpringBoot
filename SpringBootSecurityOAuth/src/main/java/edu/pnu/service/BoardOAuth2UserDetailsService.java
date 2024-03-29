package edu.pnu.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class BoardOAuth2UserDetailsService extends DefaultOAuth2UserService{

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) {
		OAuth2User ouser = super.loadUser(userRequest);
		System.out.println("OAuth2User" + ouser.getAttributes());
		return ouser;
	}
}
