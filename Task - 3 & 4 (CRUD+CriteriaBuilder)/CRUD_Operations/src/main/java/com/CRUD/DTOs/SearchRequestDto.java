package com.CRUD.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {

    String column;
    String value;
}
