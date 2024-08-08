package br.danilovolles.urlshortener.LinkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.danilovolles.urlshortener.dto.ApiResponseStatus;
import br.danilovolles.urlshortener.dto.LinkInputDTO;
import br.danilovolles.urlshortener.dto.ResponseDTO;
import br.danilovolles.urlshortener.entity.Link;
import br.danilovolles.urlshortener.repository.LinkRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkRepository linkRepository;
    
    @Override
    public ResponseEntity<ResponseDTO<?>> saveNewLink(LinkInputDTO newLink) throws Exception {
        try {
            
            if (linkRepository.findByUrl(newLink.url()) != null) {
                throw new Exception("Link already shortened in our database");
            }
            
            Link newLinkToSave = new Link(
                newLink.url()
            );
            
            linkRepository.save(newLinkToSave);

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "New Link added to our database"));

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
}
