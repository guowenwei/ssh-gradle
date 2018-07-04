package com.wgw.test;

import com.wgw.entity.student.Course;
import com.wgw.entity.student.Student;
import com.wgw.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StuCouTest {
    @Test
    public void test() {
        operator("save");
        //operator("delete");
    }

    public void operator(String type) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            switch (type) {
                case "save":

                    Student student = new Student("张三", "高三年纪");
                    Course course1 = new Course("数学",22.60);
                    Course course2 = new Course("语文",21.60);
                    Course course3 = new Course("英语",20.60);

                    /*session.save(course1);
                    session.save(course2);
                    session.save(course3);*/

                    List<Course> courses = new ArrayList<Course>();
                    courses.add(course1);
                    courses.add(course2);
                    courses.add(course3);
                    student.setCourses(courses);

                    session.save(student);
                    break;
                case "delete":
                    session.delete(session.get(Student.class,20));
                    break;
                default:
                    break;
            }
            transaction.commit();
        }catch (HibernateException he){
            Assert.fail(he.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
        }finally {
            if(session != null){
                session.close();
            }
        }
    }
}
