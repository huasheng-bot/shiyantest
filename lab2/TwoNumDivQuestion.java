package lab2;

import java.util.Random;

/** 除法题：先定除数与商，再由二者相乘得到被除数，保证必然整除且 <= 99 */
public class TwoNumDivQuestion extends ArithmeticQuestion {
    private static final Random RND = new Random();

    @Override
    public void generate() {
        reset();
        int divisor = 2 + RND.nextInt(8);       // 除数 2~9
        int quotient = 1 + RND.nextInt(99 / divisor); // 商 1~⌊99/除数⌋
        int dividend = divisor * quotient;      // 被除数 <= 99
        numbers.add(dividend);
        numbers.add(divisor);
        operators.add('/');
        result = quotient;
    }
}
