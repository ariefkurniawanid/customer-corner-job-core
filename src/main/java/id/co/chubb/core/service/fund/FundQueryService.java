package id.co.chubb.core.service.fund;

import id.co.chubb.core.model.dto.fund.FundDataDto;
import id.co.chubb.core.model.dto.fund.response.ApimJwtResponseDto;
import id.co.chubb.core.model.dto.fund.response.FundQueryResponseDto;
import id.co.chubb.core.model.dto.fund.response.PublicApiDto;
import id.co.chubb.core.model.entity.TblDetailFund;
import id.co.chubb.core.model.entity.TblMasterFund;
import id.co.chubb.core.model.entity.TblTokenStore;
import id.co.chubb.core.service.webservice.WebClientService;
import id.co.chubb.core.util.Base64Utils;
import id.co.chubb.core.util.ConstantUtils;
import id.co.chubb.core.util.DateUtils;
import id.co.chubb.core.util.MessageUtils;
import id.co.chubb.core.util.commons.CommonsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FundQueryService {

    private final WebClientService webClientService;
    private final TblTokenStoreService tblTokenStoreService;
    private final TblMasterFundService tblMasterFundService;
    private final TblDetailsFundService tblDetailsFundService;

    public void queryFundDetails(String sourceIndi) {
        MessageUtils.setLogMessage("Query Fund Details Daily", log);
        try {
            String token = checkingToken(ConstantUtils.SESSION_APIM_JWT);
            if (StringUtils.isNotEmpty(token)) {
                String effectiveDate = DateUtils.dateTimeToString(LocalDateTime.now());
//                FundQueryResponseDto fundQueryResponseDto = webClientService.getFundQuery(token, effectiveDate, sourceIndi).block();
                FundQueryResponseDto fundQueryResponseDto = CommonsUtils.getFundQueryJson("static/fundquery.json");
                if (ObjectUtils.isNotEmpty(fundQueryResponseDto)) {
                    String businessResultCode = fundQueryResponseDto.getFundQuery().getBusinessResultCode();
                    if (ConstantUtils.BR300_SUCCESS.equalsIgnoreCase(businessResultCode)) {
                        List<FundDataDto> fundDataDtoList = fundQueryResponseDto.getFundQuery().getResult().getQueryFundDetail().getFundData();
                        checkingFundCode(fundDataDtoList);
                        List<TblDetailFund> tblDetailFundList = new ArrayList<>();
                        for (FundDataDto fundDataDto : fundDataDtoList) {
                            TblDetailFund tblDetailFund = new TblDetailFund();
                            tblDetailFund.setFundCode(fundDataDto.getFundCode().toUpperCase());
                            tblDetailFund.setBidPrice(new BigDecimal(fundDataDto.getBidPrice()));
                            tblDetailFund.setOfferPrice(new BigDecimal(fundDataDto.getOfferPrice()));
                            tblDetailFund.setPriceEntryDate(DateUtils.stringToDateTime(fundDataDto.getPriceEntryDate(), DateUtils.FORMAT_DATE_DDMMYYYY));
                            tblDetailFund.setPriceEffectiveDate(DateUtils.stringToDateTime(fundDataDto.getPriceEffectiveDate(), DateUtils.FORMAT_DATE_DDMMYYYY));
                            tblDetailFund.setSourceFund(ConstantUtils.EBAO);
                            if ("2".equals(sourceIndi)) {
                                tblDetailFund.setSourceFund(ConstantUtils.IPAS);
                            }
                            tblDetailFund.setFundStatus(fundDataDto.getFundStatus());
                            tblDetailFund.setApprovedDate(DateUtils.stringToDateTime(fundDataDto.getApprovedDate(), DateUtils.FORMAT_DATE_DDMMYYYY));
                            tblDetailFund.setPriceStatus(fundDataDto.getPriceStatus());
                            tblDetailFund.setLastApprovedBy(fundDataDto.getLastApprovedby());
                            tblDetailFund.setLastEnteredBy(fundDataDto.getLastEnteredby());
                            tblDetailFundList.add(tblDetailFund);
                        }
                        tblDetailsFundService.saveAll(tblDetailFundList);
                    } else {
                        MessageUtils.setLogMessage("Query Fund Details Not Success With Result Code : " + businessResultCode, log);
                    }
                } else {
                    MessageUtils.setLogMessage("Query Fund Details Not Available", log);
                }
            }
        } catch (Exception e) {
            MessageUtils.setErrorLogMessage("Query Fund Details Error", log);
        }
    }

    protected String checkingToken(String sessionName) {
        MessageUtils.setLogMessage("Checking Token", log);
        String token = null;
        try {
            TblTokenStore tblTokenStore = tblTokenStoreService.findBySessionName(sessionName);
            LocalDateTime now = LocalDateTime.now();
            if (tblTokenStore == null) {
                MessageUtils.setLogMessage("Token is Not Available. Generate New Token.", log);
                //CUMA BUAT TESTING AJA
//                ApimJwtResponseDto apimJwtResponseDto = webClientService.getApimToken().block();
                ApimJwtResponseDto apimJwtResponseDto = CommonsUtils.getApimJson("static/token.json");
                tblTokenStoreService.save(apimJwtResponseDto);
                token = apimJwtResponseDto.getAccessToken();
            } else {
                if (now.isAfter(tblTokenStore.getExpiryDate())) {
                    MessageUtils.setLogMessage("Token Expired. Generate New Token", log);
                    //CUMA BUAT TESTING AJA
//                    ApimJwtResponseDto apimJwtResponseDto = webClientService.getApimToken().block();
                    ApimJwtResponseDto apimJwtResponseDto = CommonsUtils.getApimJson("static/token.json");
                    tblTokenStoreService.update(tblTokenStore, apimJwtResponseDto);
                    token = apimJwtResponseDto.getAccessToken();
                } else {
                    MessageUtils.setLogMessage("Token is Available. Use existing token.", log);
                    token = Base64Utils.base64Decode(tblTokenStore.getTokenValue());
                }
            }
        } catch (Exception e) {
            MessageUtils.setErrorLogMessage("Error Checking Token", log);
        }
        return token;
    }

    protected void checkingFundCode(List<FundDataDto> fundDataDtoList) {
        MessageUtils.setLogMessage("Checking Fund Code", log);
        try {
            if (fundDataDtoList.isEmpty()) {
                MessageUtils.setLogMessage("Fund Code Details is Empty", log);
                return;
            }
            Set<String> fundCodeSet = fundDataDtoList
                    .stream()
                    .map(FundDataDto::getFundCode)
                    .map(String::toUpperCase)
                    .collect(Collectors.toSet());
            Map<String, TblMasterFund> masterFundMap = tblMasterFundService.findAllByCode(fundCodeSet)
                    .stream()
                    .collect(Collectors.toMap(TblMasterFund::getCode, objectMasterFund -> objectMasterFund));

            List<TblMasterFund> saveData = new ArrayList<>();
            for (FundDataDto dto : fundDataDtoList) {
                TblMasterFund existingFund = masterFundMap.get(dto.getFundCode());
                Boolean fundStatus = Boolean.TRUE;
                if (!ConstantUtils.ACTIVE.equalsIgnoreCase(dto.getFundStatus())) {
                    fundStatus = Boolean.FALSE;
                }
                if (existingFund != null) {
                    if (fundStatus != existingFund.getStatus()) {
                        existingFund.setStatus(fundStatus);
                        saveData.add(existingFund);
                    }
                } else {
                    TblMasterFund newFund = new TblMasterFund();
                    newFund.setCode(dto.getFundCode());
                    newFund.setName(dto.getFundName());
                    newFund.setStatus(fundStatus);
                    saveData.add(newFund);
                }
            }
            if (!saveData.isEmpty()) {
                tblMasterFundService.saveAll(saveData);
            }
        } catch (Exception e) {
            MessageUtils.setErrorLogMessage("Error Checking Fund Code", log);
        }
    }
}