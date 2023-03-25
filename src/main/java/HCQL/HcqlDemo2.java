package HCQL;

import domain.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;

import java.util.List;

public class HcqlDemo2 {
    public static void main(String[] args) {
        Configuration cfg;
        SessionFactory factory;
        Session ses;
        cfg=new Configuration();
        cfg=cfg.addAnnotatedClass(Product.class);
        cfg =cfg.configure();
        factory=cfg.buildSessionFactory();
        ses= factory.openSession();
        Criteria crt1=ses.createCriteria(Product.class);
        //fetch first three records
        crt1.setMaxResults(3);
        List<Product> products= crt1.list();
        for (Product p:products)
        {
            System.out.println(p);
        }
        System.out.println("==================================");
        //exclude first three records
        Criteria ctr2= ses.createCriteria(Product.class);
        ctr2.setFirstResult(3);
        List<Product> prod=ctr2.list();
        for (Product p:prod){
            System.out.println(p);
        }
        System.out.println("===============================");
        //display product name of first two products
        Criteria crt3=ses.createCriteria(Product.class);
        ProjectionList pList=Projections.projectionList();
        pList.add(Projections.property("productName"));
        crt3.setProjection(pList);
        crt3.setMaxResults(2);
        List<String> names=crt3.list();
        for(String name:names){
            System.out.println(name);
        }
        System.out.println("==============================================");
        //products having price less than 700
        Criteria crt4= ses.createCriteria(Product.class);
        //here we have to pass double value because in db column datatype is double
        crt4.add(Restrictions.lt("productPrice",700.0));
        List<Product> list=crt4.list();
        for (Product p:list){
            System.out.println(p);
        }
        System.out.println("======================================");
        Criteria crt5=ses.createCriteria(Product.class);
        crt5.add(Restrictions.between("productPrice",1000.0,1700.0));
        List<Product> list1=crt5.list();
        for (Product p:list1){
            System.out.println(p);
        }
        System.out.println("======================================");
        Criteria crt6=ses.createCriteria(Product.class);
        crt6.add(Restrictions.eq("productCategory","ELECTRONICS"));
        List<Product> list3=crt6.list();
        for (Product p:list1) {
            System.out.println(p);
        }
        System.out.println("=============================");
        Criteria crt7=ses.createCriteria(Product.class);
        crt7.add(Restrictions.like("productName","L%"));
            List<Product> like1=crt7.list();
            for (Product lik:like1){
                System.out.println(lik);
            }
        System.out.println("=======================================");
            //display all products from electronics category having price greater than 1000
        Criteria crt8=ses.createCriteria(Product.class);
        crt8.add(Restrictions.eq("productCategory","ELECTRONICS"));
        crt8.add(Restrictions.gt("productPrice",1000.0));
        List<Product> pl=crt8.list();
        for (Product p:pl){
            System.out.println(p);
        }
        System.out.println("=============================================");
        //all products in ascending order according to price
        Criteria crt9=ses.createCriteria(Product.class);
        crt9.addOrder(Order.asc("productPrice"));
        List<Product> list4=crt9.list();
        for (Product p9:list4){
            System.out.println(p9);
        }




    }
}
