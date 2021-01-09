package org.maral.eshop.watcheseshop.api.service;

import java.util.Optional;
import org.maral.eshop.watcheseshop.api.ui.models.CreateWatchRequestModel;
import org.maral.eshop.watcheseshop.api.ui.models.GetWatchResponseModel;

public interface WatchStockItemsService {

    Long createWatchStockItemAndReturnId(CreateWatchRequestModel model);

    Optional<GetWatchResponseModel> getWatchStockItemById(long id);
}
