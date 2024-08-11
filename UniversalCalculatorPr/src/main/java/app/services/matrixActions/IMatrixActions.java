package app.services.matrixActions;

import app.model.impl.Matrix;
import app.model.impl.SquareMatrix;

public interface IMatrixActions
{
	Matrix addMatrices(Matrix m1, Matrix m2);

	Matrix subtractMatrices(Matrix m1, Matrix m2);

	Matrix multiplicationMatrices(Matrix a, Matrix b);

	Matrix triangularShapeUpper(Matrix matrix);

	Matrix triangularShapeLower(Matrix matrix);

	Matrix addRowToMatrix(Matrix matrix, Matrix row, int index);

	Matrix permuteColumn(Matrix matrix, int column1, int column2);

	SquareMatrix inverse(Matrix matrix);

	Matrix transposeMatrix(Matrix matrix);

	SquareMatrix powerMatrix(SquareMatrix matrix, int degree);

}
