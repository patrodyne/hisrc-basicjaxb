# Sample: UN/CEFACT Cross Industry Invoice

This is a Maven project to demonstrate how to use the **XJC** plugins `Xannotate` and `XfluentAPI` to generate **JAXB** classes. The `Xannotate` extension is used to add a Java annotation to one of the generated classes: `@XmlRootElement`. And, the `XfluentAPI` extension adds `useXXX` methods on each generated **JAXB** property for method chaining.

This project generates **JAXB** classes for the [_UN/CEFACT_][50] schemas publicly available at [CII_D23B_0.zip][51] and [XMLSchemas-D23B.zip][52]. The first ZIP is used to generate the JAXB classes and the second zip is scraped to provide additional [CCTS][53] names and descriptions.

Sample XML data from these GitHub repositories is gratefully included in this project to implement unit testing for the generated schema:

+ [ConnectingEurope/eInvoicing-EN16931][54]
+ [xrechnung-testsuite][55]

Each of the sample data files provides an XML representation of a mock `CrossIndustryInvoice` instance. The generated **JAXB** class for this element is annotated with `@XmlRootElement` to support _unmarshalling_ and _marshaling_ in the unit tests and in the sample application.

Further, it includes examples of **XJC** plugins to add custom `hashCode`, `equals`, `toString` and implementations to each generated JAXB class.

#### Execution

This is a stand-alone Maven project. You can run the test using:

~~~
mvn -Ptest clean test
mvn -Pexec compile exec:java
~~~

The [output][3] shows the test results.

### Problem ( from [StackOverflow](https://stackoverflow.com/questions/79283071/) )

+ How to create files from XSD schema with XJC plugins: `Xannotate` and `XfluentAPI`?

### Solution

You can use these dependencies to facilitate your migration from JAXB 2 to latest version. In fact, the provided [demo (zip)][1] uses these artifacts in its [`pom.xml`][4]

~~~
+- org.patrodyne.jvnet:hisrc-higherjaxb-maven-plugin:2.2.1
+- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
+- org.glassfish.jaxb:jaxb-runtime:jar:4.0.5:runtime
|  \- org.glassfish.jaxb:jaxb-core:jar:4.0.5:runtime
+- org.patrodyne.jvnet:hisrc-basicjaxb-runtime:jar:2.2.1:compile
+- org.patrodyne.jvnet:hisrc-basicjaxb-testing:jar:2.2.1:test
|  +- org.junit.jupiter:junit-jupiter-api:jar:5.10.2:test
|  \- org.glassfish.jaxb:jaxb-xjc:jar:4.0.5:test
\- org.slf4j:slf4j-simple:jar:2.0.12:runtime
~~~

The demo is a stand-alone Maven project. After downloading it and expanding the zip to your local path, you can run the test(s) and/or execute the application using:

~~~
mvn -Ptest clean test
mvn -Pexec compile exec:java
~~~

Here's an example of the [OUTPUT.txt][3] from running unit test(s).

#### Xannotate

The demo includes this binding file to add an `@XmlRootElement` annotation to the Java class generated for the `CrossIndustryInvoiceType` from the [CrossIndustryInvoice_100pD23B.xsd][21].

**Binding file: [uncefact1.xjb][22]**
~~~
<jaxb:bindings schemaLocation="uncefact/CII_D23B_0/100/CrossIndustryInvoice_100pD23B.xsd" node="/xs:schema">
    <jaxb:bindings node="xs:complexType[@name='CrossIndustryInvoiceType']">

        <!-- Annotate CrossIndustryInvoiceType with XmlRootElement -->
        <annox:annotate>@jakarta.xml.bind.annotation.XmlRootElement(name="CrossIndustryInvoice")</annox:annotate>

        <!-- Annotate namespace(s) with custom prefixes -->
        ...

    </jaxb:bindings>
</jaxb:bindings>
~~~

#### XfluentAPI

The demo uses the `XfleuntAPI` option to add method chaining to the generated classes. Here is an example of unit test that fluently configures an instance of `CrossIndustryInvoiceType`.

**[InvoiceTest.java][34]**
~~~
...
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
...
~~~


<!-- References -->

[1]: https://github.com/patrodyne/hisrc-basicjaxb/releases/download/2.1.1/hisrc-basicjaxb-sample-uncefact-2.1.1-mvn-src.zip
[2]: /README.md
[3]: /OUTPUT.txt
[4]: /project-pom.xml
[5]: /bin/CreateToplevelXJBindings.sh
[6]: /bin/CreateTypesafeEnumMember.ant
[7]: /bin/run.cmd
[8]: /bin/run.sh
[9]: /bin/CreateTypesafeEnumMember.sh
[10]: /doc/annox.xsd
[11]: /doc/bindingschema_3_0.xsd
[20]: /src/main/resources/uncefact/13DEC23
[21]: /src/main/resources/uncefact/CII_D23B_0
[22]: /src/main/resources/uncefact1.xjb
[23]: /src/main/resources/uncefact2.xjb
[24]: /src/main/resources/uncefact3.xjb
[25]: /src/main/resources/uncefact.xsd
[26]: /src/main/java/org/example/uncefact/Main.java
[30]: /src/test/samples
[31]: /src/test/resources/jvmsystem.arguments
[32]: /src/test/resources/jvmsystem.properties
[33]: /src/test/resources/simplelogger.properties
[34]: /src/test/java/org/example/uncefact/InvoiceTest.java
[40]: https://github.com/patrodyne/hisrc-basicjaxb#readme
[41]: https://github.com/patrodyne/hisrc-higherjaxb#readme
[50]: https://unece.org/trade/uncefact
[51]: https://unece.org/sites/default/files/2024-09/CII_D23B_0.zip
[52]: https://unece.org/sites/default/files/2024-01/XMLSchemas-D23B.zip
[53]: https://en.wikipedia.org/wiki/Core_Component_Technical_Specification
[54]: https://github.com/ConnectingEurope/eInvoicing-EN16931/tree/master/cii/examples
[55]: https://projekte.kosit.org/xrechnung/xrechnung-testsuite/-/tree/74f90a88bafe0a91d5faefa92b106ec20e9a0409/src/test/business-cases
