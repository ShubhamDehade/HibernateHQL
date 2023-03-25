package Main;

import domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class UpdateSpecificCategory {
    public static void main(String[] args) {
        Configuration cfg;
        SessionFactory factory;
        Session ses;
        Transaction tx;

        cfg=new Configuration();
        cfg=cfg.configure();
        cfg=cfg.addAnnotatedClass(Product.class);
       factory= cfg.buildSessionFactory();
       ses=factory.openSession();
        Query q1=ses.createQuery("select  DISTINCT p.productCategory from Product p");
        List<String> category=q1.list();
        for (String c:category) {
            System.out.println(c);
        }
        System.out.println("ENTER CATEGORY");
        Scanner sc1=new Scanner(System.in);
        String cat=sc1.next();
        if(category.contains(cat)){
            Query q2=ses.createQuery("select p from Product p where p.productCategory='"+cat+"'");
            List<Product> products=q2.list();
            for (Product L:products) {
                System.out.println(L);
            }
            System.out.println("SELECT COLUMN NAME YOU WANT TO UPDATE");
            System.out.println("1.ID\n2.PRODUCT NAME\n3.PRICE");
            String choice=sc1.next();
         if(choice.matches("[1-3]{1}")){
             tx=ses.beginTransaction();
            if (choice.equals("1")){
                System.out.println("ENTER ID YOU WANT TO CHANGE");
                int old=sc1.nextInt();
                System.out.println("ENTER NEW ID");
                int id= sc1.nextInt();
                Query q3=ses.createQuery("UPDATE Product p set p.productId="+id+ "where p.productID="+old);

             int count=   q3.executeUpdate();
             tx.commit();
                System.out.println(count +"ID CHANGED");

            }
            else if(choice.equals("2")){
                System.out.println("ENTER PRODUCT NAME");
                String old= sc1.next();
                System.out.println("ENTER NEW NAME");
                String name= sc1.next();
                Query q4=ses.createQuery("UPDATE Product p set p.productName='"+name+"' where p.productName='"+old+"'");
                int count=   q4.executeUpdate();
                tx.commit();
                System.out.println(count +"NAME CHANGED");
            } else if (choice.equals("3")) {
                System.out.println("ENTER ID OF PRODUCT OF WHICH PRICE YOU WANT TO CHANGE");
                int id=sc1.nextInt();
                System.out.println("ENTER NEW PRICE");
                double price= sc1.nextDouble();
                Query q5=ses.createQuery("UPDATE Product p set p.productPrice="+price+"where id="+id);
                int count=   q5.executeUpdate();
                tx.commit();
                System.out.println(count +"PRICE CHANGED");
            }
            else {
                System.out.println("INVALID CREDENTIALS PASSED");
            }
         }
         else {
             System.out.println("INVALID CREDENTIALS PASSED");
         }
        }
    }
}
