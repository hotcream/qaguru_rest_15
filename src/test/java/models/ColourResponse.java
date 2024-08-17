package models;

import lombok.Data;

import java.util.List;

@Data
public class ColourResponse {

    Integer page,
            per_page,
            total,
            total_pages;
    List<Colour> data;
    Support support;

}