package com.example.helloworld.resources;

import com.example.helloworld.core.Card;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/card")
@Produces(MediaType.APPLICATION_JSON)
public class CardResource {


    @GET

    public List<Card> getCardsForBoardAndLocation() {
        Card newCard = new Card();
        newCard.setId(5939);
        newCard.setText("order vitamins");

        List<Card> cardList = new ArrayList<Card>();
        cardList.add(newCard);
        return cardList;
    }

}
