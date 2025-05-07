package az.turing.tinderapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min=1,max=30)
    @Column(name="username",nullable = false)
    private String username;

    @Length(min=6,max=30)
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="email")
    private String email;
}
