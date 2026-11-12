
package com.campaigniq.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.campaigniq.entity.Campaign;
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
    public Campaign createCampaign(@RequestBody Campaign campaign) {
        return campaignService.createCampaign(campaign);
    }

    @GetMapping
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }
    
    @GetMapping("/{id}")
    public Campaign getCampaignById(@PathVariable Long id) {
        return campaignService.getCampaignById(id);
    }
    
    @PutMapping("/{id}")
    public Campaign updateCampaign(
            @PathVariable Long id,
            @RequestBody Campaign campaign) {

        return campaignService.updatedCampaign(id, campaign);
    }
}
