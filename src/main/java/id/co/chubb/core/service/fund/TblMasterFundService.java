package id.co.chubb.core.service.fund;

import id.co.chubb.core.model.entity.TblMasterFund;
import id.co.chubb.core.respository.TblMasterFundRepository;
import id.co.chubb.core.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class TblMasterFundService {

    private final TblMasterFundRepository tblMasterFundRepository;

    @Transactional
    public List<TblMasterFund> findAllByCode(Set<String> fundCodeSet) {
        MessageUtils.setLogMessage("Getting Master Fund by Code", log);
        return tblMasterFundRepository.findByCodeIn(fundCodeSet);
    }

    @Transactional
    public void save(TblMasterFund tblMasterFund) {
        MessageUtils.setLogMessage("Save Data Tbl Master Fund", log);
        tblMasterFundRepository.save(tblMasterFund);
    }

    @Transactional
    public void saveAll(List<TblMasterFund> tblMasterFundList) {
        MessageUtils.setLogMessage("Save All Data Tbl Master Fund", log);
        tblMasterFundRepository.saveAll(tblMasterFundList);
    }

}
