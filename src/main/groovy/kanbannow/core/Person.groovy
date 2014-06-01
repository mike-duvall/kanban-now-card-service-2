package kanbannow.core

import javax.persistence.*

@Entity
@Table(name = "people")
@NamedQueries([
        @NamedQuery(
                name = "kanbannow.core.Person.findAll",
                query = "SELECT p FROM Person p"
        ),
        @NamedQuery(
                name = "kanbannow.core.Person.findById",
                query = "SELECT p FROM Person p WHERE p.id = :id"
        )
])
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id

    @Column(name = "fullName", nullable = false)
    String fullName

    @Column(name = "jobTitle", nullable = false)
    String jobTitle
}
