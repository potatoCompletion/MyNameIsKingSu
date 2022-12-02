//package hello.hellospring.repository;
//
//import hello.hellospring.domain.Member;
//import org.springframework.data.domain.Sort;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//import java.util.Optional;
//
//public class JpaMemberRepository implements MemberRepository {
//
//    private final EntityManager em;
//
//    public JpaMemberRepository(EntityManager em) {
//        this.em = em;
//    }
//
//    @Override
//    public Member save(Member member) {
//        //insert (알아서 매핑 후 INSERT 처리 해줍니다.)
//        em.persist(member);
//        return member;
//    }
//
//    @Override
//    public Optional<Member> findById(Long id) {
//        //find 함수에 조회할 타입(객체)이랑 식별자를 넣어주면 됩니다.
//        Member member = em.find(Member.class, id);
//        return Optional.ofNullable(member);
//    }
//
//    @Override
//    public Optional<Member> findByName(String name) {
//        //JPQL
//        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
//                .setParameter("name", name)
//                .getResultList();
//
//        return result.stream().findAny();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        return em.createQuery("select m from Member m",Member.class).getResultList();
//    }
//
//}
