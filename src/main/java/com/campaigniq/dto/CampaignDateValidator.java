
package com.campaigniq.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CampaignDateValidator
        implements ConstraintValidator<ValidCampaignDates, CampaignRequest> {

    @Override
    public boolean isValid(
            CampaignRequest request,
            ConstraintValidatorContext context) {

        if (request == null ||
                request.getStartDate() == null ||
                request.getEndDate() == null) {
            return true;
        }

        return request.getEndDate().isAfter(request.getStartDate());
    }
}
