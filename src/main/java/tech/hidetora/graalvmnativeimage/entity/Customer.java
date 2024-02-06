package tech.hidetora.graalvmnativeimage.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * @author hidetora
 * @version 1.0.0
 * @since 2022/04/18
 */

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty @Size(min = 3)
    private String firstName;
    @NotEmpty
    @Size (min = 3)
    private String lastName;
    @NotEmpty @Size (min = 5)
    @Column(unique = true)
    private String email;
}
