package hello.hellospring.controller;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@RequiredArgsConstructor
@Data
public class LoginForm {

    private String id;
    private String password;
}
