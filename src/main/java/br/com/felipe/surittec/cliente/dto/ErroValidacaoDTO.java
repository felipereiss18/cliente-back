package br.com.felipe.surittec.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErroValidacaoDTO {

    private String campo;

    private String descricaoErro;

}
