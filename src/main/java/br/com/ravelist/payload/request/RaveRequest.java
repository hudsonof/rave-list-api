package br.com.ravelist.payload.request;

import br.com.ravelist.model.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaveRequest {
    private String name;
    private LocalDateTime date;
    private String location;
    private String city;
    private String state;
    private String image;
    private String page;
    private String tickets;
}
