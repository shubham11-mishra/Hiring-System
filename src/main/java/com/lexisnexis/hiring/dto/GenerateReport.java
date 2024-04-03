package com.lexisnexis.hiring.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GenerateReport {
        private LocalDateTime startDate;

        private  LocalDateTime endDate;
        private  int requisitionId;
}
