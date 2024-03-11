package tech.hidetora.graalvmnativeimage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Customer{
    @Id
    private UUID id;
    private String name;
    private String email;
    private String imageUrl;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Invoice> invoices = new ArrayList<>();
}
