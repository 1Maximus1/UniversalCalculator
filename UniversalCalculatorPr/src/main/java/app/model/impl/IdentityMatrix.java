package app.model.impl;

import app.model.IMatrix;

public class IdentityMatrix extends SquareMatrix implements IMatrix
{
	public IdentityMatrix(int size) {
		super(size);
		initializeIdentityMatrix();
	}

	public IdentityMatrix(Matrix matrix) {
		super(matrix);
		initializeIdentityMatrix();
	}

	public IdentityMatrix(double[][] matrix){
		super(matrix);
		initializeIdentityMatrix();
	}

	private void initializeIdentityMatrix() {
		for (int i = 1; i <= getRows(); i++) {
			for (int j = 1; j <= getColumns(); j++) {
				if (i == j) {
					setElement(i, j, 1.0);
				} else {
					setElement(i, j, 0.0);
				}
			}
		}
	}

	@Override
	public double getDeterminant(){
		return 1;
	}

	@Override
	public int getRang() {
		return getColumns();
	}

}
