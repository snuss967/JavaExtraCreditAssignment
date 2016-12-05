package HangmanGameScreens;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import GameLogic.GameData;

//Lose screen will be displayed when we lose can probably use the same screen for both win and lose
public class Lose extends JFrame {
	// instance variables
	private int guessedTimes = 0;
	private int missedTimes = 0;
	private String word = "";

	// constructor
	public Lose(int missedTimes, int guessedTimes, String word) {
		this.guessedTimes = guessedTimes;
		this.missedTimes = missedTimes;
		this.word = word;
		intializeGUI();
	}

	private void intializeGUI() {
		setResizable(false);
		BoxLayout box = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		setLayout(box);

		// create game data display labels
		JLabel titleLabel = new JLabel("<html><div style='font-size: 40;'> You Lose </div></html>");
		getContentPane().add(titleLabel);

		JLabel theAnswerTitle = new JLabel("<html><div style=' font-size: 36;'> The Answer Was: </div></html>");
		getContentPane().add(theAnswerTitle);

		JLabel answer = new JLabel("<html><div style='font-size: 30;'>" + word + "</div></html>");
		getContentPane().add(answer);

		JLabel guessedTimesLabel = new JLabel(
				"<html><div style='font-size: 30;'>You Guessed " + guessedTimes + " times </div></html>");
		getContentPane().add(guessedTimesLabel);

		JLabel missedTimesLabel = new JLabel(
				"<html><div style=' font-size: 30;'>You Guessed Wrong " + missedTimes + " times </div></html>");
		getContentPane().add(missedTimesLabel);

		Dimension playGameButtonDim = new Dimension(100, 40);
		JButton playGame = new JButton("<html><div style'text-align: center;'> Play Again </div></html>");
		playGame.setPreferredSize(playGameButtonDim);
		Font fontPlayGame = new Font("Times New Roman", Font.BOLD, 20);
		playGame.setFont(fontPlayGame);
		playGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameData.getInstance().setGuessWord("No Current Word");
				NewGame newGame = new NewGame();
				setVisible(false);
				newGame.setVisible(true);
			}
		});
		getContentPane().add(playGame);

		setTitle("Hangman");
		setBounds(100, 100, 450, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
}