package haluva.banck.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity conflitoDeCadastro() {
        return ResponseEntity.badRequest().body("Cliente já existe no haluvaBanck");
    }

    @ExceptionHandler(ExcepetionTransacaoRetiradaInvalida.class)
    public ResponseEntity erroAoRetirar() {
        return ResponseEntity.badRequest().body("Sua retirada falhou, causas: saldo insuficiente ou você não tem uma conta do tipo ESPECIAL. ");
    }

    @ExceptionHandler(ExcepetionTransacaoTipoDeContaInvalida.class)
    public ResponseEntity tipoDeContaInvalida() {
        return ResponseEntity.badRequest().body("Atenção somente contas do tipo CORRENTE e ESPECIAL podem fazer transferencia");
    }

    @ExceptionHandler(ExcepetionValidacaoDeAgendamento.class)
    public ResponseEntity excepetionAgendamentos( ExcepetionValidacaoDeAgendamento ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
