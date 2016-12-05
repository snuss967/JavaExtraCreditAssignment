package HangmanGameScreens;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import GameLogic.GameData;

//our game play interface
public class GameScreen extends JFrame {

	// Variables
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	StringBuilder blanksWithUnderscores = new StringBuilder();
	private String[] labelNames = new String[8];
	HashMap<String, String> hashMapPhotos = new HashMap<String, String>();
	HashMap<String, JLabel> hangManLabels = new HashMap<String, JLabel>();

	// set the values of our LabelName Array with image names we know the size
	// of this and will never be adding anything else so I went with an array
	public void createLabelNameArray() {
		for (int i = 0; i < 8; i++) {
			labelNames[i] = "lblNewLabel_" + (i + 1);
		}
	}

	// using our label names from our array set the empty images we don't need
	// to keep track of the off images because we will never have to revert. An
	// undo button is cheating.
	public void createHashMapPhotos() {
		for (int i = 0; i < labelNames.length; i++) {
			hashMapPhotos.put(labelNames[i], "/Images/Bottom" + (8 - i) + ".png");
		}
	}

	// intialize our GUI
	private void intializeGUI() {
		setTitle("Hangman");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		for (int i = 0; i < GameData.getInstance().getGuessWord().length(); i++) {
			blanksWithUnderscores.append('_');

		}

		JLabel label = new JLabel("");
		contentPane.add(label);

		// create our hangman picture have empty photos in right now as an easy
		// way to keep size as disabling the visiblitiy will cause things to
		// fill in our their space
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(GameScreen.class.getResource("/Images/EmptyBottom8.png")));
		contentPane.add(lblNewLabel_1);
		hangManLabels.put(labelNames[0], lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(GameScreen.class.getResource("/Images/EmptyBottom7.png")));
		hangManLabels.put(labelNames[1], lblNewLabel_2);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(GameScreen.class.getResource("/Images/EmptyBottom6.png")));
		hangManLabels.put(labelNames[2], lblNewLabel_3);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(GameScreen.class.getResource("/Images/EmptyBottom5.png")));
		hangManLabels.put(labelNames[3], lblNewLabel_4);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(GameScreen.class.getResource("/Images/EmptyBottom4.png")));
		hangManLabels.put(labelNames[4], lblNewLabel_5);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(GameScreen.class.getResource("/Images/EmptyBottom3.png")));
		hangManLabels.put(labelNames[5], lblNewLabel_6);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(GameScreen.class.getResource("/Images/EmptyBottom2.png")));
		hangManLabels.put(labelNames[6], lblNewLabel_7);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(GameScreen.class.getResource("/Images/EmptyBottom1.png")));
		hangManLabels.put(labelNames[7], lblNewLabel_8);
		contentPane.add(lblNewLabel_8);

		Component verticalStrut = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut);

		JLabel lblNewLabel_9 = new JLabel(blanksWithUnderscores.toString());
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("LETTERS GUESSED:");
		contentPane.add(lblNewLabel_10);

		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnGuessLetter = new JButton("Guess Letter");
		btnGuessLetter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().toString().length() == 1) {
					if (GameData.getInstance().addCharacter(textField.getText().toString().toUpperCase().charAt(0))) {
						if (GameData.getInstance().getGuessWord()
								.contains(textField.getText().toUpperCase().toString())) {
							for (int i = 0; i < GameData.getInstance().getGuessWord().length(); i++) {
								if (GameData.getInstance().getGuessWord().toUpperCase().charAt(i) == textField.getText()
										.toUpperCase().toString().charAt(0)) {
									blanksWithUnderscores.setCharAt(i,
											textField.getText().toString().toUpperCase().charAt(0));
									if (blanksWithUnderscores.toString()
											.equals(GameData.getInstance().getGuessWord().toUpperCase())) {
										GameData.getInstance().incrementGuessCount();
										Win winScreen = new Win(GameData.getInstance().getMissedCount(),
												GameData.getInstance().getGuessCount(),
												GameData.getInstance().getGuessWord().toUpperCase());
										setVisible(false);
										winScreen.setVisible(true);
									}
								}
							}
							lblNewLabel_9.setText(blanksWithUnderscores.toString());
							String lettersUsed = lblNewLabel_10.getText().toUpperCase();
							if (lettersUsed.length() != 16) {
								lettersUsed += ',';
							}
							lettersUsed += textField.getText().toString().toUpperCase().charAt(0);
							lblNewLabel_10.setText(lettersUsed);
							GameData.getInstance().incrementGuessCount();
						} else {
							String labelName = labelNames[GameData.getInstance().getMissedCount()];
							JLabel tempLabel = hangManLabels.get(labelName);
							tempLabel
									.setIcon(new ImageIcon(GameScreen.class.getResource(hashMapPhotos.get(labelName))));
							GameData.getInstance().incrementGuessCount();
							GameData.getInstance().incrementMissedCount();
							if (GameData.getInstance().getMissedCount() == 8) {
								Lose lose = new Lose(GameData.getInstance().getMissedCount(),
										GameData.getInstance().getGuessCount(), GameData.getInstance().getGuessWord());
								setVisible(false);
								lose.setVisible(true);
							}
							String lettersUsed = lblNewLabel_10.getText().toUpperCase();
							if (lettersUsed.length() != 16) {
								lettersUsed += ',';
							}
							lettersUsed += textField.getText().toString().toUpperCase().charAt(0);
							lblNewLabel_10.setText(lettersUsed);
						}
					} else {
						JOptionPane.showMessageDialog(null, "You have already used this character.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "You can only enter one letter at a time.");
				}

			}
		});
		contentPane.add(btnGuessLetter);

		textField_1 = new JTextField();
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnGuessButton = new JButton("Guess Word");
		btnGuessButton.addActionListener((new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (GameData.getInstance().getGuessWord().toString().equals(textField_1.getText().toUpperCase())) {
					// Winner
					Win winScreen = new Win(GameData.getInstance().getMissedCount(),
							GameData.getInstance().getGuessCount(), GameData.getInstance().getGuessWord());
					setVisible(false);
					winScreen.setVisible(true);
				} else {
					// Loser
					GameData.getInstance().incrementGuessCount();
					GameData.getInstance().incrementMissedCount();
					Lose loseScreen = new Lose(GameData.getInstance().getMissedCount(),
							GameData.getInstance().getGuessCount(),
							GameData.getInstance().getGuessWord().toUpperCase());
					setVisible(false);
					loseScreen.setVisible(true);
				}
			}
		}));
		contentPane.add(btnGuessButton);

		JButton btnForfeit = new JButton("Forfeit");
		btnForfeit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lose lostGame = new Lose(GameData.getInstance().getMissedCount(),
						GameData.getInstance().getGuessCount(), GameData.getInstance().getGuessWord());
				setVisible(false);
				lostGame.setVisible(true);
			}
		});
		contentPane.add(btnForfeit);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener((new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameData.getInstance().setGuessWord("No Current Word");
				NewGame newGame = new NewGame();
				setVisible(false);
				newGame.setVisible(true);
			}
		}));
		contentPane.add(btnNewGame);
	}

	/**
	 * Create the frame.
	 */
	public GameScreen() {
		GameData.getInstance().reset();
		createLabelNameArray();
		createHashMapPhotos();
		intializeGUI();
	}

}
