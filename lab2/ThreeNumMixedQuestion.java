package lab2;

import java.util.Random;

/** 四种混合模式：a+b-c、a-b+c、a×b÷c、a÷b×c */
public class ThreeNumMixedQuestion extends ArithmeticQuestion {
    private static final Random RND = new Random();

    @Override
    public void generate() {
        reset();
        switch (RND.nextInt(4)) {
            case 0: {                                  // a + b - c
                int a = 1 + RND.nextInt(98);
                int b = 1 + RND.nextInt(99 - a);
                int mid = a + b;
                int c = 1 + RND.nextInt(mid - 1);
                numbers.add(a); numbers.add(b); numbers.add(c);
                operators.add('+'); operators.add('-');
                result = mid - c;
                break;
            }
            case 1: {                                  // a - b + c
                int a = 2 + RND.nextInt(98);
                int b = 1 + RND.nextInt(a - 1);
                int mid = a - b;
                int c = 1 + RND.nextInt(99 - mid);
                numbers.add(a); numbers.add(b); numbers.add(c);
                operators.add('-'); operators.add('+');
                result = mid + c;
                break;
            }
            case 2: {                                  // a × b ÷ c
                int b = 2 + RND.nextInt(8);
                int c = 2 + RND.nextInt(8);
                int q = 1 + RND.nextInt(99 / (b * c));
                int a = c * q;                          // a*b = b*c*q 可被 c 整除
                numbers.add(a); numbers.add(b); numbers.add(c);
                operators.add('*'); operators.add('/');
                result = b * q;
                break;
            }
            default: {                                 // a ÷ b × c
                int b = 2 + RND.nextInt(8);
                int c = 2 + RND.nextInt(8);
                int q = 1 + RND.nextInt(99 / Math.max(b, c));
                int a = b * q;
                numbers.add(a); numbers.add(b); numbers.add(c);
                operators.add('/'); operators.add('*');
                result = q * c;
                break;
            }
        }
    }
}

