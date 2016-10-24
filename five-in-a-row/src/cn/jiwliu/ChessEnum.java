package cn.jiwliu;
/**
 * 定义黑白棋
 * @author foolSky
 *
 */
public enum ChessEnum {

	BLACK("●"), WHITE("○");
	private String chess;
	/**
	 * 私有构造器；
	 * @return
	 * @param chess
	 */
	private  ChessEnum(String chess) {
		this.chess = chess;
	}

	/**
	 * 返回黑白棋；
	 * @return
	 */
	public String getChess() {
		return this.chess; 
	}
}
