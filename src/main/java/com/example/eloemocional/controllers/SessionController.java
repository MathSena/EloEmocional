package com.example.eloemocional.controllers;

import com.example.eloemocional.models.Session;
import com.example.eloemocional.models.dtos.SessionDTO;
import com.example.eloemocional.services.SessionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/sessions")
public class SessionController {

    @Autowired
    private SessionService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SessionDTO> findById(@PathVariable Integer id) {
        Session obj = service.findById(id);
        return ResponseEntity.ok()
                .body(new SessionDTO(obj));
    }

    @GetMapping()
    public ResponseEntity<List<SessionDTO>> findAll() {
        List<Session> list = service.findAll();
        List<SessionDTO> listDTO = list.stream()
                .map(SessionDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(listDTO);
    }

    @PostMapping
    public ResponseEntity<SessionDTO> create(@Valid @RequestBody SessionDTO objDto) throws IllegalAccessException {
        Session obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SessionDTO> update(@PathVariable Integer id, @Valid @RequestBody SessionDTO objDto) throws IllegalAccessException {
        Session newObj = service.update(id, objDto);
        return ResponseEntity.ok()
                .body(new SessionDTO(newObj));
    }
}

