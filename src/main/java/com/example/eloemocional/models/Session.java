package com.example.eloemocional.models;

import com.example.eloemocional.models.enums.CaseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Session implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate sessionCreationDate = LocalDate.now();
    private String sessionDate;
    private String notes;
    private CaseStatus status;

    @ManyToOne
    @JoinColumn(name = "psychologist_id")
    private Psychologist psychologist;

    @ManyToOne
    @JoinColumn(name = "patiente_id")
    private Patiente patiente;


}
