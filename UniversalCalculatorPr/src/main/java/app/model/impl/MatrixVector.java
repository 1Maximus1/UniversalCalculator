package app.model.impl;

import app.model.IMatrix;

public class MatrixVector extends Matrix implements IMatrix {
	public MatrixVector() {
		super();
	}

	public MatrixVector(int size) {
		super(size, 1);
	}

	public MatrixVector(MatrixVector otherVector) {
		super(otherVector);
		if (otherVector.getColumns() != 1) {
			throw new IllegalArgumentException("Invalid vector dimensions.");
		}
	}

	public MatrixVector(double[] data) {
		super(new double[data.length][1]);
		for (int i = 0; i < data.length; i++) {
			setElement(i + 1, 1, data[i]);
		}
	}

	public void setElement(int index, double value) {
		super.setElement(index, 1, value);
	}

	public double getElement(int index) {
		return super.getElement(index, 1);
	}

	public int getSize() {
		return getRows();
	}

	@Override
	public int getRang() {
		int rang = 0;
		for (int i = 1; i <= getSize(); i++) {
			if (getElement(i) != 0) {
				return 1;
			}
		}
		return rang;
	}


	@Override
	public double getDeterminant() {
		if (getSize() == 1){
			return getElement(1);
		}
		throw new IllegalArgumentException("Invalid dimension");
	}


}


