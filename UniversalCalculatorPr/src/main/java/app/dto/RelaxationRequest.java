package app.dto;

import app.model.impl.Matrix;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelaxationRequest {
	private Matrix matrix;
	private Matrix vector;
}
