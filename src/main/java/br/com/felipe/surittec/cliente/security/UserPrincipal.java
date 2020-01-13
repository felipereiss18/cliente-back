package br.com.felipe.surittec.cliente.security;

import br.com.felipe.surittec.cliente.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EqualsAndHashCode
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = -181326870758836880L;

	@Getter private Long id;

	@Getter private String perfil;

	@Getter private String username;

	@JsonIgnore
	@Getter private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Long id, String username, String perfil, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.perfil = perfil;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public UserPrincipal(Long id){
		this.id = id;
	}

	public static UserPrincipal create(Usuario usuario) {

		List<GrantedAuthority> authorities = Stream.of(new SimpleGrantedAuthority(usuario.getPerfil()))
				.collect(Collectors.toList());

		return new UserPrincipal(usuario.getId(), usuario.getLogin(), usuario.getPerfil(), usuario.getSenha(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
