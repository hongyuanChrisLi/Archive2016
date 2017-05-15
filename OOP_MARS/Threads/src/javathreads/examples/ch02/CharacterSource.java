package javathreads.examples.ch02;

/*
 * Character Listeners are used to listen to the right source. 
 */


public interface CharacterSource {
	public void addCharacterListener(CharacterListener cl);

	public void removeCharacterListener(CharacterListener cl);

	public void nextCharacter();
}