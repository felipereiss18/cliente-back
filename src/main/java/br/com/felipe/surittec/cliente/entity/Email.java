package br.com.felipe.surittec.cliente.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Email extends AbstractEntity<Long>{

    @Id
    @GeneratedValue
    private Long id;

    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;
}
