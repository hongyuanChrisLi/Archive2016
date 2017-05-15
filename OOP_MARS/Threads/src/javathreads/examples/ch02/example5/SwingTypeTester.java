package javathreads.examples.ch02.example5;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javathreads.examples.ch02.*;

@SuppressWarnings("serial")
public class SwingTypeTester extends JFrame implements CharacterSource {
	protected RandomCharacterGenerator producer;
	private CharacterDisplayCanvas displayCanvas;
	private CharacterDisplayCanvas feedbackCanvas;
	private JButton quitButton;
	private JButton startButton;
	private JButton stopButton;
	private CharacterEventHandler handler;

	public SwingTypeTester() {
		initComponents();
	}

	private void initComponents() {
		handler = new CharacterEventHandler();
		displayCanvas = new CharacterDisplayCanvas();
		feedbackCanvas = new CharacterDisplayCanvas(this);
		quitButton = new JButton();
		startButton = new JButton();
		stopButton = new JButton();
		add(displayCanvas, BorderLayout.NORTH);
		add(feedbackCanvas, BorderLayout.CENTER);
		JPanel p = new JPanel();
		startButton.setText("Start");
		quitButton.setText("Quit");
		stopButton.setText("Stop");
		stopButton.setEnabled(false);
		p.add(startButton);
		p.add(stopButton);
		p.add(quitButton);
		add(p, BorderLayout.SOUTH);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				quit();
			}
		});
		feedbackCanvas.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (c != KeyEvent.CHAR_UNDEFINED)
					newCharacter((int) c);
			}
		});
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				producer = new RandomCharacterGenerator();
				displayCanvas.setCharacterSource(producer);
				Thread t = new Thread(producer);
				t.start();
				startButton.setEnabled(false);
				stopButton.setEnabled(true);
				feedbackCanvas.setEnabled(true);
				feedbackCanvas.requestFocus();
			}
		});
		
		stopButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent evt) {
				startButton.setEnabled(true);
				stopButton.setEnabled(false);
				producer.setDone( );
				feedbackCanvas.setEnabled(false);
			}
		});
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				quit();
			}
		});
		pack();
	}

	private void quit() {
		System.exit(0);
	}
	
	@Override
	public void addCharacterListener(CharacterListener cl) {
		handler.addCharacterListener(cl);
	}

	@Override
	public void removeCharacterListener(CharacterListener cl) {
		handler.removeCharacterListener(cl);
	}

	public void newCharacter(int c) {
		handler.fireNewCharacter(this, c);
	}

	@Override
	public void nextCharacter() {
		throw new IllegalStateException("We don't produce on demand");
	}

	public static void main(String args[]) {
		new SwingTypeTester().setVisible(true);;
	}
}