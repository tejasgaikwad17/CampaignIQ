
package com.campaigniq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.campaigniq.CampaignStatus;
import com.campaigniq.dto.CampaignRequest;
import com.campaigniq.dto.CampaignResponse;
import com.campaigniq.entity.Campaign;
import com.campaigniq.repository.CampaignRepository;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    // CREATE CAMPAIGN
    public CampaignResponse createCampaign(CampaignRequest campaignRequest) {

        Campaign campaign = new Campaign();

        campaign.setCampaignName(campaignRequest.getCampaignName());
        campaign.setDescription(campaignRequest.getDescription());
        campaign.setBudget(campaignRequest.getBudget());
        campaign.setStartDate(campaignRequest.getStartDate());
        campaign.setEndDate(campaignRequest.getEndDate());
        campaign.setStatus(campaignRequest.getStatus());

        Campaign savedCampaign = campaignRepository.save(campaign);

        CampaignResponse response = new CampaignResponse();

        response.setId(savedCampaign.getId());
        response.setCampaignName(savedCampaign.getCampaignName());
        response.setDescription(savedCampaign.getDescription());
        response.setBudget(savedCampaign.getBudget());
        response.setStartDate(savedCampaign.getStartDate());
        response.setEndDate(savedCampaign.getEndDate());
        response.setStatus(savedCampaign.getStatus());

        return response;
    }

    // GET ALL CAMPAIGNS
    public List<CampaignResponse> getAllCampaigns() {

        List<Campaign> campaigns = campaignRepository.findAll();

        List<CampaignResponse> responses = new ArrayList<>();

        for (Campaign campaign : campaigns) {

            CampaignResponse response = new CampaignResponse();

            response.setId(campaign.getId());
            response.setCampaignName(campaign.getCampaignName());
            response.setDescription(campaign.getDescription());
            response.setBudget(campaign.getBudget());
            response.setStartDate(campaign.getStartDate());
            response.setEndDate(campaign.getEndDate());
            response.setStatus(campaign.getStatus());

            responses.add(response);
        }

        return responses;
    }

    // GET CAMPAIGN BY ID
    public CampaignResponse getCampaignById(Long id) {

        Campaign campaign = campaignRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Campaign not found with the id: " + id
            ));

        CampaignResponse response = new CampaignResponse();

        response.setId(campaign.getId());
        response.setCampaignName(campaign.getCampaignName());
        response.setDescription(campaign.getDescription());
        response.setBudget(campaign.getBudget());
        response.setStartDate(campaign.getStartDate());
        response.setEndDate(campaign.getEndDate());
        response.setStatus(campaign.getStatus());

        return response;
    }

    // UPDATE CAMPAIGN
    public CampaignResponse updateCampaign(
            Long id,
            CampaignRequest campaignRequest) {

        Campaign existingCampaign = campaignRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Campaign not found with the id: " + id
            ));

        // Validate campaign status transition
        CampaignStatus currentStatus = existingCampaign.getStatus();
        CampaignStatus newStatus = campaignRequest.getStatus();

        if (currentStatus == CampaignStatus.COMPLETED ||
                currentStatus == CampaignStatus.CANCELLED) {

            if (currentStatus != newStatus) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot change campaign status from "
                        + currentStatus + " to " + newStatus
                );
            }
        }

        // Update campaign details
        existingCampaign.setCampaignName(campaignRequest.getCampaignName());
        existingCampaign.setDescription(campaignRequest.getDescription());
        existingCampaign.setBudget(campaignRequest.getBudget());
        existingCampaign.setStartDate(campaignRequest.getStartDate());
        existingCampaign.setEndDate(campaignRequest.getEndDate());
        existingCampaign.setStatus(campaignRequest.getStatus());

        Campaign savedCampaign = campaignRepository.save(existingCampaign);

        CampaignResponse response = new CampaignResponse();

        response.setId(savedCampaign.getId());
        response.setCampaignName(savedCampaign.getCampaignName());
        response.setDescription(savedCampaign.getDescription());
        response.setBudget(savedCampaign.getBudget());
        response.setStartDate(savedCampaign.getStartDate());
        response.setEndDate(savedCampaign.getEndDate());
        response.setStatus(savedCampaign.getStatus());

        return response;
    }

    // DELETE CAMPAIGN
    public void deleteCampaign(Long id) {

        Campaign existingCampaign = campaignRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Campaign not found with the id: " + id
            ));

        campaignRepository.delete(existingCampaign);
    }
    
    
 // SEARCH CAMPAIGNS BY NAME
public List<CampaignResponse> searchCampaigns(String name) {

    List<Campaign> campaigns =
            campaignRepository.findByCampaignNameContainingIgnoreCase(name);

    List<CampaignResponse> responses = new ArrayList<>();

    for (Campaign campaign : campaigns) {

        CampaignResponse response = new CampaignResponse();

        response.setId(campaign.getId());
        response.setCampaignName(campaign.getCampaignName());
        response.setDescription(campaign.getDescription());
        response.setBudget(campaign.getBudget());
        response.setStartDate(campaign.getStartDate());
        response.setEndDate(campaign.getEndDate());
        response.setStatus(campaign.getStatus());

        responses.add(response);
    }

    return responses;
}

public List<CampaignResponse> filterCampaignByStatus(CampaignStatus status){
	
	List<Campaign> campaigns = campaignRepository.findByStatus(status);
	
	List<CampaignResponse> responses = new ArrayList<>();
	
	for(Campaign campaign : campaigns) {
		
		CampaignResponse response = new CampaignResponse();
		
		response.setId(campaign.getId());
		response.setCampaignName(campaign.getCampaignName());
		response.setDescription(campaign.getDescription());
		response.setBudget(campaign.getBudget());
		response.setStartDate(campaign.getStartDate());
		response.setEndDate(campaign.getEndDate());
		response.setStatus(campaign.getStatus());
		
		responses.add(response);
		
		
	}
	return responses;
}

}
