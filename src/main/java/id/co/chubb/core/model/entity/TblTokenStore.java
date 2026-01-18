package id.co.chubb.core.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TBL_TOKEN_STORE")
public class TblTokenStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "SESSION_NAME", nullable = false, length = 50)
    private String sessionName;

    @Size(max = 4000)
    @Column(name = "TOKEN_VALUE", length = 4000)
    private String tokenValue;

    @Column(name = "EXPIRY_DATE")
    private LocalDateTime expiryDate;

}