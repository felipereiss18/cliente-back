package br.com.felipe.surittec.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailDTO {

    private Long id;

    @NotBlank(message = "valid.naoinformado:E-mail")
    private String descricao;
}
