package com.it.pkj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.it.pkj.domain.PaymentRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.it.pkj.dto.PaymentRecordDto;

import java.util.List;

/**
* @author Administrator
* @description 针对表【paymentrecord】的数据库操作Service
* @createDate 2025-01-16 12:42:17
*/
public interface PaymentRecordService extends IService<PaymentRecord> {
    PaymentRecord savePaymentRecord(PaymentRecordDto paymentRecordDto);
    PaymentRecord updatePaymentRecord(Long id, PaymentRecordDto paymentRecordDto);
    void deletePaymentRecord(Long id);
    PaymentRecord getPaymentRecordById(Long id);
    IPage<PaymentRecord> getPaymentRecords(Long partyMemberId, String startDate, String endDate, int page, int size);
    List<PaymentRecord> queryPaymentRecords(Long partyMemberId, String startDate, String endDate);
    PaymentRecord calculatePayment(Long partyMemberId);
    void remindPayment(Long partyMemberId);
}
