package com.dissertation.subtrackerbackend.web.rest;

import com.dissertation.subtrackerbackend.domain.SubscriptionCategory;
import com.dissertation.subtrackerbackend.service.InsightService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/insight")
public class InsightController {

    InsightService insightService;
    @GetMapping("/ceva-1")
    public ResponseEntity<Map<String, Double>> getFirstThing() {
        return ResponseEntity.ok(insightService.calculateMonthlySpendings());
    }
    @GetMapping("/ceva-2")
    public ResponseEntity<Map<String, Map<SubscriptionCategory, Double>>> get2ndThing() {
        return ResponseEntity.ok(insightService.calculateMonthlySpendingsByCategory());
    }
    @GetMapping("/ceva-3")
    public ResponseEntity<Map<String, Double>> get3rdThing() {
        return ResponseEntity.ok(insightService.calculateTotalSpendingPerMonthOrYear("yyyy"));
    }
    @GetMapping("/ceva-4/{months}")
    public ResponseEntity<Map<String, Double>> get4thThing(@PathVariable int months) {
        return ResponseEntity.ok(insightService.estimateFutureSpending(months));
    }


}
