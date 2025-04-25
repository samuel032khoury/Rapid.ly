package com.rapidly.shortener.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClickEventDTO {
    private LocalDate clickDate;
    private Long count;
}
