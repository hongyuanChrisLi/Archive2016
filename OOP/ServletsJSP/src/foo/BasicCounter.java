package foo;

public class BasicCounter {
	private static int count;

	public static synchronized int getCount() {
		count++;
		return count;
	}
}