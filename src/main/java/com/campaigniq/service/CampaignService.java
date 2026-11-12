package com.campaigniq.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.campaigniq.entity.Campaign;
import com.campaigniq.repository.CampaignRepository;

@Service
public class CampaignService {

	private final CampaignRepository campaignRepository;
	
	public CampaignService(CampaignRepository campaignRepository) {
		this.campaignRepository = campaignRepository;
	}
	
	public Campaign createCampaign(Campaign campaign) {
		return campaignRepository.save(campaign);
	}
	
	public List<Campaign> getAllCampaigns(){
		return campaignRepository.findAll();
	}
	
	public Campaign getCampaignById(long id) {
		return campaignRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
							HttpStatus.NOT_FOUND,
							"Campaign not found with the id:  " + id
						));
	}
	
	public Campaign updatedCampaign(Long id, Campaign updatedCampaign) {
		Campaign existingCampaign = campaignRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND,
						"Campaign not found with the id:  " + id
					));
		
		
	    existingCampaign.setCampaignName(updatedCampaign.getCampaignName());
	    existingCampaign.setDescription(updatedCampaign.getDescription());
	    existingCampaign.setBudget(updatedCampaign.getBudget());
	    existingCampaign.setStartDate(updatedCampaign.getStartDate());
	    existingCampaign.setEndDate(updatedCampaign.getEndDate());
	    existingCampaign.setStatus(updatedCampaign.getStatus());

	    return campaignRepository.save(existingCampaign);
				
	}
	
}
