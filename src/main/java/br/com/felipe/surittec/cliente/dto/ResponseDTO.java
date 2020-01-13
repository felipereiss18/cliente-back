package br.com.felipe.surittec.cliente.dto;

import br.com.felipe.surittec.cliente.util.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private Object data;

    private String message = "Sucesso!";

    private int status = 200;

    public ResponseDTO(Object data){
        this.data = data;
    }

    public ResponseDTO(String message){
        this.message = message;
    }

    public ResponseDTO(Object data, String message){
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return MessageUtil.tratarMensagem(this.message);
    }
}
