package br.com.ravelist.payload.response;

import br.com.ravelist.model.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaveResponse {
    private UUID id;
    private String name;
    private LocalDateTime date;
    private String location;
    private String city;
    private String state;
    private String image;
    private String page;
    private String tickets;
}
