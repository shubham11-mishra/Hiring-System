package com.lexisnexis.hiring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDescription {
    private int errorCode;
    private String errorDescription;
    private Date date;
}
