package app.model.impl;

import app.model.IMatrix;
import app.services.matrixActions.impl.MatrixActions;

import java.util.Arrays;

public class Matrix implements IMatrix {
    private int rows;
    private int columns;
    protected double[][] matrix;

    public Matrix() {
        this.rows = 0;
        this.columns = 0;
        this.matrix = new double[0][0];
    }

    public Matrix(int rows, int columns) {
        if(rows < 0 || columns < 0){
            throw new IllegalArgumentException(String.format("Dimension can 0x0 but not: %dx%d",rows, columns));
        }
        if(rows == 0 ^ columns == 0){
            throw new IllegalArgumentException(String.format("Dimension can't have only one 0: %dx%d",rows, columns));
        } 
        this.rows = rows;
        this.columns = columns;
        this.matrix = new double[rows][columns];
    }

    public Matrix(Matrix otherMatrix) {
        this.rows = otherMatrix.getRows();
        this.columns = otherMatrix.getColumns();
        this.matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(otherMatrix.matrix[i], 0, this.matrix[i], 0, columns);
        }
    }

    public Matrix(double[][] data) {
        this.rows = data.length;
        this.columns = data[0].length;
        this.matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, this.matrix[i], 0, columns);
        }
    }

    public void setElement(int row, int column, double value) {
        CheckForNumInMatrix(row, column);
        matrix[row - 1][column - 1] = value;
    }

    public double getElement(int row, int column) {
        CheckForNumInMatrix(row, column);
        return matrix[row - 1][column - 1];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double[][] getMatrix()
    {
        return matrix;
    }

    public Matrix getRow(int row) {

        row = row - 1;

        if (row < 0 || row >= rows) {
            throw new IllegalArgumentException("Invalid row index.");
        }

        Matrix matrixRow = new Matrix(1, columns);

        for (int i = row; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                matrixRow.setElement(1, j + 1, matrix[row][j]);
            }
        }
        return matrixRow;
    }

    public Matrix getColumn(int column) {

        column = column - 1;

        if (column < 0 || column >= columns) {
            throw new IllegalArgumentException("Invalid column index.");
        }

        Matrix matrixColumn = new Matrix(rows, 1);

        for (int i = 0; i < getRows(); i++) {
            for (int j = column; j < getColumns(); j++) {
                matrixColumn.setElement(i + 1, 1, matrix[i][column]);
            }
        }
        return matrixColumn;
    }


    @Override
    public int getRang(){
        
        if(this.getDimension().contains("0 x 0")){
            return 0;
        }
        MatrixActions matrixActions = new MatrixActions();
        Matrix matrix_copy = new Matrix(matrix);
        Matrix mat = matrixActions.triangularShapeUpper(matrix_copy);

        int LowestLength;
        if (this.getColumns() < this.getRows()) {
            LowestLength = this.getColumns();
        } else {
            LowestLength = this.getRows();
        }

        int rang = 0;
        for (int i = 0; i < LowestLength; i++) {
            if (mat.matrix[i][i] != 0) {
                rang++;
            }
        }

        return rang;
    }

    protected static Matrix triangularShapePermutCountForDeterminat(Matrix matrix) {
        int counterOfPermute = 0;
        MatrixActions matrixActions = new MatrixActions();
        for (int centalR = 0, centalC = 0; centalR < matrix.getRows() && centalC < matrix.getColumns(); centalR++, centalC++) {
            if (matrix.getMatrix()[centalR][centalC] != 0) {
                for (int i = centalC; i < matrix.getRows(); i++) {
                    Matrix divideM = matrix.getRow(centalR + 1);
                    if ((i + 1) < matrix.getRows()) {
                        divideM = matrixActions.multiplicationByScalar(divideM, -1 * matrix.getMatrix()[i + 1][centalC] / matrix.getMatrix()[centalR][centalC]);
                        matrix = matrixActions.addRowToMatrix(matrix, divideM, i + 1 + 1);
                    } else {
                        break;
                    }
                }
            } else {
                for (int j = centalC; j < matrix.getColumns(); j++) {
                    if ((j + 1) < matrix.getColumns()) {
                        matrix = matrixActions.permuteColumn(matrix,j + 1, j + 2);
                        counterOfPermute++;
                    }
                    if (matrix.getMatrix()[centalR][centalC] != 0) {
                        break;
                    }
                }
                if (matrix.getMatrix()[centalR][centalC] == 0) {
                    break;
                }
                centalR--;
                centalC--;

            }
        }

        for (int centalR = 0, centalC = 0; centalR < matrix.getRows() && centalC < matrix.getColumns(); centalR++, centalC++) {
            for (int i = centalC; i < matrix.getRows() - 1; i++) {
                matrix.setElement(i + 1 + 1, centalC + 1, Math.round(matrix.getMatrix()[i + 1][centalC]));

            }
        }

        if (Math.abs(counterOfPermute) % 2 == 0) {
        } else {
            matrix = matrixActions.multiplicationByScalar(matrix, -1);
        }
        return new Matrix(matrix);
    }


    @Override
    public double getDeterminant(){
        if (getColumns()!=getRows()) {
            throw new IllegalArgumentException("Determinant cannot be found");
        }

        if(getColumns() == 2){
            return Math.round((matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0])*1000)/1000;
        }
        
        Matrix matrix_copy = new Matrix(matrix);
        Matrix mat = triangularShapePermutCountForDeterminat(matrix_copy);
        double d = 1;
        for(int i = 0; mat.matrix.length > i; i++){
            d = d * mat.matrix[i][i];
        }

        System.out.println(d);
        return Math.round(d*1000)/1000;

    }

    public String getDimension(){
        return rows + " x " + columns;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            result.append("[ ");
            for (int j = 0; j < columns; j++) {
                result.append(matrix[i][j]);
                if (j < columns - 1) {
                    result.append(", ");
                }
            }
            result.append(" ]\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Matrix)) {
            return false;
        }
        Matrix m = (Matrix) o;
        boolean answer = this.getRows() == m.getRows()
                && this.getColumns() == m.getColumns()
                && Arrays.deepEquals(this.matrix, m.matrix);
        return answer;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + getRows();
        result = 31 * result + getColumns();

        for (int i = 0; i < rows; i++) {
            result = 31 * result + Double.hashCode(matrix[i][0]);
        }

        return result;
    }

    protected void CheckForNumInMatrix(int rows, int columns){
        if (rows <= 0 || rows > this.getRows()
                || columns <= 0 || columns > this.getColumns()){
                    throw new IllegalArgumentException("Element num is bigger than matrix dim");
                }
    }
}

