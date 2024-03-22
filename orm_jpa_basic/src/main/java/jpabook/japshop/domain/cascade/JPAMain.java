package jpabook.japshop.domain.cascade;//package jpabook.japshop.domain.lazyloading;//package jpabook.japshop.domain.extendsmapping;


import jpabook.japshop.domain.cascade.entity.Child;
import jpabook.japshop.domain.cascade.entity.Parent;

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

           Child child1 = new Child();
           Child child2 = new Child();
           Parent parent = new Parent();

           parent.addChild(child1);
           parent.addChild(child2);

           /**
            * 기본 형태
            */
//           entityManager.persist(parent);
//           entityManager.persist(child1);
//           entityManager.persist(child2);
           /**
            * @OneToMany(mappedBy = "parent" , cascade = CascadeType.ALL)
            *
           Hibernate:
                 insert
                    into
                        Parent
                            (name, id)
                        values
                        (?, ?)
           Hibernate:
                insert
                    into
                        Child
                            (name, PARENT_ID, id)
                    values
                        (?, ?, ?)
           Hibernate:
                insert
                    into
                        Child
                            (name, PARENT_ID, id)
                        values
                        (?, ?, ?)

            뒤에 나머지도 persist 하겠다는 것.
            같이 영속성 컨텍스트에 올릴 것임.
            연관관계 맵핑과는 아무 관련 없음.
            함꼐 영속화하는 편의성 제공

            */

           entityManager.persist(parent);

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
