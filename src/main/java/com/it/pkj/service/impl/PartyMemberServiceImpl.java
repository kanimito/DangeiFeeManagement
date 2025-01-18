package com.it.pkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.pkj.domain.PartyMember;
import com.it.pkj.dto.PartyMemberDto;
import com.it.pkj.mapper.PartyMemberMapper;
import com.it.pkj.service.PartyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
* @author Administrator
* @description 针对表【partymember】的数据库操作Service实现
* @createDate 2025-01-16 12:42:17
*/
@Service
public class PartyMemberServiceImpl extends ServiceImpl<PartyMemberMapper, PartyMember>
    implements PartyMemberService {
    @Autowired
    private PartyMemberMapper partyMemberMapper;

    @Override
    public PartyMember savePartyMember(PartyMemberDto partyMemberDto) {
        PartyMember partyMember = convertToEntity(partyMemberDto);
        partyMemberMapper.insert(partyMember);
        return partyMember;
    }

    @Override
    public PartyMember updatePartyMember(Long id, PartyMemberDto partyMemberDto) {
        PartyMember partyMember = convertToEntity(partyMemberDto);
        partyMember.setPartyMemberId(id);
        partyMemberMapper.updateById(partyMember);
        return partyMember;
    }

    @Override
    public void deletePartyMember(Long id) {
        partyMemberMapper.deleteById(id);
    }

    @Override
    public PartyMember getPartyMemberById(Long id) {
        return partyMemberMapper.selectById(id);
    }

    @Override
    public IPage<PartyMember> getPartyMembers(String name, String organization, String startDate, String endDate, int page, int size) {
        QueryWrapper<PartyMember> queryWrapper = new QueryWrapper<>();
        if (name!= null) {
            queryWrapper.like("party_member_name", name);
        }
        if (organization!= null) {
            queryWrapper.eq("party_member_organization", organization);
        }
        if (startDate!= null && endDate!= null) {
            queryWrapper.between("party_member_joining_date", startDate, endDate);
        }
        IPage<PartyMember> pageObj = new Page<>(page, size);
        return partyMemberMapper.selectPage(pageObj, queryWrapper);
    }

    @Override
    public List<PartyMember> importPartyMembers(List<PartyMemberDto> partyMemberDtos) {
        for (PartyMemberDto dto : partyMemberDtos) {
            PartyMember partyMember = convertToEntity(dto);
            // 进行数据校验，比如入党时间是否合法
            if (!isJoiningDateValid(dto.getPartyMemberJoiningDate())) {
                throw new IllegalArgumentException("Invalid joining date");
            }
            partyMemberMapper.insert(partyMember);
        }
        return null;
    }

    private PartyMember convertToEntity(PartyMemberDto partyMemberDto) {
        PartyMember partyMember = new PartyMember();
        partyMember.setPartyMemberName(partyMemberDto.getPartyMemberName());
        partyMember.setPartyMemberGender(partyMemberDto.getPartyMemberGender());
        partyMember.setPartyMemberContactInformation(partyMemberDto.getPartyMemberContactInformation());
        partyMember.setPartyMemberJoiningDate(partyMemberDto.getPartyMemberJoiningDate());
        partyMember.setPartyMemberRegionId(partyMemberDto.getPartyMemberRegionId());
        return partyMember;
    }

    private boolean isJoiningDateValid(String joiningDate) {
        // 这里添加具体的日期校验逻辑，例如检查日期格式是否正确，是否符合逻辑等
        return true;
    }
}




