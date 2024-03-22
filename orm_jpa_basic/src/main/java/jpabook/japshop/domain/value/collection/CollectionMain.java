package jpabook.japshop.domain.value.collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class CollectionMain {

    public static EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("jpaBook");
    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        try {

            Member member = new Member();
            member.setUserName("member");
            member.setHomeAddress(new Address("4" , "4" , "4"));


            //
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");
            AddressEntity addressEntity = new AddressEntity("CITY1" , "STREET1" , "ZIPCODE1");
            member.getAddressHistory().add(addressEntity);
            member.getAddressHistory().add(new AddressEntity("CITY2" , "STREET2" , "ZIPCODE2"));
            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            System.out.println("========================");
            Member findMember = entityManager.find(Member.class ,  member.getId());
            System.out.println("findMember = " + findMember);

            List<AddressEntity> addresses = findMember.getAddressHistory();
            for (AddressEntity address : addresses){
                System.out.println("address.getCity() = " + address.getAddress().getCity());
            }

            // 변경 하고 싶다

            //findMember.setHomeAddress(new Address("1" , "2" , "3"));
            /**
             * 컬렉션이잖아요~
             * DB 도 같이 변경됩니다. 컬렉션 변경만 해도.
             */
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("당당치킨");

            findMember.getAddressHistory().remove(addressEntity);
            findMember.getAddressHistory().add(new AddressEntity("CITY4" , "STREET4" , "ZIPCODE4"));

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
