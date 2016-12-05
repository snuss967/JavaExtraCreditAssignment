package GameLogic;

import java.util.ArrayList;
import java.util.List;

//GameData is a class that persists our game data between different frames so that we don't have to pass data to the individual frames and have a central data model

//I create an object of this class as a singleton because A) we only want one instance managing the data of my game B) we can easily serialize this object for basic save game functionality
//If we were writing this game for real I would probably want to put in a bit more security in terms of what can read/write from this object. Would probably want to put another layer in front
//of this object at a bare minimum to filter requests
public class GameData {

	private static GameData singleton = null;

	private GameData() {
		// our singleton constructor
	}

	public static GameData getInstance() {
		if (singleton == null) {
			singleton = new GameData();
		}
		return singleton;
	}

	// game data variables
	// Design Consideration: could remove the guessCount variable and only use
	// the length of the charactersGuessedArray list as they should be the same
	private int guessCount = 0;
	private int missedCount = 0;
	private String guessWord = "No Current Word";
	// the characters we have already guessed
	private List<Character> charactersGuessed = new ArrayList<Character>();

	public List<Character> getCharactersGuessed() {
		return charactersGuessed;
	}

	public boolean addCharacter(char letter) {
		if (!charactersGuessed.contains(letter)) {
			charactersGuessed.add(letter);
			return true;
		} else {
			return false;
		}
	}

	public int getGuessCount() {
		return guessCount;
	}

	public void incrementGuessCount() {
		guessCount++;
	}

	public int getMissedCount() {
		return missedCount;
	}

	public void incrementMissedCount() {
		missedCount++;
	}

	public String getGuessWord() {
		return guessWord;
	}

	public void setGuessWord(String guessWord) {
		this.guessWord = guessWord;
	}

	// resets the game stats guess and miss count as well as the letters that we
	// have already guessed
	public void reset() {
		guessCount = 0;
		missedCount = 0;
		charactersGuessed = new ArrayList<Character>();
	}

}
