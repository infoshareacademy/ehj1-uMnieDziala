package com.isa.unasdziala.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Event {
    public UUID id;
    public LocalDate eventDate;
}
