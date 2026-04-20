import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class DSK_MON extends JFrame{
private final String[] symbols = {"🦞", "🦞", "🦞", "🐢", "🐢", "🐢", "🐅", "🐅", "🐅", "🦎", "🦎", "🦎", "🦜", "🦜", "🦜", "🦋", "🦋", "🦋"};

private final JButton[] buttons = new JButton[15];
private String[] board;
private JButton firstcard = null;
private JButton secondcard = null;
private JButton thirdcard = null;
private int matchesFound = 0;

public DSK_MON() {
setTitle("The DSK_MON Game");
setSize(600, 600);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLayout(new GridLayout(4,5,4,5));

setupGame();
setVisible(true);
}
private void setupGame() {
//POJO Logic: Prepare the shuffled board
ArrayList<String> list = new ArrayList<>();
Collections.addAll(list,symbols);
Collections.shuffle(list);
board = list.toArray(new String[0]);

//GUI Setup: Create buttons
for (int i = 0; i < 18; i++){
buttons[i] = new JButton("?");
buttons[i].setFont(new Font("Segoe UI Emoji",Font.BOLD, 45));
buttons[i].setFocusPainted(false);
final int index = i;
buttons[i].addActionListener(e -> handleCardClick(buttons[index],index));
add(buttons[i]);
	}
}
private void handleCardClick(JButton clickedButton, int index) {
if (clickedButton.getText().equals("") || clickedButton == firstcard || clickedButton == secondcard || thirdcard != null) return;

clickedButton.setText(board[index]);
if (firstcard == null) {
firstcard = clickedButton;
} else {
secondcard = clickedButton;
thirdcard = clickedButton;
Timer timer = new Timer(100, e -> checkMatch());
timer.setRepeats(false);
timer.start();
	}
}
private void checkMatch() {
if (firstcard.getText().equals(secondcard.getText().equals(thirdcard.getText()))) {
firstcard.setEnabled(false);
secondcard.setEnabled(false);
thirdcard.setEnabled(false);
matchesFound++;
if (matchesFound == 9) JOptionPane.showMessageDialog(this, "Good Job, You WON!");
} else {
firstcard.setText("?");
secondcard.setText("?");
thirdcard.setText("?");
}
firstcard = null;
secondcard = null;
thirdcard = null;
}
public static void main(String [] args) {
SwingUtilities.invokeLater(DSK_MON::new);
	}
}
