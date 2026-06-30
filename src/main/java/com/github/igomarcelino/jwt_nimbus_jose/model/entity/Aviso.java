package com.github.igomarcelino.jwt_nimbus_jose.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_aviso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(nullable = false, length = 100)
    @Setter
    private String conteudo;
    @Column(nullable = false)
    @Setter
    private Boolean lido;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pessoa")
    @Setter
    private Pessoa pessoa;

}
