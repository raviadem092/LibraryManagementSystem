package com.library.models;

import java.util.List;

import com.library.dao.MemberDAO;
import com.library.services.IMemberService;

public class MemberService implements IMemberService {
    private MemberDAO memberDAO = new MemberDAO();

    @Override
    public void registerMember(Member member) {
        memberDAO.addMember(member);
    }

    @Override
    public void removeMember(int memberId) {
        memberDAO.removeMember(memberId);
    }

    @Override
    public Member findMemberById(int memberId) {
        return memberDAO.findMemberById(memberId);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }
}

