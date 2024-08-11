package app.controllers;


import app.services.*;
import app.clientIp.IpUtils;
import app.model.impl.Matrix;
import app.model.impl.SquareMatrix;
import app.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import app.util.parser.UserSaveDataStruct;

import java.util.Arrays;

@RestController
public class MatrixController {
	private final MatrixService matrixService;
	private final ParserService parserService;

	@Autowired
	public MatrixController(MatrixService matrixService, ParserService parserService) {
        this.matrixService = matrixService;
        this.parserService = parserService;
    }

	@PostMapping("/add")
	public ResponseEntity<double[][]> addMatrices(HttpServletRequest httpServletRequest, @RequestBody MatrixActionRequest request) {
		try {
			Matrix result = matrixService.addMatrices(new Matrix(request.getM1()), new Matrix(request.getM2()));
			parserService.save(IpUtils.getClientIp(httpServletRequest), "addMatrices", new Matrix(request.getM1()), new Matrix(request.getM2()), result);
			return ResponseEntity.ok(result.getMatrix());
		} catch (Exception e) {
			parserService.save(IpUtils.getClientIp(httpServletRequest), "addMatrices", new Matrix(request.getM1()), new Matrix(request.getM2()), e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping("/multiplyMatrices")
	public ResponseEntity<double[][]> multiplyMatrices(HttpServletRequest httpServletRequest, @RequestBody MatrixActionRequest request) {
		try {
			Matrix result = matrixService.multiplyMatrices(new Matrix(request.getM1()), new Matrix(request.getM2()));
			parserService.save(IpUtils.getClientIp(httpServletRequest), "multiplyMatrices", new Matrix(request.getM1()), new Matrix(request.getM2()), result);
			return ResponseEntity.ok(result.getMatrix());
		}catch (Exception e) {
			parserService.save(IpUtils.getClientIp(httpServletRequest), "multiplyMatrices", new Matrix(request.getM1()), new Matrix(request.getM2()), e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping("/determinant")
	public ResponseEntity<Double> determinant(HttpServletRequest httpServletRequest, @RequestBody MatrixRequest request) {
		try {
			double result = new SquareMatrix(request.getMatrix()).getDeterminant();
			parserService.save(IpUtils.getClientIp(httpServletRequest), "determinant", new SquareMatrix(request.getMatrix()), result);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			parserService.save(IpUtils.getClientIp(httpServletRequest), "determinant", new SquareMatrix(request.getMatrix()), e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}


	@PostMapping("/inverse")
	public ResponseEntity<double[][]> inverse(HttpServletRequest httpServletRequest, @RequestBody MatrixRequest request) {
		try {
			Matrix result = matrixService.inverse(new Matrix(request.getMatrix()));
			parserService.save(IpUtils.getClientIp(httpServletRequest), "inverse", new Matrix(request.getMatrix()), result);
			return ResponseEntity.ok(result.getMatrix());
		}catch (Exception e){
			parserService.save(IpUtils.getClientIp(httpServletRequest), "inverse", new Matrix(request.getMatrix()), e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@PostMapping("/rang")
	public ResponseEntity<Integer> calculateRank(HttpServletRequest httpServletRequest, @RequestBody MatrixRequest request) {
		try {
			int result = new Matrix(request.getMatrix()).getRang();
			parserService.save(IpUtils.getClientIp(httpServletRequest), "rang", new Matrix(request.getMatrix()), result);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			parserService.save(IpUtils.getClientIp(httpServletRequest), "rang", new Matrix(request.getMatrix()), e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping("/transpose")
	public ResponseEntity<double[][]> transposeMatrix(HttpServletRequest httpServletRequest,@RequestBody MatrixRequest request) {
		try {
			Matrix transposedMatrix = matrixService.transposeMatrix(new Matrix(request.getMatrix()));
			parserService.save(IpUtils.getClientIp(httpServletRequest), "transpose", new Matrix(request.getMatrix()), transposedMatrix);
			return ResponseEntity.ok(transposedMatrix.getMatrix());
		} catch (Exception e) {
			UserSaveDataStruct userData = new UserSaveDataStruct(IpUtils.getClientIp(httpServletRequest), "transpose", new Matrix(request.getMatrix()), e.getMessage());
			userData.saveToJsonFile("file.json");
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PostMapping("/powerMatrix")
	public ResponseEntity<double[][]> powerMatrix(HttpServletRequest httpServletRequest, @RequestBody MatrixActionRequest matrixPowerRequest) {
		try {
			SquareMatrix result = matrixService.powerMatrix(
					new SquareMatrix(matrixPowerRequest.getM1()),
					ReverseToDouble.getFirstElement(matrixPowerRequest.getM2())
			);
			parserService.save(IpUtils.getClientIp(httpServletRequest), "power_matrix_degree{%d}".formatted(ReverseToDouble.getFirstElement(matrixPowerRequest.getM2())), new Matrix(matrixPowerRequest.getM1()), result);
			return ResponseEntity.ok(result.getMatrix());
		} catch (Exception e) {
			parserService.save(IpUtils.getClientIp(httpServletRequest), "power_matrix_degree{%d}".formatted(ReverseToDouble.getFirstElement(matrixPowerRequest.getM2())), new Matrix(matrixPowerRequest.getM1()), e.getMessage());
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PostMapping("/subtract")
	public ResponseEntity<double[][]> subtractMatrices(HttpServletRequest httpServletRequest, @RequestBody MatrixActionRequest request) {
		try {
			Matrix result = matrixService.subtractMatrices(
					new Matrix(request.getM1()),
					new Matrix(request.getM2())
			);
			parserService.save(IpUtils.getClientIp(httpServletRequest), "subtract", new Matrix(request.getM1()), new Matrix(request.getM2()), result);
			return ResponseEntity.ok(result.getMatrix());
		} catch (IllegalArgumentException e) {
			parserService.save(IpUtils.getClientIp(httpServletRequest), "subtract", new Matrix(request.getM1()), new Matrix(request.getM2()), e.getMessage());
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PostMapping("/triangularShapeLower")
	public ResponseEntity<double[][]> triangularShapeLower(HttpServletRequest httpServletRequest, @RequestBody MatrixRequest request) {
		try {
			Matrix result = matrixService.triangularShapeLower(new Matrix(request.getMatrix()));
			parserService.save(IpUtils.getClientIp(httpServletRequest), "triangularShapeLower", new Matrix(request.getMatrix()), result);
			return ResponseEntity.ok(result.getMatrix());
		} catch (IllegalArgumentException e) {
			parserService.save(IpUtils.getClientIp(httpServletRequest), "triangularShapeLower", new Matrix(request.getMatrix()), e.getMessage());
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PostMapping("/triangularShapeUpper")
	public ResponseEntity<double[][]> triangularShapeUpper(HttpServletRequest httpServletRequest, @RequestBody MatrixRequest request) {
		try {
			Matrix result = matrixService.triangularShapeUpper(new Matrix(request.getMatrix()));
			parserService.save(IpUtils.getClientIp(httpServletRequest), "triangularShapeUpper", new Matrix(request.getMatrix()), result);
			return ResponseEntity.ok(result.getMatrix());
		} catch (IllegalArgumentException e) {
			parserService.save(IpUtils.getClientIp(httpServletRequest), "triangularShapeUpper", new Matrix(request.getMatrix()), e.getMessage());
			return ResponseEntity.badRequest().body(null);
		}
	}


	@PostMapping("/relaxationMethod")
	public ResponseEntity<double[]> relaxationMethod(HttpServletRequest httpServletRequest, @RequestBody RelaxationRequest request) {
		try {
			Matrix matrix = request.getMatrix();
			Matrix vector = request.getVector();
			double[] result = RelaxationMethod.relaxationMethod(matrix, vector);
			parserService.save(IpUtils.getClientIp(httpServletRequest), "relaxationMethod", new Matrix(request.getMatrix()), new Matrix(request.getVector()), Arrays.toString(result));
			return ResponseEntity.ok(result);
		}catch (Exception e){
			parserService.save(IpUtils.getClientIp(httpServletRequest), "relaxationMethod", new Matrix(request.getMatrix()), new Matrix(request.getVector()), e.getMessage());
			return ResponseEntity.badRequest().body(null);
		}
	}
}

