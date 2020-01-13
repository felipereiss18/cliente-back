package br.com.felipe.surittec.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EnderecoDTO {

    private Long id;

    @NotBlank(message = "validacao.naoinformado:CEP")
    private String cep;

    @NotBlank(message = "validacao.naoinformado:Logradouro")
    private String logradouro;

    private String complemento;

    @NotBlank(message = "validacao.naoinformado:Bairro")
    private String bairro;

    @NotBlank(message = "validacao.naoinformado:Cidade")
    private String cidade;

    @NotBlank(message = "validacao.naoinformado:UF")
    private String uf;
}
