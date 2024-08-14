package br.danilovolles.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.danilovolles.urlshortener.entity.Link;
import java.util.List;


public interface LinkRepository extends JpaRepository <Link, Long> {
    Link findByLongUrl(String longUrl) throws Exception;
    Link findByShortUrl(String shortUrl);
}
