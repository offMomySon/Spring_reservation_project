package kr.or.connect.reservation.core.presentation.dto;

import io.swagger.annotations.ApiModelProperty;
import kr.or.connect.reservation.core.presentation.domain.ReservationInfo;
import kr.or.connect.reservation.core.presentation.domain.ReservationUserComment;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResult {
    @ApiModelProperty(example = "1")
    private Long commentId;
    @ApiModelProperty(example = "1")
    private Long productId;
    @ApiModelProperty(example = "1")
    private Long reservationInfoId;
    @ApiModelProperty(example = "4.0")
    private Double score;
    @ApiModelProperty(example = "좋았어요.")
    private String comment;
    @ApiModelProperty(example = "김진수")
    private String reservationName;
    @ApiModelProperty(example = "010-0000-0001")
    private String reservationTelephone;
    @ApiModelProperty(example = "kimjinsu@naver.com")
    private String reservationEmail;
    @ApiModelProperty(example = "2020-08-11T01:02:49")
    private LocalDateTime reservationDate;
    @ApiModelProperty(example = "2020-08-11T01:02:49")
    private LocalDateTime createDate;
    @ApiModelProperty(example = "2020-08-11T01:02:49")
    private LocalDateTime modifyDate;
    private List<CommentImageResult> commentImageResults;

    public CommentResult(Long commentId, Long productId, Long reservationInfoId, Double score, String comment,
                         String reservationName, String reservationTelephone, String reservationEmail, LocalDateTime reservationDate,
                         LocalDateTime createDate, LocalDateTime modifyDate) {
        super();
        this.commentId = commentId;
        this.productId = productId;
        this.reservationInfoId = reservationInfoId;
        this.score = score;
        this.comment = comment;
        this.reservationName = reservationName;
        this.reservationTelephone = reservationTelephone;
        this.reservationEmail = reservationEmail;
        this.reservationDate = reservationDate;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public static CommentResult makeCommentResult(@Nonnull ReservationInfo reservationInfo, ReservationUserComment reservationUserComment) {
        return new CommentResult(reservationUserComment.getId(), reservationInfo.getProduct().getId(), reservationInfo.getId(),
                reservationUserComment.getScore(), reservationUserComment.getComment(),
                reservationInfo.getReservationName(), reservationInfo.getReservationTel(), reservationInfo.getReservationEmail(), reservationInfo.getReservationDate(),
                reservationUserComment.getCreateDate(), reservationUserComment.getModifyDate());
    }
}
