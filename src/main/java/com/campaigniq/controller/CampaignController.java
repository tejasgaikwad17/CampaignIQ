
package com.campaigniq.controller;

import java.util.List;
import jakarta.validation.Valid;

import com.campaigniq.dto.CampaignRequest;
import com.campaigniq.dto.CampaignResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.campaigniq.service.CampaignService;


@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignResponse createCampaign(
            @Valid @RequestBody CampaignRequest campaignRequest) {

        return campaignService.createCampaign(campaignRequest);
    }

    @GetMapping
    public List<CampaignResponse> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }
    
    @GetMapping("/search")
    public List<CampaignResponse> searchCampaigns(
            @RequestParam String name) {

        return campaignService.searchCampaigns(name);
    }
    
    @GetMapping("/{id}")
    public CampaignResponse getCampaignById(@PathVariable Long id) {
        return campaignService.getCampaignById(id);
    }
    
    @PutMapping("/{id}")
    public CampaignResponse updateCampaign(
            @PathVariable Long id,
            @Valid @RequestBody CampaignRequest campaignRequest) {

        return campaignService.updateCampaign(id, campaignRequest);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCampaign(@PathVariable Long id) {
    	campaignService.deleteCampaign(id);
    }
}
