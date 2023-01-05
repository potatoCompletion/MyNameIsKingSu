package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemoryMemberRepositoryTest {

    @Autowired
    private MemberRepository repository;
//    private JpaMemberRepository repository = new JpaMemberRepository();

//    @AfterEach
//    public void afterEach() {
//        repository;
//    }

    @Test
    void save() {
        Member member = Member.builder()
                        .userId("spring")
                        .userPassword("spring")
                        .build();

        repository.save(member);

        Member result = repository.findById(member.getId()).orElse(Member.builder().build());

        assertThat(member).isEqualTo(result);
    }

    @Test
    void findByName() {
        Member member1 = Member.builder()
                .userId("spring1")
                .userPassword("spring1PW")
                .build();

        repository.save(member1);

        Member result = repository.findByUserId("spring1").get();

        assertThat(member1).isEqualTo(result);
    }

    @Test
    void findAll() {
        Member member1 = Member.builder()
                .userId("spring1")
                .userPassword("spring1PW")
                .build();

        repository.save(member1);

        Member member2 = Member.builder()
                .userId("spring2")
                .userPassword("spring2PW")
                .build();
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
