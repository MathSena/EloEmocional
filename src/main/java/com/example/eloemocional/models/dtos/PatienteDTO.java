package com.example.eloemocional.models.dtos;

import com.example.eloemocional.models.Patiente;
import com.example.eloemocional.models.enums.UserProfile;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatienteDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID cannot be null")
    private Integer id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "CPF cannot be null")
    private String cpf;

    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Password cannot be null")
    private String password;

    protected Set<Integer> profiles = new HashSet<>();
    protected LocalDate dateCreation = LocalDate.now();


    public PatienteDTO(Patiente objDto) {
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
