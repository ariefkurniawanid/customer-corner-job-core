package id.co.chubb.core.model.dto.fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FundDataDto {
    @JsonProperty("ApprovedDate")
    private String approvedDate;
    @JsonProperty("BidPrice")
    private String bidPrice;
    @JsonProperty("FundCode")
    private String fundCode;
    @JsonProperty("FundName")
    private String fundName;
    @JsonProperty("FundStatus")
    private String fundStatus;
    @JsonProperty("LastApprovedby")
    private String lastApprovedby;
    @JsonProperty("LastEnteredby")
    private String lastEnteredby;
    @JsonProperty("OfferPrice")
    private String offerPrice;
    @JsonProperty("PriceEffectiveDate")
    private String priceEffectiveDate;
    @JsonProperty("PriceEntryDate")
    private String priceEntryDate;
    @JsonProperty("PriceStatus")
    private String priceStatus;
}
