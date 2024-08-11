package app.services;

public class ReverseToDouble {
    public static int getFirstElement(double[][] array) {
        if(array.length > 1 && array[0].length > 1){
            throw new IllegalArgumentException("Degree can`t be array.");
        }
        return (int) array[0][0];
    }
}
