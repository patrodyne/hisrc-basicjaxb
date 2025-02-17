package org.example.uncefact;

import static jakarta.xml.bind.JAXBIntrospector.getValue;
import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;
import org.patrodyne.jvnet.basicjaxb.validation.SchemaOutputDomResolver;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import un.unece.uncefact.codelist.standard.iso.iso3alphacurrencycode._2012_08_31.ISO3AlphaCurrencyCodeContentType;
import un.unece.uncefact.codelist.standard.unece.dateonlyformatcode.d23a.DateOnlyFormatCodeContentType;
import un.unece.uncefact.codelist.standard.unece.documentnamecode.d23a.DocumentNameCodeContentType;
import un.unece.uncefact.codelist.standard.unece.dutyortaxorfeecategorycode.d23a.DutyorTaxorFeeCategoryCodeContentType;
import un.unece.uncefact.codelist.standard.unece.dutytaxfeetypecode.d23a.DutyTaxFeeTypeCodeContentType;
import un.unece.uncefact.codelist.standard.unece.paymentmeanscode.d23a.PaymentMeansCodeContentType;
import un.unece.uncefact.codelist.standard.unece.referencetypecode.d23a.ReferenceTypeCodeContentType;
import un.unece.uncefact.data.standard.crossindustryinvoice._100.CrossIndustryInvoiceType;
import un.unece.uncefact.identifierlist.standard.iso.isotwo_lettercountrycode.secondedition2006.ISOTwoletterCountryCodeContentType;

/**
 * A JUnit test to check a sample XML file.
 */
public class InvoiceTest extends AbstractSamplesTest
{
	/**
	 * UN/CEFACT ObjectFactory
	 */
	protected static final un.unece.uncefact.data.standard.qualifieddatatype._100.ObjectFactory QDT =
		new un.unece.uncefact.data.standard.qualifieddatatype._100.ObjectFactory();
	protected static final un.unece.uncefact.data.standard.unqualifieddatatype._100.ObjectFactory UDT =
		new un.unece.uncefact.data.standard.unqualifieddatatype._100.ObjectFactory();
	protected static final un.unece.uncefact.data.standard.crossindustryinvoice._100.ObjectFactory RSM =
		new un.unece.uncefact.data.standard.crossindustryinvoice._100.ObjectFactory();
	protected static final un.unece.uncefact.data.standard.reusableaggregatebusinessinformationentity._100.ObjectFactory RAM =
		new un.unece.uncefact.data.standard.reusableaggregatebusinessinformationentity._100.ObjectFactory();
	
	/**
	 * Configure the JAXB context path in AbstractSamplesTest
	 * to include all classes from two packages.
	 */
	@Override
	protected String getContextPath()
	{
		StringBuilder cp = new StringBuilder();
		cp.append(RSM.getClass().getPackageName());
		cp.append(":" + RAM.getClass().getPackageName());
		return cp.toString();
	}
	
	@BeforeEach
	public void beforeEach() throws JAXBException
	{
		setJaxbContext(createContext());
		setUnmarshaller(getJaxbContext().createUnmarshaller());
		setMarshaller(getJaxbContext().createMarshaller());
		getMarshaller().setProperty(JAXB_FORMATTED_OUTPUT, true);
		// Enable XML Schema Validation
		// generateXmlSchemaValidatorFromDom();
	}
	
	@Test
	public void testMarshalSample() throws Exception
	{
		final String SAMPLE_CCI_FILENAME = "src/test/samples/01.01a-INVOICE_uncefact.xml";
		
		// Unmarshal the sample for to assert expectations
		Object sample = getValue(getUnmarshaller().unmarshal(new File(SAMPLE_CCI_FILENAME)));
		assertInstanceOf(CrossIndustryInvoiceType.class, sample);
		CrossIndustryInvoiceType cii1 = (CrossIndustryInvoiceType) sample;
		
		// Create CrossIndustryInvoiceType and use ExchangedDocumentContextType
		CrossIndustryInvoiceType cii2 = RSM.createCrossIndustryInvoiceType()
			.useExchangedDocumentContext(RAM.createExchangedDocumentContextType()
				.useTestIndicator(UDT.createIndicatorType()
					.useIndicator(true))
				.useGuidelineSpecifiedDocumentContextParameter(RAM.createDocumentContextParameterType()
					.useID(UDT.createIDType()
						.useValue("urn:cen.eu:en16931:2017#compliant#urn:xoev-de:kosit:standard:xrechnung_2.3")))
			);
		
		// CII: Use ExchangedDocument
		cii2.useExchangedDocument(RAM.createExchangedDocumentType()
			.useID(UDT.createIDType()
				.useValue("123456XX"))
			.useTypeCode(QDT.createDocumentCodeType()
				.useValue(DocumentNameCodeContentType.COMMERCIAL_INVOICE))
			.useIssueDateTime(UDT.createDateTimeType()
				.useDateTimeString(UDT.createDateTimeDateTimeStringType()
					.useFormat(DateOnlyFormatCodeContentType.CCYYMMDD.value())
					.useValue("20160404")))
			.useIncludedNote(RAM.createNoteType()
				.useContent(UDT.createTextType()
					.useValue("Es gelten unsere Allgem. Geschäftsbedingungen, die Sie unter […] finden."))
				.useSubjectCode(UDT.createCodeType()
					.useValue("ADU") 
					)
				)
			);
		
		// CII: Use SupplyChainTradeTransaction
		cii2.useSupplyChainTradeTransaction(RAM.createSupplyChainTradeTransactionType()
			.useIncludedSupplyChainTradeLineItem(RAM.createSupplyChainTradeLineItemType()
				.useAssociatedDocumentLineDocument(RAM.createDocumentLineDocumentType()
					.useLineID(UDT.createIDType()
						.useValue("Zeitschrift [...]"))
					.useIncludedNote(RAM.createNoteType()
						.useContent(UDT.createTextType()
							.useValue("Die letzte Lieferung im Rahmen des abgerechneten Abonnements erfolgt in 12/2016 Lieferung erfolgt / erfolgte direkt vom Verlag"))))
				.useSpecifiedTradeProduct(RAM.createTradeProductType()
					.useSellerAssignedID(UDT.createIDType()
						.useValue("246"))
					.useName(UDT.createTextType()
						.useValue("Zeitschrift [...]"))
					.useDescription(UDT.createTextType()
						.useValue("Zeitschrift Inland"))
					.useDesignatedProductClassification(RAM.createProductClassificationType()
						.useClassCode(UDT.createCodeType()
							.useListID("IB")
							.useValue("0721-880X"))))
				.useSpecifiedLineTradeAgreement(RAM.createLineTradeAgreementType()
					.useBuyerOrderReferencedDocument(RAM.createReferencedDocumentType()
						.useLineID(UDT.createIDType()
							.useValue("6171175.1")))
					.useNetPriceProductTradePrice(RAM.createTradePriceType()
						.useChargeAmount(UDT.createAmountType()
							.useValue(new BigDecimal("288.79")))))
				.useSpecifiedLineTradeDelivery(RAM.createLineTradeDeliveryType()
					.useBilledQuantity(UDT.createQuantityType()
						.useUnitCode("XPP")
						.useValue(BigDecimal.ONE)))
				.useSpecifiedLineTradeSettlement(RAM.createLineTradeSettlementType()
					.useApplicableTradeTax(RAM.createTradeTaxType()
						.useTypeCode(QDT.createTaxTypeCodeType()
							.useValue(DutyTaxFeeTypeCodeContentType.VALUE_ADDED_TAX))
						.useCategoryCode(QDT.createTaxCategoryCodeType()
							.useValue(DutyorTaxorFeeCategoryCodeContentType.STANDARD_RATE))
						.useRateApplicablePercent(UDT.createPercentType()
							.useValue(new BigDecimal("7"))))
					.useBillingSpecifiedPeriod(RAM.createSpecifiedPeriodType()
						.useStartDateTime(UDT.createDateTimeType()
							.useDateTimeString(UDT.createDateTimeDateTimeStringType()
								.useFormat(DateOnlyFormatCodeContentType.CCYYMMDD.value())
								.useValue("20160101")))
						.useEndDateTime(UDT.createDateTimeType()
							.useDateTimeString(UDT.createDateTimeDateTimeStringType()
								.useFormat(DateOnlyFormatCodeContentType.CCYYMMDD.value())
								.useValue("20161231"))))
					.useSpecifiedTradeSettlementLineMonetarySummation(RAM.createTradeSettlementLineMonetarySummationType()
						.useLineTotalAmount(UDT.createAmountType()
							.useValue(new BigDecimal("288.79"))))))
			.useIncludedSupplyChainTradeLineItem(RAM.createSupplyChainTradeLineItemType()
				.useAssociatedDocumentLineDocument(RAM.createDocumentLineDocumentType()
					.useLineID(UDT.createIDType()
						.useValue("Porto + Versandkosten")))
				.useSpecifiedTradeProduct(RAM.createTradeProductType()
					.useName(UDT.createTextType()
						.useValue("Porto + Versandkosten")))
				.useSpecifiedLineTradeAgreement(RAM.createLineTradeAgreementType()
					.useNetPriceProductTradePrice(RAM.createTradePriceType()
						.useChargeAmount(UDT.createAmountType()
							.useValue(new BigDecimal("26.07")))))
				.useSpecifiedLineTradeDelivery(RAM.createLineTradeDeliveryType()
					.useBilledQuantity(UDT.createQuantityType()
						.useUnitCode("XPP")
						.useValue(BigDecimal.ONE)))
				.useSpecifiedLineTradeSettlement(RAM.createLineTradeSettlementType()
					.useApplicableTradeTax(RAM.createTradeTaxType()
						.useTypeCode(QDT.createTaxTypeCodeType()
							.useValue(DutyTaxFeeTypeCodeContentType.VALUE_ADDED_TAX))
						.useCategoryCode(QDT.createTaxCategoryCodeType()
							.useValue(DutyorTaxorFeeCategoryCodeContentType.STANDARD_RATE))
						.useRateApplicablePercent(UDT.createPercentType()
							.useValue(new BigDecimal("7"))))
					.useSpecifiedTradeSettlementLineMonetarySummation(RAM.createTradeSettlementLineMonetarySummationType()
						.useLineTotalAmount(UDT.createAmountType()
							.useValue(new BigDecimal("26.07"))))))
			.useApplicableHeaderTradeAgreement(RAM.createHeaderTradeAgreementType()
				.useBuyerReference(UDT.createTextType()
					.useValue("04011000-12345-03"))
				.useSellerTradeParty(RAM.createTradePartyType()
					.useName(UDT.createTextType()
						.useValue("[Seller name]"))
					.useDescription(UDT.createTextType()
						.useValue("123/456/7890, HRA-Eintrag in […]"))
					.useSpecifiedLegalOrganization(RAM.createLegalOrganizationType()
						.useID(UDT.createIDType()
							.useValue("[HRA-Eintrag]"))
						.useTradingBusinessName(UDT.createTextType()
							.useValue("[Seller trading name]")))
					.useDefinedTradeContact(RAM.createTradeContactType()
						.usePersonName(UDT.createTextType()
							.useValue("nicht vorhanden"))
						.useTelephoneUniversalCommunication(RAM.createUniversalCommunicationType()
							.useCompleteNumber(UDT.createTextType()
								.useValue("+49 1234-5678")))
						.useEmailURIUniversalCommunication(RAM.createUniversalCommunicationType()
							.useURIID(UDT.createIDType()
								.useValue("seller@email.de"))))
					.usePostalTradeAddress(RAM.createTradeAddressType()
						.usePostcodeCode(UDT.createCodeType()
							.useValue("12345"))
						.useLineOne(UDT.createTextType()
							.useValue("[Seller address line 1]"))
						.useCityName(UDT.createTextType()
							.useValue("[Seller city]"))
						.useCountryID(QDT.createCountryIDType()
							.useValue(ISOTwoletterCountryCodeContentType.DE)))
					.useSpecifiedTaxRegistration(RAM.createTaxRegistrationType()
						.useID(UDT.createIDType()
							.useSchemeID(ReferenceTypeCodeContentType.VAT_REGISTRATION_NUMBER.value())
							.useValue("DE 123456789"))))
				.useBuyerTradeParty(RAM.createTradePartyType()
					.useID(UDT.createIDType()
						.useValue("[Buyer identifier]"))
					.useName(UDT.createTextType()
						.useValue("[Buyer name]"))
					.usePostalTradeAddress(RAM.createTradeAddressType()
						.usePostcodeCode(UDT.createCodeType()
							.useValue("12345"))
						.useLineOne(UDT.createTextType()
							.useValue("[Buyer address line 1]"))
						.useCityName(UDT.createTextType()
							.useValue("[Buyer city]"))
						.useCountryID(QDT.createCountryIDType()
							.useValue(ISOTwoletterCountryCodeContentType.DE)))
				)
			)
			.useApplicableHeaderTradeDelivery(RAM.createHeaderTradeDeliveryType())
			.useApplicableHeaderTradeSettlement(RAM.createHeaderTradeSettlementType()
				.useInvoiceCurrencyCode(QDT.createCurrencyCodeType()
					.useValue(ISO3AlphaCurrencyCodeContentType.EUR))
				.useSpecifiedTradeSettlementPaymentMeans(RAM.createTradeSettlementPaymentMeansType()
					.useTypeCode(QDT.createPaymentMeansCodeType()
						.useValue(PaymentMeansCodeContentType.SEPA_CREDIT_TRANSFER))
					.usePayeePartyCreditorFinancialAccount(RAM.createCreditorFinancialAccountType()
						.useIBANID(UDT.createIDType()
							.useValue("DE75512108001245126199")))
					)
				.useApplicableTradeTax(RAM.createTradeTaxType()
					.useCalculatedAmount(UDT.createAmountType()
						.useValue(new BigDecimal("22.04")))
					.useTypeCode(QDT.createTaxTypeCodeType()
						.useValue(DutyTaxFeeTypeCodeContentType.VALUE_ADDED_TAX))
					.useBasisAmount(UDT.createAmountType()
						.useValue(new BigDecimal("314.86")))
					.useCategoryCode(QDT.createTaxCategoryCodeType()
						.useValue(DutyorTaxorFeeCategoryCodeContentType.STANDARD_RATE))
					.useRateApplicablePercent(UDT.createPercentType()
						.useValue(new BigDecimal("7"))))
				.useSpecifiedTradePaymentTerms(RAM.createTradePaymentTermsType()
					.useDescription(UDT.createTextType()
						.useValue("Zahlbar sofort ohne Abzug.")))
				.useSpecifiedTradeSettlementHeaderMonetarySummation(RAM.createTradeSettlementHeaderMonetarySummationType()
					.useLineTotalAmount(UDT.createAmountType()
						.useValue(new BigDecimal("314.86")))
						.useTaxBasisTotalAmount(UDT.createAmountType()
							.useValue(new BigDecimal("314.86")))
						.useTaxTotalAmount(UDT.createAmountType()
							.useCurrencyID(ISO3AlphaCurrencyCodeContentType.EUR.toString())
							.useValue(new BigDecimal("22.04")))
						.useGrandTotalAmount(UDT.createAmountType()
							.useValue(new BigDecimal("336.9")))
						.useDuePayableAmount(UDT.createAmountType()
							.useValue(new BigDecimal("336.9")))
					)
				)
		);
		
		// Assert that the fluent construct matches the sample.
		assertEquals(cii1, cii2, "Instance should match sample");
				
		String xmlDoc = marshal(getMarshaller(), cii2);
		getLogger().debug("Document:\n\n{}", xmlDoc);
		
	}
	
	/**
	 * Override the test method in AbstractSamplesTest to read
	 * this project's sample files and assert expectations.
	 */
	@Override
	protected void checkSample(File sample) throws Exception
	{
		setFailFast(true);
		
		Object root = getUnmarshaller().unmarshal(sample);
		assertNotNull(root, "root cannot be null");
		String xmlRoot = marshal(getMarshaller(), root);
		getLogger().debug("Root:\n\n" + xmlRoot);
		
		root = getValue(root);

		if ( root instanceof CrossIndustryInvoiceType )
		{
			CrossIndustryInvoiceType cii = (CrossIndustryInvoiceType) root;
			
			assertNotNull(cii.getExchangedDocumentContext(), "Expected ExchangedDocumentContext");
			assertNotNull(cii.getExchangedDocumentContext().getTestIndicator(), "Expected ExchangedDocumentContext TestIndicator");
			assertTrue(cii.getExchangedDocumentContext().getTestIndicator().isIndicator());
			
			assertNotNull(cii.getExchangedDocument(), "Expected ExchangedDocument");
			assertNotNull(cii.getSupplyChainTradeTransaction(), "Expected SupplyChainTradeTransaction");
			
			// getLogger().debug("CrossIndustryInvoiceType: {}", cii);
			// getLogger().debug("  ExchangedDocumentContext: {}", cii.getExchangedDocumentContext());
			// getLogger().debug("  ExchangedDocument: {}", cii.getExchangedDocument());
			// getLogger().debug("  SupplyChainTradeTransaction: {}", cii.getSupplyChainTradeTransaction());
			// getLogger().debug("  ValuationBreakdownStatement: {}", cii.getValuationBreakdownStatement());
		}
		else
			getLogger().debug("Root: {}", root);
	}

	// Marshall a JAXB object into a String for logging, etc.
	private String marshal(Marshaller marshaller, Object jaxbObject)
		throws JAXBException, IOException
	{
		String xml;
		try ( StringWriter sw = new StringWriter() )
		{
			marshaller.marshal(jaxbObject, sw);
			xml = sw.toString();
		}
		return xml;
	}

	@SuppressWarnings("unused")
	private void generateXmlSchemaValidatorFromDom() throws JAXBException
	{
		try
		{
			if ( (getMarshaller() != null) && (getUnmarshaller() != null) )
			{
				// Generate a Schema Validator from given the JAXB context.
				SchemaOutputDomResolver sodr = new SchemaOutputDomResolver();
				getJaxbContext().generateSchema(sodr);
				SchemaFactory schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
				Schema schemaValidator = schemaFactory.newSchema(sodr.getDomSource());
				
				// Configure Marshaller / unmarshaller to use validator.
				getMarshaller().setSchema(schemaValidator);
				getUnmarshaller().setSchema(schemaValidator);
				
				println("Schema Validation of XML is ON.");
			}
			else
				errorln("Please create marshaller and unmarshaller!");
		}
		catch ( IOException | SAXException ex )
		{
			errorln("generateXmlSchemaValidatorFromDom", ex);
		}
	}

	private void errorln(String msg)
	{
		getLogger().error(msg);
	}
	
	private void errorln(String msg, Exception ex)
	{
		getLogger().error(msg, ex);
		
	}
	
	private void println(String msg)
	{
		getLogger().info(msg);
	}
}
