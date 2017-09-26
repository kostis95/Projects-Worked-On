package com.service;

import com.dao.MemberDAO;
import com.model.Member;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    public void addMember(Member member) {
        memberDAO.addMember(member);
    }

    public void updateMember(Member member) {
        memberDAO.updateMember(member);
    }

    public Member getMember(int id) {
        return memberDAO.getMember(id);
    }

    public void deleteMember(int id) {
        memberDAO.deleteMember(id);
    }

    public void deleteTeam(int id) {
        memberDAO.deleteTeam(id);
    }

    public List<Member> getMembers() {
        return memberDAO.getMembers();
    }

    public void storeAllMembers(List<Member> members) {

        memberDAO.storeAllMembers(members);

    }
}
