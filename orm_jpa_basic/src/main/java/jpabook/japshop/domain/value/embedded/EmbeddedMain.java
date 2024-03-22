//package jpabook.japshop.domain.value.embedded;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//public class EmbeddedMain {
//
//    /**
//     * 예를 들어,
//     * 이름 근무 싲가일 , 근무 종료일 , 주소도시 , 주소 번지 , 주소 우편번호를 가진다면,
//     *
//     * @Entity
//     * Member
//     * id: Long
//     * name: Strign
//     * workPeriod : Period
//     * homeAddress :  Address
//     *
//     * @Entity
//     * Member
//     * id: Long
//     * name: String
//     *
//     * @ValueType
//     * workPeriod
//     * startDate: Date
//     * endDate: Date
//     *
//     * @ValueType
//     * Address
//     * city: String
//     * street: String
//     * zipcode: String
//     *
//     * 이런 식으로 클래스를 두개 더 만든다.
//     *
//     * 만드는 법
//     * @Embeddable
//     * @Embedded
//     * 기본 생성자 필수
//     *
//     * 장점
//     * 1. 재사용
//     * 2. 높은 응집도
//     * 3. Period.isWork() 처럼 해당 값 타입만 사용하는 의미 있는 메소드 만들기 가능
//     * 4. 임베디드타입을 포함한 모든 값 타입은 값 타입을 소유한 엔티티에 생명주기를 의존함
//     *
//     * DB 에는 모든 값이 Member 테이블에 있어야함.
//     * 테이블 구성
//     *     create table Member (
//     *        MEMBER_ID bigint not null,
//     *         city varchar(255),
//     *         endDate datetime,
//     *         startDate datetime,
//     *         street varchar(255),
//     *         USERNAME varchar(255),
//     *         zipCode varchar(255),
//     *         primary key (MEMBER_ID)
//     *     ) engine=InnoDB
//     *
//     */
//    public static EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("jpaBook");
//    public static void main(String[] args) {
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//
//        entityTransaction.begin();
//
//        try {
//            Address address =new Address("citiy" , "street" , "zipCode");
//
//            /** 같은 어드레스를 사용할 때
//             *
//             */
//            Member member1 = new Member();
//            member1.setUserName("test1");
//            member1.setHomeAddress(address);
//            member1.setWorkPeriod(new Period());
//
//            /**
//             * 임베디드 복사
//             */
//            Address member2Address = new Address(address.getCity(), address.getStreet(), address.getZipCode());
//            Member member2 = new Member();
//            member2.setUserName("test1");
//            member2.setHomeAddress(member2Address);
//            member2.setWorkPeriod(new Period());
//
//            /**
//             * 이렇게 하면 address 가 같기 때문에
//             * member1 , member2 둘다 city 가 newCity 로 변경 된다.
//             *  Address address =new Address("citiy" , "street" , "zipCode");
//             *              Member member1 = new Member();
//             *             member1.setUserName("test1");
//             *             member1.setHomeAddress(address);
//             *             member1.setWorkPeriod(new Period());
//             *
//             *
//             *             Member member2 = new Member();
//             *             member2.setUserName("test1");
//             *             member2.setHomeAddress(address);
//             *             member2.setWorkPeriod(new Period());
//             *
//             *             해결 방안은 아래에 있다.
//             *             하지만 이것 또한 문제가 있다.
//             *                         Address member2Address = new Address(address.getCity(), address.getStreet(), address.getZipCode());
//             *             Member member2 = new Member();
//             *             member2.setUserName("test1");
//             *             member2.setHomeAddress(member2Address);
//             *             member2.setWorkPeriod(new Period());
//             *
//             *             공유 참조로 인해 발생하는 부작용은 피할 수 있다.
//             *             직접 정의한 값 타입은 객체 타입이다. 복사로 넘기는게 아니다.
//             *             자바 기본타입은 값을 대입하면 값을 복사한다.
//             *             객체타입은 참조 값을 직접 대입하는 것을 막을 방법은 없음.
//             *             객체의 공유 참조는 피할 수 없다.
//             *             이 것이 객체타입의 한계이다.
//             *
//             *             그래서 객체 타입을 수정할 수 없게 만들면 된다.
//             *             그 것이 불변객체이다.
//             *
//             *             불변객체 : 생성 시점 이후 절대 값을 변경할 수 없는 객체
//             *             생성자로만 값을 생성하고 setter를 만들지 말 것.
//             *             Integer, String 은 자바의 불변객체 이다.
//             *             new Integer , new String 만 가능하기 때문.
//             *
//             *               Address member2Address = new Address("NewCity", address.getStreet(), address.getZipCode());
//             *               불변 객체를 만듬으로써 위험성 삭제
//             *
//             */
////            member1.getHomeAddress().setCity("newCity");
//
//            entityManager.persist(member1);
//            entityManager.persist(member2);
//
//            entityTransaction.commit();
//        } catch (Exception e){
//            entityTransaction.rollback();
//
//            e.printStackTrace();
//        } finally {
//            entityManager.close();
//        }
//
//        entityManagerFactory.close();
//    }
//
//}
