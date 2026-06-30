package com.github.igomarcelino.jwt_nimbus_jose.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "tbl_roles")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Setter
    @Column(nullable = false, length = 20, unique = true)
    private String nome;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return Objects.equals(id, roles.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
