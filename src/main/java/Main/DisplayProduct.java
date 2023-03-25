package Main;

import domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class DisplayProduct {
    public static void main(String[] args) {
        Configuration cfg;
        SessionFactory factory;
        Session ses;
        cfg=new Configuration();
        cfg=cfg.configure();
        cfg=cfg.addAnnotatedClass(Product.class);
        factory= cfg.buildSessionFactory();
        ses= factory.openSession();
        //HQL-->to display all the objects of Product class
        Query q =ses.createQuery("select p from Product p");
      List<Product> productDetails =q.list();
        for (Product p:productDetails) {
            System.out.println(p);
        }


    }
}
