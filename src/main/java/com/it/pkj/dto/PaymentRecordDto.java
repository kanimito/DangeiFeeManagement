package com.it.pkj.dto;

import lombok.Data;

@Data
public class PaymentRecordDto {
    private Long paymentRecordId;
    private Long partyMemberId;
    private String paymentTime;
    private Double paymentAmount;
}