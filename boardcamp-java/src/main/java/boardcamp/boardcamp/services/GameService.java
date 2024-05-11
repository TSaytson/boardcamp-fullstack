package boardcamp.boardcamp.services;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import boardcamp.boardcamp.dto.GameDTO;
import boardcamp.boardcamp.exceptions.GameExceptions.GameConflictException;
import boardcamp.boardcamp.exceptions.GameExceptions.GameNotFoundException;
import boardcamp.boardcamp.models.GameModel;
import boardcamp.boardcamp.repositories.GameRepository;

@Service
public class GameService {
  final GameRepository gameRepository;

  GameService(GameRepository gameRepository){
    this.gameRepository = gameRepository;
  }

  @GetMapping
  public List<GameModel> getGames(){
    return gameRepository.findAll();
  }

  @PostMapping
  public GameModel createGame(GameDTO body){
    if (gameRepository.existsByName(body.getName()))
      throw new GameConflictException("Game already exists");
    
    GameModel game = new GameModel(body);
    return gameRepository.save(game);
  }

  public GameModel getGameById(Long id){
    GameModel game = gameRepository.findById(id).orElseThrow(
      () -> new GameNotFoundException("Game does not exists")
    );
    return game;
  }
}
