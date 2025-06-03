package com.tcs.controller;

import com.tcs.dto.PersonDTO;
import com.tcs.services.PostService;
import com.tcs.client.ExternalApiException;
import com.tcs.dto.GenericResponse;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private final PostService postService;
    
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<GenericResponse<PersonDTO>> getAllsBooks(){
        List<PersonDTO> list = postService.getPosts();
        List<PersonDTO> obfuscatedList = list.stream()
        .map(this::obfuscateSensitiveData)
        .toList();
        return ResponseEntity.ok(new GenericResponse<>(200,"success",obfuscatedList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<PersonDTO>> getPersonById(
            @PathVariable @jakarta.validation.constraints.Positive(message = "ID must be positive") Integer id) {
        List<PersonDTO> persons = postService.getPosts();
        return persons.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .map(this::obfuscateSensitiveData)
                .map(person -> ResponseEntity.ok(new GenericResponse<>(200, "success", List.of(person))))
                .orElseThrow(() -> new com.tcs.exception.ModelNotFoundException("Person not found with ID: " + id));
    }

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<GenericResponse<PersonDTO>> handleExternalApiException(ExternalApiException ex) {
        return ResponseEntity
            .status(502)
            .body(new GenericResponse<>(502, "External API error: " + ex.getMessage(), List.of()));
    }
    
    @ExceptionHandler(com.tcs.exception.ModelNotFoundException.class)
    public ResponseEntity<GenericResponse<PersonDTO>> handleModelNotFoundException(com.tcs.exception.ModelNotFoundException ex) {
        logger.warn("Person not found: {}", ex.getMessage());
        return ResponseEntity
            .status(404)
            .body(new GenericResponse<>(404, ex.getMessage(), List.of()));
    }

    private PersonDTO obfuscateSensitiveData(PersonDTO dto) {
        return new PersonDTO(
            dto.id(),
            dto.name(),      // name
            dto.username(),       // username
            dto.email(),  // email
            "99999999999",        // phone
            dto.website()     // website
        );
    }
}