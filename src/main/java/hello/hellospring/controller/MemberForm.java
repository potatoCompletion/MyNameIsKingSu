package hello.hellospring.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberForm {

    private String userId;
    private String userPassword;
}
