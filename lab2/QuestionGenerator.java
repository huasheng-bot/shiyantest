package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** 按题型编号批量生成题目，只依赖抽象基类 */
public class QuestionGenerator {
    public static final int TWO_ADD = 1;
    public static final int TWO_SUB = 2;
    public static final int TWO_MUL = 3;
    public static final int TWO_DIV = 4;
    public static final int THREE_ADD = 5;
    public static final int THREE_SUB = 6;
    public static final int THREE_MUL = 7;
    public static final int THREE_DIV = 8;
    public static final int THREE_MIXED = 9;
    public static final int RANDOM_MODE = 10;

    private static final Random RND = new Random();
    private final int type;
    private final int count;

    public QuestionGenerator(int type, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("题目数量必须为正整数");
        }
        this.type = type;
        this.count = count;
    }

    public List<ArithmeticQuestion> generateQuestions() {
        List<ArithmeticQuestion> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int t = (type == RANDOM_MODE) ? 1 + RND.nextInt(9) : type;
            ArithmeticQuestion q = create(t);
            q.generate();
            while (!q.isValid()) {          // 二次兜底，保证 100% 合法
                q.generate();
            }
            list.add(q);
        }
        return list;
    }

    private ArithmeticQuestion create(int t) {
        switch (t) {
            case TWO_ADD: return new TwoNumAddQuestion();
            case TWO_SUB: return new TwoNumSubQuestion();
            case TWO_MUL: return new TwoNumMulQuestion();
            case TWO_DIV: return new TwoNumDivQuestion();
            case THREE_ADD: return new ThreeNumAddQuestion();
            case THREE_SUB: return new ThreeNumSubQuestion();
            case THREE_MUL: return new ThreeNumMulQuestion();
            case THREE_DIV: return new ThreeNumDivQuestion();
            case THREE_MIXED: return new ThreeNumMixedQuestion();
            default: throw new IllegalArgumentException("非法题型编号：" + t);
        }
    }
}
