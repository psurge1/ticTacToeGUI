package backEnd;

public class Computer {
	static int[] chosen;
	static int max = 10;
	static int min = 1;
	public static void setChosen(int[] arr) {
		chosen = arr;
	}
	public static int[] getChosen() {
		return chosen;
	}
	public static int random() {
        while (true) {
            int compGuess = (int) (Math.random()*(max-min)+min);
            if (!in(compGuess, chosen)) {
                return compGuess;
            }
        }
	}

	public static boolean in(int n, int[] array) {
    	for (int number:array) {
    		if (number == n) {
    			return true;
    		}
    	}
    	return false;
    }
}
