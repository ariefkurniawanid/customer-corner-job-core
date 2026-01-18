package id.co.chubb.core.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_MASTER_FUND")
public class TblMasterFund {
    @Id
    @Size(max = 10)
    @Column(name = "CODE", nullable = false, length = 10)
    private String code;

    @Size(max = 200)
    @Column(name = "NAME", length = 200)
    private String name;

    @Column(name = "STATUS")
    private Boolean status;

}