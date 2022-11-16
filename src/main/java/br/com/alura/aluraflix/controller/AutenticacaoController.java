package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.config.security.TokenService;
import br.com.alura.aluraflix.controller.dto.TokenDto;
import br.com.alura.aluraflix.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Profile(value = {"prod", "test"})
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        if (dadosLogin.isAuthenticated()){
            try {
                Authentication authentication = authManager.authenticate(dadosLogin);
                String token = tokenService.gerarToken(authentication);
                return ResponseEntity.ok(new TokenDto(token, "Bearer"));
            }catch (AuthenticationException e){
                return ResponseEntity.badRequest().body("Não autorizado");
            }
        }
        else {
            return ResponseEntity.badRequest().body("Usuário e/ou senha inválidos");
        }


    }
}
