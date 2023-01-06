package hello.hellospring.controller;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class LoginForm {

    private String id;
    private String password;
}
