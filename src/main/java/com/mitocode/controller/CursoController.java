package com.mitocode.controller;

import com.mitocode.dto.CursoDTO;
import com.mitocode.dto.GenericResponse;
import com.mitocode.model.Curso;
import com.mitocode.service.ICursoService;
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
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final ICursoService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<GenericResponse<CursoDTO>> getAllsBooks(){
        List<CursoDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(new GenericResponse<>(200,"success",list));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CursoDTO dto) {
        Curso obj = service.save(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<CursoDTO>> update(@PathVariable("id") Integer id, @Valid @RequestBody CursoDTO dto) {
        Curso obj = service.update(id, convertToEntity(dto));

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(convertToDto(obj))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private CursoDTO convertToDto(Curso obj) { return modelMapper.map(obj, CursoDTO.class); }

    private Curso convertToEntity(CursoDTO dto) {
        return modelMapper.map(dto, Curso.class);
    }
}