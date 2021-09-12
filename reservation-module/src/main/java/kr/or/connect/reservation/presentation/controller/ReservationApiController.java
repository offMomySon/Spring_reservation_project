package kr.or.connect.reservation.presentation.controller;

import io.swagger.annotations.*;
import kr.or.connect.reservation.application.service.ReservationService;
import kr.or.connect.reservation.core.presentation.dto.Price;
import kr.or.connect.reservation.core.presentation.dto.ReservationCancleResult;
import kr.or.connect.reservation.core.presentation.dto.ReservationRequestResult;
import kr.or.connect.reservation.core.presentation.dto.ReservationResponseResult;
import kr.or.connect.reservation.core.presentation.dto.request.ReservationRequest;
import kr.or.connect.reservation.core.presentation.dto.response.ReservationCancleResponse;
import kr.or.connect.reservation.core.presentation.dto.response.ReservationGetApiResponse;
import kr.or.connect.reservation.core.presentation.dto.response.ReservationPostApiResponse;
import kr.or.connect.reservation.infrastructure.exception.ApiErrorResponse;
import kr.or.connect.reservation.infrastructure.exception.list.ParamNotValidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.servlet.http.HttpSession;
import java.util.List;

import static kr.or.connect.reservation.core.presentation.dto.response.ReservationCancleResponse.createReservationCancleResponse;
import static kr.or.connect.reservation.core.presentation.dto.response.ReservationGetApiResponse.createReservationGetApiResponse;
import static kr.or.connect.reservation.core.presentation.dto.response.ReservationPostApiResponse.createReservationPostApiResponse;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/reservations")
@Api(tags = "예약 API")
public class ReservationApiController {
    final private ReservationService reservationService;

    @ApiOperation(value = "예약하기", notes = "성공시 예약생성.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success, reservation.", response = ReservationPostApiResponse.class),
            @ApiResponse(code = 400, message = "Fail, reservation", response = ApiErrorResponse.class),
    })
    @PostMapping
    public ResponseEntity<?> postBook(@RequestBody ReservationRequest reservationRequest) {
        ReservationRequestResult reservationRequestResult = reservationService.addReservation(reservationRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(createReservationPostApiResponse(reservationRequestResult));
    }

    private boolean isNotRequestPriceListValid(@Nonnull List<Price> prices) {
        prices.removeIf((Price price) -> isPriceCountInvalid(price));
        if (prices.isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isPriceCountInvalid(@ParametersAreNonnullByDefault Price price) {
        if (price.getCount() > 0) {
            return false;
        }
        return true;
    }

    @ApiOperation(value = "예약정보 조회", notes = "성공시 예약정보를 가져옵니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success, reservation get.", response = ReservationGetApiResponse.class),
            @ApiResponse(code = 400, message = "Fail, reservation get", response = ApiErrorResponse.class),
    })
    @GetMapping
    public ResponseEntity<?> getBook(@ApiParam(value = "test@naver.com ", required = true)
                                         @RequestParam(required = true) String reservationEmail, @ApiIgnore HttpSession session) {
        List<ReservationResponseResult> reservationResponseResults = reservationService.getReservation(reservationEmail);

        session.setAttribute("reservationEmail", reservationEmail);

        return ResponseEntity.ok().body(createReservationGetApiResponse(reservationResponseResults));
    }

    @ApiOperation(value = "예약취소", notes = "성공시 예약취소.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success, reservation cancle.", response = ReservationCancleResponse.class),
            @ApiResponse(code = 400, message = "Fail, reservation cancle", response = ApiErrorResponse.class),
    })
    @PutMapping(path = "/{reservationId}")
    public ResponseEntity<?> cancleBook(@PathVariable long reservationId) {
        if (isNotCancleBookParamValid(reservationId)) {
            throw new ParamNotValidException();
        }

        ReservationCancleResult reservationCancleResult = reservationService.cancleReservation(reservationId);
        List<Price> prices = reservationService.selectPriceList(reservationId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createReservationCancleResponse(reservationCancleResult, prices));
    }

    private boolean isNotCancleBookParamValid(long reservationId) {
        if (reservationId < 0) {
            return true;
        }
        return false;
    }
}
