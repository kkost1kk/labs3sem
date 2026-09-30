import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputFile = "students.txt";
        String outputFile1 = "excellent_students.txt";
        String outputFile2 = "academic_performance.txt";

        try {
            System.out.println("Читаем данные из файла: " + inputFile);
            List<GradeBook> gradeBooks = FileHandler.readGradeBooks(inputFile);

            System.out.println("Формируем файл №1 (Отличники)...");
            FileHandler.writeExcellentStudents(outputFile1, gradeBooks);

            System.out.println("Формируем файл №2 (Успевающие)...");
            FileHandler.writeProgressingStudents(outputFile2, gradeBooks);

            System.out.println("Выполнено! Проверьте выходные файлы.");

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлами: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка парсинга числовых данных в файле: " + e.getMessage());
        }
    }
}
