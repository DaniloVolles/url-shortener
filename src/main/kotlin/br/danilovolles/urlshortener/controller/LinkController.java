package br.danilovolles.urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.danilovolles.urlshortener.LinkService.LinkService;
import br.danilovolles.urlshortener.dto.LinkInputDTO;
import br.danilovolles.urlshortener.dto.ResponseDTO;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<?>> saveNewLink(@Valid @RequestBody LinkInputDTO newLink) throws Exception {  
        return linkService.saveNewLink(newLink);
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDTO<?>> getLinkByLongLink(@RequestBody LinkInputDTO link) throws Exception {
        String linkRequest = link.longUrl();
        return linkService.getLinkByLongLink(linkRequest);
    }
    

    // @GetMapping("/getLink/{link}")
    // public ResponseEntity<ResponseDTO<?>> getMethodName(@PathVariable String link) throws Exception {
    //     return linkService.getLink(link);
    // }
    
    
}
