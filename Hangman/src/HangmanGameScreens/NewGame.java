package HangmanGameScreens;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import GameLogic.GameData;

//our initial screen
public class NewGame extends JFrame {

	public NewGame() {
		intializeGUI();
	}

	private void intializeGUI() {
		setBounds(100, 100, 450, 450);
		setResizable(false);
		BoxLayout box = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		setLayout(box);

		// Set labels for display
		JLabel titleLabel = new JLabel("<html><div style='text-align: center; font-size: 40;'> New Game </div></html>");
		getContentPane().add(titleLabel);

		JLabel yourWord = new JLabel("<html><div style='text-align: center; font-size: 36;'> Your Word: </div></html>");
		getContentPane().add(yourWord);

		JLabel emptyWord = new JLabel("<html><div style='text-align: center; font-size: 30;'>"
				+ GameData.getInstance().getGuessWord().toUpperCase() + "</div></html>");
		getContentPane().add(emptyWord);

		Component verticalStrut = Box.createVerticalStrut(95);
		getContentPane().add(verticalStrut);

		JTextField textField = new JTextField();
		getContentPane().add(textField);

		// Save Button to set the word
		Dimension saveButtonDim = new Dimension(100, 40);
		JButton saveGame = new JButton("<html><div style'text-align: center;'> Save Word </div></html>");
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().equals("")) {
					if (textField.getText().length() < 22) {
						GameData.getInstance().setGuessWord(textField.getText().toUpperCase());
						emptyWord.setText("<html><div style='text-align: center; font-size: 30;'>"
								+ GameData.getInstance().getGuessWord().toUpperCase() + "</div></html>");
					} else {
						JOptionPane.showMessageDialog(null, "Your word is too long it must be 22 characters or less.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "You must enter a word before you hit save.");

				}
			}
		});
		Font fontSaveGame = new Font("Times New Roman", Font.BOLD, 20);
		saveGame.setPreferredSize(saveButtonDim);
		saveGame.setFont(fontSaveGame);
		getContentPane().add(saveGame);

		/// Button to launch the game
		Dimension playGameButtonDim = new Dimension(100, 40);
		JButton playGame = new JButton("<html><div style'text-align: center;'> Play Game </div></html>");
		playGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (GameData.getInstance().getGuessWord().equals("No Current Word")) {
					JOptionPane.showMessageDialog(null, "You must enter a new word before you can play.");

				} else {
					GameScreen gs = new GameScreen();
					gs.setVisible(true);
					dispose();
				}
			}
		});
		playGame.setPreferredSize(playGameButtonDim);
		Font fontPlayGame = new Font("Times New Roman", Font.BOLD, 20);
		playGame.setFont(fontPlayGame);
		getContentPane().add(playGame);

		Component verticalStrut2 = Box.createVerticalStrut(95);
		getContentPane().add(verticalStrut2);

		setTitle("Hangman");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}