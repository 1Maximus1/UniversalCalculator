package app.services;


import app.model.impl.Matrix;

public class RelaxationMethod {
    public  static double[] relaxationMethod(Matrix matrix, Matrix vector)
    {
        double omega = 1.2;
        double epsilon = 0.0005; 
        int maxIterations = 5000;
        double[][] coefficients = matrix.getMatrix();
        double[][] constants = vector.getMatrix();

        int n = constants.length;
        double[] x = new double[n];
        double[] xNew = new double[n];
        double error;

        int iteration = 0;
        do {
            for (int i = 0; i < n; i++) {
                double sum = constants[i][0];
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= coefficients[i][j] * x[j];
                    }
                }
                xNew[i] = (1 - omega) * x[i] + (omega / coefficients[i][i]) * sum;
            }

            error = calculateError(x, xNew); 
            System.arraycopy(xNew, 0, x, 0, n); 

            iteration++;
        } while (error > epsilon && iteration < maxIterations);

        if (iteration >= maxIterations) {
            throw new StackOverflowError("Iteration overflow");
        }

        return x;
    }

    private static double calculateError(double[] x, double[] xNew) {
        double maxError = 0.0;
        for (int i = 0; i < x.length; i++) {
            double error = Math.abs(xNew[i] - x[i]);
            if (error > maxError) {
                maxError = error;
            }
        }
        return maxError;
    }
}









    

