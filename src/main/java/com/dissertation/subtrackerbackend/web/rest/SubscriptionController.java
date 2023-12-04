package com.dissertation.subtrackerbackend.web.rest;

import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;
import com.dissertation.subtrackerbackend.service.SubscriptionService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("api/v1/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    @GetMapping
    public ResponseEntity<List<Subscription>> fetchAllSubscriptionProviders(){
        return ResponseEntity.ok(subscriptionService.fetchAllSubscriptions());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Subscription> fetchSubscriptionProviderById(@PathVariable long id){
        return ResponseEntity.ok(subscriptionService.fetchSubscriptionById(id));
    }
    @PostMapping("/multiple")
    public ResponseEntity<List<Subscription>> saveMultipleSubscriptionProviders(@RequestBody List<Subscription> subscriptionList){
        return ResponseEntity.ok(subscriptionService.saveMultipleSubscriptions(subscriptionList));
    }
    @PostMapping
    public ResponseEntity<Subscription> saveSubscriptionProvider(@RequestBody Subscription subscription){
        return ResponseEntity.ok(subscriptionService.saveSubscription(subscription));
    }
    @PutMapping("/update")
    public ResponseEntity<Subscription> updateSubscriptionProvider(@RequestBody SubscriptionDTO subscriptionDTO){
        return ResponseEntity.ok(subscriptionService.updateSubscription(subscriptionDTO));
    }
}
