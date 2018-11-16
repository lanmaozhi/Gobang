package chessboard;

/**
 * <pre>
 *
 * </pre>
 *
 * @author maozhi.lan@meicloud.com
 * @version 1.00.00
 * <p>
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public interface Chessboard {

    /**
     * 随机走一步
     */
    void randomChess();

    /**
     * 是否已结束
     */
    int judgeIsComplete();
}
