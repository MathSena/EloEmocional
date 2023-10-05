package com.example.eloemocional.models;

import com.example.eloemocional.models.dtos.PatienteDTO;
import com.example.eloemocional.models.enums.UserProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Patiente extends Person {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "patiente")
    private List<Session> sessions = new ArrayList<>();

    public Patiente(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);

    }

    public Patiente(PatienteDTO objDto) {
        super();
        this.id = objDto.getId();
        this.name = objDto.getName();
        this.cpf = objDto.getCpf();
        this.email = objDto.getEmail();
        this.password = objDto.getPassword();
        this.profiles = objDto.getProfiles()
                .stream()
                .map(UserProfile::toEnumProfile)
                .map(UserProfile::getCode)
                .collect(Collectors.toSet());


        this.dateCreation = objDto.getDateCreation();
    }
}
