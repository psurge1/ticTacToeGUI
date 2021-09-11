package frontEnd;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;

import backEnd.backEndTicTacToe;

public class frontEndTicTacToe implements ActionListener{
	static backEndTicTacToe tttBE;
	public static boolean won;
	private static JFrame frame;
	private static JPanel panel;
	private static JLabel label, 
		turn,
		ask,
		winner,
		labelOne=new JLabel(),
		labelTwo=new JLabel(),
		labelThree=new JLabel(),
		labelFour=new JLabel(),
		labelFive=new JLabel(),
		labelSix=new JLabel(),
		labelSeven=new JLabel(),
		labelEight=new JLabel(),
		labelNine=new JLabel();
	private static JLabel[] positionLabels = {
		labelOne,
		labelTwo,
		labelThree,
		labelFour,
		labelFive,
		labelSix,
		labelSeven,
		labelEight,
		labelNine
		};
	private static JButton buttonOne, 
		buttonTwo,
		buttonThree,
		buttonFour,
		buttonFive,
		buttonSix,
		buttonSeven,
		buttonEight,
		buttonNine,
		play,
		exit,
		comp;
	private static ImageIcon logo;
	private static char XorO;
	private static HashMap<JButton, Integer[]> buttons;
	private static int side = 50;
	private static int[] rows = {80, 140, 200}, columns = {10, 70, 130}; //rows: vertical, columns: horizontal
	public static int placement;
	public static void main(String[] args) {
		tttBE = new backEndTicTacToe();
		frame = new JFrame("Tic Tac Toe");
		panel = new JPanel();
		buttons = new HashMap<JButton, Integer[]>();
		
		frame.setSize(203, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		logo = new ImageIcon(frontEndTicTacToe.class.getResource("logo1.png"));
		frame.setIconImage(logo.getImage());
		
		panel.setLayout(null);

		label = new JLabel("Tic Tac Toe");
		label.setBounds(60, 10, 80, 25);
		panel.add(label);
		
		winner = new JLabel("");;
		winner.setBounds(10, 40, 80, 25);
		panel.add(winner);
		
		turn = new JLabel("");
		turn.setBounds(10, 40, 80, 25);
		panel.add(turn);
		
		menu();
		
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton genericButton = (JButton) e.getSource();
		if (genericButton==play) {
			winner.setText("X:");
			tttBE.init(1);
			buttonInit();
			frame.setSize(203, 300);
		} else if (genericButton==exit) {
			tttBE.init(3);
			frame.dispose();
		} else if (genericButton==comp) {
			winner.setText("");
			tttBE.init(2);
			buttonInit();
			frame.setSize(203, 300);
		} else if (buttons.containsKey(genericButton)){
			XorO = tttBE.select();
			if (XorO == 'X') {
				winner.setText("O:");
			} else if (XorO == 'O') {
				winner.setText("X:");
			}
			placement = onClicked(genericButton);
			if (!tttBE.toArr(placement, XorO)) {
				winner.setText("Winner is " + XorO + "!");
				onFinish();
			}
		}
	}
	
	private static void buttonInit() {
		// buttons
		Integer[] oneCoord = {1, 0, 0}, twoCoord = {2, 1, 0}, threeCoord = {3, 2, 0}, fourCoord = {4, 0, 1}, fiveCoord = {5, 1, 1}, sixCoord = {6, 2, 1}, sevenCoord = {7, 0, 2}, eightCoord = {8, 1, 2}, nineCoord = {9, 2, 2};
		buttonOne = new JButton("");
		buttonOne.setBounds(columns[0], rows[0], side, side);
		buttonOne.addActionListener(new frontEndTicTacToe());
		buttons.put(buttonOne, oneCoord);
		panel.add(buttonOne);

		buttonTwo = new JButton("");
		buttonTwo.setBounds(columns[1], rows[0], side, side);
		buttonTwo.addActionListener(new frontEndTicTacToe());
		buttons.put(buttonTwo, twoCoord);
		panel.add(buttonTwo);

		buttonThree = new JButton("");
		buttonThree.setBounds(columns[2], rows[0], side, side);
		buttonThree.addActionListener(new frontEndTicTacToe());
		buttons.put(buttonThree, threeCoord);
		panel.add(buttonThree);

		buttonFour = new JButton("");
		buttonFour.setBounds(columns[0], rows[1], side, side);
		buttonFour.addActionListener(new frontEndTicTacToe());
		buttons.put(buttonFour, fourCoord);
		panel.add(buttonFour);

		buttonFive = new JButton("");
		buttonFive.setBounds(columns[1], rows[1], side, side);
		buttonFive.addActionListener(new frontEndTicTacToe());
		buttons.put(buttonFive, fiveCoord);
		panel.add(buttonFive);

		buttonSix = new JButton("");
		buttonSix.setBounds(columns[2], rows[1], side, side);
		buttonSix.addActionListener(new frontEndTicTacToe());
		buttons.put(buttonSix, sixCoord);
		panel.add(buttonSix);

		buttonSeven = new JButton("");
		buttonSeven.setBounds(columns[0], rows[2], side, side);
		buttonSeven.addActionListener(new frontEndTicTacToe());
		buttons.put(buttonSeven, sevenCoord);
		panel.add(buttonSeven);

		buttonEight = new JButton("");
		buttonEight.setBounds(columns[1], rows[2], side, side);
		buttonEight.addActionListener(new frontEndTicTacToe());
		buttons.put(buttonEight, eightCoord);
		panel.add(buttonEight);

		buttonNine = new JButton("");
		buttonNine.setBounds(columns[2], rows[2], side, side);
		buttonNine.addActionListener(new frontEndTicTacToe());
		buttons.put(buttonNine, nineCoord);
		panel.add(buttonNine);
	}
	
	private static int onClicked(JButton clickedButton) {
		int clickedPosition = buttons.get(clickedButton)[0];
		clickedButton.setVisible(false);
		JLabel labelToBeDisplayed = positionLabels[clickedPosition-1];
		labelToBeDisplayed.setText(String.valueOf(XorO));
		labelToBeDisplayed.setBounds(columns[buttons.get(clickedButton)[1]]+20, rows[buttons.get(clickedButton)[2]], side, side);
		panel.add(labelToBeDisplayed);
		return clickedPosition;
	}
	
	public static void menu() {
		int baseHeight = 285;
		ask = new JLabel("Menu");
		ask.setBounds(77, 260, 100, 20);
		panel.add(ask);
		
		play = new JButton("Multiplayer");
		play.setBounds(45, baseHeight, 100, 20);
		play.addActionListener(new frontEndTicTacToe());
		panel.add(play);
		
		exit = new JButton("Quit");
		exit.setBounds(45, baseHeight+40, 100, 20);
		exit.addActionListener(new frontEndTicTacToe());
		panel.add(exit);
		
		comp = new JButton("Computer");
		comp.setBounds(45, baseHeight+20, 100, 20);
		comp.addActionListener(new frontEndTicTacToe());
		panel.add(comp);
	}
	
	private static void onFinish() {
		frame.setSize(203, 400);
		for (JButton l:buttons.keySet()) {
			l.setVisible(false);
		}
		for (JLabel clickedPlace:positionLabels) {
			panel.remove(clickedPlace);
		}
	}
}
