package com.example.order.domain.partner;

public interface PartnerReader {
    Partner getPartner(Long partnerId);

    Partner getPartner(String partnerToken);
}
