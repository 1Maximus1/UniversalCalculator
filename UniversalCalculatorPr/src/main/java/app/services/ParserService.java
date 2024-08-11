package app.services;

import app.model.impl.Matrix;
import app.util.parser.UserSaveDataStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ParserService {
    @Value("${filename_location}")
    private String filename;

    private UserSaveDataStruct saveData;

    @Autowired
    public ParserService(UserSaveDataStruct saveData) {
        this.saveData = saveData;
    }

    public <T extends Matrix> void save(String clientIp, String action, T matrixA, T matrixB, Matrix resultMatrix) {
        new UserSaveDataStruct(clientIp, action, matrixA, matrixB, resultMatrix).saveToJsonFile(filename);
    }


    public <T extends Matrix> void save(String clientIp, String action, T matrixA, T matrixB, String error) {
        new UserSaveDataStruct(clientIp, action, matrixA, matrixB, error).saveToJsonFile(filename);
    }

    public <T extends Matrix> void save(String clientIp, String action, T matrixSquare, double result) {
        new UserSaveDataStruct(clientIp, action, matrixSquare, result).saveToJsonFile(filename);
    }

    public <T extends Matrix> void save(String clientIp, String action, T matrix, String error) {
        new UserSaveDataStruct(clientIp,action,matrix,error).saveToJsonFile(filename);
    }

    public <T extends Matrix> void save(String clientIp, String action, T matrix, T result) {
        new UserSaveDataStruct(clientIp, action, matrix, result).saveToJsonFile(filename);
    }
}
