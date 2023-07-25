package edu.pnu.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {

	@Id
	private String username;
	private String password;
	private String role; // "MEMBER", "MEMBER, ADMIN, MANAGER"
	private boolean enabled;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		//case 1
		 return AuthorityUtils.createAuthorityList(role); //"MEMBER, ADMIN, MANAGER"처럼
		// 권한이 여러개 일때 ,(콤마)로 나눠서 리스트를 만들어줌

		 //case 2
//		Collection<GrantedAuthority> list = new ArrayList<>(); // 이렇게 내가 만들어서 사용 가능
//		list.add(new GrantedAuthority() {
//
//			@Override
//			public String getAuthority() {
//				return role;
//			}
//		});
//		return list;
	}
}
