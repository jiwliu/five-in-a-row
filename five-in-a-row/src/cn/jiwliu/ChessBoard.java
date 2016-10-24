package cn.jiwliu;

/**
 * ��������
 * @author foolSky
 *
 */
public class ChessBoard {

	//�������̴�С
	public static final int BOARD_SIZE = 22;
	//������������
	private String[][] board = {};
	public ChessBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * ��ʼ������
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
	 * 			X����
	 * @param posY
	 * 			Y����
	 * @param chess
	 * 			����
	 */
	public void setBoard(int posX,int posY,String chess){
		this.board[posX-1][posY-1] = chess;
	}
	
	/**
	 * ��ӡ����
	 */
	public void printBoard(){
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	//��ȡ����
	public String[][] getBoard(){
		return this.board;
	}
}
