package app.services;

import app.model.impl.Matrix;
import app.model.impl.SquareMatrix;
import app.services.matrixActions.impl.MatrixActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatrixService {
    private final MatrixActions matrixActions;

    @Autowired
    public MatrixService(MatrixActions matrixActions) {
        this.matrixActions = matrixActions;
    }

    public <T extends Matrix> T addMatrices(T matrix1, T matrix2) {
        return (T) matrixActions.addMatrices(matrix1,matrix2);
    }

    public <T extends Matrix> T multiplyMatrices(T matrixA, T matrixB) {
        return (T) matrixActions.multiplicationMatrices(matrixA, matrixB);
    }

    public <T extends Matrix> T inverse(T matrix) {
        return (T) matrixActions.inverse(matrix);
    }

    public <T extends Matrix> T transposeMatrix(T matrix) {
        return (T) matrixActions.transposeMatrix(matrix);
    }

    public <T extends SquareMatrix> T powerMatrix(T squareMatrix, int degree) {
        return (T) matrixActions.powerMatrix(squareMatrix,degree);
    }

    public <T extends Matrix> T subtractMatrices(T matrixA, T matrixB) {
        return (T) matrixActions.subtractMatrices(matrixA,matrixB);
    }

    public <T extends Matrix> T triangularShapeLower(T matrix) {
        return (T) matrixActions.triangularShapeLower(matrix);
    }

    public <T extends Matrix> T triangularShapeUpper(T matrix) {
        return (T) matrixActions.triangularShapeUpper(matrix);
    }
}
