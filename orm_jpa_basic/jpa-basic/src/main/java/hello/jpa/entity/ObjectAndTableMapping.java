package hello.jpa.entity;

import javax.persistence.*;
import java.util.Date;

/**
 *  JPA가 관리하는 Entity 라는 것을 명시
 *  주의점
 *  1. 기본 생성자 필수
 *  2. final , enum , interface , inner 클래스는 불가능 하다.
 *  3. 필드에 final 사용 불가능.
 *
 *  attribute
 *  1. name => default = ObjectAndTableMapping 인데 , 다른 이름으로 변경해주고 싶을 때 다만, 기본으로 쓰는 것이 젤 좋음.
 *
 *  @Table
 *  테이블 축약어나 이름이 다를 때 테이블을 직접적으로 맵핑해줄 수 있다.
 *
 *  자동 스키마 생성
 *  Table 을 자동으로 만들어준다고 생각하면 된다.
 *  개발 환경만 쓸 것
 *  <property name="hibernate.hbm2ddl.auto " value="create" />
 *  Hibernate:
 *
 *     drop table if exists Member
 *       10월 11, 2022 2:36:49 오후 org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
 *       INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@6a1ebcff] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
*     Hibernate:
 *
 *     create table Member (
 *        id bigint not null,
 *         name varchar(255),
 *         primary key (id)
 *     ) engine=InnoDB
 *
 *     로그를 찍으면 이런 형식으로 DDL 이 적용 되는 것을 볼 수 있다.
 *     물론 객체에 필드 변수를 생성하게 되면 테이블 또한 컬럼이 추가 된다.
 *
 *     <property name="hibernate.hbm2ddl.auto " value="create-drop" />
 *     에플리케이션 종료 시 삭제 되는 테이블 만들기. TEST 할때 사용한다.
 *     <property name="hibernate.hbm2ddl.auto " value="update" />
 *     드롭테이블이 아니라, Alter table 을 하고 싶을 경우 등록 즉 변경 사항만 적용 DROP TABLE , 필드 지우기 는 불가함.
 *     <property name="hibernate.hbm2ddl.auto " value="validate" />
 *     필드명과 컬럼에 대한 검증
 *     <property name="hibernate.hbm2ddl.auto " value="none" />
 *     사용하지 않음
 *     alter 명령문을 쓰면 데이터베이스 락이 걸리는 사실을 안다면 , update 도 활용하면 안되는 이유와 같습니다.
 *
 *     주의 해야할 점
 *     1. 운영서버에서는 create , create-drop , update 사용하면 안됨.  << 데이터가 다 날라가겠죠 ?
 *     2. 개발 초기에는 create , update
 *     3. 테스트 서버는 update , validate
 *     4. 운영 서버는 validate , none
 */
@Entity(name = "ObjectAndTableMapping")
@Table(name = "Member")
//@SequenceGenerator(name = "MEMBER_ID_SEQUENCE_GENERATOR" ,
//                    sequenceName = "MEMBER_SEQ" , // DB SEQ 네
//                    initialValue = 1 , allocationSize = 1
//)
public class ObjectAndTableMapping {

    /**
     * 직접 할당하기 => @Id
     * 자동 할당 => GeneratedValue
     *     default = > strategy = GenerationType.AUTO
     *    1. IDENTITY strategy = GenerationType.IDENTITY DB에 위임 하는 것
     *       MySQL -> auto_increment
     *    2. SEQUENCE strategy = GenerationType.SEQUENCE
     *       Oracle , postageSQL-> sequence
     *       테이블마다 시퀀스를 따로 써야하기 때문에 테이블 위에 어노테이션으로
     *       @SequenceGenerator(name = "MEMBER_ID_SEQUENCE_GENERATOR" ,
     *                     sequenceName = "MEMBER_SEQ" , // DB SEQ 네
     *                     initialValue = 1 , allocationSize = 1
     *       )
     *       등록
     *       allocationSize = 기본값 50 이기 때문에 1씩 증가하는 시퀀스의 경우 1이라고 명시해야함 단, 50개씩 늘리면 메모리 상에서 넣고 콜을 51이 되었을 때 하기 때문에 성능향상을 노릴 수 있음.
     *       initialValue = 처음 시작하는 수
     *      이와 다르게 TABLE 전략이 있으나 실무에서 잘 사용하지 않음.
     *      em.persist(member);  영속성 컨텍스트에 올릴 때 db에 인설트문이 날라가게 됨
     *      왜냐하면 auto_increment 처리 가 된 후에 아이디 값을 알아야 하기 때문에.
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)//, generator = "MEMBER_ID_SEQUENCE_GENERATOR")
    private Long id;
    /**
     * Table Column 명과 동일하게 맞춰주는 역할 name = "table Colum Name"
     * unique = true > 유니크 제약 추가 add constraint DDL 생성 도움 유니크 제약조건 이름이 이상하게 나옴 그래서 @Table 에서 설정함
     * length = 길이 조절 DDL 생성 도움
     * insertable = false => 인설트문이 적용되지 않음.
     * updatable = false => 변경사항이 적용되지 않음.
     * nullable = false => not null
     * columnDefinition = 옵션 넣기 가능
     */
    @Column(name = "name", unique = true, length = 10 , insertable = false , updatable = true , nullable = false , columnDefinition = "varchar(100)  not null" )
    private String name;
    private Integer age;
    /**
     * DB 에는 enum 타입이 없어서 비슷하게 만들어줌
     * EnumType.ORDINAL = enum 의 선수대로 db에 순서가 들어감 USER 를 넣으면 0 ADMIN 을 넣으면 1
     */
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastDate;
    /**
     * database 에 varchar 보다 큰 컨텐츠를 넣을 때
     */
    @Lob
    private String description;
    /**
     * DB 랑 상관없이 쓰고싶은 변수 DB 에는 추가가 안됨.
     */
    @Transient
    private int temp;
}
