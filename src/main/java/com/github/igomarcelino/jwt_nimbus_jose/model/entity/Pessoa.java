package com.github.igomarcelino.jwt_nimbus_jose.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_pessoa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Setter
    @Column(nullable = false, length = 30)
    private String nome;
    @Setter
    @Column(nullable = false, length = 11, unique = true)
    @CPF
    private String cpf;
    @Setter
    @Column(nullable = false, length = 30, unique = true)
    @Email
    private String email;
    @Setter
    @Column(nullable = false, length = 60)
    private String senha;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pessoa_role",
               joinColumns = @JoinColumn(name = "id_pessoa"),
                inverseJoinColumns = @JoinColumn(name="id_role"))
    @Setter
    private Set<Roles> roles = new HashSet<>();


}
