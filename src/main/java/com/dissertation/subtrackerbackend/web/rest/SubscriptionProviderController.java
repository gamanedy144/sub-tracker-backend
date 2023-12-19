package com.dissertation.subtrackerbackend.web.rest;

import com.dissertation.subtrackerbackend.domain.SubscriptionProvider;
import com.dissertation.subtrackerbackend.domain.dto.SubscriptionProviderDTO;
import com.dissertation.subtrackerbackend.service.SubscriptionProviderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/subscription-provider")
public class SubscriptionProviderController {

    private final SubscriptionProviderService subscriptionProviderService;
    @GetMapping
    public ResponseEntity<List<SubscriptionProviderDTO>> fetchAllSubscriptionProviders(){
        return ResponseEntity.ok(subscriptionProviderService.fetchAllSubscriptionProviders());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionProviderDTO> fetchSubscriptionProviderById(@PathVariable long id){
        return ResponseEntity.ok(subscriptionProviderService.fetchSubscriptionProviderById(id));
    }
    @PostMapping("/multiple")
    public ResponseEntity<List<SubscriptionProviderDTO>> saveMultipleSubscriptionProviders(@RequestBody List<SubscriptionProvider> subscriptionProviderList){
        return ResponseEntity.ok(subscriptionProviderService.saveMultipleSubscriptionProviders(subscriptionProviderList));
    }
    @PostMapping
    public ResponseEntity<SubscriptionProviderDTO> saveSubscriptionProvider(@RequestBody SubscriptionProvider subscriptionProvider){
        return ResponseEntity.ok(subscriptionProviderService.saveSubscriptionProvider(subscriptionProvider));
    }
    @PutMapping("/update")
    public ResponseEntity<SubscriptionProviderDTO> updateSubscriptionProvider(@RequestBody SubscriptionProviderDTO subscriptionProviderDTO){
        return ResponseEntity.ok(subscriptionProviderService.updateSubscriptionProvider(subscriptionProviderDTO));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<SubscriptionProviderDTO> updateSubscriptionProviderById(@PathVariable long id, @RequestBody SubscriptionProviderDTO subscriptionProviderDTO){
        return ResponseEntity.ok(subscriptionProviderService.updateSubscriptionProviderById(id, subscriptionProviderDTO));
    }
    @DeleteMapping("/delete/{id}")
    public void deleteSubscriptionProvider(@PathVariable long id){
        subscriptionProviderService.delete(id);
    }
}
