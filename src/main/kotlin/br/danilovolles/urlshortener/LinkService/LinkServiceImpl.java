package br.danilovolles.urlshortener.LinkService;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.danilovolles.urlshortener.dto.ApiResponseStatus;
import br.danilovolles.urlshortener.dto.LinkInputDTO;
import br.danilovolles.urlshortener.dto.LinkOutputDTO;
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
            
            if (linkRepository.findByLongUrl(newLink.longUrl()) != null) {
                throw new Exception("Link already shortened in our database");
            }
     
            String shortenedUrl;
            do {
                shortenedUrl = shortenUrl();
            } while (linkRepository.findByShortUrl(shortenedUrl) != null);
            
            Link newLinkToSave = new Link(
                newLink.longUrl(),
                shortenedUrl
            );
            
            linkRepository.save(newLinkToSave);

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "New Link added to our database"));

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    public ResponseEntity<ResponseDTO<?>> getLinkByLongLink(String existingLongLink) throws Exception{
        try {
        
            Link link = linkRepository
                .findByLongUrl(existingLongLink);

            if (link == null) {
                throw new Exception("URL not found in our database");
            }

            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO<>(ApiResponseStatus.SUCCESS.name(), link));
                
        } catch (Exception e) {
            throw new Exception();
        }
    }

    
    private String shortenUrl(){

        int manyChar = 8; // 8 caracteres 

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder randomString = new StringBuilder(manyChar);

        for (int i = 0; i < manyChar; i++) {
            randomString.append(chars.charAt(random.nextInt(chars.length())));
        }

        return randomString.toString();
    }

}
