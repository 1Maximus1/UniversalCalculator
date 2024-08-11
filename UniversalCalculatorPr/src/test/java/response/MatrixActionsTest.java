package response;

import app.services.matrixActions.impl.MatrixActions;
import app.model.impl.IdentityMatrix;
import app.model.impl.Matrix;
import app.model.impl.SquareMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class MatrixActionsTest {
    
    private MatrixActions matrixActions;

    @BeforeEach
    public void setUp(){
        matrixActions = new MatrixActions();
    }
    
    @Test
    public void addMatrices_SimpleMatrices(){
        double[][] array_d_1 = new double[][]{{2,1},{0,5},{3,4}};
        double[][] array_d_2 = new double[][]{{3,2},{5,1},{-3,-9}};
        Matrix mat_1 = new Matrix(array_d_1);
        Matrix mat_2 = new Matrix(array_d_2);

        mat_1 = matrixActions.addMatrices(mat_1, mat_2);
        double[][] result = new double[][]{{5.0,3.0},{5.0,6.0},{0.0,-5.0}};
        mat_2 = new Matrix(result);
        assertTrue(mat_1.equals(mat_2));
    }

    @Test
    public void ThrowExceptionOnAddMatricesWithDifferentDim(){
        Matrix mat_1 = new Matrix(2,3);
        Matrix mat_2 = new Matrix(3, 2);

        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> matrixActions.addMatrices(mat_1, mat_2));
            
        String expectedMessage = "Matrices dim should be equal: 2 x 3 != 3 x 2";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ThrowExceptionOnMultiplicationMatricesWithZeroMatrix(){
        Matrix mat_1 = new Matrix();
        Matrix mat_2 = new Matrix(3, 2);

        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> matrixActions.multiplicationMatrices(mat_1, mat_2));
            
        String expectedMessage = "No matrices provided for addition.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ThrowExceptionOnMultiplicationMatricesWithDifferentColAndRows(){
        Matrix mat_1 = new Matrix(3,2);
        Matrix mat_2 = new Matrix(3, 2);

        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> matrixActions.multiplicationMatrices(mat_1, mat_2));
            
        String expectedMessage = "Such matrices cannot be multiplied, since the number of columns of matrix 'a' is not equal to the number of rows of matrix 'b'.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    public void MultiplicationMatrices_simpleMatrix(){
        double[][] array_d_2 = new double[][]{{2,1},{0,5}};
        double[][] array_d_1 = new double[][]{{3,2}};
        Matrix mat_1 = new Matrix(array_d_1);
        Matrix mat_2 = new Matrix(array_d_2);
        
        mat_1 = matrixActions.multiplicationMatrices(mat_1, mat_2);
        assertEquals("1 x 2", mat_1.getDimension());
        assertEquals(6, mat_1.getElement(1, 1));
        assertEquals(13, mat_1.getElement(1, 2));
    }

    @Test
    public void MultiplicationByScalar_simpleMatrix(){

        double[][] array_d_1 = new double[][]{{3,2}};
        Matrix mat_1 = new Matrix(array_d_1);
        
        mat_1 = matrixActions.multiplicationByScalar(mat_1, -1);
        assertEquals("1 x 2", mat_1.getDimension());
        assertEquals(-3, mat_1.getElement(1, 1));
        assertEquals(-2, mat_1.getElement(1, 2));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4})
    public void ThrowExceptionOnAddRowToMatrixWithRowsOutOfMatrix(int input){
        Matrix mat_1 = new Matrix(1,3);
        Matrix mat_2 = new Matrix(3,3);

        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> matrixActions.addRowToMatrix(mat_2, mat_1, input));
            
        String expectedMessage = "Invalid index or row dimensions.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
        
    @Test
    public void ThrowExceptionOnAddRowToMatrixWithRowsMatrixThatNotEqualToColumnsNum(){
        Matrix mat_1 = new Matrix(1,4);
        Matrix mat_2 = new Matrix(3,3);

        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> matrixActions.addRowToMatrix(mat_2, mat_1, 1));
            
        String expectedMessage = "Invalid index or row dimensions.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ThrowExceptionOnAddRowToMatrixWithRowsMatrixThatNotEqualToColumnsNum_1(){
        Matrix mat_1 = new Matrix(2,3);
        Matrix mat_2 = new Matrix(3,3);

        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> matrixActions.addRowToMatrix(mat_2, mat_1, 1));
            
        String expectedMessage = "Row matrix isn't row, it have 2 rows not 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void AddRowToMatrix_SipmleValues(){
        double[][] array_d_1 = new double[][]{{2,1}};
        double[][] array_d_2 = new double[][]{{3,2},{5,1}};

        Matrix mat_1 = new Matrix(array_d_1);
        Matrix mat_2 = new Matrix(array_d_2);

        mat_2 = matrixActions.addRowToMatrix(mat_2, mat_1, 1);

        assertEquals(5, mat_2.getElement(1, 1));
        assertEquals(3, mat_2.getElement(1, 2));
    }

    @Test
    public void triangularShapeLowerMatrix(){
        double[][] array_d = new double[][]{{2,3,4},{1,2,1}};
        Matrix mat = new Matrix(array_d);

        mat = matrixActions.triangularShapeLower(mat);
        double[][] result = new double[][]{{-2,-5,0},{1,2,1}};
        Matrix result_mat = new Matrix(result);
        assertTrue(mat.equals(result_mat));
    }

    @Test
    public void triangularShapeLowerMatrix_1(){
        double[][] array_d = new double[][]{{2,1},{3,2},{4,1}};
        Matrix mat = new Matrix(array_d);

        mat = matrixActions.triangularShapeLower(mat);
        double[][] result = new double[][]{{0,0},{-5,0},{4,1}};
        Matrix result_mat = new Matrix(result);
        assertTrue(mat.equals(result_mat));
        
    }

    @Test
    public void triangularShapeLowerMatrix_2(){

        double[][] array_d = new double[][]{{3,2,1},{4,2,0},{6,1,0}};
        Matrix mat = new Matrix(array_d);
        MatrixActions matrixActions = new MatrixActions();
        mat = matrixActions.triangularShapeLower(mat);
        double[][] result = new double[][]{{4,0,0},{4,2,0},{3,2,1}};
        Matrix result_mat = new Matrix(result);
        assertTrue(mat.equals(result_mat));
    }

    @Test
    public void ThrowExceptionOnInverseWithNonSquareMatrix(){
        Matrix mat = new Matrix(2,4);
        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> matrixActions.inverse(mat));
        String expectedMessage = "NoN square matrix";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ThrowExceptionOnInverseWithZeroDeterminant(){
        double[][] array_d = new double[][]{{1,2,3},{4,5,6},{7,8,9}};
        Matrix mat = new Matrix(array_d);
        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> matrixActions.inverse(mat));
        String expectedMessage = "Inverse matrix does not exist. Determinant is 0";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void Inverse_SimpleValues(){
        double[][] array_d = new double[][]{{1,2,3},{4,6,6},{7,8,9}};
        Matrix mat = new Matrix(array_d);
        Matrix InMat = matrixActions.inverse(mat);

        assertEquals(1, InMat.getElement(2, 2));
    }

    @Test
    public void Transpose_simpleMatrix(){
        double[][] array_d = new double[][]{{1,2},{8,9}};
        Matrix mat = new Matrix(array_d);
        mat = matrixActions.transposeMatrix(mat);

        double[][] result = new double[][]{{1,8},{2,9}};
        Matrix resultMatrix = new Matrix(result);
        assertTrue(mat.equals(resultMatrix));
    }

    @Test
    public void PowerMatrix_SimpleValues(){
        double[][] array_d = new double[][]{{0,0,3},{6,0,0},{1,0,0}};
        SquareMatrix mat = new SquareMatrix(array_d);
        mat = matrixActions.powerMatrix(mat, 2);

        double[][] result = new double[][]{{3,0,0},{0,0,18},{0,0,3}};
        Matrix resultMatrix = new Matrix(result);
        assertTrue(mat.equals(resultMatrix));
    }

    @Test
    public void PowerMatrix_DegreeZero(){
        double[][] array_d = new double[][]{{0,0,3},{6,0,0},{1,0,0}};
        SquareMatrix mat = new SquareMatrix(array_d);
        mat = matrixActions.powerMatrix(mat, 0);

        IdentityMatrix result = new IdentityMatrix(3);
        Matrix resultMatrix = new Matrix(result);
        assertTrue(mat.equals(resultMatrix));
    }

    @Test
    public void ThrowExceptionOnPowerWithNegativeDegree(){
        double[][] array_d = new double[][]{{0,0,3},{6,0,0},{1,0,0}};
        SquareMatrix mat = new SquareMatrix(array_d);
        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> matrixActions.powerMatrix(mat, -3));
        String expectedMessage = "Matrix cannot be raised to a negative power.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void SubtractMatrices_simpleMatrix(){
        double[][] array_d = new double[][]{{1,2},{8,9}};
        Matrix mat = new Matrix(array_d);
        Matrix mat_2 = new Matrix(array_d); 
        mat = matrixActions.subtractMatrices(mat, mat_2);

        double[][] result = new double[][]{{0,0},{0,0}};
        Matrix resultMatrix = new Matrix(result);
        assertTrue(mat.equals(resultMatrix));
    }

    @Test
    public void ThrowExceptionOnSubtractWithDifferentDimMatrix(){
        Matrix mat = new Matrix(1,2);
        Matrix mat_2 = new Matrix(2,2); 
        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> matrixActions.subtractMatrices(mat, mat_2));
        String expectedMessage = "Matrices must have the same dimensions for subtraction.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
