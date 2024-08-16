package models;

import lombok.Data;

@Data
public class PostLoginRequest {

    String email, password;

}