package lab2;

import java.util.Random;

public class ThreeNumAddQuestion extends ArithmeticQuestion {
    private static final Random RND = new Random();

    @Override
    public void generate() {
        reset();
        int a = 1 + RND.nextInt(97);            // 1~97
        int b = 1 + RND.nextInt(98 - a);        // a+b <= 98
        int c = 1 + RND.nextInt(99 - a - b);    // ×ÜºÍ <= 99
        numbers.add(a);
        numbers.add(b);
        numbers.add(c);
        operators.add('+');
        operators.add('+');
        result = a + b + c;
    }
}
