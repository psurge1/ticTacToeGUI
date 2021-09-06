package backEnd;

public class computer {
	static int[] chosen;
	static int max = 10;
	static int min = 1;
	protected static void setChosen(int[] arr) {
		chosen = arr;
	}
	protected static int levelZero() {
        while (true) {
            int compGuess = (int) (Math.random()*(max-min)+min);
            if (!in(compGuess, chosen)) {
                return compGuess;
            }
        }
	}
	protected static int levelOne() {
		//TODO complete level one function
		return 0;
	}
	protected static int levelTwo() {
		//TODO complete level two function
		return 0;
	}
	protected static boolean in(int n, int[] array) {
    	for (int number:array) {
    		if (number == n) {
    			return true;
    		}
    	}
    	return false;
    }
}
