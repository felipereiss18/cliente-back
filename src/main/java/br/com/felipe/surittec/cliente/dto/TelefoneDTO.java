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
public class TelefoneDTO {

    private Long id;

    @NotBlank(message = "validacao.naoinformado:Tipo")
    private int tipo;

    @NotBlank(message = "validacao.naoinformado:Número")
    private String numero;
}
