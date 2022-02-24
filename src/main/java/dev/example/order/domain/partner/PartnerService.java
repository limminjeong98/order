package dev.example.order.domain.partner;

public interface PartnerService {
    // Command 명령쪽
    // Criteria 조회쪽
    // Info 객체리턴
    PartnerInfo registerPartner(PartnerCommand command);
    PartnerInfo getPartnerInfo(String partnerToken);
    PartnerInfo enablePartner(String partnerToken);
    PartnerInfo disablePartner(String partnerToken);
}
