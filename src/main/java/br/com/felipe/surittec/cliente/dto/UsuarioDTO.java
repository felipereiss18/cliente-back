package br.com.felipe.surittec.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO implements Serializable {

	private Long id;

	@NotBlank(message = "validacao.naoinformado:Login")
	private String login;

	@NotBlank(message = "validacao.naoinformado:Senha")
	private String senha;

	private String tokenApp;

	private String perfil;

}
