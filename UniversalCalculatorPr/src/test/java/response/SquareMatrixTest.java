package response;

import app.model.impl.Matrix;
import app.model.impl.SquareMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SquareMatrixTest {
    private SquareMatrix SqMat;

    @Test
    public void ThrowExceptionOnConstuctorWithNegativeDim(){

        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> SqMat = new SquareMatrix(-1));
            
        String expectedMessage = "Dimension can 0x0 but not: -1x-1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ConstuctorWithOrderOfMatrix(){
        SqMat = new SquareMatrix(4);
        assertEquals("4 x 4", SqMat.getDimension());
    }

    @Test
    public void ConstuctorWithOtherMatrix(){
        Matrix mat = new Matrix(2, 2);
        SqMat = new SquareMatrix(mat);
        assertEquals("2 x 2", SqMat.getDimension());
    }
    
    @Test
    public void ThrowExceptionOnConstuntorWithOtherNonSquareMatrix(){
        Matrix mat = new Matrix(2, 3);
        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> SqMat = new SquareMatrix(mat));
            
        String expectedMessage = "Invalid matrix dimensions for a square matrix.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ThrowExceptionOnConstuntorWithDoubleArrayALikeNonSquareMatrix(){
        double[][] array_d = new double[2][3];
        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> SqMat = new SquareMatrix(array_d));
            
        String expectedMessage = "Invalid matrix dimensions for a square matrix.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ConstuctorWithDoubleArray(){
        double[][] array_d = new double[2][2];
        SqMat = new SquareMatrix(array_d);
        assertEquals("2 x 2", SqMat.getDimension());
    }

    @Test
    public void GetDeterminant_simlpeMatrix(){
        double[][] array_d = new double[][]{{2,1,7},{0,5,1},{3,4,0}};
        SqMat = new SquareMatrix(array_d);

        assertEquals(-110, SqMat.getDeterminant());
    }


}
