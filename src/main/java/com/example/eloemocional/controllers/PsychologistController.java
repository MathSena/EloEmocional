package com.example.eloemocional.controllers;

import com.example.eloemocional.models.Psychologist;
import com.example.eloemocional.models.dtos.PsychologistDTO;
import com.example.eloemocional.services.PsychologistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/psychologists")
public class PsychologistController {

    @Autowired
    private PsychologistService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PsychologistDTO> findById(@PathVariable Integer id) {
        Psychologist obj = service.findById(id);
        return ResponseEntity.ok()
                .body(new PsychologistDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<PsychologistDTO>> findAll() {
        List<Psychologist> list = service.findAll();
        List<PsychologistDTO> listDTO = list.stream()
                .map(PsychologistDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(listDTO);
    }

    @PostMapping
    public ResponseEntity<PsychologistDTO> create(@Valid @RequestBody PsychologistDTO objDto) {
        Psychologist newObj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newObj.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PsychologistDTO> update(@PathVariable Integer id, @Valid @RequestBody PsychologistDTO objDto) {
        Psychologist obj = service.update(id, objDto);
        return ResponseEntity.ok()
                .body(new PsychologistDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PsychologistDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent()
                .build();
    }
}
