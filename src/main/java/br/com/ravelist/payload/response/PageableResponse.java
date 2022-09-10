package br.com.ravelist.payload.response;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.stereotype.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PageableResponse {

    @JsonProperty("_limit")
    private Integer limit = 0;

    @JsonProperty("_offset")
    private Long offset = 0L;

    @JsonProperty("_pageNumber")
    private Integer pageNumber = 0;

    @JsonProperty("_pageElement")
    private Integer pageElement = 0;

    @JsonProperty("_totalPages")
    private Integer totalPages = 0;

    @JsonProperty("_totalElements")
    private Long totalElements = 0L;
}
