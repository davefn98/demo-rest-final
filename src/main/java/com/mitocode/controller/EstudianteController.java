package com.mitocode.controller;

import com.mitocode.dto.EstudianteDTO;
import com.mitocode.dto.GenericResponse;
import com.mitocode.model.Estudiante;
import com.mitocode.service.ICursoService;
import com.mitocode.service.IEstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final IEstudianteService estudianteService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<GenericResponse<EstudianteDTO>> getAllsBooks(){
        List<EstudianteDTO> list = estudianteService.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(new GenericResponse<>(200,"success",list));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody EstudianteDTO dto) {
        Estudiante obj = estudianteService.save(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<EstudianteDTO>> update(@PathVariable("id") Integer id, @Valid @RequestBody EstudianteDTO dto) {
        Estudiante obj = estudianteService.update(id, convertToEntity(dto));

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(convertToDto(obj))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        estudianteService.delete(id);

        return ResponseEntity.noContent().build();
    }

    private EstudianteDTO convertToDto(Estudiante obj) { return modelMapper.map(obj, EstudianteDTO.class); }

    private Estudiante convertToEntity(EstudianteDTO dto) {
        return modelMapper.map(dto, Estudiante.class);
    }
}