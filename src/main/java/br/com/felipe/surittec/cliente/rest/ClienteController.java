package br.com.felipe.surittec.cliente.rest;

import br.com.felipe.surittec.cliente.annotation.PerfilAdministrador;
import br.com.felipe.surittec.cliente.annotation.PerfilGlobal;
import br.com.felipe.surittec.cliente.dto.ClienteDTO;
import br.com.felipe.surittec.cliente.dto.ResponseDTO;
import br.com.felipe.surittec.cliente.entity.Cliente;
import br.com.felipe.surittec.cliente.service.ClienteService;
import br.com.felipe.surittec.cliente.util.Converter;
import br.com.felipe.surittec.cliente.util.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    @PerfilAdministrador
    public ResponseEntity<ResponseDTO> cadastrar(@RequestBody @Valid ClienteDTO dto){
        Cliente cliente = ConverterUtil.deepConvertToDTO(dto, Cliente.class);

        Cliente finalCliente = cliente;
        cliente.getTelefones().stream().forEach(telefone -> {
            telefone.setCliente(finalCliente);
        });

        cliente.getEmails().stream().forEach(email -> {
            email.setCliente(finalCliente);
        });

        cliente = service.salvar(cliente);

        return new ResponseEntity<>(
                new ResponseDTO(cliente.getId(),"sucesso.salvo:Cliente"),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    @PerfilGlobal
    public ResponseEntity<ResponseDTO> buscarPorId(@PathVariable(name = "id") Long id){
        ClienteDTO dto = Converter.clienteToDTO(service.buscarPorId(id));

        return new ResponseEntity<>(new ResponseDTO(dto), HttpStatus.OK);
    }

    @GetMapping
    @PerfilGlobal
    public ResponseEntity<ResponseDTO> listar(@PageableDefault Pageable pageable){
        List<ClienteDTO> lista = service.listar(pageable)
                .stream()
                .map(Converter::clienteToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ResponseDTO(lista), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PerfilAdministrador
    public ResponseEntity<ResponseDTO> editar(@PathVariable(name = "id") Long id, @RequestBody ClienteDTO dto){
        Cliente cliente = ConverterUtil.deepConvertToDTO(dto, Cliente.class);
        cliente.setId(id);

        Cliente finalCliente = cliente;
        cliente.getTelefones().stream().forEach(telefone -> {
            telefone.setCliente(finalCliente);
        });

        cliente.getEmails().stream().forEach(email -> {
            email.setCliente(finalCliente);
        });

        service.editar(cliente);

        return new ResponseEntity<>(new ResponseDTO("sucesso.alterado:Cliente"), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PerfilAdministrador
    public ResponseEntity<ResponseDTO> excluir(@PathVariable(name = "id") Long id){
        service.excluir(id);

        return new ResponseEntity<>(new ResponseDTO("sucesso.removido:Cliente"), HttpStatus.OK);
    }

}
