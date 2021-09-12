package kr.or.connect.reservation.core.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import kr.or.connect.reservation.core.presentation.domain.ReservationInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationResponseResult {
    @ApiModelProperty(example = "33")
    private long reservationInfoId;

    @ApiModelProperty(example = "1")
    private long productId;

    @ApiModelProperty(example = "1")
    private long displayInfoId;

    @ApiModelProperty(example = "testUser")
    private String reservationName;

    @ApiModelProperty(example = "010-1234-1234")
    @JsonProperty("reservationTelephone")
    private String reservationTel;

    @ApiModelProperty(example = "testUser@naver.com")
    private String reservationEmail;

    @ApiModelProperty(example = "2020-09-05T06:51:23")
    private LocalDateTime reservationDate;

    @ApiModelProperty(example = "false")
    @JsonProperty("cancelYn")
    private Boolean cancelFlag;

    @ApiModelProperty(example = "2020-09-05T06:51:23")
    private LocalDateTime createDate;

    @ApiModelProperty(example = "2020-09-05T06:51:23")
    private LocalDateTime modifyDate;

    private DisplayInfoResult displayInfoResult;

    @ApiModelProperty(example = "15000")
    private Long totalPrice;

    public ReservationResponseResult(long reservationInfoId, long productId, long displayInfoId, String reservationName,
                                     String reservationTel, String reservationEmail, LocalDateTime reservationDate, Boolean cancelFlag, LocalDateTime createDate,
                                     LocalDateTime modifyDate, DisplayInfoResult displayInfoResult, Long totalPrice) {
        super();
        this.reservationInfoId = reservationInfoId;
        this.productId = productId;
        this.displayInfoId = displayInfoId;
        this.reservationName = reservationName;
        this.reservationTel = reservationTel;
        this.reservationEmail = reservationEmail;
        this.reservationDate = reservationDate;
        this.cancelFlag = cancelFlag;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.displayInfoResult = displayInfoResult;
        this.totalPrice = totalPrice;
    }

    public static ReservationResponseResult makeReservationResponseResult(@Nonnull ReservationInfo reservationInfo, @Nonnull DisplayInfoResult displayInfoResult, @Nonnull Long totalPrice) {
        ReservationResponseResult reservationResponseResult = new ReservationResponseResult(
                reservationInfo.getId(),
                reservationInfo.getProduct().getId(),
                reservationInfo.getDisplayInfo().getId(),
                reservationInfo.getReservationName(),
                reservationInfo.getReservationTel(),
                reservationInfo.getReservationEmail(),
                reservationInfo.getReservationDate(),
                reservationInfo.getCancelFlag(),
                reservationInfo.getCreateDate(),
                reservationInfo.getModifyDate(),
                displayInfoResult,
                totalPrice
        );

        return reservationResponseResult;
    }
}
