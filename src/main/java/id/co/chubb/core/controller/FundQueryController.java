package id.co.chubb.core.controller;

import id.co.chubb.core.service.fund.FundQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fund")
@RequiredArgsConstructor
public class FundQueryController {

    private final FundQueryService fundQueryService;

    @GetMapping("/details")
    public ResponseEntity<?> queryFund(@RequestParam String sourceIndi) {
        fundQueryService.queryFundDetails(sourceIndi);
        return ResponseEntity.ok("Successfully Query Fund Daily");
    }
}
