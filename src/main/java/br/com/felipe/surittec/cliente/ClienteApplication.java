package br.com.felipe.surittec.cliente;

import br.com.felipe.surittec.cliente.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class ClienteApplication extends SpringBootServletInitializer {

    @Autowired
    private MessageSource messageSource;

    public static void main(String[] args) {
        SpringApplication.run(ClienteApplication.class, args);
    }

    @PostConstruct
    public void started(){
        Locale.setDefault(new Locale("pt", "BR"));
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
        MessageUtil.injectMessageSource(messageSource);
    }

}
