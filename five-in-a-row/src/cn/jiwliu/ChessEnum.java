package cn.jiwliu;
/**
 * ����ڰ���
 * @author foolSky
 *
 */
public enum ChessEnum {

	BLACK("��"), WHITE("��");
	private String chess;
	/**
	 * ˽�й�������
	 * @return
	 * @param chess
	 */
	private  ChessEnum(String chess) {
		this.chess = chess;
	}

	/**
	 * ���غڰ��壻
	 * @return
	 */
	public String getChess() {
		return this.chess; 
	}
}
