package com.campaigniq.dto;

import java.math.BigDecimal;
import com.campaigniq.CampaignStatus;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@ValidCampaignDates
public class CampaignRequest {

	@NotBlank(message = "Campaign name is required")
	private String campaignName;

	@NotBlank(message = "Description is required")
	private String description;
	
	@NotNull(message = "Budget is required")
	@Positive(message = "Budget must be greater than zero")
	private BigDecimal budget;

	@NotNull(message = "Start date is required")
	private LocalDate startDate;
	
	@NotNull(message = "End date is required")
	private LocalDate endDate;

	@NotNull(message = "Status is required")
	private CampaignStatus status;
    
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

    public CampaignStatus getStatus() {
        return status;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }
}