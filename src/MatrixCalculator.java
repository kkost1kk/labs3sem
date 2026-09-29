import java.util.ArrayList;
import java.util.List;

public class MatrixCalculator {
     /*  Выведите номера столбцов, элементы каждого из которых образуют монотонную последовательность
    (монотонно убывающую или монотонно возрастающую).*/

    public List<Integer> findMonotonicColumns(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<Integer> monotonicIndices = new ArrayList<>();
        for (int j = 0; j < cols; ++j) {
            boolean isIncreasing = true;
            boolean isDecreasing = true;

            for (int i = 0; i < rows - 1; ++i) {
                if (matrix[i][j] >= matrix[i + 1][j]) isIncreasing = false;
                if (matrix[i][j] <= matrix[i + 1][j]) isDecreasing = false;
            }
            if (isIncreasing || isDecreasing) {
                monotonicIndices.add(j + 1);
            }
        }
        return monotonicIndices;
    }
    /*Пусть m(А, i) означает номер столбца матрицы A, в котором находится
      последний в строке минимум i-й строки. Проверить, что для заданной матрицы A
      выполняются неравенства m(A,1) <= m(A,2) <= ... <= m(A,n).*/

    public static int[] checkLastMinimumsOrder(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] lastMinimumIndices = new int[rows];
        for (int i = 0; i < rows; i++) {
            double minVal = matrix[i][0];
            int lastMinIndex = 0;
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] <= minVal) {
                    minVal = matrix[i][j];
                    lastMinIndex = j;
                }
            }
            lastMinimumIndices[i] = lastMinIndex;
        }
        return lastMinimumIndices;
    }

public static boolean checkConditions(int[] lastMinimumIndices){
        boolean conditionHolds = true;
        int rows = lastMinimumIndices.length;
        for (int i = 0; i < rows - 1; i++) {
            if (lastMinimumIndices[i] > lastMinimumIndices[i + 1]) {
                conditionHolds = false;
                break;
            }
        }
        return conditionHolds;
    }


   /* Даны две действительные квадратные матрицы порядка n. Получить новую матрицу умножением элементов каждой строки первой матрицы
    на наибольшее из значений элементов соответствующей строки второй матрицы.*/


    public static double[][] solveTask3(double[][] matrix1, double[][] matrix2) {
        int n = matrix1.length;
        double multiplyer;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; ++i) {
            multiplyer = -Double.MAX_VALUE;
            for (double elements : matrix2[i]) {
                if (elements > multiplyer) {
                    multiplyer = elements;
                }
            }
            for (int j = 0; j < n; ++j) {
                result[i][j] = matrix1[i][j] * multiplyer;
            }
        }
        return result;
    }
}
