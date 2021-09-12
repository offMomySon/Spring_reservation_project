package kr.or.connect.reservation.presentation.controller;

import io.swagger.annotations.*;
import kr.or.connect.reservation.application.service.DisplayInfoService;
import kr.or.connect.reservation.application.service.ProductService;
import kr.or.connect.reservation.core.presentation.dto.response.ProductsApiResponse;
import kr.or.connect.reservation.core.presentation.dto.response.PromotionApiResponse;
import kr.or.connect.reservation.infrastructure.exception.ApiErrorResponse;
import kr.or.connect.reservation.infrastructure.exception.list.ParamNotValidException;
import kr.or.connect.reservation.core.presentation.dto.ProductDisplayInfoResult;
import kr.or.connect.reservation.core.presentation.dto.response.ProductsApiAtDisplayInfoIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kr.or.connect.reservation.core.presentation.dto.response.ProductsApiResponse.createProductsApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/products")
@Api(tags = "상품 API")
public class ProductApiController {
    final private ProductService productService;
    final private DisplayInfoService displayInfoService;

    @ApiOperation(value = "상품 목록 구하기", notes = "성공시 상품 목록을 가져옵니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success, get product list", response = ProductsApiResponse.class),
            @ApiResponse(code = 400, message = "Fail, get product list", response = ApiErrorResponse.class),
    })
    @Cacheable(cacheNames = "product_cache")
    @GetMapping
    public ResponseEntity<?> getProduct(@ApiParam(value = "0", required = false)
                                            @RequestParam(defaultValue = "0") long categoryId,
                                        @ApiParam(value = "0", required = false)
                                        @RequestParam(defaultValue = "0") long start) {
        if (isNotProductParamValid(categoryId, start)) {
            throw new ParamNotValidException();
        }

        ProductDisplayInfoResult productDisplayInfo = productService.getProductDisplayInfo(categoryId, start);

        return ResponseEntity.ok().body(createProductsApiResponse(productDisplayInfo.getProductDisplaySize(), productDisplayInfo.getProductDisplayInfos()));
    }

    private boolean isNotProductParamValid(long categoryId, long start) {
        if (categoryId < 0 || start < 0) {
            return true;
        }
        return false;
    }

    @ApiOperation(value = "상품 전시 정보 구하기", notes = "성공시 상품 전시 정보를 가져옵니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success, get displayInfo.", response = ProductsApiAtDisplayInfoIdResponse.class),
            @ApiResponse(code = 400, message = "Fail, get displayInfo.", response = ApiErrorResponse.class),
    })
    @GetMapping(path = "/{displayInfoId}")
    public ResponseEntity<?> getDisplayInfo(@ApiParam(value = "0", required = true) @PathVariable long displayInfoId) {
        if (isNotGetDispalyInfoParamValid(displayInfoId)) {
            throw new ParamNotValidException();
        }

        ProductsApiAtDisplayInfoIdResponse productsApiAtDisplayInfoIdResponse = displayInfoService.getDisplayInfo(displayInfoId);

        return ResponseEntity.ok().body(productsApiAtDisplayInfoIdResponse);
    }

    private boolean isNotGetDispalyInfoParamValid(long displayInfoId) {
        if (displayInfoId < 0) {
            return true;
        }
        return false;
    }
}
