package com.it.pkj.dto;

import lombok.Data;

@Data
public class PartyMemberDto {
    private Long partyMemberId;
    private String partyMemberName;
    private String partyMemberGender;
    private String partyMemberContactInformation;
    private String partyMemberJoiningDate;
    private Long partyMemberRegionId;
}