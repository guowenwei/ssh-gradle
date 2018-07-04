package com.wgw.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    private static SessionFactory sf;

    static {
        try {
            //1.创建配置对象
            Configuration config = new Configuration();
            //2.读取配置文件
            config.configure();
            //3.创建serviceRegistry对象
            ServiceRegistry registry = new StandardServiceRegistryBuilder().build();
            //4.创建sessionFactory对象
            sf = config.buildSessionFactory(registry);
            System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFDDDD");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("**********" + e.getMessage());
        }
    }

    private HibernateUtils() {
    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }

}
