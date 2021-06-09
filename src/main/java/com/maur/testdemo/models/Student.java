package com.maur.testdemo.models;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;


//@Getter
//@Setter
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(generator = "student_sequence", strategy = SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;


    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Student(String firstName, String lastName, String email, Gender gender){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

}
