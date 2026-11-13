package com.campaigniq.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CampaignRequest {

	private String campaignName;
	private String description;
	private BigDecimal budget;
	private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    
    //Getter and Setter
    
    public String getCampaignName() {
    	return campaignName;
    }
    
    public void setCampaignName(String campaignName) {
    	this.campaignName = campaignName;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
	
    public BigDecimal getBudget() {
    	return budget;
    }
    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}