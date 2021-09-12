package kr.or.connect.reservation.core.presentation.dto;

import io.swagger.annotations.ApiModelProperty;
import kr.or.connect.reservation.core.presentation.domain.ProductPrice;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductPriceResult {
    @ApiModelProperty(example = "1")
    private long productPriceId;
    @ApiModelProperty(example = "1")
    private long productId;
    @ApiModelProperty(example = "A")
    private String priceTypeName;
    @ApiModelProperty(example = "6000")
    private long price;
    @ApiModelProperty(example = "20.0")
    private double discountRate;
    @ApiModelProperty(example = "2020-08-11T01:02:48")
    private LocalDateTime createDate;
    @ApiModelProperty(example = "2020-08-11T01:02:48")
    private LocalDateTime modifyDate;

    public ProductPriceResult(long productPriceId, long productId, String priceTypeName, long price, double discountRate,
                              LocalDateTime createDate, LocalDateTime modifyDate) {
        super();
        this.productPriceId = productPriceId;
        this.productId = productId;
        this.priceTypeName = priceTypeName;
        this.price = price;
        this.discountRate = discountRate;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public static ProductPriceResult makeProductPriceResult(ProductPrice productPrice) {
        return new ProductPriceResult
                (productPrice.getId(), productPrice.getProduct().getId(), productPrice.getPriceTypeName(),
                        productPrice.getPrice(), productPrice.getDiscountRate(),
                        productPrice.getCreateDate(), productPrice.getModifyDate());
    }
}
