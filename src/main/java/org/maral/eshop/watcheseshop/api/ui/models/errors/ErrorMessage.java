package org.maral.eshop.watcheseshop.api.ui.models.errors;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class ErrorMessage {

    private final Date timestamp;
    private final String message;
    private final String detail;
}
