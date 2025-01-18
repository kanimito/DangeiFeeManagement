package com.it.pkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.pkj.domain.PaymentStandard;
import com.it.pkj.dto.PaymentStandardDto;
import com.it.pkj.mapper.PaymentStandardMapper;
import com.it.pkj.service.PaymentStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
* @author Administrator
* @description 针对表【paymentstandard】的数据库操作Service实现
* @createDate 2025-01-16 12:42:17
*/
@Service
public class PaymentStandardServiceImpl extends ServiceImpl<PaymentStandardMapper, PaymentStandard>
    implements PaymentStandardService {
    @Autowired
    private PaymentStandardMapper paymentStandardMapper;

    @Override
    public PaymentStandard savePaymentStandard(PaymentStandardDto paymentStandardDto) {
        PaymentStandard paymentStandard = convertToEntity(paymentStandardDto);
        paymentStandardMapper.insert(paymentStandard);
        return paymentStandard;
    }

    @Override
    public PaymentStandard updatePaymentStandard(Long id, PaymentStandardDto paymentStandardDto) {
        PaymentStandard paymentStandard = convertToEntity(paymentStandardDto);
        paymentStandard.setStandardId(id);
        paymentStandardMapper.updateById(paymentStandard);
        return paymentStandard;
    }

    @Override
    public void deletePaymentStandard(Long id) {
        paymentStandardMapper.deleteById(id);
    }

    @Override
    public PaymentStandard getPaymentStandardById(Long id) {
        return paymentStandardMapper.selectById(id);
    }

    @Override
    public IPage<PaymentStandard> getPaymentStandards(int page, int size) {
        IPage<PaymentStandard> pageObj = new Page<>(page, size);
        return paymentStandardMapper.selectPage(pageObj, null);
    }

    @Override
    public PaymentStandard getPaymentStandardByMemberId(Long memberId) {
        // 根据党员的职级、收入等信息查询对应的标准，这里先简单返回一个默认标准
        return paymentStandardMapper.selectOne(null);
    }

    @Override
    public PaymentStandard calculateFeeForMember(Long partyMemberId) {
        // 这里需要根据党员信息和党费标准进行计算逻辑的实现，例如查询党员信息和党费标准信息，根据计算方法进行计算
        // 以下是一个简单示例，假设使用固定金额计算
        PaymentStandard paymentStandard = paymentStandardMapper.selectOne(null);
        PaymentStandard result = new PaymentStandard();
        result.setStandardAmount(paymentStandard.getStandardAmount());
        return result;
    }

    private PaymentStandard convertToEntity(PaymentStandardDto paymentStandardDto) {
        PaymentStandard paymentStandard = new PaymentStandard();
        paymentStandard.setStandardAmount(paymentStandardDto.getStandardAmount());
        paymentStandard.setCalculationMethod(paymentStandardDto.getCalculationMethod());
        paymentStandard.setDueDate(paymentStandardDto.getDueDate());
        return paymentStandard;
    }
}




