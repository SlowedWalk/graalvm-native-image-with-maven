package tech.hidetora.graalvmnativeimage.dto;

import lombok.*;

/**
 * @author hidetora
 * @version 1.0.0
 * @since 2022/04/18
 */
@Builder @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
