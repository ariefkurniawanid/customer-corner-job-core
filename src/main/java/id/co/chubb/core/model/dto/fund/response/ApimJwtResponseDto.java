package id.co.chubb.core.model.dto.fund.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApimJwtResponseDto {
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private Long expiresIn;
    @JsonProperty("ext_expires_in")
    private Long extExpiresIn;
    @JsonProperty("expires_on")
    private Long expiresOn;
    @JsonProperty("not_before")
    private Long notBefore;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("access_token")
    private String accessToken;
}
