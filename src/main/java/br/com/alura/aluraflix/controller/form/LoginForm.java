package br.com.alura.aluraflix.controller.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
    @Getter
    @Setter
    private String email;

    @Getter @Setter
    private String senha;

    public UsernamePasswordAuthenticationToken converter (){
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
