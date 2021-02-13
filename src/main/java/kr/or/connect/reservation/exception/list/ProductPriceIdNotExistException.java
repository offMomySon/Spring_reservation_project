package kr.or.connect.reservation.exception.list;

import kr.or.connect.reservation.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ProductPriceIdNotExistException extends ApiCommonException {
    private long productPriceId;

    public ProductPriceIdNotExistException(long productPriceId) {
        super(ErrorCode.PRODUCT_PRICE_ID_NOT_EXIST);
        this.productPriceId = productPriceId;
    }
}

