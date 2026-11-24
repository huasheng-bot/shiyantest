package lab2;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/** 控制台交互入口，所有输入均做合法性校验 */
public class Main {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========== 加减乘除练习题生成系统 ==========");
        while (true) {
            printMenu();
            int type = readInt("请选择题型(1-10，0 退出)：", 0, 10);
            if (type == 0) {
                System.out.println("程序已退出。");
                break;
            }
            int count = readInt("请输入题目数量(1-500)：", 1, 500);
            int cols = readInt("请输入每行列数(1-10)：", 1, 10);
            boolean withAnswer = readYesNo("CSV 中是否包含答案(y/n)：");
            String path = readPath();

            List<ArithmeticQuestion> questions =
                    new QuestionGenerator(type, count).generateQuestions();

            System.out.println("\n---------- 题目预览 ----------");
            printPreview(questions, cols);
            System.out.println();

            try {
                CsvExporter.export(questions, cols, withAnswer, path);
                System.out.printf("已生成 %d 道题，保存至：%s%n%n", questions.size(), path);
            } catch (IOException e) {
                System.out.println("写入文件失败：" + e.getMessage());
            }
        }
        IN.close();
    }

    private static void printMenu() {
        System.out.println("1. 2数加法      2. 2数减法      3. 2数乘法      4. 2数除法");
        System.out.println("5. 3数加法      6. 3数减法      7. 3数乘法      8. 3数除法");
        System.out.println("9. 3数混合      10. 随机模式     0. 退出");
    }

    private static void printPreview(List<ArithmeticQuestion> list, int cols) {
        for (int i = 0; i < list.size(); i += cols) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < cols && i + j < list.size(); j++) {
                sb.append(String.format("%-18s", (i + j + 1) + ". "
                        + list.get(i + j).getQuestionText()));
            }
            System.out.println(sb);
        }
    }

    private static int readInt(String tip, int min, int max) {
        while (true) {
            System.out.print(tip);
            String line = IN.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("输入不能为空，请重新输入。");
                continue;
            }
            try {
                int value = Integer.parseInt(line);
                if (value < min || value > max) {
                    System.out.printf("输入超出范围，应为 %d~%d，请重新输入。%n", min, max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("请输入数字，请重新输入。");
            }
        }
    }

    private static boolean readYesNo(String tip) {
        while (true) {
            System.out.print(tip);
            String line = IN.nextLine().trim();
            if ("y".equalsIgnoreCase(line)) {
                return true;
            }
            if ("n".equalsIgnoreCase(line)) {
                return false;
            }
            System.out.println("只能输入 y 或 n，请重新输入。");
        }
    }

    private static String readPath() {
        System.out.print("请输入 CSV 文件名（直接回车默认 练习题.csv）：");
        String line = IN.nextLine().trim();
        return line.isEmpty() ? "练习题.csv" : line;
    }
}
