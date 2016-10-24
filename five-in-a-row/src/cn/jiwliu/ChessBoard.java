package cn.jiwliu;

/**
 * 定义棋盘
 * @author foolSky
 *
 */
public class ChessBoard {

	//定义棋盘大小
	public static final int BOARD_SIZE = 22;
	//定义期盼内容
	private String[][] board = {};
	public ChessBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 初始化棋盘
	 */
	public void init(){
		board = new String[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = "+";
			}
		}
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	/**
	 * 
	 * @param posX
	 * 			X坐标
	 * @param posY
	 * 			Y坐标
	 * @param chess
	 * 			下棋
	 */
	public void setBoard(int posX,int posY,String chess){
		this.board[posX-1][posY-1] = chess;
	}
	
	/**
	 * 打印棋盘
	 */
	public void printBoard(){
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	//获取棋盘
	public String[][] getBoard(){
		return this.board;
	}
}
