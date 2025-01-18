package com.it.pkj.service.impl;

import com.it.pkj.domain.PartyMember;
import com.it.pkj.domain.PaymentRecord;
import com.it.pkj.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendNotification(PartyMember partyMember, PaymentRecord paymentRecord) {
        // 这里可以集成短信服务或邮件服务，发送催缴通知
        // 以下是一个简单的示例，仅打印消息
        System.out.println("Send notification to " + partyMember.getPartyMemberName() + " for payment record " + paymentRecord.getPaymentRecordId());
    }
}