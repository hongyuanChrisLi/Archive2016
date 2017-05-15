package javathreads.examples.ch02.example2;

import java.util.*;
import javathreads.examples.ch02.*;

public class RandomCharacterGenerator extends Thread implements CharacterSource {
	static char[] chars;
	static String charArray = "abcdefghijklmnopqrstuvwxyz0123456789";
	static {
		chars = charArray.toCharArray();
	}
	Random random;
	CharacterEventHandler handler;

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
		for (;;) {
			nextCharacter();
			try {
				Thread.sleep(getPauseTime());
			} catch (InterruptedException ie) {
				return;
			}
		}
	}
}