package com.it.pkj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.it.pkj.dto.PaymentRecordDto;
import com.it.pkj.domain.PaymentRecord;
import com.it.pkj.service.PaymentRecordService;
import com.it.pkj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentrecords")
public class PaymentRecordController {
    @Autowired
    private PaymentRecordService paymentRecordService;

    // 保存缴费记录
    @PostMapping("/save")
    public Result<PaymentRecord> savePaymentRecord(@RequestBody PaymentRecordDto paymentRecordDto) {
        try {
            PaymentRecord paymentRecord = paymentRecordService.savePaymentRecord(paymentRecordDto);
            return Result.success(paymentRecord);
        } catch (Exception e) {
            return Result.error(500, "保存缴费记录失败：" + e.getMessage());
        }
    }

    // 更新缴费记录
    @PostMapping("/update/{id}")
    public Result<PaymentRecord> updatePaymentRecord(@PathVariable Long id, @RequestBody PaymentRecordDto paymentRecordDto) {
        try {
            PaymentRecord paymentRecord = paymentRecordService.updatePaymentRecord(id, paymentRecordDto);
            return Result.success(paymentRecord);
        } catch (Exception e) {
            return Result.error(500, "更新缴费记录失败：" + e.getMessage());
        }
    }

    // 删除缴费记录
    @PostMapping("/delete/{id}")
    public Result<Void> deletePaymentRecord(@PathVariable Long id) {
        try {
            paymentRecordService.deletePaymentRecord(id);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(500, "删除缴费记录失败：" + e.getMessage());
        }
    }

    // 获取缴费记录
    @GetMapping("/{id}")
    public Result<PaymentRecord> getPaymentRecordById(@PathVariable Long id) {
        try {
            PaymentRecord paymentRecord = paymentRecordService.getPaymentRecordById(id);
            if (paymentRecord == null) {
                return Result.error(404, "未找到缴费记录");
            }
            return Result.success(paymentRecord);
        } catch (Exception e) {
            return Result.error(500, "获取缴费记录失败：" + e.getMessage());
        }
    }

    // 分页查询缴费记录
    @GetMapping
    public Result<IPage<PaymentRecord>> getPaymentRecords(
            @RequestParam(required = false) Long partyMemberId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam int page,
            @RequestParam int size) {
        try {
            IPage<PaymentRecord> paymentRecords = paymentRecordService.getPaymentRecords(partyMemberId, startDate, endDate, page, size);
            return Result.success(paymentRecords);
        } catch (Exception e) {
            return Result.error(500, "分页查询缴费记录失败：" + e.getMessage());
        }
    }

    // 查询缴费记录
    @GetMapping("/query")
    public Result<List<PaymentRecord>> queryPaymentRecords(
            @RequestParam(required = false) Long partyMemberId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        try {
            List<PaymentRecord> paymentRecords = paymentRecordService.queryPaymentRecords(partyMemberId, startDate, endDate);
            return Result.success(paymentRecords);
        } catch (Exception e) {
            return Result.error(500, "查询缴费记录失败：" + e.getMessage());
        }
    }

    // 计算缴费
    @GetMapping("/calculate/{partyMemberId}")
    public Result<PaymentRecord> calculatePayment(@PathVariable Long partyMemberId) {
        try {
            PaymentRecord paymentRecord = paymentRecordService.calculatePayment(partyMemberId);
            return Result.success(paymentRecord);
        } catch (Exception e) {
            return Result.error(500, "计算缴费失败：" + e.getMessage());
        }
    }

    // 催缴
    @PostMapping("/remind/{partyMemberId}")
    public Result<Void> remindPayment(@PathVariable Long partyMemberId) {
        try {
            paymentRecordService.remindPayment(partyMemberId);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(500, "催缴操作失败：" + e.getMessage());
        }
    }
}