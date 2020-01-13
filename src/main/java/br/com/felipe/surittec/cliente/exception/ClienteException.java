package br.com.felipe.surittec.cliente.exception;

import br.com.felipe.surittec.cliente.dto.ResponseDTO;
import br.com.felipe.surittec.cliente.util.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteException extends RuntimeException {

    private HttpStatus httpStatus;

    public ClienteException(){
        super("Ocorreu um erro.");
    }

    public ClienteException(String message){
        super(message);
    }

    public ClienteException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public ResponseEntity<ResponseDTO> getResponseDTO(){
        if(httpStatus != null){
            return ResponseEntity.status(httpStatus).body(new ResponseDTO(null, MessageUtil.tratarMensagem(this.getMessage()), httpStatus.value()));
        }else {
            return ResponseEntity.badRequest().body(new ResponseDTO(null, MessageUtil.tratarMensagem(this.getMessage()), HttpStatus.BAD_REQUEST.value()));
        }
    }

}
