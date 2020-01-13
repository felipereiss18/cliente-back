package br.com.felipe.surittec.cliente.util;

import br.com.felipe.surittec.cliente.dto.ClienteDTO;
import br.com.felipe.surittec.cliente.dto.EmailDTO;
import br.com.felipe.surittec.cliente.dto.EnderecoDTO;
import br.com.felipe.surittec.cliente.dto.TelefoneDTO;
import br.com.felipe.surittec.cliente.entity.Cliente;
import br.com.felipe.surittec.cliente.entity.Email;
import br.com.felipe.surittec.cliente.entity.Endereco;
import br.com.felipe.surittec.cliente.entity.Telefone;

import java.util.stream.Collectors;

public class Converter {


    public static ClienteDTO clienteToDTO(Cliente cliente) {
        ClienteDTO dto = ConverterUtil.converterToDTO(cliente, ClienteDTO.class, "telefones", "emails");
        dto.setEndereco(Converter.enderecoToDTO(cliente.getEndereco()));

        dto.setTelefones(cliente.getTelefones()
                .stream()
                .map(Converter::telefoneToDTO)
                .collect(Collectors.toList()));

        dto.setEmails(cliente.getEmails()
                .stream()
                .map(Converter::emailToDTO)
                .collect(Collectors.toList())
        );

        return dto;
    }

    private static EmailDTO emailToDTO(Email email) {
        return ConverterUtil.converterToDTO(email, EmailDTO.class, "cliente");
    }

    private static EnderecoDTO enderecoToDTO(Endereco endereco) {
        return ConverterUtil.converterToDTO(endereco, EnderecoDTO.class, "cliente");
    }

    private static TelefoneDTO telefoneToDTO(Telefone telefone) {
        return ConverterUtil.converterToDTO(telefone, TelefoneDTO.class, "cliente");
    }
}
