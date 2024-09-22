package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstudianteDTO {
    private int id;

    @NotNull
    @Size(min = 2, max = 50)
    private String nombres;

    @NotNull
    @Size(min = 2, max = 100)
    private String apellidos;

    @NotNull
    @Size(min = 8, max = 8)
    private String dni;

    @NotNull
    private int edad;
}
