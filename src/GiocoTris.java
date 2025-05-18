import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GiocoTris extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private boolean turno = true;
	private JButton[] caselle = new JButton[9];

	public GiocoTris() {
		setTitle("Tris");
		setLayout(new BorderLayout());
		setSize(800, 600);
		add(mainPanel(), BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JPanel mainPanel() {
		JPanel mainPanel = new JPanel(new GridLayout(3, 3));
		for (int i = 0; i < 9; i++) {
			JButton casella = new JButton();
			casella.setFont(casella.getFont().deriveFont(100f));
			casella.addActionListener(this);
			caselle[i] = casella;
			mainPanel.add(casella);
		}
		return mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton source = (JButton) e.getSource();

		if ("".equals(source.getText())) {
			source.setText(turno ? "X" : "O");

			if (controllaVittoria()) {
				JOptionPane.showMessageDialog(this, "Ha vinto " + (turno ? "X" : "O") + "!");
				reset();
			} else if (tuttePiene()) {
				JOptionPane.showMessageDialog(this, "Pareggio!");
				reset();
			} else {
				turno = !turno;
			}
		}
	}

	private boolean tuttePiene() {
		for (JButton b : caselle) {
			if ("".equals(b.getText()))
				return false;
		}
		return true;
	}

	private boolean controllaVittoria() {
		int[][] combinazioni = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // righe
								 { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // colonne
								 { 0, 4, 8 }, { 2, 4, 6 } // diagonali
		};

		for (int[] comb : combinazioni) {
			String t1 = caselle[comb[0]].getText();
			String t2 = caselle[comb[1]].getText();
			String t3 = caselle[comb[2]].getText();

			if (!t1.equals("") && t1.equals(t2) && t2.equals(t3)) {
				return true;
			}
		}
		return false;
	}

	private void reset() {
		for (JButton b : caselle) {
			b.setText("");
		}
	}
}