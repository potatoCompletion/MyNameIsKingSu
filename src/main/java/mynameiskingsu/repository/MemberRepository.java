package mynameiskingsu.repository;

import mynameiskingsu.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByUserId(String userId);

    Optional<Member> findByUserIdAndUserPassword(String userId ,String userPassword);

    List<Member> findAll();
}
