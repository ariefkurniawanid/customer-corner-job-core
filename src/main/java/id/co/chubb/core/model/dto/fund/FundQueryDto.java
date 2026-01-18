package id.co.chubb.core.model.dto.fund;

import lombok.Data;

@Data
public class FundQueryDto {
    private String businessResultCode;
    private String description;
    private FundResultDto result;

}
