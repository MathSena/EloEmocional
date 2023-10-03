package com.example.eloemocional.models;

import com.example.eloemocional.models.enums.CaseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Session {
    private Long id;
    private String title;
    private LocalDate sessionCreationDate = LocalDate.now();
    private String sessionDate;
    private String notes;
    private CaseStatus status;

    private Psychologist psychologist;
    private Patiente patiente;


}
