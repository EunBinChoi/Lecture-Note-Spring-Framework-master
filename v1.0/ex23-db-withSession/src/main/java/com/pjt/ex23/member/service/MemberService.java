package com.pjt.ex23.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt.ex23.member.Member;
import com.pjt.ex23.member.MemberPhone;
import com.pjt.ex23.member.dao.MemberDao;

//@Repository, @Component
//@Service("memService") // �ڵ����� MemberService ��ü�� ������ �����̳� ���
@Service
public class MemberService implements IMemberService{
	
	@Autowired
	MemberDao dao;
	
	@Override
	public int memberRegister(Member member) {
		// TODO Auto-generated method stub
		return dao.memberInsert(member);
	}

	@Override
	public Member memberSearch(Member member) {
		// TODO Auto-generated method stub
		return dao.memberSelect(member);
	}

	@Override
	public Member memberModify(Member member) {
		// TODO Auto-generated method stub
		int result = dao.memberUpdate(member);
		if(result == 0) {
			return null;
		}
		return member;
		
	}

	@Override
	public int memberRemove(Member member) {
		// TODO Auto-generated method stub
		return dao.memberDelete(member);
	}

}
