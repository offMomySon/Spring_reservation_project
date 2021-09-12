package kr.or.connect.reservation.core.presentation.dto;

import io.swagger.annotations.ApiModelProperty;
import kr.or.connect.reservation.core.presentation.domain.DisplayInfo;
import kr.or.connect.reservation.core.presentation.domain.DisplayInfoImage;
import kr.or.connect.reservation.core.presentation.domain.FileInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DisplayInfoImageResult {
    @ApiModelProperty(example = "1")
    private long displayInfoImageId;
    @ApiModelProperty(example = "1")
    private long displayInfoId;
    @ApiModelProperty(example = "1")
    private long fileId;
    @ApiModelProperty(example = "1_map_1.png")
    private String fileName;
    @ApiModelProperty(example = "img_map/1_map_1.png")
    private String saveFileName;
    @ApiModelProperty(example = "image/png")
    private String contentType;
    @ApiModelProperty(example = "false")
    private Boolean deleteFlag;
    @ApiModelProperty(example = "2020-08-11T01:02:45")
    private LocalDateTime createDate;
    @ApiModelProperty(example = "2020-08-11T01:02:45")
    private LocalDateTime modifyDate;

    public DisplayInfoImageResult(long displayInfoImageId, long displayInfoId, long fileId, String fileName,
                                  String saveFileName, String contentType, Boolean deleteFlag, LocalDateTime createDate, LocalDateTime modifyDate) {
        this.displayInfoImageId = displayInfoImageId;
        this.displayInfoId = displayInfoId;
        this.fileId = fileId;
        this.fileName = fileName;
        this.saveFileName = saveFileName;
        this.contentType = contentType;
        this.deleteFlag = deleteFlag;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public static DisplayInfoImageResult makeDisplayInfoImageResult(DisplayInfo displayInfo, DisplayInfoImage displayInfoImage, FileInfo fileInfo) {
        return new DisplayInfoImageResult(displayInfoImage.getId(), displayInfo.getId(),
                fileInfo.getId(), fileInfo.getFileName(), fileInfo.getSaveFileName(), fileInfo.getContentType(),
                fileInfo.getDeleteFlag(), fileInfo.getCreateDate(), fileInfo.getModifyDate());
    }
}
