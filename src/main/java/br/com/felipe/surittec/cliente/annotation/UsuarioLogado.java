package br.com.felipe.surittec.cliente.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target ({ ElementType.PARAMETER, ElementType.TYPE })
@Retention (RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface UsuarioLogado {
}
