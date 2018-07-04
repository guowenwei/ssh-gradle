package com.wgw.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.wgw.entity.user.Account;
import com.wgw.entity.user.Employee;
import org.hibernate.*;
import com.wgw.util.HibernateUtils;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeTest {
    @Test
    public void test() {
        Employee e = new Employee("天", 4);
        //accountOperator("saveEmployee");
        //operator("nameSql");

    }

    //@Test
    public void initData() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                Employee employee = new Employee();
                if (i % 3 == 0) {
                    employee.setName("张" + i);
                } else if (i % 3 == 1) {
                    employee.setName("李" + i);
                } else {
                    employee.setName("王" + i);
                }
                employee.setGender(random.nextBoolean());
                employee.setAge(20 + random.nextInt(5));
                employee.setRegisterDate(new Date());
                session.save(employee);
            }
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * account对象的操作
     *
     * @param type save,
     */
    public static void accountOperator(String type) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            switch (type) {
                case "save":
                    Employee e = new Employee("天", 4);
                    session.save(e);
                    Account account = new Account("root", "123");
                    session.save(account);
                    break;
                case "get":
                    Employee employee = (Employee) session.get(Employee.class, 412);
                    Account account1 = (Account) session.get(Account.class, 2);
                    System.out.printf("查询出来的员工信息为 ==" + account1.toString());
                    //Hibernate.initialize(account1.getEmployee());

                   // System.out.printf("fjkdjfkd"+account1.getEmployee().toString());
                    break;
                case "find":
                    Query query = session.createQuery("from Account");
                    List<Account> accounts = query.list();
                    for (Account account2 : accounts){
                        //System.out.printf("查询出来的 account2 ="+ account2.getEmployee().toString());
                    }
                    break;
                case "saveEmployee":
                    Account account2 = new Account("ff1","23434");
                    session.save(account2);
                    Account account3 = new Account("ff2","23434");
                    session.save(account3);
                    Account account4 = new Account("ff3","23434");
                    session.save(account4);
                    List<Account> accounts1 = new ArrayList<Account>();
                    accounts1.add(account2);
                    accounts1.add(account3);
                    accounts1.add(account4);
                    Employee employee1 = new Employee("zhangsan",18);
                    employee1.setAccounts(accounts1);
                    session.save(employee1);
                    break;
                default:
                    break;
            }

            transaction.commit();
        } catch (HibernateException hibernateExcption) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.printf("hibernateExcption == " + hibernateExcption.getMessage());
            Assert.fail(hibernateExcption.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 增删改查操作
     *
     * @param type save、update、delete get query
     */
    public static void operator(String type) {
        Employee e = new Employee("小米", 4);

        // 解析配置文件，生成信息对象
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        // 获取持久化管理器
        Session session = sessionFactory.openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            switch (type) {
                case "save":
                    System.out.println("插入的数据库为:" + e.toString());
                    session.save(e);

                    break;
                case "delete":

                    Employee deleteEmp = (Employee) session.load(Employee.class, 1);
                    session.delete(deleteEmp);
                    break;
                case "update":

                    Employee updateEmp = (Employee) session.load(Employee.class, 1);
                    updateEmp.setName("huawei");
                    session.update(updateEmp);

                    break;
                case "get":

                    Employee getEmp = (Employee) session.get(Employee.class, 1);
                    System.out.println("查询id为1的" + getEmp.toString());

                    break;
                case "query":

                    List<Employee> list = session.createSQLQuery("select * from employee").addEntity(Employee.class).list();
                    for (Employee queryE : list) {
                        System.out.println("遍历查询id为1的" + queryE.toString());
                    }
                    break;
                case "hql":
                    String hql = "FROM " +
                            "Employee " +
                            "WHERE " +
                            "NAME LIKE ? " +
                            "AND gender = ? " +
                            "AND age BETWEEN ? AND 22 " +
                            "and register_date BETWEEN ? " +
                            "and '2018-06-28 00:00:00'";
                    List<Employee> employees = session.createQuery(hql)
                            .setString(0, "李%")
                            .setBoolean(1, true)
                            .setInteger(2, 20)
                            .setString(3, "2018-06-26 15:41:004").list();
                    System.out.printf("HQL共查询出" + employees.size() + "条数据！！！" +
                            "\n");
                    for (Employee employee : employees) {
                        System.out.printf("通过HQL查询 -- > " + employee.toString());
                    }
                    break;
                case "page":
                    Integer pageNum = 2;
                    Integer pageSize = 2;
                    String pageHql = "from Employee";
                    Query query = session.createQuery(pageHql);
                    query.setFirstResult((pageNum - 1) * pageSize);
                    query.setMaxResults(pageSize);
                    List<Employee> employeeList = query.list();
                    for (Employee employee : employeeList) {
                            /*
                            第1页的数据为 == Employee [id=110, name=张0, age=23]
                            第1页的数据为 == Employee [id=111, name=李1, age=24]
                            第2页的数据为 == Employee [id=112, name=王2, age=20]
                            第2页的数据为 == Employee [id=113, name=张3, age=23]
                             */
                        System.out.printf("第" + pageNum + "页的数据为 == " + employee.toString());
                    }

                    break;

                case "nameSql":
                    //Query query1 = session.getNamedQuery("findAgebiggerThan").setParameter("param1",23);
                    Query query1 = session.getNamedQuery("findEarlierRegisterDate").setString("param1", "2018-06-27 00:00:00");
                    List<Employee> employees1 = query1.list();
                    for (Employee employee : employees1) {
                        System.out.printf("命名Sql查出的数据为 --> " + employee);
                    }
                    break;

                default:
                    break;
            }

            transaction.commit();
        } catch (HibernateException exception) {
            // TODO: handle exception
            System.out.println("异常回滚" + exception);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
