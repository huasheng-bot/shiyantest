package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 算术题目抽象基类：负责题目表示、文本输出与合法性校验 */
public abstract class ArithmeticQuestion {
    protected final List<Integer> numbers = new ArrayList<>();
    protected final List<Character> operators = new ArrayList<>();
    protected int result;

    /** 生成一道符合取值约束的题目，由子类实现 */
    public abstract void generate();

    protected void reset() {
        numbers.clear();
        operators.clear();
    }

    public String getQuestionText() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.size(); i++) {
            sb.append(numbers.get(i));
            if (i < operators.size()) {
                sb.append(' ').append(operators.get(i)).append(' ');
            }
        }
        return sb.append(" =").toString();
    }

    public int getResult() {
        return result;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public List<Character> getOperators() {
        return Collections.unmodifiableList(operators);
    }

    /** 三数题的第一个中间结果；两数题返回 -1 */
    public int getMiddleResult() {
        if (numbers.size() < 3) {
            return -1;
        }
        return calc(numbers.get(0), operators.get(0), numbers.get(1));
    }

    /** 校验操作数、中间结果、最终结果是否都在 (0,100) 内 */
    public boolean isValid() {
        if (result <= 0 || result >= 100) {
            return false;
        }
        for (int n : numbers) {
            if (n <= 0 || n >= 100) {
                return false;
            }
        }
        int mid = getMiddleResult();
        return mid == -1 || (mid > 0 && mid < 100);
    }

    protected static int calc(int a, char op, int b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: throw new IllegalArgumentException("未知运算符：" + op);
        }
    }
}

