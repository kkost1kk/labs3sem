import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MatrixIO {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static int readInt(String string) throws IOException{
        System.out.print(string);
        return Integer.parseInt(reader.readLine());
    }
    public static double[][] fillMatrix(int rows, int cols) throws IOException {
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                System.out.print("enter element[" + (i + 1) + "][" + (j + 1) + "]: ");
                matrix[i][j] = Double.parseDouble(reader.readLine());
            }
        }
        return matrix;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
