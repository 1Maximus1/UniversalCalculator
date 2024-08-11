package app.model.impl;

public class SystemOfLinearEquations
{
	private SquareMatrix coefficientsMatrix;
	private MatrixVector constantsVector;

	public SystemOfLinearEquations(SquareMatrix coefficientsMatrix, MatrixVector constantsVector) {
		if (coefficientsMatrix.getColumns() != constantsVector.getSize()) {
			throw new IllegalArgumentException("Incompatible matrix and vector dimensions.");
		}
		this.coefficientsMatrix = new SquareMatrix(coefficientsMatrix);
		this.constantsVector = new MatrixVector(constantsVector);
	}

	public SquareMatrix getCoefficientsMatrix() {
		return coefficientsMatrix;
	}

	public MatrixVector getConstantsVector() {
		return constantsVector;
	}

}
