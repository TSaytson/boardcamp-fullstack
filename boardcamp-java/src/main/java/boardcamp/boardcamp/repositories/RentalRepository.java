package boardcamp.boardcamp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import boardcamp.boardcamp.models.RentalModel;

@Repository
public interface RentalRepository extends JpaRepository<RentalModel, Long> {
  List<RentalModel> findByGameId(@Param("gameId") Long gameId);

  @Query(value = "SELECT * FROM rentals WHERE game_id=:gameId AND return_date IS NULL",
  nativeQuery = true)
  List<RentalModel> findUnfinishedRentalsByGameId(@Param("gameId") Long gameId);
}
