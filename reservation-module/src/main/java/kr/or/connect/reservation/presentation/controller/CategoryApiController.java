package kr.or.connect.reservation.presentation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.reservation.application.service.CategoryService;
import kr.or.connect.reservation.core.presentation.dto.CategoryResult;
import kr.or.connect.reservation.core.presentation.dto.response.CategoryApiResponse;
import kr.or.connect.reservation.core.presentation.dto.response.ProductsApiResponse;
import kr.or.connect.reservation.infrastructure.exception.ApiErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static kr.or.connect.reservation.core.presentation.dto.response.CategoryApiResponse.createCategoryApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/categories")
@Api(tags = "카테고리 API")
public class CategoryApiController {
    final private CategoryService categoryService;

    @ApiOperation(value = "카테고리 목록 구하기", notes = "성공시 카테고리 목록을 가져옵니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success, get category list", response = CategoryApiResponse.class),
            @ApiResponse(code = 400, message = "Fail, get category list", response = ApiErrorResponse.class),
    })
    @Cacheable(cacheNames = "category_list_cache")
    @GetMapping
    public ResponseEntity<?> getCategoryItems() {
        List<CategoryResult> categoryResults = categoryService.getCategoryList();
        return ResponseEntity.ok().body(createCategoryApiResponse(categoryResults));
    }
}