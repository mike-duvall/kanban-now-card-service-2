package com.example.helloworld.resources

import com.example.helloworld.core.Card
import com.example.helloworld.core.Person
import com.example.helloworld.db.PersonDAO
import com.yammer.dropwizard.hibernate.UnitOfWork

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/card")
@Produces(MediaType.APPLICATION_JSON)
public class CardResource {

//    final PersonDAO peopleDAO
//
//    public CardResource(PersonDAO peopleDAO) {
//        this.peopleDAO = peopleDAO
//    }

//    @POST
//    @UnitOfWork
//    public Person createPerson(Person person) {
//        return peopleDAO.create(person)
//    }

    @GET
//    @UnitOfWork
    public List<Card> getCardsForBoardAndLocation() {
        Card newCard = new Card()
        newCard.id = 5939
        newCard.text = 'order vitamins'

        List<Card> cardList = new ArrayList<Card>()
        cardList.add(newCard)
//        return [newCard]
        return cardList

    }

}
