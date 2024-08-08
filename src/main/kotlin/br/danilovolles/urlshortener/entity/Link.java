package br.danilovolles.urlshortener.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Link {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank
    private String url;

    public Link(String url) {
        this.url = url;
    }
}