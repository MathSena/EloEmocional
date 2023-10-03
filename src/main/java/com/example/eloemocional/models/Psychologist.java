package com.example.eloemocional.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Psychologist extends Person {

    private List<Session> sessions = new ArrayList<>();
}
