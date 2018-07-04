package com.wgw.test;

import com.wgw.entity.user.Citizen;
import com.wgw.entity.user.IDCard;
import com.wgw.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

public class CitizenIDCardTest {

    @Test
    public void test() {
        operater("save");
    }

    public void operater(String type) {

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            switch (type) {
                case "save":
                    IDCard idCard = new IDCard();
                    idCard.setNo(412144454);

                    Citizen citizen = new Citizen();
                    citizen.setName("张三");
                    citizen.setGender(true);

                    citizen.setIdCard(idCard);
                    idCard.setCitizen(citizen);

                    session.save(citizen);
                    session.save(idCard);

                    break;
                default:
                    break;
            }
            transaction.commit();
        } catch (HibernateException hibernateExcepiton) {
            if (transaction != null) {
                transaction.rollback();
            }
            Assert.fail(hibernateExcepiton.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
