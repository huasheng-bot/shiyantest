package lab2;

import java.util.Random;

public class TwoNumSubQuestion extends ArithmeticQuestion {
    private static final Random RND = new Random();

    @Override
    public void generate() {
        reset();
        int a = 2 + RND.nextInt(98);            // 2~99
        int b = 1 + RND.nextInt(a - 1);         // 1~a-1£¬²î >= 1
        numbers.add(a);
        numbers.add(b);
        operators.add('-');
        result = a - b;
    }
}
