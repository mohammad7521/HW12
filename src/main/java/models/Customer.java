package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Customer {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String firstName;
    private String lastName;

    @Column(length = 500)
    private String address;

    @Column(nullable = false)
    private List<Account> account;

}
