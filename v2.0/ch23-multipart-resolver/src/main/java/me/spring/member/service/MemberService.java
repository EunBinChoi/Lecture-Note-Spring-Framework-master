package me.spring.member.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import me.spring.member.beans.MemberDTO;
import me.spring.member.beans.MemberEntity;
import me.spring.member.beans.MemberParser;
import me.spring.member.dao.MemberDao;

@Service
public class MemberService implements IMemberService {
	private final MemberDao memberDao;

	
	@Autowired
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public boolean signup(MemberDTO member) throws SQLException, DataAccessException {
		// TODO DAO 들어가기 전에 id를 가진 사람이 이미 있으면 오류
		if(!canSignup(member)) return false;


		// TODO registerDTO -> registerEntity로 변환과정 필요
		MemberEntity memberEntity = MemberParser.parseMemberDTOtoEntity(member);
		System.out.println(member);
		System.out.println(memberEntity);
		return memberDao.insert(memberEntity) > 0;
	}

	@Override
	public MemberDTO select(String id) throws SQLException, DataAccessException {
		// TODO DAO 들어가기 전에 id를 통해 조회할 수 있는지 확인
		if(!canSelect(id)) return null;


		// TODO registerEntity -> registerDTO로 변환과정 필요
		MemberEntity memberEntity = memberDao.select(id);
		MemberDTO memberDTO = MemberParser.parseMemberEntitytoDTO(memberEntity);
		System.out.println(memberEntity);
		System.out.println(memberDTO);
		return memberDTO;
	}

	@Override
	public boolean update(MemberDTO member) throws SQLException, DataAccessException {
		// TODO DAO 들어가기 전에 id를 통해 조회할 수 있는지 확인
		if(!canUpdate(member)) return false;


		// TODO registerDTO -> registerEntity로 변환과정 필요
		MemberEntity memberEntity = MemberParser.parseMemberDTOtoEntity(member);
		System.out.println(member);
		System.out.println(memberEntity);
		return memberDao.update(memberEntity) > 0;
	}
	
	@Override
	public boolean delete(String id) throws SQLException, DataAccessException {
		// TODO DAO 들어가기 전에 id를 통해 조회할 수 있는지 확인
		if(!canDelete(id)) return false;


		// TODO registerDTO -> registerEntity로 변환과정 필요
		return memberDao.delete(id) > 0;
	}

	@Override
	public List<MemberDTO> selectAll() throws SQLException, DataAccessException {
		List<MemberDTO> memberDTOs = new Vector<>();
		List<MemberEntity> memberEntities = memberDao.selectAll();
		for(MemberEntity memberEntity : memberEntities) {
			memberDTOs.add(MemberParser.parseMemberEntitytoDTO(memberEntity));
		}
		return memberDTOs;
	}


	////////////////////////////////////////////////////////////////////
	/////////////////////////////// 예외 처리 ////////////////////////////
	///////////////////////  DAO에 try-catch 없애기 //////////////////////
	public boolean canSelect(String id) throws SQLException, DataAccessException {
		if(id == null) return false;
		
		List<MemberEntity> memberEntities = memberDao.selectAll();
		boolean isMember = false;
		for(MemberEntity memberEntity : memberEntities) {
			if(memberEntity.getmId() != null && memberEntity.getmId().equals(id)) {
				isMember = true;
				break;
			}
		}
		return isMember;
	}

	public boolean canSignup(MemberDTO member) throws SQLException, DataAccessException {
		if(member == null || member.getmId() == null) return false;
		
		MemberEntity memberEntity = memberDao.select(member.getmId());
		if(memberEntity != null) {
			return false;
		}
		return true;
	}

	public boolean canUpdate(MemberDTO member) throws SQLException, DataAccessException {
		if(member == null || member.getmId() == null) return false;
		
		MemberEntity memberEntity = memberDao.select(member.getmId());
		// TODO DAO 들어가기 전에 id의 curpwd와 DB에서 select한 curpwd가 다를 경우
		// SQL에서는 update register set pwd=?, name=?, email=?, phone=? where id=?
		if((memberEntity == null) || memberEntity.getmPwd() == null ||
				!memberEntity.getmPwd().equals(member.getmCurpwd())) {
			return false;
		}
		return true;
	}

	public boolean canDelete(String id) throws SQLException, DataAccessException {
		if(id == null) return false;
		
		MemberEntity memberEntity = memberDao.select(id);
		if(memberEntity == null) {
			return false;
		}
		return true;
	}
}
