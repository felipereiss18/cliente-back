package br.com.felipe.surittec.cliente.exception;

import br.com.felipe.surittec.cliente.dto.ErroValidacaoDTO;
import br.com.felipe.surittec.cliente.dto.ResponseDTO;
import br.com.felipe.surittec.cliente.util.MessageUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Order (Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionMapper {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ResponseDTO> handleValidationExceptions(ValidationException ex){
		return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new ResponseDTO(null, MessageUtil.tratarMensagem(ex.getMessage())));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErroValidacaoDTO> erroValidacaoDTOList = ex.getBindingResult().getFieldErrors()
				.stream()
				.map(e -> new ErroValidacaoDTO(e.getField(), MessageUtil.tratarMensagem(e.getDefaultMessage())))
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new ResponseDTO(erroValidacaoDTOList, MessageUtil.tratarMensagem("erro.validacao")));
	}

	@ExceptionHandler(ClienteException.class)
	public ResponseEntity<ResponseDTO> handleCuriaException(ClienteException ex){
		return ex.getResponseDTO();
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDTO(MessageUtil.tratarMensagem("erro.violacao")));
	}

}
