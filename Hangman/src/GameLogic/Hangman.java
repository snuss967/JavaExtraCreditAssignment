package GameLogic;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import HangmanGameScreens.NewGame;

public class Hangman {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGame newGameFrame = new NewGame();
					JOptionPane.showMessageDialog(null,
							"Welcome To Hangman Version 1.0\nOn the next screen you will be given the opportunity to set up your game.\nEnter the word you want to play hangman with and then pass the game to someone else.");
					newGameFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
