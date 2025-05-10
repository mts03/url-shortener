package com.study.encurtador.controllers;

import com.study.encurtador.dtos.URLRequestDto;
import com.study.encurtador.dtos.URLResponseDTO;
import com.study.encurtador.models.URLModel;
import com.study.encurtador.repositories.URLRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Implementação dos Métodos CRUD
@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class URLController {

    @Autowired
    private URLRepository repository;

    //Método GET By ID
    @GetMapping("url/{id}")
    public ResponseEntity<URLModel> getProduct(@PathVariable UUID id){
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Método para redirecionar do link encurtado para o link original
    @GetMapping("/{id}")
    public ResponseEntity<URLModel> redirect(@PathVariable UUID id){
        Optional<URLModel> model = repository.findById(id);
        return model.<ResponseEntity<URLModel>>map(urlModel -> ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(urlModel.getBigUrl())) //Cria o redirecionamento
                .build()).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Método GET All
    @GetMapping("/url")
    public ResponseEntity<List<URLModel>> getAll(){
        List<URLModel> urls = repository.findAll();
        return ResponseEntity.ok(urls);
    }

    //Método POST
    @PostMapping("/url")
    public ResponseEntity<URLModel> encurtarUrl(@RequestBody URLRequestDto request){
        URLModel newUrl = new URLModel();
        newUrl.setBigUrl(request.originalUrl());

        URLModel savedUrl = repository.save(newUrl);
        UUID shortUrl = savedUrl.getIdUrl();
        newUrl.setIdUrl(shortUrl);

        URLModel updatedUrl = repository.save(savedUrl);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUrl);

    }
}

