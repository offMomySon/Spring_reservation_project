package kr.or.connect.reservation.core.presentation.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PromotionResult {
    @ApiModelProperty(example = "5")
    private long id;
    @ApiModelProperty(example = "11")
    private long productId;
    @ApiModelProperty(example = "11_th_30.png")
    private String productImageUrl;
}
