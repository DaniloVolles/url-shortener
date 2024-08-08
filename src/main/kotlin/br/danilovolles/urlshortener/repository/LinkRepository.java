package br.danilovolles.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.danilovolles.urlshortener.entity.Link;

public interface LinkRepository extends JpaRepository <Link, Long> {
    Link findByUrl(String url);
}
