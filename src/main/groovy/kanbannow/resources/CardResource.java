package kanbannow.resources;

import kanbannow.core.Card;
import kanbannow.jdbi.CardDAO;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/card")
@Produces(MediaType.APPLICATION_JSON)
public class CardResource {

    private CardDAO cardDAO;

    public CardResource(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    @GET
    public List<Card> getCardsForBoardAndLocation() {
//        Card newCard = new Card();
//        newCard.setId(5939);
//        newCard.setText("order vitamins");
//
//        List<Card> cardList = new ArrayList<Card>();
//        cardList.add(newCard);
//        return cardList;
        int boardId = 66;
        int locationId = 1;
        List<Card> cardList = cardDAO.getCardsForBoardAndLocation(boardId, locationId);

        return cardList;
    }

}
