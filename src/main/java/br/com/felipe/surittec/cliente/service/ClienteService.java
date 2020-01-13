package br.com.felipe.surittec.cliente.service;

import br.com.felipe.surittec.cliente.entity.Cliente;
import br.com.felipe.surittec.cliente.repository.IClienteRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ClienteService extends ServiceAbstract<Cliente, IClienteRepository> {

    @Autowired
    public ClienteService(IClienteRepository repo){
        this.repository = repo;
    }


}
