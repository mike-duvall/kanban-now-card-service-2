package kanbannow.resources;

import kanbannow.core.Card;
import kanbannow.jdbi.CardDAO;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/card")
@Produces(MediaType.APPLICATION_JSON)
public class CardResource {

    private CardDAO cardDAO;

    public CardResource(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    @GET
    public List<Card> getCardsForBoardAndLocation(
            @QueryParam("boardId") int boardId,
            @QueryParam("locationId") int locationId ) {

        List<Card> cardList = cardDAO.getCardsForBoardAndLocation(boardId, locationId);
        return cardList;
    }

}
