package br.com.ravelist.payload.response;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@Getter
@Setter
public class PageableCustomResponse<T> {

    @JsonProperty("_content")
    private List<T> content;

    @Autowired
    @JsonProperty("_pageable")
    private PageableResponse pageable;

    public PageableCustomResponse() {
        content = new ArrayList<>();
        pageable = new PageableResponse();
    }
}
