package utils;

/**
 * 棋形
 */
public enum ChessPatternEnum {

    //五连，11111, 00000
    FIVE(0xF8000000, 0, 5),
    //活四，011110, 000000
    ALIVE_FOUR(0x78000000, 0, 6),
    //冲四，011110, 100000
    SINGLE_FOUR_1(0x78000000, 0x80000000, 6),
    //冲四，011110, 000001
    SINGLE_FOUR_2(0x78000000, 0x04000000, 6),
    //冲四，10111, 00000
    SINGLE_FOUR_3(0xB8000000, 0, 5),
    //冲四，11011, 00000
    SINGLE_FOUR_4(0xD8000000, 0, 5),
    //冲四，11101, 00000
    SINGLE_FOUR_5(0xE8000000, 0, 5),
    //活三，011100，000000
    ALIVE_THREE_1(0x70000000, 0, 6),
    //活三，001110，000000
    ALIVE_THREE_2(0x38000000, 0, 6),
    //活三，010110，000000
    ALIVE_THREE_3(0x58000000, 0, 6),
    //活三，011010，000000
    ALIVE_THREE_4(0x68000000, 0, 6),
    //眠三，001110, 100000
    SLEEP_THREE_1(0x38000000, 0x80000000, 6),
    //眠三，010110, 100000
    SLEEP_THREE_2(0x58000000, 0x80000000, 6),
    //眠三，011010, 100000
    SLEEP_THREE_3(0x68000000, 0x80000000, 6),
    //眠三，011100, 100000
    SLEEP_THREE_4(0x70000000, 0x80000000, 6),
    //眠三，001110, 000001
    SLEEP_THREE_5(0x38000000, 0x04000000, 6),
    //眠三，010110, 000001
    SLEEP_THREE_6(0x58000000, 0x04000000, 6),
    //眠三，011010, 000001
    SLEEP_THREE_7(0x68000000, 0x04000000, 6),
    //眠三，011100, 000001
    SLEEP_THREE_8(0x70000000, 0x04000000, 6),
    //眠三，11001, 00000
    SLEEP_THREE_9(0xC8000000, 0, 5),
    //眠三，10011, 00000
    SLEEP_THREE_10(0x98000000, 0, 5),
    //眠三，10101, 00000
    SLEEP_THREE_11(0xA8000000, 0, 5),
    //活二，001100, 000000
    ALIVE_TWO_1(0x30000000, 0, 6),
    //活二，011000, 000000
    ALIVE_TWO_2(0x60000000, 0, 6),
    //活二，000110, 000000
    ALIVE_TWO_3(0x18000000, 0, 6),
    //活二，010100, 000000
    ALIVE_TWO_4(0x50000000, 0, 6),
    //活二，001010, 000000
    ALIVE_TWO_5(0x28000000, 0, 6),
    //活二，010010, 000000
    ALIVE_TWO_6(0x48000000, 0, 6),
    //眠二，




    ;

    //棋子数组下标
    public int pattern;
    public int limit;
    public int bitLength;

    ChessPatternEnum(int pattern, int limit, int bitLength) {
        this.pattern = pattern;
        this.limit = limit;
        this.bitLength = bitLength;
    }
}
