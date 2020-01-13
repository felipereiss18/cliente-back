package br.com.felipe.surittec.cliente.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoTelefone {

    RESIDENCIAL(1, "Residencial"),
    COMERCIAL(2, "Comercial"),
    CELULAR(3, "Celular");

    private int id;

    private String descricao;
}
