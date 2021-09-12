package kr.or.connect.reservation.presentation.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.reservation.core.presentation.dto.response.ReservationPostApiResponse;
import kr.or.connect.reservation.infrastructure.exception.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@ApiIgnore
public class ReservationController {

    @GetMapping(path = "/htmls/mainpage.html")
    public String showMainPage(HttpSession session) {
        log.debug("mainpage.html page called");
        return "mainpage";
    }

    @GetMapping(path = "/htmls/detail.html")
    public String showDetailPage(HttpSession session) {
        log.debug("detail.html page called");
        return "detail";
    }

    @GetMapping(path = "/htmls/reserve.html")
    public String showReservePage(HttpSession session) {
        log.debug("reserve.html page called");
        return "reserve";
    }
}
