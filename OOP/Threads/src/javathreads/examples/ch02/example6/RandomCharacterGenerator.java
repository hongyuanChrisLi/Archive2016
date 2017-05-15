package javathreads.examples.ch02.example6;

import java.util.*;
import javathreads.examples.ch02.*;

public class RandomCharacterGenerator implements CharacterSource,Runnable {
	static char[] chars;
	static String charArray = "abcdefghijklmnopqrstuvwxyz0123456789";
	static {
		chars = charArray.toCharArray();
	}
	Random random;
	CharacterEventHandler handler;
	private volatile boolean done = false;

	public RandomCharacterGenerator() {
		random = new Random();
		handler = new CharacterEventHandler();
	}

	public int getPauseTime() {
		return (int) (Math.max(1000, 5000 * random.nextDouble()));
	}
	
	@Override
	public void addCharacterListener(CharacterListener cl) {
		handler.addCharacterListener(cl);
	}

	@Override
	public void removeCharacterListener(CharacterListener cl) {
		handler.removeCharacterListener(cl);
	}

	@Override
	public void nextCharacter() {
		int num = (int) chars[random.nextInt(chars.length)];
		System.out.println(num);
		handler.fireNewCharacter(this,num);
	}

	@Override
	public void run() {
		while (!done) {
			nextCharacter();
			try {
				Thread.sleep(getPauseTime());
			} catch (InterruptedException ie) {
				return;
			}
		}
	}
	
	public void setDone () {
		done = true;
	}
}