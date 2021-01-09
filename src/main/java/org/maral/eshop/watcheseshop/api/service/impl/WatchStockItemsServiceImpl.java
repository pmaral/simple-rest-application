package org.maral.eshop.watcheseshop.api.service.impl;

import java.util.Optional;
import javax.transaction.Transactional;
import org.maral.eshop.watcheseshop.api.entity.WatchStockItemEntity;
import org.maral.eshop.watcheseshop.api.exceptions.EshopServiceException;
import org.maral.eshop.watcheseshop.api.repository.WatchStockItemsRepository;
import org.maral.eshop.watcheseshop.api.service.WatchStockItemsService;
import org.maral.eshop.watcheseshop.api.ui.models.CreateWatchRequestModel;
import org.maral.eshop.watcheseshop.api.ui.models.GetWatchResponseModel;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchStockItemsServiceImpl implements WatchStockItemsService {

    private static final Logger LOG = LoggerFactory.getLogger(WatchStockItemsServiceImpl.class);

    @Autowired
    WatchStockItemsRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional
    public Long createWatchStockItemAndReturnId(CreateWatchRequestModel createWatchRequestModel) {
        return Optional.ofNullable(createWatchRequestModel)
            .map(model -> modelMapper.map(model, WatchStockItemEntity.class))
            .flatMap(this::trySaveEntity)
            .map(WatchStockItemEntity::getWatchId)
            .orElseThrow(() -> new EshopServiceException("Error when creating watch stock item."));
    }

    @Override
    public Optional<GetWatchResponseModel> getWatchStockItemById(long id) {
        return repository.findById(id)
            .map(watchEntity -> modelMapper.map(watchEntity, GetWatchResponseModel.class));
    }

    private Optional<WatchStockItemEntity> trySaveEntity(WatchStockItemEntity entity) {
        try {
            return Optional.of(repository.save(entity));
        } catch (Exception exception) {
            LOG.error("Error when watch entity was saved.", exception);
            return Optional.empty();
        }
    }
}
