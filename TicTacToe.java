
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TicTacToe implements ActionListener {

	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JPanel panel_1 = new JPanel();
	JPanel panel_2 = new JPanel();
	JLabel textfield = new JLabel();
	JOptionPane pane = new JOptionPane();
	JLabel Player1NewLabel, Player2NewLabel, TieLabel_2;
	JButton[] buttons = new JButton[9];
	JButton ResetButton;
	JTextArea textArea;
	JTextField player1Score, player2Score, tieScore;
	boolean player1_turn;

	TicTacToe() {
		player1_turn = true;

		// create a title for th  game
		frame.setTitle("Tic Tac Toe Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);

		// create a panel for the frame
		frame.add(panel, BorderLayout.NORTH);

		// create and set the RESET button
		ResetButton = new JButton("Reset");

		// using ActionListener method to reset the button after finishing the game
		ResetButton.addActionListener(new MyActionListener());
		ResetButton.setEnabled(true);
		panel.add(ResetButton); // make a panel that contains the reset button

		// create a player 1 to play in a label
		Player1NewLabel = new JLabel("Player1: ");
		player1Score = new JTextField("0");
		player1Score.setColumns(5);
		player1Score.setEditable(false);

		// add player 1 label to the panel
		panel.add(Player1NewLabel);
		panel.add(player1Score);

		// create a player 1 to play in a label
		Player2NewLabel = new JLabel("Player2: ");
		player2Score = new JTextField("0");
		player2Score.setColumns(5);
		player2Score.setEditable(false);

		// add player 1 label to the panel
		panel.add(Player2NewLabel);
		panel.add(player2Score);

		// create a tie in case if no one win to a label
		TieLabel_2 = new JLabel("Tie: ");
		tieScore = new JTextField("0");
		tieScore.setColumns(5);
		tieScore.setEditable(false);

		// add tie label to the panel
		panel.add(TieLabel_2);
		panel.add(tieScore);

		// move the panel_1 to the EAST direction in BorderLayout
		frame.add(panel_1, BorderLayout.EAST);
		// set the row and column for textArea
		textArea = new JTextArea(400, 400);
		textArea.setEditable(false);
		// add the textArea to the Jpanel panel_1
		panel_1.add(textArea);

		textArea.setColumns(20);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		textfield.setHorizontalAlignment(JLabel.CENTER);

		title_panel.setLayout(new BorderLayout());

		button_panel.setLayout(new GridLayout(3, 3));

		/**
		 * create a for loop to assign the index to an array button which respectively
		 * from button 1 to button 9
		 */
		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);

			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}

		title_panel.add(textfield);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);

		frame.add(panel, BorderLayout.NORTH);
		frame.add(panel_1, BorderLayout.EAST);

		firstTurn();

	}

	private class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ResetButton) {
				for (int i = 0; i < 9; i++) {
					// make sure that after resetting the game player1 always go first
					player1_turn = true;
					buttons[i].setBackground(null);
					buttons[i].setText("");
					buttons[i].setEnabled(true);
					buttons[i].setFocusable(false);
					buttons[i].addActionListener(this);
					textArea.setText("");
				}
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < 9; i++) {

			if (e.getSource() == buttons[i]) {
				if (buttons[i].getText().isEmpty()) {
					if (player1_turn) {
						buttons[i].setText("1");
						// buttons[i].setEnabled(false);
						// changing to others player after player1_turn
						player1_turn = false;
						// print player 1 move
						textArea.append("player 1: " + text(i) + "\n");
						// textfield.setText("player2 turn");
						check();

					} else {
						buttons[i].setText("2");
						// buttons[i].setEnabled(false);
						// changing to others player after player2_turn
						player1_turn = true;
						// print player 2 move
						textArea.append("player 2: " + text(i) + "\n");
						// textfield.setText("player1 turn");
						check();
					}

				} else {
					JOptionPane.showMessageDialog(frame, "Duplicated move. Choose another one.");
				}

			}
		}

	}

	public void firstTurn() {

		try {
			// make a time interval of 2 seconds to determine which person is going first
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Check method to check if any player got a diagonal,horizontal or vertical
	 * straight line
	 */
	public void check() {
		boolean someoneWin = false;
		if ((buttons[0].getText() == "1") && (buttons[1].getText() == "1") && (buttons[2].getText() == "1")) {
			someoneWin = true;
			xWins(0, 1, 2);
		}
		if ((buttons[3].getText() == "1") && (buttons[4].getText() == "1") && (buttons[5].getText() == "1")) {
			someoneWin = true;
			xWins(3, 4, 5);
		}
		if ((buttons[6].getText() == "1") && (buttons[7].getText() == "1") && (buttons[8].getText() == "1")) {
			someoneWin = true;
			xWins(6, 7, 8);
		}
		if ((buttons[0].getText() == "1") && (buttons[3].getText() == "1") && (buttons[6].getText() == "1")) {
			someoneWin = true;
			xWins(0, 3, 6);

		}
		if ((buttons[1].getText() == "1") && (buttons[4].getText() == "1") && (buttons[7].getText() == "1")) {
			someoneWin = true;
			xWins(1, 4, 7);

		}
		if ((buttons[2].getText() == "1") && (buttons[5].getText() == "1") && (buttons[8].getText() == "1")) {
			someoneWin = true;
			xWins(2, 5, 8);

		}
		if ((buttons[0].getText() == "1") && (buttons[4].getText() == "1") && (buttons[8].getText() == "1")) {
			someoneWin = true;
			xWins(0, 4, 8);

		}
		if ((buttons[2].getText() == "1") && (buttons[4].getText() == "1") && (buttons[6].getText() == "1")) {
			someoneWin = true;
			xWins(2, 4, 6);

		}
		// check O win conditions
		if ((buttons[0].getText() == "2") && (buttons[1].getText() == "2") && (buttons[2].getText() == "2")) {
			someoneWin = true;
			oWins(0, 1, 2);

		}
		if ((buttons[3].getText() == "2") && (buttons[4].getText() == "2") && (buttons[5].getText() == "2")) {
			someoneWin = true;
			oWins(3, 4, 5);

		}
		if ((buttons[6].getText() == "2") && (buttons[7].getText() == "2") && (buttons[8].getText() == "2")) {
			someoneWin = true;
			oWins(6, 7, 8);

		}
		if ((buttons[0].getText() == "2") && (buttons[3].getText() == "2") && (buttons[6].getText() == "2")) {
			someoneWin = true;
			oWins(0, 3, 6);

		}
		if ((buttons[1].getText() == "2") && (buttons[4].getText() == "2") && (buttons[7].getText() == "2")) {
			someoneWin = true;
			oWins(1, 4, 7);

		}
		if ((buttons[2].getText() == "2") && (buttons[5].getText() == "O") && (buttons[8].getText() == "2")) {
			someoneWin = true;
			oWins(2, 5, 8);
		}
		if ((buttons[0].getText() == "2") && (buttons[4].getText() == "2") && (buttons[8].getText() == "2")) {
			someoneWin = true;
			oWins(0, 4, 8);
		}
		if ((buttons[2].getText() == "2") && (buttons[4].getText() == "2") && (buttons[6].getText() == "2")) {
			someoneWin = true;
			oWins(2, 4, 6);
		}
		// this if statement show a tie result if there is no digonal,vertical or
		// horizontal straigt line after everyone's play
		if (!buttons[0].getText().isEmpty() && !buttons[1].getText().isEmpty() && !buttons[2].getText().isEmpty()
				&& !buttons[3].getText().isEmpty() && !buttons[4].getText().isEmpty() && !buttons[5].getText().isEmpty()
				&& !buttons[6].getText().isEmpty() && !buttons[7].getText().isEmpty() && !buttons[8].getText().isEmpty()
				&& someoneWin == false) {
			tie();
		}
	}

	public void xWins(int a, int b, int c) {
		// setting the background BLUE to indicate the winner
		buttons[a].setBackground(Color.BLUE);
		buttons[b].setBackground(Color.BLUE);
		buttons[c].setBackground(Color.BLUE);

		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		String player1 = player1Score.getText();
		// increasing the number (integer type) if the player 1 win, then store it in
		// the Player1Score
		int number = Integer.parseInt(player1) + 1;
		player1Score.setText(Integer.toString(number));
		// the dialog box POP UP right after finishing the game,which the result is
		// Player 1 wins
		JOptionPane.showMessageDialog(frame, "Player 1 Win");
	}

	public void oWins(int a, int b, int c) {
		// setting the background BLUE to indicate the winner
		buttons[a].setBackground(Color.BLUE);
		buttons[b].setBackground(Color.BLUE);
		buttons[c].setBackground(Color.BLUE);

		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		String player2 = player2Score.getText();
		// increasing the number (integer type) if the player 2 win, then store it in
		// the Player2Score
		int number = Integer.parseInt(player2) + 1;
		player2Score.setText(Integer.toString(number));
		// the dialog box POP UP right after finishing the game,which the result is
		// Player 2 wins
		JOptionPane.showMessageDialog(frame, "Player 2 Win");
	}

	public void tie() {

		String score = tieScore.getText();
		// increasing the number (integer type) if two players are tied, then store it
		// in the tieScore
		int number = Integer.parseInt(score) + 1;
		tieScore.setText(Integer.toString(number));
		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		// the dialog box POP UP right after finishing the game,which the result is
		// tie , draw
		JOptionPane.showMessageDialog(frame, "Tie");
	}

	/**
	 * using switch case to indicate the player's click movement button which
	 * respectively be appeared from the textArea
	 */
	public String text(int i) {
		String text = null;
		switch (i) {
		case 0:
			text = "(1,1)";
			break;
		case 1:
			text = "(1,2)";
			break;
		case 2:
			text = "(1,3)";
			break;
		case 3:
			text = "(2,1)";
			break;
		case 4:
			text = "(2,2)";
			break;
		case 5:
			text = "(2,3)";
			break;
		case 6:
			text = "(3,1)";
			break;
		case 7:
			text = "(3,2)";
			break;
		case 8:
			text = "(3,3)";
			break;
		}
		return text;

	}

	public static void main(String[] args) {
		new TicTacToe();
	}

}
