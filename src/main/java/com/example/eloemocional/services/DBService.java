package com.example.eloemocional.services;

import com.example.eloemocional.models.Patiente;
import com.example.eloemocional.models.Psychologist;
import com.example.eloemocional.models.Session;
import com.example.eloemocional.models.enums.CaseStatus;
import com.example.eloemocional.models.enums.UserProfile;
import com.example.eloemocional.repository.PatienteRepository;
import com.example.eloemocional.repository.PsychologistRepository;
import com.example.eloemocional.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

    @Autowired
    private PsychologistRepository psychologistRepository;
    @Autowired
    private PatienteRepository patienteRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public void instantiateDB() {

        Psychologist psy1 = new Psychologist(1, "Fran Dias", "123456123", "frandias@gmail.com", "12345");
        psy1.addUserProfile(UserProfile.ADMIN);

        Patiente pat1 = new Patiente(1, "Roberta Poiato", "76898709076", "rpoiato@gmail.com", "123");

        Session s1 = new Session(1, "Initial Consultation", null, "15/10/2023", "First session notes", CaseStatus.IN_PROGRESS, psy1, pat1);

        psychologistRepository.saveAll(List.of(psy1));
        patienteRepository.saveAll(List.of(pat1));
        sessionRepository.saveAll(List.of(s1));
    }
}
