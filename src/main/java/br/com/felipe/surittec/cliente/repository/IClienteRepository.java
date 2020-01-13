package br.com.felipe.surittec.cliente.repository;

import br.com.felipe.surittec.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

}
