package com.campaigniq.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;

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
	
	public Campaign updateCampaign(Long id, Campaign updateCampaign) {
		Campaign existingCampaign = campaignRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND,
						"Campaign not found with the id:  " + id
					));
		
		
	    existingCampaign.setCampaignName(updateCampaign.getCampaignName());
	    existingCampaign.setDescription(updateCampaign.getDescription());
	    existingCampaign.setBudget(updateCampaign.getBudget());
	    existingCampaign.setStartDate(updateCampaign.getStartDate());
	    existingCampaign.setEndDate(updateCampaign.getEndDate());
	    existingCampaign.setStatus(updateCampaign.getStatus());

	    return campaignRepository.save(existingCampaign);
				
	}
	
	public void deleteCampaign(Long id) {
		Campaign existingCampaign = campaignRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND,
						"Campaign not found with the id:  " + id
					));
		campaignRepository.delete(existingCampaign);
	}
	
}
