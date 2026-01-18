package id.co.chubb.core.service.fund;

import id.co.chubb.core.model.dto.fund.response.ApimJwtResponseDto;
import id.co.chubb.core.model.entity.TblTokenStore;
import id.co.chubb.core.respository.TblTokenStoreRepository;
import id.co.chubb.core.util.Base64Utils;
import id.co.chubb.core.util.ConstantUtils;
import id.co.chubb.core.util.DateUtils;
import id.co.chubb.core.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TblTokenStoreService {

    private final TblTokenStoreRepository tblTokenStoreRepository;

    @Transactional
    public TblTokenStore findBySessionName(String name) {
        return tblTokenStoreRepository.findBySessionName(name);
    }

    @Transactional
    public void save(ApimJwtResponseDto apimJwtResponseDto) {
        TblTokenStore tokenStore = new TblTokenStore();
        tokenStore.setSessionName(ConstantUtils.SESSION_APIM_JWT);
        tokenStore.setTokenValue(Base64Utils.base64Encode(apimJwtResponseDto.getAccessToken()));
        tokenStore.setExpiryDate(DateUtils.epochToLocalDateTime(apimJwtResponseDto.getNotBefore()));
        tblTokenStoreRepository.save(tokenStore);
        MessageUtils.setLogMessage("Succes save data into TblTokenStore", log);
    }

    @Transactional
    public void update(TblTokenStore tblTokenStore, ApimJwtResponseDto apimJwtResponseDto) {
        tblTokenStore.setTokenValue(Base64Utils.base64Encode(apimJwtResponseDto.getAccessToken()));
        tblTokenStore.setExpiryDate(DateUtils.epochToLocalDateTime(apimJwtResponseDto.getNotBefore()));
        tblTokenStoreRepository.save(tblTokenStore);
        MessageUtils.setLogMessage("Succes update data into TblTokenStore", log);
    }
}
