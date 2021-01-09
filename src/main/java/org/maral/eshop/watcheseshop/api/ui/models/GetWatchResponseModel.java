package org.maral.eshop.watcheseshop.api.ui.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public final class GetWatchResponseModel {

    private Long watchId;
    private String title;
    private int price;
    private String description;
    private byte[] image;
}
