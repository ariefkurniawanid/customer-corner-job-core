package id.co.chubb.core.service.fund;

import id.co.chubb.core.model.entity.TblDetailFund;
import id.co.chubb.core.respository.TblDetailFundRepository;
import id.co.chubb.core.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TblDetailsFundService {

    private final TblDetailFundRepository tblDetailFundRepository;

    @Transactional
    public void saveAll(List<TblDetailFund> tblDetailFunds) {
        MessageUtils.setLogMessage("Save All TblDetailsFund", log);
        tblDetailFundRepository.saveAll(tblDetailFunds);
    }
}
