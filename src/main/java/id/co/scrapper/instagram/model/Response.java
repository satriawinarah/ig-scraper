package id.co.scrapper.instagram.model;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	private String responseCode;
	private Optional<Object> responseBody;
	private Boolean success;
	private String errorDetail;
}
