package org.maral.eshop.watcheseshop.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WATCH_STOCK_ITEMS")
@NoArgsConstructor
@Data
public class WatchStockItemEntity {

    @Id
    @GeneratedValue
    private long watchId;

    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false, length = 200)
    private String description;
    @Column(nullable = false)
    @Lob
    private byte[] image;
}
