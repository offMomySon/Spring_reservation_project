package kr.or.connect.reservation.core.presentation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDisplayInfo {
    @ApiModelProperty(example = "1")
    private Long displayInfoId;
    @ApiModelProperty(example = "1")
    private Long productId;
    @ApiModelProperty(example = "Paper, Present:너를 위한 선물")
    private String productDescription;
    @ApiModelProperty(example = "대림미술관")
    private String placeName;
    @ApiModelProperty(example = "대림미술관은 오는 12월 7일부터 2018년 5월 27일까지 세계적인 아티스트들의 섬세한 감각과 아날로그적 소재인 종이가 만나 감성적인 매체로 확장되는 과정을 소개하는 전시 〈PAPER, PRESENT: 너를 위한 선물〉을 개최합니다. 이번 전시에서...")
    private String productContent;
    @ApiModelProperty(example = "img/1_th_1.png")
    private String productImageUrl;

    public ProductDisplayInfo(Long displayInfoId, Long productId, String productDescription, String placeName,
                              String productContent, String productImageUrl) {
        super();
        this.displayInfoId = displayInfoId;
        this.productId = productId;
        this.productDescription = productDescription;
        this.placeName = placeName;
        this.productContent = productContent;
        this.productImageUrl = productImageUrl;
    }

    public static ProductDisplayInfo makeProductResult(Long displayInfoId, Long productId, String productDescription, String placeName, String productContent, String productImageUrl) {
        ProductDisplayInfo productDisplayInfo = new ProductDisplayInfo(
                displayInfoId,
                productId,
                productDescription,
                placeName,
                productContent,
                productImageUrl
        );
        return productDisplayInfo;
    }
}
