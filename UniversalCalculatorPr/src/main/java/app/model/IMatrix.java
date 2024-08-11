package app.model;

public interface IMatrix {
	public double getElement(int row, int column);
	public int getRows();
	public int getColumns();
	public double[][] getMatrix();
	public int getRang();
	public double getDeterminant();
	public void setElement(int row, int column, double value);
}
