package Main;

import domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInteraction {
    public static void main(String[] args) {
        Scanner sc1=new Scanner(System.in);
        System.out.println("1.DISPLAY ALL PRODUCTS\t2.DISPLAY BY PRODUCT  CATEGORY");
        int choice=sc1.nextInt();
        Configuration cfg;
        SessionFactory factory;
        Session ses;
        Transaction tx;
        cfg=new Configuration();
        cfg=cfg.configure();
        cfg=cfg.addAnnotatedClass(Product.class);
        factory= cfg.buildSessionFactory();
        ses= factory.openSession();
        if(choice==1){
            Query q=ses.createQuery("select p.productName from Product p");
            List<String> all=q.list();
            for(String p:all){
                System.out.println(p);
            }
        } else if (choice==2) {
            Query q=ses.createQuery("select distinct p.productCategory from Product p");
            List<String> category=q.list();
            int count=1;
            for (String p:category) {
                System.out.println(count++ +"."+p);
            }
            System.out.println("ENTER CATEGORY NAME");
            String  option=sc1.next();

            if(category.contains(option)){
                Query q1=ses.createQuery("select p from Product p where p.productCategory='"+option+"'");
                List<Product> details=q1.list();
                for (Product p1:details){
                    System.out.println(p1);
                }
            }


        }


    }
}
