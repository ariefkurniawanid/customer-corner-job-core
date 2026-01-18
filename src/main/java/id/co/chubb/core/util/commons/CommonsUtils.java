package id.co.chubb.core.util.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.chubb.core.model.dto.fund.response.ApimJwtResponseDto;
import id.co.chubb.core.model.dto.fund.response.FundQueryResponseDto;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class CommonsUtils {
    public static ApimJwtResponseDto getApimJson(String pathFile) throws IOException {
        Resource resource = new ClassPathResource(pathFile);
        ObjectMapper mapper = new ObjectMapper();
        ApimJwtResponseDto data = mapper.readValue(resource.getInputStream(), ApimJwtResponseDto.class);
        return data;
    }

    public static FundQueryResponseDto getFundQueryJson(String pathFile) throws IOException {
        Resource resource = new ClassPathResource(pathFile);
        ObjectMapper mapper = new ObjectMapper();
        FundQueryResponseDto data = mapper.readValue(resource.getInputStream(), FundQueryResponseDto.class);
        return data;
    }
}
