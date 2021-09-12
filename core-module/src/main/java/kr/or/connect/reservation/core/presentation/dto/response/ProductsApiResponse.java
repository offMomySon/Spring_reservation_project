package kr.or.connect.reservation.core.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import kr.or.connect.reservation.core.presentation.dto.ProductDisplayInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductsApiResponse {
    @ApiModelProperty(example = "59")
    long totalCount;
    @JsonProperty("items")
    List<ProductDisplayInfo> Products;

    @Nonnull
    public static ProductsApiResponse createProductsApiResponse(long totalCount, @Nonnull List<ProductDisplayInfo> products) {
        ProductsApiResponse productsApiResponse = new ProductsApiResponse();
        productsApiResponse.setTotalCount(totalCount);
        productsApiResponse.setProducts(products);
        return productsApiResponse;
    }
}
