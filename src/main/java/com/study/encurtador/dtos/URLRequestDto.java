package com.study.encurtador.dtos;

import jakarta.validation.constraints.NotBlank;

//Entrada
//Representar os dados enviados pelo usuario ao fazer a requisição para encurtar um URL
public record URLRequestDto(@NotBlank String originalUrl) {
}
