package org.maral.eshop.watcheseshop.api.ui.models.errors.validation;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ValidationErrorsResponse {

    private Date timestamp;
    private String message;
    private List<ValidationError> validationErrors;
}
