package models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Account {

    //attributes

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long balance;

    @Column (nullable = false)
    private int  clientID;

    @Column (nullable = false)
    private long creditCardNumber;

    @Column (nullable = false)
    private int branchID;


}
