package id.co.chubb.core.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TBL_DETAIL_FUND")
public class TblDetailFund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "FUND_CODE", nullable = false, length = 50)
    private String fundCode;

    @Column(name = "BID_PRICE")
    private BigDecimal bidPrice;

    @Column(name = "OFFER_PRICE")
    private BigDecimal offerPrice;

    @Column(name = "PRICE_ENTRY_DATE")
    private LocalDateTime priceEntryDate;

    @Column(name = "PRICE_EFFECTIVE_DATE")
    private LocalDateTime priceEffectiveDate;

    @Size(max = 20)
    @Column(name = "SOURCE_FUND", length = 20)
    private String sourceFund;

    @Size(max = 20)
    @Column(name = "FUND_STATUS", length = 20)
    private String fundStatus;

    @Column(name = "APPROVED_DATE")
    private LocalDateTime approvedDate;

    @Size(max = 20)
    @Column(name = "PRICE_STATUS", length = 20)
    private String priceStatus;

    @Size(max = 100)
    @Column(name = "LAST_APPROVED_BY", length = 100)
    private String lastApprovedBy;

    @Size(max = 100)
    @Column(name = "LAST_ENTERED_BY", length = 100)
    private String lastEnteredBy;

}