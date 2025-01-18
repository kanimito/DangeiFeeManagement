package com.it.pkj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.it.pkj.domain.PaymentStandard;
import com.baomidou.mybatisplus.extension.service.IService;
import com.it.pkj.dto.PaymentStandardDto;

/**
* @author Administrator
* @description 针对表【paymentstandard】的数据库操作Service
* @createDate 2025-01-16 12:42:17
*/
public interface PaymentStandardService extends IService<PaymentStandard> {
    PaymentStandard savePaymentStandard(PaymentStandardDto paymentStandardDto);
    PaymentStandard updatePaymentStandard(Long id, PaymentStandardDto paymentStandardDto);
    void deletePaymentStandard(Long id);
    PaymentStandard getPaymentStandardById(Long id);
    IPage<PaymentStandard> getPaymentStandards(int page, int size);
    PaymentStandard getPaymentStandardByMemberId(Long memberId);
    PaymentStandard calculateFeeForMember(Long partyMemberId);
}
