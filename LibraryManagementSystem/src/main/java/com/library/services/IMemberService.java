package com.library.services;

import java.util.List;

import com.library.models.Member;

public interface IMemberService {
	 public void registerMember(Member member);
	 public void removeMember(int memberId);
	 public Member findMemberById(int memberId);
	 public List<Member> getAllMembers();
}
