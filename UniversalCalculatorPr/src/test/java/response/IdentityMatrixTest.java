package response;

import app.model.impl.IdentityMatrix;
import app.model.impl.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdentityMatrixTest {
    
    private IdentityMatrix IMat;

    @Test
    public void ConstuctorWithOrder(){
        IMat = new IdentityMatrix(2);
        assertEquals(1.0, IMat.getElement(1, 1));
        assertEquals(1.0, IMat.getElement(2, 2));
        assertEquals(0.0, IMat.getElement(1, 2));
        assertEquals(0.0, IMat.getElement(2, 1));
    }


    @Test
    public void ConstuctorWithOtherMatrix(){
        Matrix mat = new Matrix(2,2);
        IMat = new IdentityMatrix(mat);
        assertEquals("2 x 2", IMat.getDimension());

    }

    @Test
    public void ConstuctorWithDoubleArrayMatrix(){
        double[][] mat = new double[2][2];
        IMat = new IdentityMatrix(mat);
        assertEquals("2 x 2", IMat.getDimension());
    }

    @Test
    public void GetDeterminant_simpleMatrix(){
        IMat = new IdentityMatrix(2);
        assertEquals(1, IMat.getDeterminant());
    }

    @Test
    public void GetRang_simpleMatrix(){
        IMat = new IdentityMatrix(3);
        assertEquals(3, IMat.getRang());
    }
}
