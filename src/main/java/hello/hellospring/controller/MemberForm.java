package hello.hellospring.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberForm {

    private String userId;
    private String userPassword;
}
