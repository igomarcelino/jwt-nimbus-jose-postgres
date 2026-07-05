package com.github.igomarcelino.jwt_nimbus_jose.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tbl_pessoa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa implements UserDetails {

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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pessoa_role",
               joinColumns = @JoinColumn(name = "id_pessoa"),
                inverseJoinColumns = @JoinColumn(name="id_role"))
    @Setter
    private Set<Roles> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNome()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
