package models;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    Integer page,
            per_page,
            total,
            total_pages;
    List<User> data;
    Support support;

}