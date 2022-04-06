package com.isa.unasdziala.domain;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @CsvBindByPosition(position = 2)
    String phoneNo;
    @CsvBindByPosition(position = 3)
    String emailAddress;

}
