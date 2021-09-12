package kr.or.connect.reservation.core.presentation.dto;

import io.swagger.annotations.ApiModelProperty;
import kr.or.connect.reservation.core.presentation.domain.FileInfo;
import kr.or.connect.reservation.core.presentation.domain.ProductImage;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImageResult {
    @ApiModelProperty(example = "1")
    private long productId;
    @ApiModelProperty(example = "2")
    private long productImageId;
    @ApiModelProperty(example = "ma")
    private String type;
    @ApiModelProperty(example = "61")
    private long fileInfoId;
    @ApiModelProperty(example = "1_ma_2.png")
    private String fileName;
    @ApiModelProperty(example = "img/1_ma_2.png")
    private String saveFileName;
    @ApiModelProperty(example = "image/png")
    private String contentType;
    @ApiModelProperty(example = "false")
    private Boolean deleteFlag;
    @ApiModelProperty(example = "2020-08-11T01:02:46")
    private LocalDateTime createDate;
    @ApiModelProperty(example = "2020-08-11T01:02:46")
    private LocalDateTime modifyDate;

    public ProductImageResult(long productId, long productImageId, String type, long fileInfoId, String fileName,
                              String saveFileName, String contentType, Boolean deleteFlag, LocalDateTime createDate, LocalDateTime modifyDate) {
        super();
        this.productId = productId;
        this.productImageId = productImageId;
        this.type = type;
        this.fileInfoId = fileInfoId;
        this.fileName = fileName;
        this.saveFileName = saveFileName;
        this.contentType = contentType;
        this.deleteFlag = deleteFlag;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public static ProductImageResult makeProductImageResult(@Nonnull ProductImage productImage, FileInfo fileInfo) {
        return new ProductImageResult(
                productImage.getProduct().getId(),
                productImage.getId(),
                productImage.getType(),
                fileInfo.getId(), fileInfo.getFileName(), fileInfo.getSaveFileName(), fileInfo.getContentType(),
                fileInfo.getDeleteFlag(), fileInfo.getCreateDate(), fileInfo.getModifyDate());
    }
}
