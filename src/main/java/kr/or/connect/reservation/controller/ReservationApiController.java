package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationRequestRs;
import kr.or.connect.reservation.dto.ReservationResponseRs;
import kr.or.connect.reservation.exception.ApiErrorResponse;
import kr.or.connect.reservation.exception.RsvRqtPricesNotExistExceiption;
import kr.or.connect.reservation.model.ReservationInfo;
import kr.or.connect.reservation.model.ReservationInfoPrice;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@Slf4j
@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {

	@Autowired
	private ReservationService rsvService;
	@Autowired
	private DisplayInfoService displayInfoService;

	@PostMapping
	public ResponseEntity<?> postBook(@RequestBody ReservationRequestRs rsvRequest) {		
		if (isNotPostBookParamValid(rsvRequest)) {
			throw new RsvRqtPricesNotExistExceiption(rsvRequest);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(rsvService.addReservation(rsvRequest));
	}

	private boolean isNotPostBookParamValid(@Nonnull ReservationRequestRs rsvRequest) {
		if (rsvRequest.getReservationInfoId() < 0 || rsvRequest.getProductId() < 0
				|| rsvRequest.getReservationEmail() == null || rsvRequest.getReservationName() == null
				|| rsvRequest.getReservationTel() == null || rsvRequest.getReservationDate() == null) {
			return true;
		}

		if (isNotRequestPriceListValid(rsvRequest.getPrices())) {
			return true;
		}

		return false;
	}

	private boolean isNotRequestPriceListValid(@Nonnull List<Price> priceList) {
		priceList.removeIf((Price price) -> isPriceCountInvalid(price));

		if (priceList.isEmpty()) {
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

	@GetMapping
	public ResponseEntity<?> getBook(@RequestParam(required = true) String reservationEmail, HttpSession session) {
		List<ReservationResponseRs> rsvResponseList = makeRsvResponseRsList(rsvService.getReservation(reservationEmail));
		if(isNotRsvResponseRsListValid(rsvResponseList)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse("error-0012", "getBook api result is not exist."));
		}
		
		Map<String, Object> rsvMap = new HashMap<>();
		rsvMap.put("reservations", rsvResponseList);
		rsvMap.put("size", rsvResponseList.size());

		storeEmailInfoIfNeeded(rsvResponseList, session, reservationEmail);

		return ResponseEntity.ok().body(rsvMap);
	}
	
	@Nonnull
	private List<ReservationResponseRs> makeRsvResponseRsList(@Nonnull List<ReservationInfo> rsvInfoList) {
		List<ReservationResponseRs> responseList = new ArrayList();
		
		for (ReservationInfo rsvInfo : rsvInfoList) {
			ReservationResponseRs requestRs = new ReservationResponseRs(rsvInfo.getId(), rsvInfo.getProductId(),
					rsvInfo.getDisplayInfoId(), rsvInfo.getReservationName(), rsvInfo.getReservationTel(),
					rsvInfo.getReservationEmail(), rsvInfo.getReservationDate(), rsvInfo.getCancelFlag(),
					rsvInfo.getCreateDate(), rsvInfo.getModifyDate());

			requestRs.setDisplayInfo(displayInfoService.getDisplayInfo(rsvInfo.getDisplayInfoId()));
			requestRs.setTotalPrice(rsvService.getRsvTicketTotalPrice(rsvInfo.getId()));

			responseList.add(requestRs);
		}
		
		return responseList;
	}
	
	private boolean isNotRsvResponseRsListValid(@Nonnull List<ReservationResponseRs> responseList) {
		if(responseList.isEmpty()) {
			return true;
		}
		return false;
	}
	
	
	private void storeEmailInfoIfNeeded(@ParametersAreNonnullByDefault List<ReservationResponseRs> responseList,
			@ParametersAreNonnullByDefault HttpSession session,
			@ParametersAreNonnullByDefault String reservationEmail) {
		if (responseList.isEmpty()) {
			return;
		}
		session.setAttribute("rsvEmail", reservationEmail);
	}
	
	@PutMapping(path = "/{reservationId}")
	public ResponseEntity<?> cancleBook(@PathVariable long reservationId) {
		if(isNotCancleBookParamValid(reservationId)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiErrorResponse("error-0010", "The parameter entered is invalid. at cancleBook method"));
		}

		ReservationRequestRs rsvRequestRs = rsvService.cancleReservation(reservationId);

		if (isNotReservationIdValid(rsvRequestRs)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse("error-0011", "cancleBook api result is not exist."));
		}

		List<Price> priceList = rsvService.selectPriceList(reservationId);
		if(isNotPriceListValid(priceList)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse("error-0011", "cancleBook api result is not exist."));
		}
		
		rsvRequestRs.setPrices(priceList);

		return ResponseEntity.status(HttpStatus.CREATED).body(rsvRequestRs);
	}
	
	private boolean isNotCancleBookParamValid(long reservationId) {
		if (reservationId < 0) {
			return true;
		}
		return false;
	}
	
	private boolean isNotReservationIdValid(@Nonnull ReservationRequestRs rsvRequestRs) {
		if (rsvRequestRs.getReservationInfoId() < 0) {
			return true;
		}
		return false;
	}
	
	private boolean isNotPriceListValid(List<Price> priceList) {
		if(priceList.isEmpty()) {
			return true;
		}
		return false;
	}
}
