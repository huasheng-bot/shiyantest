package lab2;

import java.util.Random;

public class TwoNumMulQuestion extends ArithmeticQuestion {
    private static final Random RND = new Random();

    @Override
    public void generate() {
        reset();
        int a = 1 + RND.nextInt(98);            // 1~98
        int b = 1 + RND.nextInt(99 / a);        // 1~⌊99/a⌋，保证 a*b <= 99
        numbers.add(a);
        numbers.add(b);
        operators.add('*');
        result = a * b;
    }
}
