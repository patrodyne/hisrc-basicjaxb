package org.example.uncefact;

import static java.lang.String.format;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.JAXBIntrospector;
import jakarta.xml.bind.Unmarshaller;
import un.unece.uncefact.data.standard.crossindustryinvoice._100.CrossIndustryInvoiceType;
import un.unece.uncefact.data.standard.crossindustryinvoice._100.ObjectFactory;
import un.unece.uncefact.data.standard.reusableaggregatebusinessinformationentity._100.HeaderTradeAgreementType;
import un.unece.uncefact.data.standard.reusableaggregatebusinessinformationentity._100.ReferencedDocumentType;
import un.unece.uncefact.data.standard.reusableaggregatebusinessinformationentity._100.SupplyChainTradeTransactionType;
import un.unece.uncefact.data.standard.unqualifieddatatype._100.BinaryObjectType;

/**
 * Example of unmarshalling a UNCEFACT Cross Industry Invoice
 */
public class Main
{
	public static final String SAMPLE1_CII_FILENAME = "src/test/samples/01.01a-INVOICE_uncefact.xml";
	public static final String SAMPLE2_CII_FILENAME = "src/test/samples/01.15a-INVOICE_uncefact.xml";
	public static final String SAMPLE_CII_FILENAME = SAMPLE1_CII_FILENAME;
	public static final String TARGET_PATH = "target";
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	public static Logger getLogger() { return logger; }
	
	private JAXBContext jaxbContext;
	public JAXBContext getJaxbContext() throws JAXBException
	{
		if ( jaxbContext == null )
			setJaxbContext(JAXBContext.newInstance(ObjectFactory.class));
		return jaxbContext;
	}
	public void setJaxbContext(JAXBContext jaxbContext)
	{
		this.jaxbContext = jaxbContext;
	}

	private Unmarshaller unmarshaller = null;
	protected Unmarshaller getUnmarshaller() throws JAXBException
	{
		if ( unmarshaller == null )
			setUnmarshaller(getJaxbContext().createUnmarshaller());
		return unmarshaller;
	}
	protected void setUnmarshaller(Unmarshaller unmarshaller)
	{
		this.unmarshaller = unmarshaller;
	}

	// Represents the unmarshalled CrossIndustryInvoice.
	private CrossIndustryInvoiceType cii = null;
	public CrossIndustryInvoiceType getCrossIndustryInvoice() { return cii; }
	public void setCrossIndustryInvoice(CrossIndustryInvoiceType cii) { this.cii = cii; }
	public void setCrossIndustryInvoice(JAXBElement<CrossIndustryInvoiceType> je)
	{
		this.cii = (CrossIndustryInvoiceType) JAXBIntrospector.getValue(je);
	}

	/**
	 * Command Line Invocation.
	 * @param args CLI argument(s)
	 */
	public static void main(String[] args) throws Exception
	{
		String sampleName = args.length > 0 ? args[0] : SAMPLE_CII_FILENAME;
		String targetPath = args.length > 1 ? args[1] : TARGET_PATH;
		Main main = new Main();
		main.execute(sampleName, targetPath);
	}
	
	@SuppressWarnings("unchecked")
	private void execute(String sampleName, String targetPath) throws Exception
	{
		File xmlFile = new File(sampleName);
        setCrossIndustryInvoice((JAXBElement<CrossIndustryInvoiceType>) getUnmarshaller().unmarshal(xmlFile));
        String docId = getCrossIndustryInvoice().getExchangedDocument().getID().getValue();
		println(format("CrossIndustryInvoice: " + docId + "\n\n" + getCrossIndustryInvoice() + "\n"));
		SupplyChainTradeTransactionType sct = getCrossIndustryInvoice().getSupplyChainTradeTransaction();
		HeaderTradeAgreementType hta = sct.getApplicableHeaderTradeAgreement();
		for ( ReferencedDocumentType rd : hta.getAdditionalReferencedDocument() )
		{
			for ( BinaryObjectType bo : rd.getAttachmentBinaryObject() )
			{
				if ( bo.getMimeCode().equals("application/pdf") )
				{
					if ( bo.getFilename() != null )
					{
						File file = new File(bo.getFilename());
						File targetFile = new File(targetPath + "/" + file.getName());
						try ( OutputStream os = new FileOutputStream(targetFile) )
						{
							os.write(bo.getValue());
						}
						println(format("Output: [%s]", targetFile));
					}
				}
			}
		}
	}

	/**
	 * Print a text string.
	 * @param text The text to be printed.
	 */
    public static void println(String text)
	{
		getLogger().info(text);
	}
}
// vi:set tabstop=4 hardtabs=4 shiftwidth=4:
