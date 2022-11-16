package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.model.Usuario;
import br.com.alura.aluraflix.model.Videos;
import br.com.alura.aluraflix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public @ResponseBody Usuario cadastrarUsuario (@Valid Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        return usuario;
    }

    @GetMapping
    public Iterable<Usuario> listarUsuarios (){
        return usuarioRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterarUsuario (@PathVariable int id, @Valid Usuario usuario){
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isPresent()){
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void excluirUsuario (@PathVariable int id){
        usuarioRepository.deleteById(id);
    }

}
