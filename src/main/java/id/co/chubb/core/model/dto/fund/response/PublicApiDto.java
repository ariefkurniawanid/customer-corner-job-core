package id.co.chubb.core.model.dto.fund.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicApiDto {
    private int userId;
    private int id;
    private String title;
    private Boolean completed;
}
