package com.example.eloemocional.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Psychologist extends Person {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "psychologist")
    private List<Session> sessions = new ArrayList<>();
}
