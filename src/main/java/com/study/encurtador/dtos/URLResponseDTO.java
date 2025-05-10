package com.study.encurtador.dtos;

import com.study.encurtador.models.URLModel;
import jakarta.validation.constraints.NotBlank;

//Saída
//Retornar para o usuário a URL encurtada
public record URLResponseDTO(@NotBlank String shortUrl) {
}
