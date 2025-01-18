package com.it.pkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.pkj.domain.PartyMember;
import com.it.pkj.domain.PaymentRecord;
import com.it.pkj.domain.PaymentStandard;
import com.it.pkj.dto.PaymentRecordDto;
import com.it.pkj.mapper.PaymentRecordMapper;
import com.it.pkj.service.NotificationService;
import com.it.pkj.service.PaymentRecordService;
import com.it.pkj.service.PartyMemberService;
import com.it.pkj.service.PaymentStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
* @author Administrator
* @description 针对表【paymentrecord】的数据库操作Service实现
* @createDate 2025-01-16 12:42:17
*/
@Service
public class PaymentRecordServiceImpl extends ServiceImpl<PaymentRecordMapper, PaymentRecord>
    implements PaymentRecordService {
    @Autowired
    private PaymentRecordMapper paymentRecordMapper;
    @Autowired
    private PaymentStandardService paymentStandardService;
    @Autowired
    private PartyMemberService partyMemberService;
    @Autowired
    private NotificationService notificationService;

    @Override
    public PaymentRecord savePaymentRecord(PaymentRecordDto paymentRecordDto) {
        PaymentRecord paymentRecord = convertToEntity(paymentRecordDto);
        paymentRecordMapper.insert(paymentRecord);
        return paymentRecord;
    }

    @Override
    public PaymentRecord updatePaymentRecord(Long id, PaymentRecordDto paymentRecordDto) {
        PaymentRecord paymentRecord = convertToEntity(paymentRecordDto);
        paymentRecord.setPaymentRecordId(id);
        paymentRecordMapper.updateById(paymentRecord);
        return paymentRecord;
    }

    @Override
    public void deletePaymentRecord(Long id) {
        paymentRecordMapper.deleteById(id);
    }

    @Override
    public PaymentRecord getPaymentRecordById(Long id) {
        return paymentRecordMapper.selectById(id);
    }

    @Override
    public IPage<PaymentRecord> getPaymentRecords(Long partyMemberId, String startDate, String endDate, int page, int size) {
        QueryWrapper<PaymentRecord> queryWrapper = new QueryWrapper<>();
        if (partyMemberId!= null) {
            queryWrapper.eq("party_member_id", partyMemberId);
        }
        if (startDate!= null && endDate!= null) {
            queryWrapper.between("payment_time", startDate, endDate);
        }
        IPage<PaymentRecord> pageObj = new Page<>(page, size);
        return paymentRecordMapper.selectPage(pageObj, queryWrapper);
    }

    @Override
    public List<PaymentRecord> queryPaymentRecords(Long partyMemberId, String startDate, String endDate) {
        QueryWrapper<PaymentRecord> queryWrapper = new QueryWrapper<>();
        if (partyMemberId!= null) {
            queryWrapper.eq("party_member_id", partyMemberId);
        }
        if (startDate!= null && endDate!= null) {
            queryWrapper.between("payment_time", startDate, endDate);
        }
        return paymentRecordMapper.selectList(queryWrapper);
    }

    @Override
    public PaymentRecord calculatePayment(Long partyMemberId) {
        // 先获取党员信息，然后根据党员信息获取对应的党费标准
        // 这里假设根据党员的职级、收入等信息来获取相应的标准
        PaymentStandard paymentStandard = paymentStandardService.getPaymentStandardByMemberId(partyMemberId);
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setPartyMemberId(partyMemberId);
        // 这里根据标准进行计算，以下是一个简单示例，实际中需要更复杂的逻辑
        paymentRecord.setPaymentAmount(paymentStandard.getStandardAmount());
        return paymentRecord;
    }

    @Override
    public void remindPayment(Long partyMemberId) {
        PartyMember partyMember = partyMemberService.getPartyMemberById(partyMemberId);
        PaymentRecord latestPaymentRecord = getLatestPaymentRecord(partyMemberId);
        // 检查是否需要催缴，这里是一个简单的示例，假设根据最后一次缴费时间来判断
        if (latestPaymentRecord == null || isPaymentOverdue(latestPaymentRecord)) {
            PaymentRecord paymentRecord = calculatePayment(partyMemberId);
            notificationService.sendNotification(partyMember, paymentRecord);
        }
    }

    private PaymentRecord getLatestPaymentRecord(Long partyMemberId) {
        QueryWrapper<PaymentRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("party_member_id", partyMemberId);
        queryWrapper.orderByDesc("payment_time");
        return paymentRecordMapper.selectOne(queryWrapper);
    }

    private boolean isPaymentOverdue(PaymentRecord paymentRecord) {
        // 这里添加具体的判断逻辑，判断缴费是否过期
        return false;
    }

    private PaymentRecord convertToEntity(PaymentRecordDto paymentRecordDto) {
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setPartyMemberId(paymentRecordDto.getPartyMemberId());
        paymentRecord.setPaymentTime(paymentRecordDto.getPaymentTime());
        paymentRecord.setPaymentAmount(paymentRecordDto.getPaymentAmount());
        return paymentRecord;
    }
}




