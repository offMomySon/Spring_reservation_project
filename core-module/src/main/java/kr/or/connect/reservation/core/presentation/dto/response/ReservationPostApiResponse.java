package kr.or.connect.reservation.core.presentation.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kr.or.connect.reservation.core.presentation.dto.Price;
import kr.or.connect.reservation.core.presentation.dto.ReservationRequestResult;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.List;

@ApiModel("예약하기 응답")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationPostApiResponse {
    @ApiModelProperty(example = "38")
    private long reservationInfoId;
    @ApiModelProperty(example = "1")
    private long productId;
    @ApiModelProperty(example = "1")
    private long displayInfoId;
    @ApiModelProperty(example = "testUser")
    private String reservationName;
    @ApiModelProperty(example = "010-1234-1234")
    private String reservationTelephone;
    @ApiModelProperty(example = "testUser@naver.com")
    private String reservationEmail;
    @ApiModelProperty(example = "2021-04-12T12:16:09.176Z")
    private LocalDateTime reservationDate;
    @ApiModelProperty(example = "false")
    private Boolean cancelYn;
    @ApiModelProperty(example = "2021-04-12T12:16:09.176Z")
    private LocalDateTime createDate;
    @ApiModelProperty(example = "2021-04-12T12:16:09.176Z")
    private LocalDateTime modifyDate;

    private List<Price> prices;

    public ReservationPostApiResponse(long reservationInfoId, long productId, long displayInfoId, String reservationName,
                                      String reservationTel, String reservationEmail, LocalDateTime reservationDate, Boolean cancelFlag, LocalDateTime createDate,
                                      LocalDateTime modifyDate, List<Price> prices) {
        this.reservationInfoId = reservationInfoId;
        this.productId = productId;
        this.displayInfoId = displayInfoId;
        this.reservationName = reservationName;
        this.reservationTelephone = reservationTel;
        this.reservationEmail = reservationEmail;
        this.reservationDate = reservationDate;
        this.cancelYn = cancelFlag;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.prices = prices;
    }

    public static ReservationPostApiResponse createReservationPostApiResponse(@Nonnull ReservationRequestResult reservationRequestResult) {
        ReservationPostApiResponse reservationPostApiResponse = new ReservationPostApiResponse(
                reservationRequestResult.getReservationInfoId(),
                reservationRequestResult.getProductId(),
                reservationRequestResult.getDisplayInfoId(),
                reservationRequestResult.getReservationName(),
                reservationRequestResult.getReservationTel(),
                reservationRequestResult.getReservationEmail(),
                reservationRequestResult.getReservationDate(),
                reservationRequestResult.getCancelFlag(),
                reservationRequestResult.getCreateDate(),
                reservationRequestResult.getModifyDate(),
                reservationRequestResult.getPrices()
        );
        return reservationPostApiResponse;
    }
}
