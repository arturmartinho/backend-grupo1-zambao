package br.insper.grupo1.controller;

import br.insper.grupo1.dto.CidadeDTO;
import br.insper.grupo1.model.Cidade;
import br.insper.grupo1.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cidades")
@CrossOrigin(origins = "http://localhost:3000")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @PostMapping
    public ResponseEntity<Cidade> createCidade(@RequestBody CidadeDTO cidadeDTO) {
        Cidade cidade = new Cidade(
                cidadeDTO.getNome(),
                cidadeDTO.getEstado(),
                cidadeDTO.getPais()
        );
        Cidade createdCidade = cidadeService.saveCidade(cidade);
        return new ResponseEntity<>(createdCidade, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> getCidadeById(@PathVariable String id) {
        Cidade cidade = cidadeService.getCidadeById(id);
        return new ResponseEntity<>(cidade, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> getAllCidades() {
        List<Cidade> cidades = cidadeService.getAllCidades();
        return new ResponseEntity<>(cidades, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCidadeById(@PathVariable String id) {
        cidadeService.deleteCidadeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}