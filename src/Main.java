import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MatrixIO io = new MatrixIO();
        MatrixCalculator calculator = new MatrixCalculator();
        try {
            int n = io.readInt("input number of rows: ");
            int m = io.readInt("input number of columns: ");
            System.out.println("input matrix elements (A[" + n + "][" + m + "]):");
            double[][] Matrix = io.fillMatrix(n, m);
            System.out.println("your matrix:\n");
            MatrixIO.printMatrix(Matrix);
            System.out.println("\ntask1:");
            List<Integer> monotonicCols = calculator.findMonotonicColumns(Matrix);
            if (monotonicCols.isEmpty()) {
                System.out.println("there is no monotonic columns");
            } else {
                for (int col : monotonicCols) {
                    System.out.println("column " + col + " is monotonic");
                }
            }
            System.out.println("\ntask2:");
            int[] minIndices = calculator.checkLastMinimumsOrder(Matrix);
            System.out.print("found indexes: ");
            for (int i = 0; i < minIndices.length; i++) {
                System.out.print("m(A," + (i + 1) + ") = " + (minIndices[i] + 1) + ", ");
            }
            System.out.println();
            boolean conditionHolds = calculator.checkConditions(minIndices);
            System.out.println(conditionHolds ? "conditions are true" : "conditions are false");
            System.out.println("\ntask3:");
            int size = io.readInt("input size: ");
            System.out.println("input first matrix elements:");
            double[][] m1 = io.fillMatrix(size, size);

            System.out.println("input second matrix elements:");
            double[][] m2 = io.fillMatrix(size, size);

            double[][] resultMatrix = calculator.solveTask3(m1, m2);
            System.out.println("result matrix:");
            io.printMatrix(resultMatrix);

        } catch (NumberFormatException e) {
            System.err.println("not an integer");
        } catch (IOException e) {
            System.err.println("input error");
        }
    }
}
