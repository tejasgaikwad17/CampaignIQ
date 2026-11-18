
package com.campaigniq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campaigniq.entity.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findByCampaignNameContainingIgnoreCase(String campaignName);

}
