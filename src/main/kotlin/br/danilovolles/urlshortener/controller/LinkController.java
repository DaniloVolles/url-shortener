package br.danilovolles.urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.danilovolles.urlshortener.LinkService.LinkService;
import br.danilovolles.urlshortener.dto.LinkInputDTO;
import br.danilovolles.urlshortener.dto.ResponseDTO;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<?>> saveNewLink(@Valid @RequestBody LinkInputDTO newLink) throws Exception {  
        return linkService.saveNewLink(newLink);
    }
    
}
