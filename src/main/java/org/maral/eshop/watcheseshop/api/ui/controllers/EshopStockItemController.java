package org.maral.eshop.watcheseshop.api.ui.controllers;

import javax.validation.Valid;
import org.maral.eshop.watcheseshop.api.service.WatchStockItemsService;
import org.maral.eshop.watcheseshop.api.ui.models.CreateWatchRequestModel;
import org.maral.eshop.watcheseshop.api.ui.models.CreateWatchResponseModel;
import org.maral.eshop.watcheseshop.api.ui.models.GetWatchResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/stockItems/watches")
public class EshopStockItemController {

    @Autowired
    private WatchStockItemsService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(
        value = "/{itemId}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<GetWatchResponseModel> getWatchById(@PathVariable(required = true) long itemId) {
        return ResponseEntity.of(service.getWatchStockItemById(itemId));
    }

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<CreateWatchResponseModel> createWatchItem(
        @Valid @RequestBody CreateWatchRequestModel watchRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            CreateWatchResponseModel.builder()
                .watchId(service.createWatchStockItemAndReturnId(watchRequestModel))
                .build()
        );
    }
}
