package kr.or.connect.reservation.core.presentation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Price {

    @ApiModelProperty(example = "86", required = true)
    private Long reservationInfoPriceId;
    @ApiModelProperty(example = "38", required = true)
    private Long reservationInfoId;
    @ApiModelProperty(example = "3", required = true)
    private Long productPriceId;
    @ApiModelProperty(example = "3", required = true)
    private Long count;

    public Price(Long reservationInfoPriceId, Long reservationInfoId, Long productPriceId, Long count) {
        this.reservationInfoPriceId = reservationInfoPriceId;
        this.reservationInfoId = reservationInfoId;
        this.productPriceId = productPriceId;
        this.count = count;
    }
}
