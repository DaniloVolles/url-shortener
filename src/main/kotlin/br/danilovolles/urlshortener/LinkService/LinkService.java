package br.danilovolles.urlshortener.LinkService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.danilovolles.urlshortener.dto.LinkInputDTO;
import br.danilovolles.urlshortener.dto.ResponseDTO;

@Service
public interface LinkService {

    ResponseEntity<ResponseDTO<?>> saveNewLink(LinkInputDTO newLink) throws Exception;
    
}
