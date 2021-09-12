package kr.or.connect.reservation.core.presentation.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import kr.or.connect.reservation.core.presentation.dto.Price;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel("예약 생성 요청")
public class ReservationRequest {

    @ApiModelProperty(example = "0", required = true)
    private long displayInfoId;

    @ApiModelProperty(example = "0", required = true)
    private long productId;

    private List<Price> prices;

    @ApiModelProperty(example = "test@naver.com")
    @NotBlank(message = "email is mandatory")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
    private String reservationEmail;

    @ApiModelProperty(example = "")
    @NotBlank(message = "testUserName")
    private String reservationName;

    @ApiModelProperty(example = "string")
    @NotBlank(message = "telephone is mandatory")
    private String reservationTelephone;

    @ApiModelProperty(example = "2020.06.10")
    @NotBlank(message = "day is mandatory")
    private LocalDateTime reservationYearMonthDay;
}
