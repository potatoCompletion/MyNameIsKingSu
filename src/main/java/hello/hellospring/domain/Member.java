package hello.hellospring.domain;

import io.jsonwebtoken.lang.Assert;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Entity
@Table(name="memberinfo_tb")
public class Member implements UserDetails {

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
    public Member(String userId, String userPassword) {
        // 생성일자를 삽입
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        this.createDate = dateTimeFormat.format(time);

        // 안전한 객체 생성을 위한 검증 (필수 값이 없을 시 에러내기 위하여)
        Assert.hasText(userId, "userId mut not be empty!");
        Assert.hasText(userPassword, "userPassword mut not be empty!");

        this.userId = userId;
        this.userPassword = userPassword;
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

//    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
//    @CollectionTable(name="member_roles_tb")
//    private List<String> roles = new ArrayList<>();
//
//    public List<String> getRoles() {
//        return roles;
//    }
    private String roles;

    public String getRoles() { return roles; }

    public void setRoles(String roles) { this.roles = roles; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(getRoles()));

        return roles;
    }

    @Override
    public String getPassword() { return userPassword; }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }

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
