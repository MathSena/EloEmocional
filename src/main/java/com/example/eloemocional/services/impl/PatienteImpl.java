package com.example.eloemocional.services.impl;

import com.example.eloemocional.models.Patiente;
import com.example.eloemocional.models.Person;
import com.example.eloemocional.models.dtos.PatienteDTO;
import com.example.eloemocional.repository.PatienteRepository;
import com.example.eloemocional.repository.PersonRepository;
import com.example.eloemocional.services.PatienteService;
import com.example.eloemocional.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatienteImpl implements PatienteService {

    @Autowired
    private PatienteRepository repository;

    @Autowired
    private PersonRepository pessonRepository;

    @Override
    public Patiente findById(Integer id) {
        Optional<Patiente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado " + id));
    }

    @Override
    public List<Patiente> findAll() {
        return repository.findAll();
    }

    @Override
    public Patiente create(PatienteDTO objDto) {
        objDto.setId(null);
        validateByCpfAndEmail(objDto);
        Patiente newObj = new Patiente(objDto);
        return repository.save(newObj);
    }

    private void validateByCpfAndEmail(PatienteDTO objDto) {
        Optional<Person> obj = pessonRepository.findByCpf(objDto.getCpf());

        if (obj.isPresent() && !Objects.equals(obj.get()
                .getId(), objDto.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        obj = pessonRepository.findByEmail(objDto.getEmail());

        if (obj.isPresent() && !Objects.equals(obj.get()
                .getId(), objDto.getId())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }
    }

    @Override
    public Patiente update(Integer id, @Valid PatienteDTO objDto) {
        objDto.setId(id);
        findById(id);
        Patiente oldObj;
        validateByCpfAndEmail(objDto);
        oldObj = new Patiente(objDto);
        return repository.save(oldObj);
    }

    @Override
    public void delete(Integer id) {
        Patiente obj = findById(id);

        if (obj.getSessions()
                .isEmpty()) {
            repository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("Paciente possui sessões e não pode ser deletado!");
        }
    }
}
