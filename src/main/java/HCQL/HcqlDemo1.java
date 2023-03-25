package HCQL;

import domain.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import java.util.Iterator;
import java.util.List;

public class HcqlDemo1 {
    public static void main(String[] args) {
        Configuration cfg;
        SessionFactory factory;
        Session ses;
        cfg=new Configuration();
        cfg=cfg.configure();
        cfg=cfg.addAnnotatedClass(Product.class);
        factory= cfg.buildSessionFactory();
        ses= factory.openSession();
        //FETCHING DETAILS FROM THE COLUMNS
        //CREATE CRITERIA
        Criteria crt= ses.createCriteria(Product.class);
        //EXECUTE CRITERIA
       List<Product> productDetails= crt.list();
       for (Product p:productDetails){
           System.out.println(p);
       }

       System.out.println("===========================================");
       //fetching details from specific column
        //create criteria
        Criteria crt2=ses.createCriteria(Product.class);
        //add requirement details
        crt2.setProjection(Projections.property("productName"));
        //execute criteria
        List<String> names=crt2.list();
        for (String s:names){
            System.out.println(s);
        }
        System.out.println("==============================================");
        //create criteria
        Criteria crt3=ses.createCriteria(Product.class);
        //add requirements
        ProjectionList pList=Projections.projectionList();
       pList.add(Projections.property("productName")) ;
       pList.add(Projections.property("productPrice"));
       crt3.setProjection(pList);
       //execute criteria
        List data=crt3.list();
        Iterator itr= data.iterator();
        while(itr.hasNext()){
            Object[] arr= (Object[]) itr.next();
            System.out.println(arr[0]+"\t"+arr[1]);
        }


    }
}
