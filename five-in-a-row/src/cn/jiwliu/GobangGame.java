package cn.jiwliu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



/**
 * 下棋逻辑实现
 * @author foolSky
 *
 */
public class GobangGame {
	//定义连赢的棋子数
	public  final int  WIN_COUNT = 5;
	//定义用户输入的x坐标
	private int posX = 0;
	//定义用户输入的Y坐标
	private int posY = 0;
	//定义棋盘
	private ChessBoard chessBoard;
	//定义人下棋次数，减少不必要的判断(少于5次一定赢不了)
	private int count = 0;
	public GobangGame() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 初始化，构造棋盘
	 * @param chessBoard
	 */
	public GobangGame(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}
	
	
	/**
	 * 人落子
	 */
	public void manDo(){
		System.out.print("用户落子,输入位置以，隔开：");
		try {
			//获取用户输入
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String inputLine =null;
			inputLine = br.readLine();
			//当用户输入验证不通过时，继续输入
			while (!valid(inputLine)) {
				br = new BufferedReader(new InputStreamReader(System.in));
				inputLine = br.readLine();
				continue;
			}
			chessBoard.setBoard(posX, posY, ChessEnum.BLACK.getChess());
			chessBoard.printBoard();
			count++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	/**
	 * 判断人落子是否超棋盘范围/是否已落子。
	 */
	public boolean valid(String inpitLine){
		//获取位置
		String[] pos = inpitLine.split(",");
		try {
			posX = Integer.parseInt(pos[0]);
			posY = Integer.parseInt(pos[1]);
		} catch (NumberFormatException e) {
			chessBoard.printBoard();
			System.out.println("请以(数字,数字)的格式输入：");
			return false;
		}
		//判断是否超范围
		if (posX > chessBoard.BOARD_SIZE || posY > chessBoard.BOARD_SIZE
				|| posX < 1 ||posY < 1) {
			System.out.println("X与Y坐标只能大于等于1,与小于等于" + chessBoard.BOARD_SIZE
					+ ",请重新输入：");
			return false;
		}
		//判断落入位置是否已经有棋子,注意这里使用的是坐标。
		if (!chessBoard.getBoard()[posX-1][posY-1].equals("+")) {
			System.out.println("此位置已经有棋子，请重新输入：");
			return false;
		}
		
		return true;
	}
	/**
	 * 机器落子
	 */
	public void computerDo() {
		System.out.print("机器落子：");
		//产生随机坐标
		posX = (int)(Math.random() * (chessBoard.BOARD_SIZE -1))+1;
		posY = (int)(Math.random() * (chessBoard.BOARD_SIZE -1))+1;
		//判断坐标位置是否已有棋子
		while(!chessBoard.getBoard()[posX][posY].equals("+")) {
			posX = (int)(Math.random() * (chessBoard.BOARD_SIZE -1))+1;
			posY = (int)(Math.random() * (chessBoard.BOARD_SIZE -1))+1;
		}
		System.out.print(posX + "：" + posY);
		System.out.println();
		//传入的是位置非坐标
		chessBoard.setBoard(posX, posY, ChessEnum.WHITE.getChess());
		chessBoard.printBoard();
	}
	/**
	 *  判断输赢，注意这里传入的是坐标
	 * @param posX
	 * @param posY
	 * @param ico 判断输入的棋子类型
	 * @return
	 */
	public boolean isWon(int posX, int posY, String ico) {
//		if (tempcount < WIN_COUNT) {//在下够WIN_COUNT之前，不会出现胜负结果。
//			return false;
//		}
		int tempcount = 0;
		String[][] chess = chessBoard.getBoard();		//判断纵向是否满足
		//向上判断有多少颗
		for (int i = 0; i < WIN_COUNT; i++) {//这里从0开始，包含了自身
			if (posX-i < 0) {
				break;
			}
			if (chess[posX-i][posY] == ico) {
				tempcount++;
			}else
				break;
			if (tempcount >= WIN_COUNT) {
				return true;
			}

		}
		//向下判断多少颗
		for (int i = 1; i < WIN_COUNT; i++) {//这里从1开始，就不算自身了
			if (posX+i > 21) {
				break;
			}
			if (chess[posX+i][posY] == ico) {
				tempcount++;
			}else
				break;
			if (tempcount >= WIN_COUNT) {
				return true;
			}
		}
		//计数归0
		tempcount = 0;
		//判断横向是否满足
		//向左判断有多少颗
		for (int i = 0; i < WIN_COUNT; i++) {
			if (posY-i < 0) {
				break;
			}
			if (chess[posX][posY-i] == ico) {
				tempcount++;
			}else
				break;
			if (tempcount >= WIN_COUNT) {
				return true;
			}
		}
		//向右判断多少颗
		for (int i = 1; i < WIN_COUNT; i++) {
			if (posY+i > 21) {
				break;
			}
			if (chess[posX][posY+i] == ico) {
				tempcount++;
			}else {
				break;
			}
			if (tempcount >= WIN_COUNT) {
				return true;
			}
		}
		//计数归0
		tempcount = 0;
		
		//判断左上到右下是否满足
		//向左上判断有多少颗
		for (int i = 0; i < WIN_COUNT; i++) {
			if (posY-i < 0 ||posX-i < 0) {
				break;
			}
			if (chess[posX-i][posY-i] == ico) {
				tempcount++;
			}else
				break;
			if (tempcount >= WIN_COUNT) {
				return true;
			}
		}
		//向右下判断多少颗
		for (int i = 1; i < WIN_COUNT; i++) {
			if (posY+i > 21 || posX+i >21) {
				break;
			}
			if (chess[posX+i][posY+i] == ico) {
				tempcount++;
			}else
				break;
			if (tempcount >= WIN_COUNT) {
				return true;
			}
		}
		//计数归0
		tempcount = 0;
		
		//判断左下到右上是否满足
		//向左下判断有多少颗
		for (int i = 0; i < WIN_COUNT; i++) {
			if (posY+i > 21 ||posX-i < 0) {
				break;
			}
			if (chess[posX-i][posY+i] == ico) {
				tempcount++;
			}else
				break;
			if (tempcount >= WIN_COUNT) {
				return true;
			}

		}
		//向右上判断多少颗
		for (int i = 1; i < WIN_COUNT; i++) {
			if (posY-i < 0 || posX+i >21) {
				break;
			}
			if (chess[posX+i][posY-i] == ico) {
				tempcount++;
			}else
				break;
			if (tempcount >= WIN_COUNT) {
				return true;
			}
		}
		//清0
		tempcount =0;
		return false;
	}

	
	/**
	 * 开始下棋
	 */
	public void start() {
		//初始化棋盘
		chessBoard.init();
		System.out.println("初始化完成.......");
		while(true){
			manDo();
			if(isWon(posX-1, posY-1, ChessEnum.BLACK.getChess())){
				System.out.println("恭喜用户，您已获胜，是否继续？ y/n");
				if (isRepaly()) {
					this.start();
				}
				break;
			}
			computerDo();
			if(isWon(posX-1, posY-1, ChessEnum.WHITE.getChess())){
				System.out.println("很遗憾，您输了，是否继续？ y/n");
				if (isRepaly()) {
					this.start();
				}
				break;
			}
		}
	}
	
	public boolean isRepaly() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputLine =null;
		try {
			inputLine = br.readLine();
			if (inputLine.equals("y")) {
				return true;
			}else
				return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		GobangGame gobangGame = new GobangGame(new ChessBoard());
		gobangGame.start();
	}
}
