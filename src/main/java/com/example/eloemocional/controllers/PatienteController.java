package com.example.eloemocional.controllers;

import com.example.eloemocional.models.Patiente;
import com.example.eloemocional.models.dtos.PatienteDTO;
import com.example.eloemocional.services.PatienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/patientes")
public class PatienteController {

    @Autowired
    private PatienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatienteDTO> findById(@PathVariable Integer id) {
        Patiente obj = service.findById(id);
        return ResponseEntity.ok()
                .body(new PatienteDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<PatienteDTO>> findAll() {
        List<Patiente> list = service.findAll();
        List<PatienteDTO> listDTO = list.stream()
                .map(PatienteDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(listDTO);
    }

    @PostMapping
    public ResponseEntity<PatienteDTO> create(@Valid @RequestBody PatienteDTO objDto) {
        Patiente newObj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newObj.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PatienteDTO> update(@PathVariable Integer id, @Valid @RequestBody PatienteDTO objDto) {
        Patiente obj = service.update(id, objDto);
        return ResponseEntity.ok()
                .body(new PatienteDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PatienteDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent()
                .build();
    }
}

