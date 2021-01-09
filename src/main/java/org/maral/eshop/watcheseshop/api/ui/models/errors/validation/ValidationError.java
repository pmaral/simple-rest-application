package org.maral.eshop.watcheseshop.api.ui.models.errors.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationError {

    private final String field;
    private final String code;
    private final String objectName;
    private final String message;
}
