package kr.or.connect.reservation.presentation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.reservation.core.presentation.dto.PromotionResult;
import kr.or.connect.reservation.application.service.PromotionService;
import kr.or.connect.reservation.core.presentation.dto.response.PromotionApiResponse;
import kr.or.connect.reservation.core.presentation.dto.response.ReservationPostApiResponse;
import kr.or.connect.reservation.infrastructure.exception.ApiErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static kr.or.connect.reservation.core.presentation.dto.response.PromotionApiResponse.createPromotionApiResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/promotions")
@Api(tags = "프로모션 API")
public class PromotionApiController {
    final private PromotionService promotionService;

    @ApiOperation(value = "프로모션 목록 구하기", notes = "성공시 프로모션 목록을 가져옵니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success, reservation.", response = PromotionApiResponse.class),
            @ApiResponse(code = 400, message = "Fail, reservation", response = ApiErrorResponse.class),
    })
    @GetMapping
    public ResponseEntity<?> promotionItems() {
        List<PromotionResult> promotionResults = promotionService.getPromotionList();

        return ResponseEntity.ok().body(createPromotionApiResponse(promotionResults));
    }
}
