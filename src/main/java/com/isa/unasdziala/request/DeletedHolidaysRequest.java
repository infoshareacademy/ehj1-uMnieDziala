package com.isa.unasdziala.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeletedHolidaysRequest {
    private List<Long> holidaysId;
}
