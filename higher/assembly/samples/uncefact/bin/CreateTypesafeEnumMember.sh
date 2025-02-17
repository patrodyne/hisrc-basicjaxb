#!/bin/sh

M2_REPO="${HOME}/.m2/repository"
BASICJAXB_VERSION="2.2.2-SNAPSHOT"
CP="${M2_REPO}/org/patrodyne/jvnet/hisrc-basicjaxb-tools/${BASICJAXB_VERSION}/hisrc-basicjaxb-tools-${BASICJAXB_VERSION}.jar"
EXEC="java -cp ${CP} org.jvnet.basicjaxb.util.CreateTypesafeEnumMember"

cd "src/main/resources/uncefact"

for XSD in \
EDIFICAS-EU_AccountingAccountType_D11A.xsd \
EDIFICAS-EU_AccountingAmountType_D11A.xsd \
UNECE_ActionCode_D23A.xsd \
UNECE_AddressType_D23A.xsd \
UNECE_AdjustmentReasonDescriptionCode_D23A.xsd \
UNECE_AllowanceChargeIdentificationCode_D23A.xsd \
UNECE_AllowanceChargeReasonCode_D23A.xsd \
UNECE_AutomaticDataCaptureMethodCode_D23A.xsd \
UNECE_CargoOperationalCategoryCode_D23A.xsd \
UNECE_CargoTypeCode_1996Rev2Final.xsd \
UNECE_CommodityIdentificationCode_D23A.xsd \
UNECE_CommunicationMeansTypeCode_D23A.xsd \
UNECE_ContactFunctionCode_D23A.xsd \
UNECE_DangerousGoodsPackingCode_D23A.xsd \
UNECE_DangerousGoodsRegulationCode_D23A.xsd \
UNECE_DateOnlyFormatCode_D23A.xsd \
UNECE_DeliveryTermsCode_2020.xsd \
UNECE_DeliveryTermsFunctionCode_D10A.xsd \
UNECE_DimensionTypeCode_D23A.xsd \
UNECE_DocumentNameCode_Accounting_D23A.xsd \
UNECE_DocumentNameCode_D23A.xsd \
UNECE_DocumentStatusCode_D23A.xsd \
UNECE_DutyorTaxorFeeCategoryCode_D23A.xsd \
UNECE_DutyTaxFeeTypeCode_D23A.xsd \
UNECE_EventTimeReferenceCode_D23A.xsd \
UNECE_EventTimeReferenceCodePaymentTermsEvent_D23A.xsd \
UNECE_FreightChargeQuantityUnitBasisCode_D23A.xsd \
UNECE_FreightChargeTariffCode_D23A.xsd \
UNECE_GoodsTypeCode_D23A.xsd \
UNECE_GoodsTypeExtensionCode_D23A.xsd \
UNECE_LocationFunctionCode_D23A.xsd \
UNECE_MeasurementUnitCommonCodeLinear_4.xsd \
UNECE_MeasurementUnitCommonCodeVolume_4.xsd \
UNECE_MeasurementUnitCommonCodeWeight_4.xsd \
UNECE_MessageFunctionCode_D23A.xsd \
UNECE_PackageTypeCode_2006.xsd \
UNECE_PackagingMarkingCode_D23A.xsd \
UNECE_PartyRoleCode_ChargePaying_D23A.xsd \
UNECE_PartyRoleCode_D23A.xsd \
UNECE_PaymentGuaranteeMeansCode_D23A.xsd \
UNECE_PaymentMeansChannelCode_D23A.xsd \
UNECE_PaymentMeansCode_D23A.xsd \
UNECE_PaymentTermsTypeCode_D23A.xsd \
UNECE_PriceTypeCode_D23A.xsd \
UNECE_ReferenceTypeCode_D23A.xsd \
UNECE_SealConditionCode_D23A.xsd \
UNECE_SealingPartyRoleCode_D23A.xsd \
UNECE_StatusCode_D23A.xsd \
UNECE_StatusDescriptionCode_AccountingDebitCredit_D23A.xsd \
UNECE_TimeOnlyFormatCode_D23A.xsd \
UNECE_TimePointFormatCode_D23A.xsd \
UNECE_TransportEquipmentCategoryCode_D23A.xsd \
UNECE_TransportEquipmentFullnessCode_D23A.xsd \
UNECE_TransportMeansTypeCode_2007.xsd \
UNECE_TransportModeCode_2.xsd \
UNECE_TransportMovementStageCode_D23A.xsd \
UNECE_TransportPaymentArrangementCode_D23A.xsd
	do
		${EXEC} "13DEC23/codelist/standard/${XSD}"
	done
exit 0

