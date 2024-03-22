package jpabook.japshop.domain.orphan;//package jpabook.japshop.domain.lazyloading;//package jpabook.japshop.domain.extendsmapping;


import jpabook.japshop.domain.orphan.entity.Child;
import jpabook.japshop.domain.orphan.entity.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JPAMain {
    public static EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("jpaBook");
    public static void main(String[] args) {

       EntityManager entityManager = entityManagerFactory.createEntityManager();
       EntityTransaction entityTransaction = entityManager.getTransaction();

       entityTransaction.begin();

       try {

           /**
            *     @OneToMany(mappedBy = "parent" , cascade = CascadeType.ALL , orphanRemoval = true)
            */

           Child child1 = new Child();
           Child child2 = new Child();
           Parent parent = new Parent();

           parent.addChild(child1);
           parent.addChild(child2);

           entityManager.persist(parent);

           entityManager.flush();
           entityManager.clear();

           Parent findParent = entityManager.find(Parent.class , parent.getId());
           findParent.getChildList().remove(0);
           /**
            * delete
            *         from
            *             Child
            *         where
            *             id=?
            *
            *             지워지는 쿼리문 날라감
            */


           entityTransaction.commit();
       } catch (Exception e){
           entityTransaction.rollback();

           e.printStackTrace();
       } finally {
           entityManager.close();
       }

       entityManagerFactory.close();
    }
}
