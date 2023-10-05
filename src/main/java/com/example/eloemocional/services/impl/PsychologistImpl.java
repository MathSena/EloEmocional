package com.example.eloemocional.services.impl;

import com.example.eloemocional.models.Person;
import com.example.eloemocional.models.Psychologist;
import com.example.eloemocional.models.dtos.PsychologistDTO;
import com.example.eloemocional.repository.PersonRepository;
import com.example.eloemocional.repository.PsychologistRepository;
import com.example.eloemocional.services.PsychologistService;
import com.example.eloemocional.services.exceptions.DataIntegrityViolationException;
import com.example.eloemocional.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PsychologistImpl implements PsychologistService {
    @Autowired
    private PsychologistRepository repository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Psychologist findById(Integer id) {
        Optional<Psychologist> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado " + id));
    }

    @Override
    public List<Psychologist> findAll() {
        return repository.findAll();
    }

    @Override
    public Psychologist create(PsychologistDTO objDto) {
        objDto.setId(null);
        validateByCpfAndEmail(objDto);
        Psychologist newObj = new Psychologist(objDto);
        return repository.save(newObj);
    }

    private void validateByCpfAndEmail(PsychologistDTO objDto) {
        Optional<Person> obj = personRepository.findByCpf(objDto.getCpf());

        if (obj.isPresent() && !Objects.equals(obj.get()
                .getId(), objDto.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        obj = personRepository.findByEmail(objDto.getEmail());

        if (obj.isPresent() && !Objects.equals(obj.get()
                .getId(), objDto.getId())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }
    }

    @Override
    public Psychologist update(Integer id, @Valid PsychologistDTO objDto) {
        objDto.setId(id);
        findById(id);
        Psychologist oldObj;
        validateByCpfAndEmail(objDto);
        oldObj = new Psychologist(objDto);
        return repository.save(oldObj);
    }

    @Override
    public void delete(Integer id) {
        Psychologist obj = findById(id);

        if (obj.getSessions()
                .isEmpty()) {
            throw new DataIntegrityViolationException("Psicólogo possui sessões e não pode ser deletado!");
        } else {
            repository.deleteById(id);
        }
    }
}
