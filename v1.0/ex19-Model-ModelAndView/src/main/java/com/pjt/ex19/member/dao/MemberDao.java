package com.pjt.ex19.member.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.pjt.ex19.member.Member;
import com.pjt.ex19.member.MemberPhone;

//@Repository, @Component
@Repository // �ڵ����� MemberDao ��ü�� ������ �����̳� ���
public class MemberDao implements IMemberDao{
	
	// DB ����ؼ� HashMap���� ������ ����
	private HashMap<String, Member> dbMap;
	
	public MemberDao() {
		dbMap = new HashMap<String, Member>();
	}
	
	
	@Override
	public void memberInsert
	(String memId, String memPw, String memMail, 
			MemberPhone memPhone) {
		// TODO Auto-generated method stub
		
		Member member = new Member();
		member.setMemId(memId);
		member.setMemPw(memPw);
		member.setMemMail(memMail);
		member.setMemPhone(memPhone);

		
		// HashMap�� ������ ���� put(key, value)
		// memId == key
		// member(id, pwd, mail, phone1, phone2, phone3) == value
		dbMap.put(memId, member);
		
		// dbMap ����� ������ ��� �ڵ� (db�� �� ������ Ȯ��)
		Set<String> keys = dbMap.keySet();
		Iterator<String> iterator = keys.iterator();
		
		while(iterator.hasNext()) {
			
			String key = iterator.next();
			Member mem = dbMap.get(key);
			
			System.out.print("memberId:" + mem.getMemId() + "\t");
			System.out.print("|memberPw:" + mem.getMemPw() + "\t");
			System.out.print("|memberMail:" + mem.getMemMail() + "\t");
			System.out.print("|memberPhone:" + mem.getMemPhone() + "\t");
		}
		
	}

	@Override
	public Member memberSelect(String memId, String memPw) {
		// TODO Auto-generated method stub
		return dbMap.get(memId);
	}

	@Override
	public void memberUpdate(String memId, String memPw, String memMail, 
			MemberPhone memPhone) {
		// TODO Auto-generated method stub
		
		memberInsert(memId, memPw, memMail, memPhone);
		
	}

	@Override
	public void memberDelete(String memId, String memPw) {
		// TODO Auto-generated method stub
		
		dbMap.remove(memId);
		
	}

}
