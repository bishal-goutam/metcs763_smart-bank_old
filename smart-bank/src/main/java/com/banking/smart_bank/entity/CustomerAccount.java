package com.banking.smart_bank.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customeraccount")
@Entity
public class CustomerAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore  // Prevents infinite recursion
    private User user;

    public String getAccountHolderName() {
        return user != null ? user.getFullname() : null;
    }

}

