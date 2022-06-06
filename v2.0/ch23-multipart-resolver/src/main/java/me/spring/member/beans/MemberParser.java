package me.spring.member.beans;

public class MemberParser {
	// TODO memberDTO -> memberEntity
	public static MemberEntity parseMemberDTOtoEntity(MemberDTO memberDTO) {
		if (memberDTO == null)	return null;

		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setmId(memberDTO.getmId());

		if (memberDTO.getmNewpwd() != null) { // UPDATE에서 접근 (curpwd, newpwd, newrepwd)
			memberEntity.setmPwd(memberDTO.getmNewpwd());
		} else if (memberDTO.getmCurpwd() != null) { // // SIGNUP에서 접근 (curpwd, currepwd)
			memberEntity.setmPwd(memberDTO.getmCurpwd());
		}

		memberEntity.setmName(memberDTO.getmName());
		memberEntity.setmEmail(memberDTO.getmEmail());
		memberEntity.setmPhone(memberDTO.getmPhone());
		memberEntity.setmUpdate(memberDTO.getmUpdate());
		memberEntity.setmRegdate(memberDTO.getmRegdate());
		
		return memberEntity;

	}

	// TODO memberEntity -> memberDTO
	public static MemberDTO parseMemberEntitytoDTO(MemberEntity memberEntity) {
		if (memberEntity == null)	return null;
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setmId(memberEntity.getmId());
		memberDTO.setmCurpwd(memberEntity.getmPwd());
		memberDTO.setmName(memberEntity.getmName());
		memberDTO.setmEmail(memberEntity.getmEmail());
		memberDTO.setmPhone(memberEntity.getmPhone());
		memberDTO.setmUpdate(memberEntity.getmUpdate());
		memberDTO.setmRegdate(memberEntity.getmRegdate());
		return memberDTO;
	}
}