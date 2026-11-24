package lab2;

import java.util.Random;

public class ThreeNumMulQuestion extends ArithmeticQuestion {
    private static final Random RND = new Random();

    @Override
    public void generate() {
        reset();
        int a = 2 + RND.nextInt(8);             // 2~9
        int b = 1 + RND.nextInt(99 / a);        // a*b <= 99
        int c = 1 + RND.nextInt(99 / (a * b));  // a*b*c <= 99
        numbers.add(a);
        numbers.add(b);
        numbers.add(c);
        operators.add('*');
        operators.add('*');
        result = a * b * c;
    }
}
