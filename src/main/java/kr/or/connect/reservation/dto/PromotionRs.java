package kr.or.connect.reservation.dto;

public class PromotionRs {
	private long id;
	private long productId;
	private String productImageUrl;

	public PromotionRs() {
		super();
	}

	public PromotionRs(long id, long productId, String productImageUrl) {
		super();
		this.id = id;
		this.productId = productId;
		this.productImageUrl = productImageUrl;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "PromotionRs [promotionID=" + id + ", productID=" + productId + ", productImageUrl" + productImageUrl
				+ "]";
	}

}
