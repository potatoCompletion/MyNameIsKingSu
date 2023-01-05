package hello.hellospring.service;

import hello.hellospring.domain.Member;
//import hello.hellospring.repository.JpaMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    public MemberServiceTest(MemberService memberService) { this.memberService = memberService; }

    @BeforeEach
    void beforeEach() {
//        jpaMemberRepository = new JpaMemberRepository(em);
//        memberService = new MemberService(jpaMemberRepository);
    }

//    @AfterEach
//    void afterEach() {
//        jpaMemberRepository.clearStore();
//    }

    @Test
    void 회원가입() {
        //given
        Member member = Member.builder()
                .userId("spring")
                .userPassword("springPW")
                .build();

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();    // findOne 메서드는 id값으로 조회
        assertThat(member.getUserId()).isEqualTo(findMember.getUserId());
    }

//    @Test
//    void 중복회원예외() {
//        //given
//        Member member1 = new Member();
//        member1.setUserId("spring");
//
//        Member member2 = new Member();
//        member2.setUserId("spring");
//
//        //when
//        memberService.join(member1);
//        IllegalStateException illegalStateException = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//
//        assertThat(illegalStateException.getMessage()).isEqualTo( "이미 존재하는 회원입니다.");
//        //then
//
//
//    }
//
//   @Test
//    void 멤버전체리스트() {
//    }
//
//    @Test
//    void 아이디로하나찾기() {
//    }
}