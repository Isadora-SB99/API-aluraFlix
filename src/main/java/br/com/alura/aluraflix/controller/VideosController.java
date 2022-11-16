package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.model.Categorias;
import br.com.alura.aluraflix.model.Videos;
import br.com.alura.aluraflix.repository.CategoriasRepository;
import br.com.alura.aluraflix.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class VideosController {

    @Autowired
    private VideosRepository videosRepository;

    @Autowired
    private CategoriasRepository categoriasRepository;

    @GetMapping("/videos/todos")
    public Iterable<Videos> listarTodosVideos(){
        return videosRepository.findAll();
    }

    @GetMapping("/videos/")
    public Iterable<Videos> listarVideos (@RequestParam(value = "search", required = false) String titulo, @RequestParam(value = "page") int pagina){

        Pageable paginacao = PageRequest.of(pagina-1, 5);

        if (titulo==null){
            return videosRepository.findAll(paginacao);
        }
        else {
            return videosRepository.findByTituloContainingIgnoreCase(titulo, paginacao);
        }
    }

    @GetMapping("/videos/{id}")
    public ResponseEntity<Optional<Videos>> obterVideoPeloId(@PathVariable int id){
        Optional<Videos> video = videosRepository.findById(id);
        if (video.isPresent()){
            return ResponseEntity.ok(video);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("categorias/{id}/videos")
    public List<Videos> listarVideosPelaCategoria(@PathVariable int id){
        return videosRepository.findByCategoria(id);
    }

    @GetMapping("/videos/free")
    public Iterable<Videos> videosLivres(){
        Pageable paginacao = PageRequest.of(0, 10);
        return videosRepository.findAll(paginacao);
    }

    @PostMapping("/videos")
    public @ResponseBody Videos cadastrarVideo(@Valid Videos video){
        //se a categoria estiver vazia setar categoria 1
        if (video.getCategoria()==null){
            Categorias cat = categoriasRepository.findByTitulo("Livre");
            video.setCategoria(cat);
        }
        videosRepository.save(video);
        return video;
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, path = "/videos/{id}")
    public ResponseEntity<Videos> alterarVideo(@PathVariable int id, @Valid Videos video){
        Optional<Videos> optional = videosRepository.findById(id);
        if (optional.isPresent()){
            videosRepository.save(video);
            return ResponseEntity.ok(video);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/videos/{id}")
    public ResponseEntity<?> deletarVideo (@PathVariable int id){
        Optional<Videos> video = videosRepository.findById(id);
        if (video.isPresent()){
            videosRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
