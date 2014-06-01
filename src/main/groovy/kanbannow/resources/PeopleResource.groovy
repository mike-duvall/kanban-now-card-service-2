package kanbannow.resources

import kanbannow.core.Person
import kanbannow.db.PersonDAO
import com.yammer.dropwizard.hibernate.UnitOfWork

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {

    final PersonDAO peopleDAO

    public PeopleResource(PersonDAO peopleDAO) {
        this.peopleDAO = peopleDAO
    }

    @POST
    @UnitOfWork
    public Person createPerson(Person person) {
        return peopleDAO.create(person)
    }

    @GET
    @UnitOfWork
    public List<Person> listPeople() {
        return peopleDAO.findAll()
    }

}
