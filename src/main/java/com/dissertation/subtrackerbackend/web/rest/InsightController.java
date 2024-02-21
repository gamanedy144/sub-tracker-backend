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
    @GetMapping("/monthly-spendings")
    public ResponseEntity<Map<String, Double>> getFirstThing() {
        return ResponseEntity.ok(insightService.calculateMonthlySpendings());
    }
    @GetMapping("/monthly-spendings-category")
    public ResponseEntity<Map<SubscriptionCategory, Double>> get2ndThing() {
        return ResponseEntity.ok(insightService.calculateCurrentMonthSpendingsByCategory());
    }
    @GetMapping("/monthly-spendings-month-year/{type}")
    public ResponseEntity<Map<String, Double>> get3rdThing(@PathVariable String type) {
        return ResponseEntity.ok(insightService.calculateTotalSpendingPerMonthOrYear(type));
    }
    @GetMapping("/monthly-spendings-current-year/")
    public ResponseEntity<Double> getSpendingsCurrentYear() {
        return ResponseEntity.ok(insightService.calculateTotalSpendingUntilPresentCurrentYear());
    }

    @GetMapping("/monthly-spendings-future/{months}")
    public ResponseEntity<Map<String, Double>> get4thThing(@PathVariable int months) {
        return ResponseEntity.ok(insightService.calculateEstimatedSpendingForCurrentYear());
    }

    @GetMapping("/admin/users-created-month")
    public ResponseEntity<Map<String, Long>> calculateUsersCreatedMonthly() {
        return ResponseEntity.ok(insightService.countUsersCreated());
    }


}
