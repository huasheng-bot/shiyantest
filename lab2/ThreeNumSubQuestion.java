package lab2;

import java.util.Random;

public class ThreeNumSubQuestion extends ArithmeticQuestion {
    private static final Random RND = new Random();

    @Override
    public void generate() {
        reset();
        int a = 3 + RND.nextInt(97);            // 3~99
        int b = 1 + RND.nextInt(a - 2);         // b <= a-2£¬ÖÐ¼ä²î >= 1
        int c = 1 + RND.nextInt(a - b - 1);     // ×îÖÕ²î >= 1
        numbers.add(a);
        numbers.add(b);
        numbers.add(c);
        operators.add('-');
        operators.add('-');
        result = a - b - c;
    }
}
