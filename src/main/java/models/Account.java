package models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity

public class Account {

    //attributes

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long balance;

    @Column (nullable = false)
    private int  clientID;

    @Column (nullable = false)
    private long creditCardNumber;

    @Column (nullable = false)
    private int branchID;


}
