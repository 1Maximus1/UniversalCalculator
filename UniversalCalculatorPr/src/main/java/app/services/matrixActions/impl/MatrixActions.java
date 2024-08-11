package app.services.matrixActions.impl;

import app.model.impl.IdentityMatrix;
import app.model.impl.Matrix;
import app.model.impl.SquareMatrix;
import app.services.matrixActions.IMatrixActions;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Component
public class MatrixActions implements IMatrixActions
{
    public Matrix addMatrices(Matrix m1, Matrix m2){
        if(!m1.getDimension().contains(m2.getDimension())){
            throw new IllegalArgumentException("Matrices dim should be equal: %s != %s".formatted(m1.getDimension(), m2.getDimension()));
        }
        Matrix newMatrix = new Matrix(m1);
        for (int i = 0; i < m1.getMatrix().length; i++) {
            for (int j = 0; j < m1.getMatrix()[0].length; j++) {
                newMatrix.getMatrix()[i][j] = m1.getMatrix()[i][j] + m2.getMatrix()[i][j];
            }
        }
        return newMatrix;
    }

    public Matrix subtractMatrices(Matrix m1, Matrix m2) {
        if (m1.getMatrix().length != m2.getMatrix().length || m1.getMatrix()[0].length != m2.getMatrix()[0].length) {
            throw new IllegalArgumentException("Matrices must have the same dimensions for subtraction.");
        }

        Matrix newMatrix = new Matrix(m1);
        for (int i = 0; i < m1.getMatrix().length; i++) {
            for (int j = 0; j < m1.getMatrix()[0].length; j++) {
                newMatrix.getMatrix()[i][j] = m1.getMatrix()[i][j] - m2.getMatrix()[i][j];
            }
        }
        return newMatrix;
    }

    public Matrix multiplicationByScalar(Matrix a, double scalar) {
        Matrix result = new Matrix(a.getRows(), a.getColumns());
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getColumns(); j++) {
                result.setElement(i + 1, j + 1, a.getElement(i + 1, j + 1) * scalar);
            }
        }
        return result;
    }
    public Matrix multiplicationMatrices(Matrix a, Matrix b) {
        if (a.getMatrix().length == 0 || b.getMatrix().length == 0) {
            throw new IllegalArgumentException("No matrices provided for addition.");
        }

        if (a.getColumns() != b.getRows()) {
            throw new IllegalArgumentException("Such matrices cannot be multiplied, since the number of columns of matrix 'a' is not equal to the number of rows of matrix 'b'.");
        }

        Matrix result = new Matrix(a.getRows(), b.getColumns());

        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < b.getColumns(); j++) {
                double sum = 0;
                for (int k = 0, l = 0; k < a.getColumns() && l < b.getRows(); k++, l++) {
                    double number1 = a.getMatrix()[i][k];
                    double number2 = b.getMatrix()[l][j];
                    double mult = number1 * number2;
                    sum = sum + mult;
                }
                result.setElement(i + 1, j + 1, sum);
            }
        }
        return result;

    }

    public Matrix triangularShapeUpper(Matrix matrix){
        int LowestLength;
        if (matrix.getColumns() < matrix.getRows()) {
            LowestLength = matrix.getColumns();
        } else {
            LowestLength = matrix.getRows();
        }

        Matrix newMatrix = new Matrix(matrix);
        for (int i = 0; i < LowestLength; i++) {
            boolean matrix_swaped = false;
            for (int j = i; j < matrix.getRows(); j++) {
                if (matrix_swaped) {

                } else {
                    if (newMatrix.getMatrix()[j][i] != 0) {
                        double[] temp = newMatrix.getMatrix()[i];
                        newMatrix.getMatrix()[i] = newMatrix.getMatrix()[j];
                        newMatrix.getMatrix()[j] = temp;
                        matrix_swaped = true;
                    }
                }
            }
            double first_d = newMatrix.getMatrix()[i][i];
            for (int j = 1 + i; j < newMatrix.getRows(); j++) {
                double other_d = newMatrix.getMatrix()[j][i];
                if (first_d != 0) {
                    double alpha = other_d / first_d;
                    for (int k = 0; k < newMatrix.getColumns(); k++) {
                        newMatrix.getMatrix()[j][k] = newMatrix.getMatrix()[j][k] - alpha*newMatrix.getMatrix()[i][k];
                    }
                }
            }
        }

        roundMatrix(newMatrix.getMatrix());
        return newMatrix;
    }

    public Matrix triangularShapeLower(Matrix matrix){

        int LastDigitOfTriangul;
        if (matrix.getColumns() < matrix.getRows()) {
            LastDigitOfTriangul = 0;
        } else {
            LastDigitOfTriangul = matrix.getColumns() - matrix.getRows();
        }
        Matrix newMatrix = new Matrix(matrix);
        int count_backing = 0;
        for (int i = newMatrix.getColumns() - 1; i > LastDigitOfTriangul - 1; i--) {
            count_backing++;
            int row_pivot = newMatrix.getRows() - count_backing;

            Boolean matrix_swaped = false;
            for (int j = row_pivot; j > -1; j--) {
                if(matrix_swaped){

                } else {
                    if (newMatrix.getMatrix()[j][i] != 0) {
                        double[] temp = newMatrix.getMatrix()[row_pivot];
                        newMatrix.getMatrix()[row_pivot] = newMatrix.getMatrix()[j];
                        newMatrix.getMatrix()[j] = temp;
                        matrix_swaped = true;
                    }
                }

            }

            double first_d = newMatrix.getMatrix()[row_pivot][i];

            for (int j = row_pivot - 1; j > -1 ; j--) {
                double other_d = newMatrix.getMatrix()[j][i];
                if(first_d != 0) {
                    double alpha = other_d / first_d;
                    for (int k = 0; k < newMatrix.getColumns(); k++) {
                        newMatrix.getMatrix()[j][k] = newMatrix.getMatrix()[j][k] - alpha*newMatrix.getMatrix()[row_pivot][k];
                    }
                }
            }
        }
        roundMatrix(newMatrix.getMatrix());
        return newMatrix;
    }

    public Matrix addRowToMatrix(Matrix matrix, Matrix row, int index){
        if (row.getRows() != 1){
            throw new IllegalArgumentException("Row matrix isn't row, it have %d rows not 1".formatted(row.getRows()));
        }

        if (index <= 0 || index > matrix.getRows() || row.getColumns() != matrix.getColumns()) {
            throw new IllegalArgumentException("Invalid index or row dimensions.");
        }

        for (int i = 0; i < row.getColumns(); i++) {
            matrix.getMatrix()[index - 1][i] += row.getElement(1, i + 1);
        }
        return matrix;
    }

    public Matrix permuteColumn(Matrix matrix, int column1, int column2) {
        Matrix matrixColumn_1 = matrix.getColumn(column1);
        Matrix matrixColumn_2 = matrix.getColumn(column2);

        for (int j = 0; j < matrix.getRows(); j++) {
            matrix.getMatrix()[j][column2 - 1] = matrixColumn_1.getMatrix()[j][0];
        }
        for (int j = 0; j < matrix.getRows(); j++) {
            matrix.getMatrix()[j][column1 - 1] = matrixColumn_2.getMatrix()[j][0];
        }
        return matrix;
    }


    public SquareMatrix inverse(Matrix matrix) {
        if (matrix.getRows() != matrix.getColumns())
        {
            throw new NumberFormatException("NoN square matrix");
        }

        int dim = matrix.getRows();
        double[][] result = new double[dim][dim];
        double[][] buffer = new double[dim][dim];


        for (int i = 0; i < buffer.length; i++)
        {
            result[i][i] = 1;
            System.arraycopy(matrix.getMatrix()[i], 0, buffer[i], 0, buffer[0].length);
        }

        for (int j = 0; j < dim; j++)
        {
            int nonZeroI = j;
            while (nonZeroI < dim && buffer[nonZeroI][j] == 0)
            {
                nonZeroI++;
            }
            if (nonZeroI == dim)
            {
                throw new NumberFormatException("Inverse matrix does not exist. Determinant is 0");
            }

            swapRows(buffer, nonZeroI, j);
            swapRows(result, nonZeroI, j);

            double scalar = buffer[j][j];
            for (int j2 = 0; j2 < dim; j2++)
            {
                result[j][j2] /= scalar;
                buffer[j][j2] /= scalar;
            }

            for (int i = j+1; i < dim; i++) {
                scalar = buffer[i][j];
                for (int j2 = 0; j2 < dim; j2++) {
                    result[i][j2] -= result[j][j2] * scalar;
                    buffer[i][j2] -= buffer[j][j2] * scalar;
                }
            }
        }

        for (int j = dim-1; j > 0; j--)
        {
            for (int i = j-1; i >= 0; i--)
            {
                double scalar = buffer[i][j];
                for (int j2 = 0; j2 < dim; j2++)
                {
                    result[i][j2] -= result[j][j2] * scalar;
                }
            }
        }

        roundMatrix(result);

        return new SquareMatrix(result);
    }

    public static void roundMatrix(double[][] matrix) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.###", symbols);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Double.parseDouble(df.format(matrix[i][j]));
            }
        }
    }


    private void swapRows(double[][] array, int r1, int r2) {
        double[] temp = array[r1];
        array[r1] = array[r2];
        array[r2] = temp;
    }

    public Matrix transposeMatrix(Matrix matrix) {
        Matrix transposedMatrix = new Matrix(matrix.getColumns(), matrix.getRows());

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                transposedMatrix.setElement(j + 1, i + 1, matrix.getMatrix()[i][j]);
            }
        }

        return transposedMatrix;
    }

    public SquareMatrix powerMatrix(SquareMatrix matrix, int degree) {
        if (degree < 0) {
            throw new IllegalArgumentException("Matrix cannot be raised to a negative power.");
        }

        if (degree == 0) {
            return new IdentityMatrix(matrix.getRows());
        }

        SquareMatrix result = new SquareMatrix(matrix);

        for (int i = 1; i < degree; i++) {
            result = new SquareMatrix(multiplicationMatrices(result, matrix));
        }

        return result;
    }
}
