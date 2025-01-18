package com.it.pkj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.it.pkj.domain.PartyMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.it.pkj.dto.PartyMemberDto;

import java.util.List;

/**
* @author Administrator
* @description 针对表【Partymember】的数据库操作Service
* @createDate 2025-01-16 12:42:17
*/
public interface PartyMemberService extends IService<PartyMember> {
    PartyMember savePartyMember(PartyMemberDto partyMemberDto);
    PartyMember updatePartyMember(Long id, PartyMemberDto partyMemberDto);
    void deletePartyMember(Long id);
    PartyMember getPartyMemberById(Long id);
    IPage<PartyMember> getPartyMembers(String name, String organization, String startDate, String endDate, int page, int size);
    List<PartyMember> importPartyMembers(List<PartyMemberDto> partyMemberDtos);
}
