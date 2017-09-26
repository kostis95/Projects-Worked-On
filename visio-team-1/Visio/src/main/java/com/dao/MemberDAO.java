package com.dao;

import com.model.Member;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addMember(Member member) {
        // no HQL needed
        getCurrentSession().save(member);
    }

    public void updateMember(Member member) {
        Member memberToUpdate = getMember(member.getId());
        memberToUpdate.setFirstName(member.getFirstName());
        memberToUpdate.setLastName(member.getLastName());

        memberToUpdate.setTeam(member.getTeam());
        getCurrentSession().update(memberToUpdate);

    }

    public Member getMember(int id) {

        hql = "from Member m where m.id= :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);

        Member member = (Member) query.list().get(0);
        return member;
    }

    public void deleteMember(int id) {
        Member member = getMember(id);
        if (member != null) {
            getCurrentSession().delete(member);
        }
    }

    public void deleteTeam(int id) {
        Member memberToUpdate = getMember(id);
        memberToUpdate.setTeam(null);
        getCurrentSession().update(memberToUpdate);

    }

    @SuppressWarnings("unchecked")
    public List<Member> getMembers() {
        hql = "from Member";
        query = getCurrentSession().createQuery(hql);

        return (List<Member>) query.list();
    }

    public void storeAllMembers(List<Member> members) {

        for (Member member : members) {
            getCurrentSession().save(member);
        }

    }
}
