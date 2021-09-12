package kr.or.connect.reservation.core.presentation.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryResult {
    @ApiModelProperty(example = "1")
    private long id;
    @ApiModelProperty(example = "전시")
    private String name;
    @ApiModelProperty(example = "10")
    private long count;

    public CategoryResult(long id, String name, long count) {
        super();
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public static CategoryResult createCategoryResult(long id, String name, long count) {
        CategoryResult categoryResult = new CategoryResult(id, name, count);
        return categoryResult;
    }
}
