package com.it.pkj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.it.pkj.dto.PartyMemberDto;
import com.it.pkj.domain.PartyMember;
import com.it.pkj.service.PartyMemberService;
import com.it.pkj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partymembers")
public class PartyMemberController {
    @Autowired
    private PartyMemberService partyMemberService;

    // 保存党员信息
    @PostMapping
    public Result<PartyMember> savePartyMember(@RequestBody PartyMemberDto partyMemberDto) {
        try {
            PartyMember partyMember = partyMemberService.savePartyMember(partyMemberDto);
            return Result.success(partyMember);
        } catch (Exception e) {
            return Result.error(500, "保存党员信息失败：" + e.getMessage());
        }
    }

    // 更新党员信息
    @PostMapping("/update/{id}")
    public Result<PartyMember> updatePartyMember(@PathVariable Long id, @RequestBody PartyMemberDto partyMemberDto) {
        try {
            PartyMember partyMember = partyMemberService.updatePartyMember(id, partyMemberDto);
            return Result.success(partyMember);
        } catch (Exception e) {
            return Result.error(500, "更新党员信息失败：" + e.getMessage());
        }
    }

    // 删除党员信息
    @PostMapping("/delete/{id}")
    public Result<Void> deletePartyMember(@PathVariable Long id) {
        try {
            partyMemberService.deletePartyMember(id);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(500, "删除党员信息失败：" + e.getMessage());
        }
    }

    // 获取党员信息
    @GetMapping("/{id}")
    public Result<PartyMember> getPartyMemberById(@PathVariable Long id) {
        try {
            PartyMember partyMember = partyMemberService.getPartyMemberById(id);
            if (partyMember == null) {
                return Result.error(404, "未找到党员信息");
            }
            return Result.success(partyMember);
        } catch (Exception e) {
            return Result.error(500, "获取党员信息失败：" + e.getMessage());
        }
    }

    // 分页查询党员信息
    @GetMapping
    public Result<IPage<PartyMember>> getPartyMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String organization,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam int page,
            @RequestParam int size) {
        try {
            IPage<PartyMember> partyMembers = partyMemberService.getPartyMembers(name, organization, startDate, endDate, page, size);
            return Result.success(partyMembers);
        } catch (Exception e) {
            return Result.error(500, "分页查询党员信息失败：" + e.getMessage());
        }
    }

    // 导入党员信息
    @PostMapping("/import")
    public Result<List<PartyMember>> importPartyMembers(@RequestBody List<PartyMemberDto> partyMemberDtos) {
        try {
            List<PartyMember> partyMembers = partyMemberService.importPartyMembers(partyMemberDtos);
            return Result.success(partyMembers);
        } catch (Exception e) {
            return Result.error(500, "导入党员信息失败：" + e.getMessage());
        }
    }
}