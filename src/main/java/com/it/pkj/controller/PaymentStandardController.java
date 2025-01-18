package com.it.pkj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.it.pkj.dto.PaymentStandardDto;
import com.it.pkj.domain.PaymentStandard;
import com.it.pkj.service.PaymentStandardService;
import com.it.pkj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentstandards")
public class PaymentStandardController {
    @Autowired
    private PaymentStandardService paymentStandardService;

    // 保存党费标准
    @PostMapping("/save")
    public Result<PaymentStandard> savePaymentStandard(@RequestBody PaymentStandardDto paymentStandardDto) {
        try {
            PaymentStandard paymentStandard = paymentStandardService.savePaymentStandard(paymentStandardDto);
            return Result.success(paymentStandard);
        } catch (Exception e) {
            return Result.error(500, "保存党费标准失败：" + e.getMessage());
        }
    }

    // 更新党费标准
    @PostMapping("/update/{id}")
    public Result<PaymentStandard> updatePaymentStandard(@PathVariable Long id, @RequestBody PaymentStandardDto paymentStandardDto) {
        try {
            PaymentStandard paymentStandard = paymentStandardService.updatePaymentStandard(id, paymentStandardDto);
            return Result.success(paymentStandard);
        } catch (Exception e) {
            return Result.error(500, "更新党费标准失败：" + e.getMessage());
        }
    }

    // 删除党费标准
    @PostMapping("/delete/{id}")
    public Result<Void> deletePaymentStandard(@PathVariable Long id) {
        try {
            paymentStandardService.deletePaymentStandard(id);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(500, "删除党费标准失败：" + e.getMessage());
        }
    }

    // 获取党费标准
    @GetMapping("/{id}")
    public Result<PaymentStandard> getPaymentStandardById(@PathVariable Long id) {
        try {
            PaymentStandard paymentStandard = paymentStandardService.getPaymentStandardById(id);
            if (paymentStandard == null) {
                return Result.error(404, "未找到党费标准");
            }
            return Result.success(paymentStandard);
        } catch (Exception e) {
            return Result.error(500, "获取党费标准失败：" + e.getMessage());
        }
    }

    // 分页查询党费标准
    @GetMapping
    public Result<IPage<PaymentStandard>> getPaymentStandards(
            @RequestParam int page,
            @RequestParam int size) {
        try {
            IPage<PaymentStandard> paymentStandards = paymentStandardService.getPaymentStandards(page, size);
            return Result.success(paymentStandards);
        } catch (Exception e) {
            return Result.error(500, "分页查询党费标准失败：" + e.getMessage());
        }
    }

    // 计算党员党费
    @GetMapping("/calculate/{partyMemberId}")
    public Result<PaymentStandard> calculateFeeForMember(@PathVariable Long partyMemberId) {
        try {
            PaymentStandard paymentStandard = paymentStandardService.calculateFeeForMember(partyMemberId);
            return Result.success(paymentStandard);
        } catch (Exception e) {
            return Result.error(500, "计算党员党费失败：" + e.getMessage());
        }
    }
}