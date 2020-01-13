package br.com.felipe.surittec.cliente.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @OneToOne(fetch = FetchType.LAZY, optional = false, orphanRemoval = true, cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Telefone> telefones;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Email> emails;
}
