package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
public class AmCartErrorResponse {
    private String errorCode;
    private String errorMessage;
    private Date timestamp;
    private Map<String, String> errorDetails;
}
