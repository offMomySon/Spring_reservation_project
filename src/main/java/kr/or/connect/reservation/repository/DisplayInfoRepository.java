package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.DisplayInfo;
import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.model.ReservationInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.annotation.Nonnull;
import java.util.List;

public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Long> {

	@Query("SELECT "
			+ "pd "
			+ "FROM Product pd "
			+ "JOIN pd.displayInfos di "
			+ "JOIN pd.productPrices pp "
			+ "WHERE di.id = ?1 "
			+ "ORDER BY pp.id DESC")
	List<Product> selectProductPrice(long displayInfoId);

	@Query("SELECT "
			+ "ruc.score "
			+ "FROM Product pd "
			+ "JOIN pd.displayInfos di "
			+ "JOIN pd.reservationUserComments ruc "
			+ "WHERE di.id = ?1")
	List<Double> selectScore(long displayInfoId);

	@Nonnull
	@Query("SELECT "
			+ "ri "
			+ "FROM ReservationInfo ri "
			+ "JOIN ri.userComments ruc "
			+ "JOIN ruc.reservationUserCommentImages ruci "
			+ "JOIN ruci.fileInfo fi "
			+ "WHERE ruc.id = ?1")
	List<ReservationInfo> selectCommentImageList(long userCommentId);
	
	@Query("SELECT "
			+ "pd "
			+ "FROM Product pd "
			+ "JOIN pd.displayInfos di "
			+ "JOIN pd.reservationInfos ri "
			+ "JOIN ri.userComments ruc "
			+ "Where di.id = ?1 "
			+ "ORDER BY ruc.id DESC")
	List<Product> selectComment(long displayInfoId);

	@Query("SELECT "
			+ "di "
			+ "FROM DisplayInfo di "
			+ "JOIN di.displayinfoImages di_img "
			+ "JOIN di_img.fileInfo fi "
			+ "Where di.id = ?1")
	DisplayInfo selectDisplayInfoImage(long displayInfoId);

	@Query("SELECT "
			+ "pr "
			+ "FROM Product pr "
			+ "JOIN pr.productImages pi "
			+ "JOIN pr.displayInfos di "
			+ "JOIN pi.fileInfo fi "
			+ "Where type in ('ma', 'et') AND di.id = ?1")
	Page<Product> selectProductImageList(long displayInfoId, Pageable pageable);

	@Query("SELECT "
			+ "pr "
			+ "FROM Product pr "
			+ "JOIN pr.displayInfos di "
			+ "JOIN pr.category ca "
			+ "Where di.id = ?1")
	Product selectDisplayInfo(long displayInfoId);

}
