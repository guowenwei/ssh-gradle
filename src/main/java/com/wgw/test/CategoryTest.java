package com.wgw.test;

import com.wgw.entity.user.Category;
import com.wgw.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CategoryTest {

    @Test
    public void Category() {
        saveCategory("delete");
    }

    public void saveCategory(String type) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        System.out.printf("给Category插入三条数据");
        try {
            transaction = session.beginTransaction();
            switch (type) {
                case "save":

                    System.out.printf("给Category插入三条数据");

                    Category category1 = new Category("桌子");
                    session.save(category1);
                    Category category2 = new Category("茶几");
                    session.save(category2);
                    Category category3 = new Category("椅子");
                    session.save(category3);

                    List<Category> categoryList = new ArrayList<Category>();
                    categoryList.add(category1);
                    categoryList.add(category2);
                    categoryList.add(category3);
                    Category category = new Category();
                    category.setName("家具");
                    category.setCategories(categoryList);
                    session.save(category);

                    break;
                case "get":

                    Category category5 = (Category) session.get(Category.class, 8);
                    for (Category category4 : category5.getCategories()){
                        System.out.printf("查询出来的Category为" + category4.toString()+"\n");
                    }
                    break;
                case "delete":
                    Category category6 = (Category) session.get(Category.class, 2);
                    session.delete(category6);

                    break;
                default:
                    break;
            }

            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            Assert.fail("异常为 == " + hibernateException.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
