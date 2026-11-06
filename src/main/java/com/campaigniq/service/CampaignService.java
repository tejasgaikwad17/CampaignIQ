package com.campaigniq.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
	
}
