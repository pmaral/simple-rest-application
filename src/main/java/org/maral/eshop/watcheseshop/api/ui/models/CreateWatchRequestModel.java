package org.maral.eshop.watcheseshop.api.ui.models;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public final class CreateWatchRequestModel {

    @NotNull(message = "Title cannot be null")
    @Size(min = 2, message = "Title name must not be less than 2 characters")
    private String title;
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price should by positive")
    private Integer price;
    @NotNull(message = "Description cannot be null")
    private String description;
    private byte[] image;
}
