package utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
public class Constants {

    //行数、列数、对角线数
    public static final int ROW_NUM = 15;
    public static final int COLUMN_NUM = 15;
    public static final int CROSS_NUM  = 29;

    //掩码列表
    public static List<Integer> MASK_CODES = Collections.unmodifiableList(Arrays.asList(0x8000, 0x4000, 0x2000, 0x1000
            , 0x0800, 0x0400, 0x0200, 0x0100, 0x0080, 0x0040, 0x0020, 0x0010, 0x0008, 0x0004, 0x0002));
}
