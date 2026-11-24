package lab2;

import java.util.Random;

/** a ÷ b ÷ c：由 b、c、商反推 a，保证两步都整除 */
public class ThreeNumDivQuestion extends ArithmeticQuestion {
    private static final Random RND = new Random();

    @Override
    public void generate() {
        reset();
        int b = 2 + RND.nextInt(8);              // 2~9
        int c = 2 + RND.nextInt(8);              // 2~9
        int q = 1 + RND.nextInt(99 / (b * c));   // 商，b*c*q <= 99
        int a = b * c * q;
        numbers.add(a);
        numbers.add(b);
        numbers.add(c);
        operators.add('/');
        operators.add('/');
        result = q;
    }
}
