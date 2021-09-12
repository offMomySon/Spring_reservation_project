package kr.or.connect.reservation.core.presentation.dto;

import io.swagger.annotations.ApiModelProperty;
import kr.or.connect.reservation.core.presentation.domain.Category;
import kr.or.connect.reservation.core.presentation.domain.DisplayInfo;
import kr.or.connect.reservation.core.presentation.domain.Product;
import kr.or.connect.reservation.core.presentation.domain.ReservationInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DisplayInfoResult {
    @ApiModelProperty(example = "1")
    private long productId;
    @ApiModelProperty(example = "1")
    private long categoryId;
    @ApiModelProperty(example = "1")
    private long displayInfoId;
    @ApiModelProperty(example = "전시")
    private String categoryName;
    @ApiModelProperty(example = "Paper, Present:너를 위한 선물")
    private String productDescription;
    @ApiModelProperty(example = "대림미술관은 오는 12월 7일부터 2018년 5월 27일까지 세계적인 아티스트들의 섬세한 감각과 아날로그적 소재인 종이가 만나 감성적인 매체로 확장되는 과정을 소...")
    private String productContent;
    @ApiModelProperty(example = "None")
    private String productEvent;
    @ApiModelProperty(example = "대림미술관")
    private String placeName;
    @ApiModelProperty(example = "서울특별시 종로구 통의동 35-1")
    private String placeLot;
    @ApiModelProperty(example = "서울특별시 종로구 자하문로4길 21 대림미술관")
    private String placeStreet;
    @ApiModelProperty(example = "02-6403-9961")
    private String telephone;
    @ApiModelProperty(example = "daelimmuseum.org")
    private String homepage;
    @ApiModelProperty(example = "")
    private String email;
    @ApiModelProperty(example = "2020-09-05T06:51:23")
    private LocalDateTime createDate;
    @ApiModelProperty(example = "2020-08-11T01:02:45")
    private LocalDateTime modifyDate;
    @ApiModelProperty(example = "13:00")
    private String openingHours;

    public DisplayInfoResult(long productId, long categoryId, long displayInfoId, String categoryName,
                             String productDescription, String productContent, String productEvent, String placeName, String placeLot,
                             String placeStreet, String telephone, String homepage, String email, LocalDateTime createDate, LocalDateTime modifyDate,
                             String openingHours) {
        super();
        this.productId = productId;
        this.categoryId = categoryId;
        this.displayInfoId = displayInfoId;
        this.categoryName = categoryName;
        this.productDescription = productDescription;
        this.productContent = productContent;
        this.productEvent = productEvent;
        this.placeName = placeName;
        this.placeLot = placeLot;
        this.placeStreet = placeStreet;
        this.telephone = telephone;
        this.homepage = homepage;
        this.email = email;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.openingHours = openingHours;
    }

    public static DisplayInfoResult makeDisplayInfoResult(@Nonnull ReservationInfo reservationInfo) {
        Product product = reservationInfo.getProduct();
        DisplayInfo displayInfo = reservationInfo.getDisplayInfo();
        Category category = product.getCategory();

        return new DisplayInfoResult(
                product.getId(), category.getId(), displayInfo.getId(),
                category.getName(), product.getDescription(), product.getContent(),
                product.getEvent(), displayInfo.getPlaceName(), displayInfo.getPlaceLot(),
                displayInfo.getPlaceStreet(), displayInfo.getTel(), displayInfo.getHomepage(), displayInfo.getEmail(),
                displayInfo.getCreateDate(), displayInfo.getModifyDate(), displayInfo.getOpeningHoursComment());
    }

    public static DisplayInfoResult makeDisplayInfoResult(DisplayInfo displayInfo, Product product, Category category) {
        return new DisplayInfoResult(
                product.getId(), category.getId(), displayInfo.getId(),
                category.getName(), product.getDescription(), product.getContent(),
                product.getEvent(), displayInfo.getPlaceName(), displayInfo.getPlaceLot(),
                displayInfo.getPlaceStreet(), displayInfo.getTel(), displayInfo.getHomepage(), displayInfo.getEmail(),
                displayInfo.getCreateDate(), displayInfo.getModifyDate(), displayInfo.getOpeningHoursComment());
    }
}
