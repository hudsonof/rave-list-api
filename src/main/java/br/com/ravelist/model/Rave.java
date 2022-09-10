package br.com.ravelist.model;

import lombok.*;
import org.hibernate.*;
import org.hibernate.annotations.*;
import org.springframework.boot.context.properties.bind.*;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "raves")
public class Rave {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(50)")
    private UUID id;

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotNull
    private LocalDateTime date;

    private String location;
    private String city;
    private String state;
    private String image;
    private String page;
    private String tickets;
    private Boolean active;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "raves_user_id_fkey")
    )
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rave rave = (Rave) o;
        return id != null && Objects.equals(id, rave.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
