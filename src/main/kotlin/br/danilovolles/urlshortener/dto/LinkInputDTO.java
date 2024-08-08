package br.danilovolles.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;

public record LinkInputDTO(

    @NotBlank(message = "URL is required")
    String url

) {}
