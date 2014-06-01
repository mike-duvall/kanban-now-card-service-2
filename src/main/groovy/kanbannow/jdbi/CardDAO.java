package kanbannow.jdbi;

import kanbannow.core.Card;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface CardDAO {


    @SqlQuery( "select id, text  from card where  board_id = :boardId  and location = :locationId")
    @Mapper(CardMapper.class)
    List<Card> getCardsForBoardAndLocation(@Bind("boardId") int boardId, @Bind("locationId") int locationId);


}
