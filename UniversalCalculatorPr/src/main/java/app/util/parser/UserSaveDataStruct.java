package app.util.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import app.model.impl.Matrix;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


@Getter
@Component
public class UserSaveDataStruct {
    private final String ip;
    private final String action;
    private final Matrix matrixA;
    private final Matrix matrixB;
    private final Matrix resultMatrix;
    private final Double resultValue;
    private final String error;

    public UserSaveDataStruct() {
        this.ip = "";
        this.action = "";
        this.matrixA = new Matrix();
        this.matrixB = new Matrix();
        this.resultMatrix = new Matrix();
        this.resultValue = null;
        this.error = "None";
    }

    public UserSaveDataStruct(String ip, String action, Matrix matrixA, Matrix matrixB, Matrix resultMatrix, String error){
        this.ip = ip;
        this.action = action;
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.resultMatrix = resultMatrix;
        this.resultValue = null;
        this.error = error;
    }

    public UserSaveDataStruct(String ip, String action, Matrix matrixA, Matrix matrixB, Matrix resultMatrix){
        this.ip = ip;
        this.action = action;
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.resultMatrix = resultMatrix;
        this.resultValue = null;
        this.error = "None";
    }

    public UserSaveDataStruct(String ip, String action, Matrix matrixA, Matrix resultMatrix){
        this.ip = ip;
        this.action = action;
        this.matrixA = matrixA;
        this.matrixB = new Matrix();
        this.resultMatrix = resultMatrix;
        this.resultValue = null;
        this.error = "None";
    }

    public UserSaveDataStruct(String ip, String action, Matrix matrixA, String error){
        this.ip = ip;
        this.action = action;
        this.matrixA = matrixA;
        this.matrixB = new Matrix();
        this.resultMatrix = new Matrix();
        this.resultValue = null;
        this.error = error;
    }

    public UserSaveDataStruct(String ip, String action, Matrix matrixA, Matrix matrixB, String error){
        this.ip = ip;
        this.action = action;
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.resultMatrix = new Matrix();
        this.resultValue = null;
        this.error = error;
    }

    public UserSaveDataStruct(String ip, String action, Matrix matrixA, double resultValue){
        this.ip = ip;
        this.action = action;
        this.matrixA = matrixA;
        this.matrixB = new Matrix();
        this.resultMatrix = new Matrix();
        this.resultValue = resultValue;
        this.error = "None";
    }

    public void saveToJsonFile(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            if (file.exists() && file.length() !=0) {
                bufferedWriter.write(",\n");
                gson.toJson(this, bufferedWriter);
                System.out.println("Data saved in: " + filePath);
            } else {
                gson.toJson(this, bufferedWriter);
                System.out.println("Data saved in new file: " + filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
