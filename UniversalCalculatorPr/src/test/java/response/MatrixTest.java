package response;

import app.model.impl.Matrix;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
    private Matrix mat;
    @Test
    public void EmptyConstuctor(){
        mat = new Matrix();
        assertEquals("0 x 0",mat.getDimension() );
    }

    @Test
    public void ConstuctorWithCertainColumnsAndRows(){
        mat = new Matrix(2,3);
        assertEquals("2 x 3",mat.getDimension());
    }

    @Test
    public void ConstuctorWithOtherMatrix(){
        Matrix other_matrix = new Matrix(3,2);
        mat = new Matrix(other_matrix);
        assertEquals("3 x 2",mat.getDimension());
    }

    @Test
    public void ConstuctorWithDoubleArray(){
        double[][] array_d = new double[4][4];
        mat = new Matrix(array_d);
        assertEquals("4 x 4",mat.getDimension());
    }

    @Test
    public void setElement_doubleNegativeAndPositive(){
        mat = new Matrix(2, 1);
        mat.setElement(1, 1, -1.2);
        mat.setElement(2, 1, 2.5);

        assertEquals(-1.2, mat.getElement(1, 1));
        assertEquals(2.5, mat.getElement(2, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3})
    public void ThrowExceptionOnGetElementColumnThatOutOfMatrix(int input){
    mat = new Matrix(2, 2);
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat.getElement(1, input));
        
    String expectedMessage = "Element num is bigger than matrix dim";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3})
    public void ThrowExceptionOnSetElementRowsThatOutOfMatrix(int input){
    mat = new Matrix(2, 2);
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat.setElement(input, 0, -1.2));
        
    String expectedMessage = "Element num is bigger than matrix dim";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ThrowExceptionOnWrongDimWithNegativeNumber(){
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat = new Matrix(-1, 2));
        
    String expectedMessage = "Dimension can 0x0 but not: -1x2";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ThrowExceptionOnDimWithOnlyOneZero(){
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat = new Matrix(1, 0));
        
    String expectedMessage = "Dimension can't have only one 0: 1x0";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ThrowExceptionOnWrongDimWithNegativeNumber_1(){
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat = new Matrix(1, -2));
        
    String expectedMessage = "Dimension can 0x0 but not: 1x-2";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ThrowExceptionOnDimWithOnlyOneZero_1(){
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat = new Matrix(0, 1));
        
    String expectedMessage = "Dimension can't have only one 0: 0x1";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }
          //  assertEquals(Arrays.hashCode(new double[][]{{-2,100000},{6,100000}}), Arrays.hashCode(mat.getMatrix()));


    @Test
    public void getMatrix_simpleValues(){
        mat = new Matrix(2,1);
        mat.setElement(1, 1, -2.1);
        mat.setElement(2, 1, 6.1);
        //assertEquals(Arrays.hashCode(new double[][]{{-2.100000},{6.100000}}), Arrays.hashCode(mat.getMatrix()));
        double[][] array_d = mat.getMatrix();
        assertEquals(-2.1, array_d[0][0]);
        assertEquals(6.1, array_d[1][0]);
    }

    @Test
    public void GetRow_GetColumn_simpleValues(){
        mat = new Matrix(2,2);
        mat.setElement(1, 1, -2.1);
        mat.setElement(2, 1, 6.1);
        mat.setElement(1, 2, 4);
        mat.setElement(2, 2, 8);

        Matrix rowMatrix = mat.getRow(1);
        Matrix columnMatrix = mat.getColumn(2);
        assertEquals("1 x 2", rowMatrix.getDimension());
        assertEquals("2 x 1", columnMatrix.getDimension());

        assertEquals(-2.1, rowMatrix.getElement(1, 1));
        assertEquals(4, rowMatrix.getElement(1, 2));
        assertEquals(4, columnMatrix.getElement(1,1));
        assertEquals(8, columnMatrix.getElement(2,1));
    }

   
    @ParameterizedTest
    @ValueSource(ints = {0, 2})
    public void ThrowExceptionOnGetRowsThatOutOfMatrix(int input){
    mat = new Matrix(1, 1);
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat.getRow(input));
        
    String expectedMessage = "Invalid row index.";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2})
    public void ThrowExceptionOnGetColumnsThatOutOfMatrix(int input){
    mat = new Matrix(1, 1);
    Exception exception = assertThrows(IllegalArgumentException.class,
     () -> mat.getColumn(input));
        
    String expectedMessage = "Invalid column index.";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void GetRang_EmpryMatrixShouldhaveZeroRang(){
        mat = new Matrix();
        assertEquals(0, mat.getRang());
    }

    @Test
    public void GetRang_SimpleMatrix(){
        double[][] array_d = new double[][]{{2,1,7},{0,5,1},{3,4,0}};
        mat = new Matrix(array_d);
        assertEquals(3, mat.getRang());
    }

    @Test
    public void GetRang_MatrixWhereRangSmallerThanDim(){
        double[][] array_d = new double[][]{{2,1,7},{0,1,2},{0,2,4}};
        mat = new Matrix(array_d);
        assertEquals(2, mat.getRang());
    }


    @Test
    public void GetRang_MatrixWhereRangAWaySmallerThanDim(){
        double[][] array_d = new double[][]{{0,1},{0,1},{0,2}};
        mat = new Matrix(array_d);
        assertEquals(1, mat.getRang());
    }

    @Test
    public void ThrowExceptionOnGetDeterminantForNonSquareMatrix(){
        mat = new Matrix(2, 1);
        Exception exception = assertThrows(IllegalArgumentException.class,
        () -> mat.getDeterminant());
            
        String expectedMessage = "Determinant cannot be found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void GetDeterminant_simlpeMatrix(){
        double[][] array_d = new double[][]{{2,1,7},{0,5,1},{3,4,0}};
        mat = new Matrix(array_d);

        assertEquals(-110, mat.getDeterminant());
    }

    @Test
    public void ToString_SimpleCMatrix() {
        double[][] array_d = new double[][]{{2,1,7},{0,5,1},{3,4,0}};
        Matrix matrix = new Matrix(array_d);
        
        String result = matrix.toString();
        
        String expected = "[ 2.0, 1.0, 7.0 ]\n[ 0.0, 5.0, 1.0 ]\n[ 3.0, 4.0, 0.0 ]\n";
        assertEquals(expected, result);
    }

    @Test
    public void equals_hashCode_SimpleMatrix(){
        double[][] array_d = new double[][]{{2,1,7},{0,5,1},{3,4,0}};
        Matrix mat= new Matrix(array_d);

        Matrix otherMatrix = new Matrix(mat);
        

        assertTrue(mat.equals(mat));
        assertTrue(mat.equals(otherMatrix));
        assertEquals(mat.hashCode(), otherMatrix.hashCode());
    }

    @Test
    public void shouldReturnFalseEqualsPassedObject(){
        mat = new Matrix(2, 1);
        assertFalse(mat.equals(new Object()));
    }

    @Test
    public void shouldReturnFalseEqualsPassedMatrixButContainDiffrentValues(){
        double[][] array_d = new double[][]{{1}, {2}};
        mat = new Matrix(array_d);

        double[][] array_d_other = new double[][]{{-1}, {62}};
        Matrix otherMatrix = new Matrix(array_d_other);

        assertFalse(mat.equals(otherMatrix));
    }

    @Test
    public void shouldReturnFalseEqualsPassedMatrixButDiffrentRows(){
        double[][] array_d = new double[][]{{1}, {2}};
        mat = new Matrix(array_d);

        double[][] array_d_other = new double[][]{{-1}};
        Matrix otherMatrix = new Matrix(array_d_other);

        assertFalse(mat.equals(otherMatrix));
    }

    @Test
    public void shouldReturnFalseEqualsPassedMatrixButDiffrentColumns(){
        double[][] array_d = new double[][]{{1}, {2}};
        mat = new Matrix(array_d);

        double[][] array_d_other = new double[][]{{-1, 62},{1,2}};
        Matrix otherMatrix = new Matrix(array_d_other);

        assertFalse(mat.equals(otherMatrix));
    }

    @Test
    public void GetDeterminant_simlpeMatrix_1(){
        double[][] array_d = new double[][]{{0, 3}, {2,1}};
        mat = new Matrix(array_d);

        assertEquals(-6, mat.getDeterminant());
    }
    @Test
    public void GetDeterminant_simlpeMatrix_2(){
        double[][] array_d = new double[][]{{0, 3,2}, {2,1,3}, {0,2,1}};
        mat = new Matrix(array_d);

        assertEquals(2, mat.getDeterminant());
    }

    @Test
    public void GetDeterminant_simlpeMatrix_3(){
        double[][] array_d = new double[][]{{0, 3,2}, {0,1,3}, {0,2,1}};
        mat = new Matrix(array_d);

        assertEquals(0, mat.getDeterminant());
    }

    @Test
    public void GetDeterminant_simlpeMatrix_4(){
        double[][] array_d = new double[][]{{0,3,2,3,1}, {0,1,3,5,1}, {0,2,1,5,1}, {3,5,1,2,6}, {3,7,4,3,4}};
        mat = new Matrix(array_d);

        assertEquals(201, mat.getDeterminant());
    }

    @Test
    public void GetDeterminant_simlpeMatrix_5(){
        double[][] array_d = new double[][]{{0,0,0}, {0,0,0}, {0,0,0}};
        mat = new Matrix(array_d);

        assertEquals(0, mat.getDeterminant());
    }

    
}
