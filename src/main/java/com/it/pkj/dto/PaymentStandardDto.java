package com.it.pkj.dto;

import lombok.Data;

@Data
public class PaymentStandardDto {
    private Long standardId;
    private Double standardAmount;
    private Integer calculationMethod;
    private String dueDate;
}