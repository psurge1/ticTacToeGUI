package backEnd;

public class backEndTicTacToe {
    public static int[] occupied = {10, 10, 10, 10, 10, 10, 10, 10, 10};
    private static char[][] playingBoard = {
            {' ', ' ', ' '}, 
            {' ', ' ', ' '}, 
            {' ', ' ', ' '}
        };
    static int turn, selection;
	public static int dice(int max, int min) {
        if (max<min) {
            return 0;
        }
        max++;
		return (int) (Math.random()*(max-min)+min);
	}
    private static boolean isIdentical(char[] arrChar) {
        for (int arrPosition=1; arrPosition<arrChar.length; arrPosition++) {
            if (arrChar[arrPosition] != arrChar[arrPosition-1]) {
		        return false;
            }
        }
        return true;
    }
    private static char isWon(char[][] playBoard) {
        // check if there is a horizontal victory
        int row;
        for (row=0; row<playBoard.length; row++) {
            if (isIdentical(playBoard[row])) {
                return playBoard[row][0];
            }
        }

        // check if there is a diagonal victory
        char[] alignment = new char[3];
        for (row=0; row<playBoard.length; row++) {
            alignment[row] = playBoard[row][row];
        }
        if (isIdentical(alignment)) {
            return alignment[0];
        }

        for (row=0; row<playBoard.length; row++) {
            alignment[row] = playBoard[row][(playBoard.length-1)-row];
        }
        if (isIdentical(alignment)) {
            return alignment[0];
        }

        // check if there is a vertical victory
        for (row=0; row<playBoard.length; row++) {
            for (int rowUnit=0; rowUnit<playBoard[row].length; rowUnit++) {
                alignment[rowUnit] = playBoard[rowUnit][row];
            }
            if (isIdentical(alignment)) {
                return alignment[0];
            }
        }
        return 'N';
    }
    
    public void init(int userStart) {
        System.out.println("Tic Tac Toe Started");
        turn = 0;
        selection = 1;
        boardReset();
        if (userStart==2) {
            computer.setChosen(occupied);
            System.out.println(computer.levelZero());
        }
    }
    
    private void boardReset() {
        for (char[] rows:playingBoard) {
            for (int unit = 0; unit<rows.length; unit++) {
                rows[unit]=' ';
            }
        }
    }
    
    public char select() {
    	selection++;
    	if (selection%2 == 0) {
    		return 'X';
    	} else {
    		return 'O';
    	}
    }
    public boolean toArr(int userPick, char XO) {
        occupied[turn] = userPick;
    	if (userPick<=3) {
        	playingBoard[0][userPick-1] = XO;
        } else if (userPick<=6) {
        	playingBoard[1][userPick-4] = XO;
        } else {
        	playingBoard[2][userPick-7] = XO;
        }
    	for (char[] carr:playingBoard) {
    		for (char c:carr) {
    			System.out.print(c);
    		}
    		System.out.print("\n");
    	}
    	if (isWon(playingBoard)=='X'||isWon(playingBoard)=='O') {
    		return false;
    	}
    	return true;
    }
}
