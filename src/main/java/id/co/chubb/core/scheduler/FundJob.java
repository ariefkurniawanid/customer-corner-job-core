package id.co.chubb.core.scheduler;

import id.co.chubb.core.service.fund.FundQueryService;
import id.co.chubb.core.util.ConstantUtils;
import id.co.chubb.core.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FundJob {

    private final FundQueryService fundQueryService;

    @Scheduled(cron = "${app.job.syncFundEbao}")
    public void syncFundEbao() {
        MessageUtils.setLogMessage("Running Job Sync Fund EBAO", log);
        String sourceEbao = ConstantUtils.SOURCE_EBAO;
        fundQueryService.queryFundDetails(sourceEbao);
    }

    @Scheduled(cron = "${app.job.syncFundIpas}")
    public void syncFundIpas() {
        MessageUtils.setLogMessage("Running Job Sync Fund IPAS", log);
        String sourceIpas = ConstantUtils.SOURCE_IPAS;
        fundQueryService.queryFundDetails(sourceIpas);
    }
}
