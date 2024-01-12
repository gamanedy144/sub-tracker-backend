package com.dissertation.subtrackerbackend.web.rest;

import com.dissertation.subtrackerbackend.domain.Subscription;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionDTO;
import com.dissertation.subtrackerbackend.service.SubscriptionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    @GetMapping
    public ResponseEntity<List<SubscriptionDTO>> fetchAllSubscriptions(){
        List<SubscriptionDTO> subs = subscriptionService.fetchAllSubscriptionsForCurrentUser();
        return ResponseEntity.ok(subs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> fetchSubscriptionById(@PathVariable long id){
        return ResponseEntity.ok(subscriptionService.fetchSubscriptionById(id));
    }
    @PostMapping("/multiple")
    public ResponseEntity<List<SubscriptionDTO>> saveMultipleSubscriptions(@RequestBody List<Subscription> subscriptionList){
        return ResponseEntity.ok(subscriptionService.saveMultipleSubscriptions(subscriptionList));
    }
    @PostMapping
    public ResponseEntity<Subscription> saveSubscription(@RequestBody SubscriptionDTO subscription){
        return ResponseEntity.ok(subscriptionService.saveSubscription(subscription));
    }
    @PutMapping("/update")
    public ResponseEntity<Subscription> updateSubscription(@RequestBody SubscriptionDTO subscriptionDTO){
        return ResponseEntity.ok(subscriptionService.updateSubscription(subscriptionDTO));
    }
    @DeleteMapping("/delete/{id}")
    public void deleteSubscription(@PathVariable long id){
        subscriptionService.delete(id);
    }
}
