package com.example.eloemocional.models.dtos;


import com.example.eloemocional.models.Session;
import com.example.eloemocional.models.enums.CaseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SessionDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID cannot be null")
    private Integer id;

    @NotNull(message = "Title cannot be null")
    private String title;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate sessionCreationDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private String sessionDate;

    @NotNull(message = "Notes cannot be null")
    private String notes;

    @NotNull(message = "Status cannot be null")
    private CaseStatus status;

    @NotNull(message = "Psychologist cannot be null")
    private Integer psychologist;
    @NotNull(message = "Patiente cannot be null")
    private Integer patiente;


    @NotNull(message = "Psychologist cannot be null")
    private String psychologistName;

    @NotNull(message = "Patiente cannot be null")
    private String patienteName;

    public SessionDTO() {
        super();
    }

    public SessionDTO(Session objDto) {
        this.id = objDto.getId();
        this.title = objDto.getTitle();
        this.sessionCreationDate = objDto.getSessionCreationDate();
        this.sessionDate = objDto.getSessionDate();
        this.notes = objDto.getNotes();
        this.status = objDto.getStatus();
        this.psychologist = objDto.getPsychologist()
                .getId();
        this.patiente = objDto.getPatiente()
                .getId();
        this.psychologistName = objDto.getPsychologist()
                .getName();
        this.patienteName = objDto.getPatiente()
                .getName();
    }
}
