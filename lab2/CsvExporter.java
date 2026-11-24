package lab2;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class CsvExporter {
    private CsvExporter() {
    }

    /**
     * @param list       题目列表
     * @param cols       每行列数
     * @param withAnswer 是否在题目后输出答案
     * @param path       目标文件路径
     */
    public static void export(List<ArithmeticQuestion> list, int cols,
                              boolean withAnswer, String path) throws IOException {
        if (cols <= 0) {
            throw new IllegalArgumentException("每行列数必须为正整数");
        }
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
            writer.write('\uFEFF');           // BOM，防止 Excel 中文乱码
            for (int i = 0; i < list.size(); i += cols) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < cols; j++) {
                    if (j > 0) {
                        line.append(',');
                    }
                    int index = i + j;
                    if (index < list.size()) {
                        ArithmeticQuestion q = list.get(index);
                        line.append(cell(String.format("%d. %s", index + 1, q.getQuestionText())));
                        if (withAnswer) {
                            line.append(',').append(q.getResult());
                        }
                    }
                }
                writer.write(line.toString());
                writer.newLine();
            }
        }
    }

    private static String cell(String text) {
        if (text.contains(",") || text.contains("\"") || text.contains("\n")) {
            return "\"" + text.replace("\"", "\"\"") + "\"";
        }
        return text;
    }
}
