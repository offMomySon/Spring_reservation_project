package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.PromotionResult;
import kr.or.connect.reservation.model.ProductImage;
import kr.or.connect.reservation.model.Promotion;
import kr.or.connect.reservation.repository.ProductImageRepository;
import kr.or.connect.reservation.repository.PromotionRepository;
import kr.or.connect.reservation.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static kr.or.connect.reservation.dto.PromotionResult.makePromotionResult;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private PromotionRepository promotionRepository;

    @Nonnull
    @Override
    public List<PromotionResult> getPromotionList() {
        List<PromotionResult> promotionResults = new ArrayList();

        List<ProductImage> productImages = productImageRepository.findByType("th");
        for (ProductImage productImage : productImages) {
            List<Promotion> promotions = promotionRepository.findByProductId(productImage.getProduct().getId());

            for (Promotion promotion : promotions) {
                promotionResults.add(makePromotionResult(promotion.getId(), productImage.getProduct().getId(), productImage.getFileInfo().getSaveFileName()));
            }
        }
        return promotionResults;
    }
}
