package br.com.felipe.surittec.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "valid.naoinformado:Login")
    @Size(min = 3, max = 100, message = "validacao.tamanho:3:100")
    private String nome;

    @NotBlank(message = "validacao.naoinformado:CPF")
    private String cpf;

    @NotNull(message = "validacao.naoinformado:Endereço")
    private EnderecoDTO endereco;

    @NotNull(message = "validacao.naoinformado:Telefone")
    @Size(message = "validacao.min:1", min = 1)
    private List<TelefoneDTO> telefones;

    @NotNull(message = "validacao.naoinformado:E-mail")
    @Size(message = "validacao.min:1", min = 1)
    private List<EmailDTO> emails;
}
