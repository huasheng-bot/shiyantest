package lab2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

class QuestionGeneratorTest {

    @Test
    void 全部题型生成结果都满足取值约束() {
        for (int type = 1; type <= 9; type++) {
            List<ArithmeticQuestion> list = new QuestionGenerator(type, 1000).generateQuestions();
            assertEquals(1000, list.size());
            for (ArithmeticQuestion q : list) {
                assertTrue(q.isValid(), "非法题目：" + q.getQuestionText() + " = " + q.getResult());
                for (int n : q.getNumbers()) {
                    assertTrue(n > 0 && n < 100, "操作数越界：" + n);
                }
                assertTrue(q.getResult() > 0 && q.getResult() < 100, "结果越界：" + q.getResult());
                int mid = q.getMiddleResult();
                if (mid != -1) {
                    assertTrue(mid > 0 && mid < 100, "中间结果越界：" + mid);
                }
            }
        }
    }

    @Test
    void 除法题必须整除() {
        List<ArithmeticQuestion> list =
                new QuestionGenerator(QuestionGenerator.TWO_DIV, 500).generateQuestions();
        for (ArithmeticQuestion q : list) {
            int dividend = q.getNumbers().get(0);
            int divisor = q.getNumbers().get(1);
            assertEquals(0, dividend % divisor, "不能整除：" + dividend + "/" + divisor);
            assertEquals(dividend / divisor, q.getResult());
        }
    }

    @Test
    void 三数除法题两步都能整除() {
        List<ArithmeticQuestion> list =
                new QuestionGenerator(QuestionGenerator.THREE_DIV, 500).generateQuestions();
        for (ArithmeticQuestion q : list) {
            int a = q.getNumbers().get(0);
            int b = q.getNumbers().get(1);
            int c = q.getNumbers().get(2);
            assertEquals(0, a % b, "第一步不能整除：" + q.getQuestionText());
            assertEquals(0, (a / b) % c, "第二步不能整除：" + q.getQuestionText());
        }
    }

    @Test
    void CSV行数与列数符合设定() throws IOException {
        Path file = Files.createTempFile("questions", ".csv");
        List<ArithmeticQuestion> list =
                new QuestionGenerator(QuestionGenerator.TWO_ADD, 30).generateQuestions();
        CsvExporter.export(list, 5, false, file.toString());
        List<String> lines = Files.readAllLines(file);
        assertEquals(6, lines.size());
        for (String line : lines) {
            assertEquals(5, line.split(",", -1).length);
        }
        Files.deleteIfExists(file);
    }

    @Test
    void CSV包含答案时每行单元格数翻倍() throws IOException {
        Path file = Files.createTempFile("questions_answer", ".csv");
        List<ArithmeticQuestion> list =
                new QuestionGenerator(QuestionGenerator.RANDOM_MODE, 30).generateQuestions();
        CsvExporter.export(list, 5, true, file.toString());
        for (String line : Files.readAllLines(file)) {
            assertEquals(10, line.split(",", -1).length);
        }
        Files.deleteIfExists(file);
    }
}
