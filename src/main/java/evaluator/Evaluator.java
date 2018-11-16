package evaluator;

import chessboard.Chessboard;

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
public interface Evaluator {

    /**
     * 根据当前棋盘状态评估分数，局势越好分数越高
     * @param now
     * @return
     */
    long evaluate(Chessboard now);
}
