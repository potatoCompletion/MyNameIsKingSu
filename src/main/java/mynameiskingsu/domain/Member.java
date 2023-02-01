package mynameiskingsu.domain;

import io.jsonwebtoken.lang.Assert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import mynameiskingsu.common.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@NoArgsConstructor
@Table(name="memberinfo_tb")
public class Member implements UserDetails {

    // Override된 메서드들은 UserDetails 구현 메서드들

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_ip")
    private String userIp;

    @Column(name = "last_login")
    private String lastLogin;

    @Column(name = "create_date", nullable = false)
    private String createDate;  // create_date 컬럼은 자동생성

    @Builder
    public Member(String userId, String userPassword, Roles roles) {

        // 안전한 객체 생성을 위한 검증 (빈 값이 들어올 시 에러내기 위하여)
        Assert.hasText(userId, "userId mut not be empty!");
        Assert.hasText(userPassword, "userPassword mut not be empty!");

        // 현재 일자를 형식에 맟춰 생성일자로 삽입
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        this.createDate = dateTimeFormat.format(time);

        this.userId = userId;
        this.userPassword = userPassword;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String username) {
        this.userId = username;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Enumerated(EnumType.STRING)
    private Roles roles;

    public Roles getRoles() { return roles; }

    public void setRoles(Roles roles) { this.roles = roles; }

    // 테이블 두개를 join 해서 정보를 가져올때 사용하는 방법인데.. UserDetails 포함 메서드라 구현만 해놓고
    // 실제 roles에 대한 정보는 memberinfo_tb에서 가져온다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(getRoles().toString()));

        return roles;
    }

    @Override
    public String getPassword() { return userPassword; }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
