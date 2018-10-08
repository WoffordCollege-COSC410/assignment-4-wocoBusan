package edu.wofford;

public class TicTacToeModel {

    public enum Mark { EMPTY, XMARK, OMARK };
    public enum Result { XWIN, OWIN, TIE, NONE };
	protected Mark[][] board;
	static boolean gameover;
    protected boolean xTurn;

    public TicTacToeModel() {
		this.board = new Mark[3][3];
		for (int row =0; row<3; row++){
            for(int column =0; column<3; column++){
                board[row][column] = Mark.EMPTY;
            }
        }
		this.gameover = false;
        this.xTurn = true;
    }
    
    public boolean setMarkAt(int row, int col) {
	if (!gameover){
		if (row >=0 && row<3 && col>=0 && col<3) {
			if (board[row][col] == Mark.EMPTY) {
				if (xTurn) {
					board[row][col] = Mark.XMARK;
				}else{
					board[row][col] = Mark.OMARK;
				}
				xTurn = !xTurn;
				return true;
			}
		}
	}
		return false;
	}
    
    public Mark getMarkAt(int row, int col) {
		if (gameover == false){
			gameover = true;
		}
		return board[row][col];
    }

    public Result getResult() {
		String[][] temp = new String[3][3];
		boolean full = true;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (board[i][j] == Mark.EMPTY) {
						temp[i][j] = " ";
						full = false;
				}
				else if (board[i][j] == Mark.XMARK) {
						temp[i][j] = "X";
				}
				else if (board[i][j] == Mark.OMARK) {
						temp[i][j] = "O";
				}
			}
		}
		
		//generating row values
		String[] outcome = new String[8];
		for (int i=0; i<3; i++) {
			String output = "";
			for (int j=0; j<3; j++) {
				output = output + temp[i][j];
				//output[0] = board[0][0] + board[0][1] + board[0][2] = row1;
				//output[1] = board[1][0] + board[1][1] + board[1][2] = row2;
				//output[2] = board[2][0] + board[2][1] + board[2][2] = row3;
				outcome[i] = output;
			}
		}
		
		//generating column values
		for (int i=0; i<3; i++) {
			String output = "";
			for (int j=0; j<3; j++) {
				output = output + temp[j][i];
				//output[3] = board[0][0] + board[1][0] + board[2][0] = column1;
				//output[4] = board[0][1] + board[1][1] + board[2][1] = column2;
				//output[5] = board[0][2] + board[1][2] + board[2][2] = column3;
				outcome[i+3] = output;
			}
		}
		
		//generating diagonal values;
		outcome[6] = temp[0][0] + temp[1][1] + temp[2][2]; //diagonall;
		outcome[7] = temp[0][2] + temp[1][1] + temp[2][0]; //diagona2;
	
		
		//outcome = {row1,row2,row3,column1,column2,column3,diagonal,diagonal2};
		for (int i=0; i<8; i++) {
			if(full) {
				gameover = true;
				return Result.TIE;
			}
			else if (outcome[i].equals("XXX")){
				gameover = true;
				return Result.XWIN;
			}
			else if(outcome[i].equals("OOO")){
				gameover = true;
				return Result.OWIN;
			}
		}
		return Result.NONE;
		
		}
    
    public String toString() {
		String[][] temp = new String[3][3];
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (board[i][j] == Mark.EMPTY) {
						temp[i][j] = " ";
				}
				else if (board[i][j] == Mark.XMARK) {
						temp[i][j] = "X";
				}
				else if (board[i][j] == Mark.OMARK) {
						temp[i][j] = "O";
				}
			}
		}
		
		String row1 = temp[0][0] +"|"+ temp[0][1]+ "|"+ temp[0][2] + "\n";
		String row2 = temp[1][0] +"|"+ temp[1][1]+ "|"+ temp[1][2] + "\n";
		String row3 = temp[2][0] +"|"+ temp[2][1]+ "|"+ temp[2][2];
		String floor = "-----" + "\n";
		
		return row1+floor+row2+floor+row3;
		}
	}
	
	