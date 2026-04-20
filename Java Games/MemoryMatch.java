import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class MemoryMatch extends JFrame {
	private final String[] symbols = {"🍏", "🍏", "🍌", "🍌", "🥭", "🥭", "🍇", "🍇", "🥜", "🥜", "🍐", "🍐", "🍉", "🍉", "🍊", "🍊", "🥒", "🥒"};
	private final JButton[] buttons = new JButton[18];
	private String[] board;
	private JButton firstcard = null;
	private JButton secondcard = null;
	private int matchesFound = 0;

	public MemoryMatch() {
		setTitle("NIIT Modern Memory Match");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4,4,10,10));

		setupGame();
		setVisible(true);
	}

	private void setupGame() {
		//POJO Logic: Prepare the shuffled board
		ArrayList<String> list = new ArrayList<>();
		Collections.addAll(list, symbols);
		Collections.shuffle(list);
		board = list.toArray(new String[0]);

		//GUI Setup: Create buttons
		for (int i = 0; i < 18; i++) {
			buttons[i] = new JButton("?");
			buttons[i].setFont(new Font("Segoe UI Emoji", Font.BOLD, 40));
			buttons[i].setFocusPainted(false);
			final int index = i;
			buttons[i].addActionListener(e -> handleCardClick(buttons[index],index));
				add(buttons[i]);
				}
			}

			private void handleCardClick(JButton clickedButton, int index) {
				if (clickedButton.getText().equals("") || clickedButton == firstcard || secondcard != null) return;

			clickedButton.setText(board[index]);

			if (firstcard == null) {
				firstcard = clickedButton;
			} else {
			secondcard = clickedButton;
			Timer timer = new Timer(800, e -> checkMatch());
			timer.setRepeats(false);
			timer.start();
		}
	}
	private void checkMatch() {
		if (firstcard.getText().equals(secondcard.getText())) {
		firstcard.setEnabled(false);
		secondcard.setEnabled(false);
		matchesFound++;
		if (matchesFound == 9) JOptionPane.showMessageDialog(this, "Master Developer, You won!");
	} else {
		firstcard.setText("?");
		secondcard.setText("?");
	}
	firstcard = null;
	secondcard = null;
}
	public static void main(String [] args) {
		SwingUtilities.invokeLater(MemoryMatch::new);
		}
	}

