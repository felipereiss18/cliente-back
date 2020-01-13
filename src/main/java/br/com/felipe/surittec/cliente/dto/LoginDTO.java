package br.com.felipe.surittec.cliente.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class LoginDTO implements Serializable {

	private Long id;

	@NotBlank(message = "erro.naoinformado:Login")
	private String login;

	@NotBlank(message = "erro.naoinformado:Senha")
	private String senha;

	private String perfil;
}
