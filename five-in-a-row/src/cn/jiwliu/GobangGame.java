package cn.jiwliu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



/**
 * �����߼�ʵ��
 * @author foolSky
 *
 */
public class GobangGame {
	//������Ӯ��������
	public  final int  WIN_COUNT = 5;
	//�����û������x����
	private int posX = 0;
	//�����û������Y����
	private int posY = 0;
	//��������
	private ChessBoard chessBoard;
	//������������������ٲ���Ҫ���ж�(����5��һ��Ӯ����)
	private int count = 0;
	public GobangGame() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * ��ʼ������������
	 * @param chessBoard
	 */
	public GobangGame(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}
	
	
	/**
	 * ������
	 */
	public void manDo(){
		System.out.print("�û�����,����λ���ԣ�������");
		try {
			//��ȡ�û�����
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String inputLine =null;
			inputLine = br.readLine();
			//���û�������֤��ͨ��ʱ����������
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
	 * �ж��������Ƿ����̷�Χ/�Ƿ������ӡ�
	 */
	public boolean valid(String inpitLine){
		//��ȡλ��
		String[] pos = inpitLine.split(",");
		try {
			posX = Integer.parseInt(pos[0]);
			posY = Integer.parseInt(pos[1]);
		} catch (NumberFormatException e) {
			chessBoard.printBoard();
			System.out.println("����(����,����)�ĸ�ʽ���룺");
			return false;
		}
		//�ж��Ƿ񳬷�Χ
		if (posX > chessBoard.BOARD_SIZE || posY > chessBoard.BOARD_SIZE
				|| posX < 1 ||posY < 1) {
			System.out.println("X��Y����ֻ�ܴ��ڵ���1,��С�ڵ���" + chessBoard.BOARD_SIZE
					+ ",���������룺");
			return false;
		}
		//�ж�����λ���Ƿ��Ѿ�������,ע������ʹ�õ������ꡣ
		if (!chessBoard.getBoard()[posX-1][posY-1].equals("+")) {
			System.out.println("��λ���Ѿ������ӣ����������룺");
			return false;
		}
		
		return true;
	}
	/**
	 * ��������
	 */
	public void computerDo() {
		System.out.print("�������ӣ�");
		//�����������
		posX = (int)(Math.random() * (chessBoard.BOARD_SIZE -1))+1;
		posY = (int)(Math.random() * (chessBoard.BOARD_SIZE -1))+1;
		//�ж�����λ���Ƿ���������
		while(!chessBoard.getBoard()[posX][posY].equals("+")) {
			posX = (int)(Math.random() * (chessBoard.BOARD_SIZE -1))+1;
			posY = (int)(Math.random() * (chessBoard.BOARD_SIZE -1))+1;
		}
		System.out.print(posX + "��" + posY);
		System.out.println();
		//�������λ�÷�����
		chessBoard.setBoard(posX, posY, ChessEnum.WHITE.getChess());
		chessBoard.printBoard();
	}
	/**
	 *  �ж���Ӯ��ע�����ﴫ���������
	 * @param posX
	 * @param posY
	 * @param ico �ж��������������
	 * @return
	 */
	public boolean isWon(int posX, int posY, String ico) {
//		if (tempcount < WIN_COUNT) {//���¹�WIN_COUNT֮ǰ���������ʤ�������
//			return false;
//		}
		int tempcount = 0;
		String[][] chess = chessBoard.getBoard();		//�ж������Ƿ�����
		//�����ж��ж��ٿ�
		for (int i = 0; i < WIN_COUNT; i++) {//�����0��ʼ������������
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
		//�����ж϶��ٿ�
		for (int i = 1; i < WIN_COUNT; i++) {//�����1��ʼ���Ͳ���������
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
		//������0
		tempcount = 0;
		//�жϺ����Ƿ�����
		//�����ж��ж��ٿ�
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
		//�����ж϶��ٿ�
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
		//������0
		tempcount = 0;
		
		//�ж����ϵ������Ƿ�����
		//�������ж��ж��ٿ�
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
		//�������ж϶��ٿ�
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
		//������0
		tempcount = 0;
		
		//�ж����µ������Ƿ�����
		//�������ж��ж��ٿ�
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
		//�������ж϶��ٿ�
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
		//��0
		tempcount =0;
		return false;
	}

	
	/**
	 * ��ʼ����
	 */
	public void start() {
		//��ʼ������
		chessBoard.init();
		System.out.println("��ʼ�����.......");
		while(true){
			manDo();
			if(isWon(posX-1, posY-1, ChessEnum.BLACK.getChess())){
				System.out.println("��ϲ�û������ѻ�ʤ���Ƿ������ y/n");
				if (isRepaly()) {
					this.start();
				}
				break;
			}
			computerDo();
			if(isWon(posX-1, posY-1, ChessEnum.WHITE.getChess())){
				System.out.println("���ź��������ˣ��Ƿ������ y/n");
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
