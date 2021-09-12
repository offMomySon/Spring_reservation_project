package kr.or.connect.reservation.core.presentation.dto;

import io.swagger.annotations.ApiModelProperty;
import kr.or.connect.reservation.core.presentation.domain.FileInfo;
import kr.or.connect.reservation.core.presentation.domain.ReservationUserComment;
import kr.or.connect.reservation.core.presentation.domain.ReservationUserCommentImage;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentImageResult {
    @ApiModelProperty(example = "1")
    private Long imageId;
    @ApiModelProperty(example = "1")
    private Long reservationInfoId;
    @ApiModelProperty(example = "1")
    private Long reservationUserCommentId;
    @ApiModelProperty(example = "1")
    private Long fileId;
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

    public CommentImageResult(Long imageId, Long reservationInfoId, Long reservationUserCommentId, Long fileId,
                              String fileName, String saveFileName, String contentType, Boolean deleteFlag, LocalDateTime createDate,
                              LocalDateTime modifyDate) {
        super();
        this.imageId = imageId;
        this.reservationInfoId = reservationInfoId;
        this.reservationUserCommentId = reservationUserCommentId;
        this.fileId = fileId;
        this.fileName = fileName;
        this.saveFileName = saveFileName;
        this.contentType = contentType;
        this.deleteFlag = deleteFlag;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }


    public static CommentImageResult makeCommentImageResult(@Nonnull ReservationUserComment reservationUserComment, ReservationUserCommentImage reservationUserCommentImage, FileInfo fileInfo) {
        return new CommentImageResult(
                reservationUserCommentImage.getId(),
                reservationUserComment.getReservationInfo().getId(),
                reservationUserComment.getId(),
                fileInfo.getId(),
                fileInfo.getFileName(),
                fileInfo.getSaveFileName(),
                fileInfo.getContentType(),
                fileInfo.getDeleteFlag(),
                fileInfo.getCreateDate(),
                fileInfo.getModifyDate());
    }
}
