package com.it.pkj.service;

import com.it.pkj.domain.PartyMember;
import com.it.pkj.domain.PaymentRecord;

public interface NotificationService {
    void sendNotification(PartyMember partyMember, PaymentRecord paymentRecord);
}