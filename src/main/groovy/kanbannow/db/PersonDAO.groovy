package kanbannow.db

import kanbannow.core.Person
import com.google.common.base.Optional
import com.yammer.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory

public class PersonDAO extends AbstractDAO<Person> {
    public PersonDAO(SessionFactory factory) {
        super(factory)
    }

    public Optional<Person> findById(Long id) {
        return Optional.fromNullable(get(id))
    }

    public Person create(Person person) {
        return persist(person)
    }

    public List<Person> findAll() {
        return list(namedQuery("kanbannow.core.Person.findAll"))
    }
}
