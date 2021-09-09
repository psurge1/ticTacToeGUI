package backEnd;

import java.util.Arrays;

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
        	if (arrChar[arrPosition] == 'X' || arrChar[arrPosition] == 'O') {
	            if (arrChar[arrPosition] != arrChar[arrPosition-1]) {
			        return false;
	            }
        	} else {
        		return false;
        	}
        }
        return true;
    }
    private static char isWon(char[][] playBoard) {
        // check if there is a horizontal victory
        int vColumn;
        for (vColumn=0; vColumn<playBoard.length; vColumn++) {
            if (isIdentical(playBoard[vColumn])) {
                return playBoard[vColumn][0];
            }
        }

        // check if there is a diagonal victory
        char[] alignment = new char[3];
        for (vColumn=0; vColumn<playBoard.length; vColumn++) {
            alignment[vColumn] = playBoard[vColumn][vColumn];
        }
        if (isIdentical(alignment)) {
            return alignment[0];
        }
        
        Arrays.fill(alignment, ' ');
        for (vColumn=0; vColumn<playBoard.length; vColumn++) {
            alignment[vColumn] = playBoard[vColumn][(playBoard.length-1)-vColumn];
        }
        if (isIdentical(alignment)) {
            return alignment[0];
        }

        // check if there is a vertical victory
        Arrays.fill(alignment, ' ');
        for (vColumn=0; vColumn<playBoard.length; vColumn++) {
            for (int vRow=0; vRow<playBoard[vColumn].length; vRow++) {
                alignment[vRow] = playBoard[vRow][vColumn];
                System.out.println(vColumn + " : " + vRow + " : " + alignment[vRow]);
            }
            System.out.println();
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
    	printBoard(playingBoard);
    	
    	var pl = isWon(playingBoard);
    	
    	if (pl =='X' || pl =='O') {
    		return false;
    	}
    	return true;
    }
    
    private static void printBoard(char[][] twoDArr) {
    	for (char[] carr:twoDArr) {
    		for (char c:carr) {
    			System.out.print(c);
    		}
    		System.out.print("\n");
    	}
    }
}
