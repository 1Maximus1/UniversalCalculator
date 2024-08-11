package app.model.impl;

import app.model.IMatrix;

public class SquareMatrix extends Matrix implements IMatrix
{

    public SquareMatrix(int size) {
        super(size, size);
    }
    
    public SquareMatrix(Matrix matrix) {
        super(matrix);
        if (matrix.getColumns() != matrix.getRows()) {
            throw new IllegalArgumentException("Invalid matrix dimensions for a square matrix.");
        }
    }

    public SquareMatrix(double[][] result) {
        super(result);
        if (getColumns() != getRows()) {
            throw new IllegalArgumentException("Invalid matrix dimensions for a square matrix.");
        }
    }


    public double getDeterminant(){
        Matrix matrix_copy = new Matrix(matrix);
        Matrix mat = triangularShapePermutCountForDeterminat(matrix_copy);
        double d = 1;
        for(int i = 0; mat.matrix.length > i; i++){
            d = d * mat.matrix[i][i];
        }
        return (double) Math.round(d * 1000) /1000;
    }


}