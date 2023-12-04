package com.dissertation.subtrackerbackend.web.rest;

import com.dissertation.subtrackerbackend.domain.SubscriptionProvider;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionProviderDTO;
import com.dissertation.subtrackerbackend.service.SubscriptionProviderService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("api/v1/subscription-provider")
public class SubscriptionProviderController {

    private final SubscriptionProviderService subscriptionProviderService;
    @GetMapping
    public ResponseEntity<List<SubscriptionProvider>> fetchAllSubscriptionProviders(){
        return ResponseEntity.ok(subscriptionProviderService.fetchAllSubscriptionProviders());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionProvider> fetchSubscriptionProviderById(@PathVariable long id){
        return ResponseEntity.ok(subscriptionProviderService.fetchSubscriptionProviderById(id));
    }
    @PostMapping("/multiple")
    public ResponseEntity<List<SubscriptionProvider>> saveMultipleSubscriptionProviders(@RequestBody List<SubscriptionProvider> subscriptionProviderList){
        return ResponseEntity.ok(subscriptionProviderService.saveMultipleSubscriptionProviders(subscriptionProviderList));
    }
    @PostMapping
    public ResponseEntity<SubscriptionProvider> saveSubscriptionProvider(@RequestBody SubscriptionProvider subscriptionProvider){
        return ResponseEntity.ok(subscriptionProviderService.saveSubscriptionProvider(subscriptionProvider));
    }
    @PutMapping("/update")
    public ResponseEntity<SubscriptionProvider> updateSubscriptionProvider(@RequestBody SubscriptionProviderDTO subscriptionProviderDTO){
        return ResponseEntity.ok(subscriptionProviderService.updateSubscriptionProvider(subscriptionProviderDTO));
    }

}
