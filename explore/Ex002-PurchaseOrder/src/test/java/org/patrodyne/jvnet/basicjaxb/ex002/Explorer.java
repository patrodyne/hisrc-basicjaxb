package org.patrodyne.jvnet.basicjaxb.ex002;

import static java.lang.Integer.toHexString;
import static java.lang.System.identityHashCode;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.patrodyne.jvnet.basicjaxb.ex002.model.Address;
import org.patrodyne.jvnet.basicjaxb.ex002.model.AddressBatch;
import org.patrodyne.jvnet.basicjaxb.ex002.model.Batch;
import org.patrodyne.jvnet.basicjaxb.ex002.model.CarrierType;
import org.patrodyne.jvnet.basicjaxb.ex002.model.Catalogue;
import org.patrodyne.jvnet.basicjaxb.ex002.model.CatalogueBatch;
import org.patrodyne.jvnet.basicjaxb.ex002.model.CatalogueType;
import org.patrodyne.jvnet.basicjaxb.ex002.model.Item;
import org.patrodyne.jvnet.basicjaxb.ex002.model.ObjectFactory;
import org.patrodyne.jvnet.basicjaxb.ex002.model.PaymentType;
import org.patrodyne.jvnet.basicjaxb.ex002.model.Preference;
import org.patrodyne.jvnet.basicjaxb.ex002.model.Product;
import org.patrodyne.jvnet.basicjaxb.ex002.model.PurchaseOrder;
import org.patrodyne.jvnet.basicjaxb.ex002.model.PurchaseOrderBatch;
import org.patrodyne.jvnet.basicjaxb.ex002.model.Stage;
import org.patrodyne.jvnet.basicjaxb.explore.AbstractExplorer;
import org.patrodyne.jvnet.basicjaxb.validation.SchemaOutputDomResolver;
import org.patrodyne.jvnet.basicjaxb.validation.SchemaOutputStringResolver;
import org.xml.sax.SAXException;

/**
 * A Swing application to explore features of the HiSrc BasicJAXB Runtime library.
 * 
 * Projects create their own custom Explorer by extending AbstractExplorer and
 * providing an HTML lesson page then adding JMenuItem(s) to trigger exploratory code.
 * 
 * The AbstractExplorer displays three panels: an HTML lesson, a print console and an
 * error console. The lesson file is read as a resource relative to this class. Text
 * is sent to the print console by calling 'println(text)' and error messages are
 * sent to the error console by calling 'errorln(msg)'. Also, 'standard out' /
 * 'standard error' streams are sent to their respective consoles.
 * 
 * @author Rick O'Sullivan
 */
@SuppressWarnings("serial")
public class Explorer extends AbstractExplorer
{
	private static final String WINDOW_TITLE = "HiSrc BasicJAXB Ex002 PurchaseOrder";
	private static final String EXPLORER_HTML = "Explorer.html";
	
	private static final File ADDRESS_BATCH = new File("src/test/examples/AddressBatch.xml");
	private static final File CATALOGUE_BATCH = new File("src/test/examples/CatalogueBatch.xml");
	private static final File PURCHASE_ORDER_BATCH = new File("src/test/examples/PurchaseOrderBatch.xml");

	private static final Random RANDOM = new Random();
	private static final String CHAOS = Character.toString('\uF91B');
	
	private ObjectFactory objectFactory;
	public ObjectFactory getObjectFactory() { return objectFactory; }
	public void setObjectFactory(ObjectFactory objectFactory) { this.objectFactory = objectFactory; }

	private JAXBContext jaxbContext;
	public JAXBContext getJaxbContext() { return jaxbContext; }
	public void setJaxbContext(JAXBContext jaxbContext) { this.jaxbContext = jaxbContext; }

	private Marshaller marshaller;
	public Marshaller getMarshaller() { return marshaller; }
	public void setMarshaller(Marshaller marshaller) { this.marshaller = marshaller; }

	private Unmarshaller unmarshaller;
	public Unmarshaller getUnmarshaller() { return unmarshaller; }
	public void setUnmarshaller(Unmarshaller unmarshaller) { this.unmarshaller = unmarshaller; }
	
	private AddressBatch addressList;
	public AddressBatch getAddressBatch() { return addressList; }
	public void setAddressBatch(AddressBatch addressList) { this.addressList = addressList; }

	private CatalogueBatch catalogueList;
	public CatalogueBatch getCatalogueBatch() { return catalogueList; }
	public void setCatalogueBatch(CatalogueBatch catalogueList) { this.catalogueList = catalogueList; }

	private PurchaseOrderBatch purchaseOrderList;
	public PurchaseOrderBatch getPurchaseOrderBatch() { return purchaseOrderList; }
	public void setPurchaseOrderBatch(PurchaseOrderBatch purchaseOrderList) { this.purchaseOrderList = purchaseOrderList; }

	/**
	 * Main entry point for command line invocation.
	 * @param args CLI arguments
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() -> {
			JFrame frame = new Explorer(EXPLORER_HTML);
			frame.setVisible(true);
		});
	}

	/**
	 * Construct application with an HTML lesson.
	 */
	public Explorer(String htmlName)
	{
		super(htmlName);
		setTitle(WINDOW_TITLE);
		try
		{
			setJMenuBar(createMenuBar());
			modifyToolBar();
			initializeLesson();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void generateXmlSchemaFromString()
	{
		try
		{
			SchemaOutputStringResolver sosr = new SchemaOutputStringResolver();
			getJaxbContext().generateSchema(sosr);
			println("Xml Schema from String:\n\n" + sosr.getSchemaString());
		}
		catch ( IOException ex )
		{
			errorln(ex);
		}
	}
	
	public void generateXmlSchemaFromDom()
	{
		try
		{
			SchemaOutputDomResolver sodr = new SchemaOutputDomResolver();
			getJaxbContext().generateSchema(sodr);
			println("Xml Schema from DOM:\n\n" + sodr.getSchemaDomNodeString());
		}
		catch ( IOException | TransformerException ex )
		{
			errorln(ex);
		}
	}
	
	public void generateXmlSchemaValidatorFromDom()
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
				
				println("Schema Validator:\n\n" + schemaValidator);
				println();
			}
			else
				errorln("Please create marshaller and unmarshaller!");
		}
		catch ( IOException | SAXException ex )
		{
			errorln(ex);
		}
	}
	
	public void unmarshalAddressBatch()
	{
		AddressBatch addressBatch = unmarshal(ADDRESS_BATCH);
		setAddressBatch(addressBatch);
	}
	
	public void unmarshalCatalogueBatch()
	{
		CatalogueBatch catalogueBatch = unmarshal(CATALOGUE_BATCH);
		setCatalogueBatch(catalogueBatch);
	}
	
	public void unmarshalPurchaseOrderBatch()
	{
		PurchaseOrderBatch purchaseOrderBatch = unmarshal(PURCHASE_ORDER_BATCH);
		setPurchaseOrderBatch(purchaseOrderBatch);
	}

	@SuppressWarnings("unchecked")
	private <T> T unmarshal(File file)
	{
		T dto = null;
		try
		{
			dto = (T) getUnmarshaller().unmarshal(file);
			println("Unmarshaled " + file.getName());
		}
		catch (JAXBException ex)
		{
			errorln(ex);
		}
		return dto;
	}

	public void marshalAddressBatch()
	{
		marshal("AddressBatch", getAddressBatch());
	}

	public void marshalCatalogueBatch()
	{
		marshal("CatalogueBatch", getCatalogueBatch());
	}

	public void marshalPurchaseOrderBatch()
	{
		marshal("PurchaseOrderBatch", getPurchaseOrderBatch());
	}
	
	private void marshal(String label, Object entity)
	{
		String phc = toHexString(entity.hashCode());
		String ihc = toHexString(identityHashCode(entity));
		String entityXml = marshalToString(entity);
		if ( entityXml != null )
			println(label + " XML: (Entity#=" + phc + ", Object#=" + ihc + ")\n\n" + entityXml);
		else
			println(label + " Error: (Entity#=" + phc + ", Object#=" + ihc + ")\n\n" + entity);
		println();
	}
	
	private String identify(Object object)
	{
		String objectId = toHexString(identityHashCode(object));
		String entityId = toHexString(object.hashCode());
		return "Object Id = " + objectId + "\t-> Entity Id = " + entityId;
	}

	public void compareHashCodes(Batch batch)
	{
		println("Compare HashCodes\n");
		println("Batch Type: "+batch.getClass().getSimpleName());
		println("Batch Time: "+batch.getBatchTime());
		println("Batch Size: "+batch.size());
		println();
		
		for ( Object item : batch.list() )
		{
			println(identify(item));
		}
		println();
	}
	
	public void compareEquality(Batch batch)
	{
		println("Compare Equality\n");
		println("Batch Type: "+batch.getClass().getSimpleName());
		println("Batch Time: "+batch.getBatchTime());
		println("Batch Size: "+batch.size());
		println();
		
		int needleIndex = RANDOM.nextInt(batch.size());
		Object needle = batch.list().get(needleIndex);
		println("Searching for index: " + needleIndex + "; " + identify(needle));
		println();
		
		for ( int index = 0; index < batch.size(); ++index )
		{
			Object haystack = batch.list().get(index);
			if ( needle.equals(haystack) )
				println("Comparing index: " + index + "; Match: " + identify(haystack));
			else
				println("Comparing index: " + index);
		}
		println();
	}
	
	public void compareToString(Batch batch)
	{
		println("Compare ToString\n");
		println("Batch Type: "+batch.getClass().getSimpleName());
		println("Batch Time: "+batch.getBatchTime());
		println("Batch Size: "+batch.size());
		println();
		
		for ( Object item : batch.list() )
		{
			println(item.toString());
		}
		println();
	}

	public void roundtripEquality(Batch batch)
	{
		println("Roundtrip Equality\n");
		println("Batch Type: "+batch.getClass().getSimpleName());
		println("Batch Time: "+batch.getBatchTime());
		println("Batch Size: "+batch.size());
		println();
		
		if ( batch.size() > 0 )
		{
			int sampleIndex = RANDOM.nextInt(batch.size());
			Object sample1 = batch.list().get(sampleIndex);
			println("Sample index: " + sampleIndex);
			println();

			Object sample2 = null;
			switch ( batch.getClass().getSimpleName() )
			{
				case "AddressBatch": sample2 = roundtrip((AddressBatch) batch, (Address) sample1); break;
				case "CatalogueBatch": sample2 = roundtrip((CatalogueBatch) batch, (Catalogue) sample1); break;
				case "PurchaseOrderBatch": sample2 = roundtrip((PurchaseOrderBatch) batch, (PurchaseOrder) sample1); break;
			}

			println(identify(sample1) + ": "+sample1);
			println(identify(sample2) + ": "+sample2);
			println();
			
			println("Sample1 vs Sample2: " + (sample1.equals(sample2) ? "EQUAL" : "UNEQUAL"));
			println();
		}
	}
	
	private Address roundtrip(AddressBatch batch, Address address1)
	{
		String xml = marshalToString(getObjectFactory().createAddress(address1));
		return unmarshalFromString(xml, Address.class);
	}

	private Catalogue roundtrip(CatalogueBatch batch, Catalogue catalogue1)
	{
		String xml = marshalToString(getObjectFactory().createCatalogue(catalogue1));
		return unmarshalFromString(xml, Catalogue.class);
	}

	private PurchaseOrder roundtrip(PurchaseOrderBatch batch, PurchaseOrder purchaseOrder1)
	{
		String xml = marshalToString(getObjectFactory().createPurchaseOrder(purchaseOrder1));
		return unmarshalFromString(xml, PurchaseOrder.class);
	}
	
	public void roundtripInequality(Batch batch)
	{
		println("Roundtrip Equality\n");
		println("Batch Type: "+batch.getClass().getSimpleName());
		println("Batch Time: "+batch.getBatchTime());
		println("Batch Size: "+batch.size());
		println();
		
		if ( batch.size() > 0 )
		{
			int sampleIndex = RANDOM.nextInt(batch.size());
			Object sample1 = batch.list().get(sampleIndex);
			println("Sample index: " + sampleIndex);
			println();

			Object sample2 = null;
			switch ( batch.getClass().getSimpleName() )
			{
				case "AddressBatch": sample2 = chaos(roundtrip((AddressBatch) batch, (Address) sample1)); break;
				case "CatalogueBatch": sample2 = chaos(roundtrip((CatalogueBatch) batch, (Catalogue) sample1)); break;
				case "PurchaseOrderBatch": sample2 = chaos(roundtrip((PurchaseOrderBatch) batch, (PurchaseOrder) sample1)); break;
			}

			println(identify(sample1) + ": "+sample1);
			println(identify(sample2) + ": "+sample2);
			println();
			
			println("Sample1 vs Sample2: " + (sample1.equals(sample2) ? "EQUAL" : "UNEQUAL"));
			println();
		}
	}

	private Address chaos(Address address)
	{
		switch (RANDOM.nextInt(7))
		{
			case 0: address.setStage(chaos(address.getStage())); break;
			case 1: address.setName(CHAOS); break;
			case 2: address.setStreet(CHAOS); break;
			case 3: address.setCity(CHAOS); break;
			case 4: address.setState(CHAOS); break;
			case 5: address.setZip(CHAOS); break;
			case 6: address.setCountry(CHAOS); break;
		}
		return address;
	}
	
	private Catalogue chaos(Catalogue catalogue)
	{
		switch (RANDOM.nextInt(5))
		{
			case 0: catalogue.setStage(chaos(catalogue.getStage())); break;
			case 1: catalogue.setCatalogueName(CHAOS); break;
			case 2: catalogue.setCatalogueType(chaos(catalogue.getCatalogueType())); break;
			case 3: catalogue.setDiscount(BigDecimal.ZERO); break;
			case 4: chaos(randomEntry(catalogue.getProducts())); break;
		}
		return catalogue;
	}
	
	private Product chaos(Product product)
	{
		switch (RANDOM.nextInt(5))
		{
			case 0: product.setStage(chaos(product.getStage())); break;
			case 1: product.setPartNum(CHAOS); break;
			case 2: product.setDescription(CHAOS); break;
			case 3: product.setPicture(CHAOS); break;
			case 4: product.setPrice(BigDecimal.ZERO); break;
		}
		return product;
	}
	
	private PurchaseOrder chaos(PurchaseOrder purchaseOrder)
	{
		switch (RANDOM.nextInt(8))
		{
			case 0: purchaseOrder.setStage(chaos(purchaseOrder.getStage())); break;
			case 1: purchaseOrder.setOrderNum(CHAOS); break;
			case 2: purchaseOrder.setOrderDate(null); break;
			case 3: purchaseOrder.setComment(CHAOS); break;
			case 4: chaos(purchaseOrder.getPreference());  break;
			case 5: chaos(purchaseOrder.getBillTo()); break;
			case 6: chaos(purchaseOrder.getShipTo()); break;
			case 7: chaos(purchaseOrder.getItems());  break;
		}
		return purchaseOrder;
	}
	
	private void chaos(Preference preference)
	{
		switch (RANDOM.nextInt(4))
		{
			case 0: preference.setStage(chaos(preference.getStage())); break;
			case 1: preference.setOrderNum(CHAOS); break;
			case 2: preference.setCarrierType(chaos(preference.getCarrierType())); break;
			case 3: preference.setPaymentType(chaos(preference.getPaymentType())); break;
		}
	}

	private void chaos(List<Item> items)
	{
		Item item = randomEntry(items);
		if ( RANDOM.nextInt(2) == 0 )
		{
			Item newItem = new Item();
			newItem.setItemNum("ITM-"+CHAOS+CHAOS+CHAOS+CHAOS);
			newItem.setProduct(item.getProduct());
			newItem.setComment(item.getComment());
			newItem.setQuantity(item.getQuantity());
			newItem.setShipDate(item.getShipDate());
			items.add(newItem);
		}
		else
		{
			switch (RANDOM.nextInt(6))
			{
				case 0: item.setStage(chaos(item.getStage())); break;
				case 1: item.setItemNum(CHAOS); break;
				case 2: chaos(item.getProduct()); break;
				case 3: item.setQuantity(0); break;
				case 4: item.setComment(CHAOS); break;
				case 5: item.setShipDate(null); break;
			}
		}
	}

	private Stage chaos(Stage value)
	{
		List<Stage> list = new ArrayList<>();
		for ( Stage other : Stage.values() )
		{
			if ( !other.equals(value) )
				list.add(other);
		}
		return randomEntry(list);
	}
	
	private CatalogueType chaos(CatalogueType value)
	{
		List<CatalogueType> list = new ArrayList<>();
		for ( CatalogueType other : CatalogueType.values() )
		{
			if ( !other.equals(value) )
				list.add(other);
		}
		return randomEntry(list);
	}
	
	private CarrierType chaos(CarrierType value)
	{
		List<CarrierType> list = new ArrayList<>();
		for ( CarrierType other : CarrierType.values() )
		{
			if ( !other.equals(value) )
				list.add(other);
		}
		return randomEntry(list);
	}
	
	private PaymentType chaos(PaymentType value)
	{
		List<PaymentType> list = new ArrayList<>();
		for ( PaymentType other : PaymentType.values() )
		{
			if ( !other.equals(value) )
				list.add(other);
		}
		return randomEntry(list);
	}
	
	private <T> T randomEntry(List<T> list)
	{
		int index = RANDOM.nextInt(list.size());
		return list.get(index);
	}

	/**
	 * Dispatch hyperlinks from the lesson to local method invocations.
	 * The markdown for hyperlinks in the lesson is declared like this:
	 * 
	 *   [description](!hyperLink)
	 */
	@Override
	public void dispatchHyperLink(String hyperLink)
	{
		switch ( hyperLink )
		{
			case "generateXmlSchemaFromString": generateXmlSchemaFromString(); break;
			case "generateXmlSchemaFromDom": generateXmlSchemaFromDom(); break;
			case "generateXmlSchemaValidatorFromDom": generateXmlSchemaValidatorFromDom(); break;
			case "unmarshalAddressBatch": unmarshalAddressBatch(); break;
			case "unmarshalCatalogueBatch": unmarshalCatalogueBatch(); break;
			case "unmarshalPurchaseOrderBatch": unmarshalPurchaseOrderBatch(); break;
			case "marshalAddressBatch": marshalAddressBatch(); break;
			case "marshalCatalogueBatch": marshalCatalogueBatch(); break;
			case "marshalPurchaseOrderBatch": marshalPurchaseOrderBatch(); break;
			case "compareHashCodesAddressBatch": compareHashCodes(getAddressBatch()); break;
			case "compareHashCodesCatalogueBatch": compareHashCodes(getCatalogueBatch()); break;
			case "compareHashCodesPurchaseOrderBatch": compareHashCodes(getPurchaseOrderBatch()); break;
			case "compareEqualityAddressBatch": compareEquality(getAddressBatch()); break;
			case "compareEqualityCatalogueBatch": compareEquality(getCatalogueBatch()); break;
			case "compareEqualityPurchaseOrderBatch": compareEquality(getPurchaseOrderBatch()); break;
			case "compareToStringAddressBatch": compareToString(getAddressBatch()); break;
			case "compareToStringCatalogueBatch": compareToString(getCatalogueBatch()); break;
			case "compareToStringPurchaseOrderBatch": compareToString(getPurchaseOrderBatch()); break;
			case "roundtripEqualityAddressBatch": roundtripEquality(getAddressBatch()); break;
			case "roundtripEqualityCatalogueBatch": roundtripEquality(getCatalogueBatch()); break;
			case "roundtripEqualityPurchaseOrderBatch": roundtripEquality(getPurchaseOrderBatch()); break;
			case "roundtripInequalityAddressBatch": roundtripInequality(getAddressBatch()); break;
			case "roundtripInequalityCatalogueBatch": roundtripInequality(getCatalogueBatch()); break;
			case "roundtripInequalityPurchaseOrderBatch": roundtripInequality(getPurchaseOrderBatch()); break;
		}
	}

	/*
	 * Create a JMenuBar to display JMenuItem(s) to trigger
	 * your own exploratory methods.
	 */
	public JMenuBar createMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		
		// Context Menu
		{
			JMenu contextMenu = new JMenu("Context");
			// Context: Generate XML Schema from String
			{
				JMenuItem menuItem = new JMenuItem("Generate XML Schema from String");
				menuItem.addActionListener((event) -> generateXmlSchemaFromString());
				contextMenu.add(menuItem);
			}
			// Context: Generate XML Schema from DOM
			{
				JMenuItem menuItem = new JMenuItem("Generate XML Schema from DOM");
				menuItem.addActionListener((event) -> generateXmlSchemaFromDom());
				contextMenu.add(menuItem);
			}
			// Context: Generate XML Schema Validator from DOM
			{
				JMenuItem menuItem = new JMenuItem("Generate XML Schema Validator from DOM");
				menuItem.addActionListener((event) -> generateXmlSchemaValidatorFromDom());
				contextMenu.add(menuItem);
			}
			menuBar.add(contextMenu);
		}

		// Unmarshal Menu
		{
			JMenu unmarshalMenu = new JMenu("Unmarshal");
			// Unmarshal: AddressBatch
			{
				JMenuItem menuItem = new JMenuItem("AddressBatch");
				menuItem.addActionListener((event) -> unmarshalAddressBatch());
				unmarshalMenu.add(menuItem);
			}
			// Unmarshal: CatalogueBatch
			{
				JMenuItem menuItem = new JMenuItem("CatalogueBatch");
				menuItem.addActionListener((event) -> unmarshalCatalogueBatch());
				unmarshalMenu.add(menuItem);
			}
			// Unmarshal: PurchaseOrderBatch
			{
				JMenuItem menuItem = new JMenuItem("PurchaseOrderBatch");
				menuItem.addActionListener((event) -> unmarshalPurchaseOrderBatch());
				unmarshalMenu.add(menuItem);
			}
			menuBar.add(unmarshalMenu);
		}

		// Marshal Menu
		{
			JMenu unmarshalMenu = new JMenu("Marshal");
			// Marshal: AddressBatch
			{
				JMenuItem menuItem = new JMenuItem("AddressBatch");
				menuItem.addActionListener((event) -> marshalAddressBatch());
				unmarshalMenu.add(menuItem);
			}
			// Marshal: CatalogueBatch
			{
				JMenuItem menuItem = new JMenuItem("CatalogueBatch");
				menuItem.addActionListener((event) -> marshalCatalogueBatch());
				unmarshalMenu.add(menuItem);
			}
			// Marshal: PurchaseOrderBatch
			{
				JMenuItem menuItem = new JMenuItem("PurchaseOrderBatch");
				menuItem.addActionListener((event) -> marshalPurchaseOrderBatch());
				unmarshalMenu.add(menuItem);
			}
			menuBar.add(unmarshalMenu);
		}

		// Compare Menu
		{
			JMenu compareMenu = new JMenu("Compare");
			// Compare: HashCodes
			{
				JMenu hashCodeMenu = new JMenu("HashCodes");
				// Compare: HashCodes AddressBatch
				{
					JMenuItem menuItem = new JMenuItem("AddressBatch");
					menuItem.addActionListener((event) -> compareHashCodes(getAddressBatch()));
					hashCodeMenu.add(menuItem);
				}
				// Compare: HashCodes CatalogueBatch
				{
					JMenuItem menuItem = new JMenuItem("CatalogueBatch");
					menuItem.addActionListener((event) -> compareHashCodes(getCatalogueBatch()));
					hashCodeMenu.add(menuItem);
				}
				// Compare: HashCodes PurchaseOrderBatch
				{
					JMenuItem menuItem = new JMenuItem("PurchaseOrderBatch");
					menuItem.addActionListener((event) -> compareHashCodes(getPurchaseOrderBatch()));
					hashCodeMenu.add(menuItem);
				}
				compareMenu.add(hashCodeMenu);
			}
			// Compare: Equality
			{
				JMenu equalityMenu = new JMenu("Equality");
				// Compare: Equality AddressBatch
				{
					JMenuItem menuItem = new JMenuItem("AddressBatch");
					menuItem.addActionListener((event) -> compareEquality(getAddressBatch()));
					equalityMenu.add(menuItem);
				}
				// Compare: Equality CatalogueBatch
				{
					JMenuItem menuItem = new JMenuItem("CatalogueBatch");
					menuItem.addActionListener((event) -> compareEquality(getCatalogueBatch()));
					equalityMenu.add(menuItem);
				}
				// Compare: Equality PurchaseOrderBatch
				{
					JMenuItem menuItem = new JMenuItem("PurchaseOrderBatch");
					menuItem.addActionListener((event) -> compareEquality(getPurchaseOrderBatch()));
					equalityMenu.add(menuItem);
				}
				compareMenu.add(equalityMenu);
			}
			// Compare: ToString
			{
				JMenu toStringMenu = new JMenu("ToString");
				// Compare: ToString AddressBatch
				{
					JMenuItem menuItem = new JMenuItem("AddressBatch");
					menuItem.addActionListener((event) -> compareToString(getAddressBatch()));
					toStringMenu.add(menuItem);
				}
				// Compare: ToString CatalogueBatch
				{
					JMenuItem menuItem = new JMenuItem("CatalogueBatch");
					menuItem.addActionListener((event) -> compareToString(getCatalogueBatch()));
					toStringMenu.add(menuItem);
				}
				// Compare: ToString PurchaseOrderBatch
				{
					JMenuItem menuItem = new JMenuItem("PurchaseOrderBatch");
					menuItem.addActionListener((event) -> compareToString(getPurchaseOrderBatch()));
					toStringMenu.add(menuItem);
				}
				compareMenu.add(toStringMenu);
			}
			menuBar.add(compareMenu);
		}

		// Roundtrip Menu
		{
			JMenu roundtripMenu = new JMenu("Roundtrip");
			// Roundtrip: Equality
			{
				JMenu equalityMenu = new JMenu("Equality");
				// Roundtrip: Equality AddressBatch
				{
					JMenuItem menuItem = new JMenuItem("AddressBatch");
					menuItem.addActionListener((event) -> roundtripEquality(getAddressBatch()));
					equalityMenu.add(menuItem);
				}
				// Roundtrip: Equality CatalogueBatch
				{
					JMenuItem menuItem = new JMenuItem("CatalogueBatch");
					menuItem.addActionListener((event) -> roundtripEquality(getCatalogueBatch()));
					equalityMenu.add(menuItem);
				}
				// Roundtrip: Equality PurchaseOrderBatch
				{
					JMenuItem menuItem = new JMenuItem("PurchaseOrderBatch");
					menuItem.addActionListener((event) -> roundtripEquality(getPurchaseOrderBatch()));
					equalityMenu.add(menuItem);
				}
				roundtripMenu.add(equalityMenu);
			}
			// Roundtrip: Inequality
			{
				JMenu inequalityMenu = new JMenu("Inequality");
				// Roundtrip: Equality AddressBatch
				{
					JMenuItem menuItem = new JMenuItem("AddressBatch");
					menuItem.addActionListener((event) -> roundtripInequality(getAddressBatch()));
					inequalityMenu.add(menuItem);
				}
				// Roundtrip: Equality CatalogueBatch
				{
					JMenuItem menuItem = new JMenuItem("CatalogueBatch");
					menuItem.addActionListener((event) -> roundtripInequality(getCatalogueBatch()));
					inequalityMenu.add(menuItem);
				}
				// Roundtrip: Equality PurchaseOrderBatch
				{
					JMenuItem menuItem = new JMenuItem("PurchaseOrderBatch");
					menuItem.addActionListener((event) -> roundtripInequality(getPurchaseOrderBatch()));
					inequalityMenu.add(menuItem);
				}
				roundtripMenu.add(inequalityMenu);
			}
			menuBar.add(roundtripMenu);
		}

		return menuBar;
	}

	public void modifyToolBar()
	{
		getToolBar().addSeparator();
		String validateOffPath = OILPATH+"/actions/flag-red.png";
		String validateOnPath = OILPATH+"/actions/flag-green.png";
		JToggleButton validateButton = createImageToggleButton(Explorer.class, validateOffPath, validateOnPath);
		validateButton.addActionListener((event) -> toggleValidateSchema(event));
		validateButton.setToolTipText("Toggle schema validation");
		getToolBar().add(validateButton);
	}
	
	private void toggleValidateSchema(ActionEvent event)
	{
		JToggleButton toggleButton = (JToggleButton) event.getSource();
		if ( toggleButton.isSelected() )
		{
			generateXmlSchemaValidatorFromDom();
			println("Schema Validation is ON.");
		}
		else
		{
			setMarshaller(createMarshaller(getJaxbContext()));
			setUnmarshaller(createUnmarshaller(getJaxbContext()));
			println("Schema Validation is OFF.");
		}
	}
	
	public void initializeLesson()
	{
		setObjectFactory(new ObjectFactory());
		setJaxbContext(createJAXBContext());
		setMarshaller(createMarshaller(getJaxbContext()));
		setUnmarshaller(createUnmarshaller(getJaxbContext()));
		setAddressBatch(new AddressBatch());
		setCatalogueBatch(new CatalogueBatch());
		setPurchaseOrderBatch(new PurchaseOrderBatch());
	}
	
	private JAXBContext createJAXBContext()
	{
		JAXBContext jaxbContext = null;
		try
		{
			Class<?>[] classesToBeBound =
				{ ObjectFactory.class, AddressBatch.class, CatalogueBatch.class,
				  PurchaseOrderBatch.class };
			jaxbContext = JAXBContext.newInstance(classesToBeBound);
		}
		catch ( JAXBException ex)
		{
			errorln(ex);
		}
		return jaxbContext;
	}

	protected Marshaller createMarshaller(JAXBContext jaxbContext)
	{
		Marshaller marshaller = null;
		try
		{
			if ( jaxbContext != null )
			{
				marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(JAXB_FORMATTED_OUTPUT, true);
			}
			else
				errorln("Cannot create marshaller because JAXB context is null!");
		}
		catch ( JAXBException ex )
		{
			errorln(ex);
		}
		return marshaller;
	}
	
	protected Unmarshaller createUnmarshaller(JAXBContext jaxbContext)
	{
		Unmarshaller unmarshaller = null;
		try
		{
			if ( jaxbContext != null )
			{
				unmarshaller = jaxbContext.createUnmarshaller();
			}
			else
				errorln("Cannot create unmarshaller because JAXB context is null!");
		}
		catch ( JAXBException ex )
		{
			errorln(ex);
		}
		return unmarshaller;
	}

	protected String marshalToString(Object instance)
	{
		String xml = null;
		try ( StringWriter writer = new StringWriter() )
		{
			getMarshaller().marshal(instance, writer);
			xml = writer.toString();
		}
		catch (JAXBException | IOException ex)
		{
			errorln(ex);
		}
		return xml;
	}

	@SuppressWarnings("unchecked")
	protected <T> T unmarshalFromString(String xml)
	{
		T instance = null;
		try ( StringReader reader = new StringReader(xml) )
		{
			instance = (T) getUnmarshaller().unmarshal(reader);
		}
		catch (JAXBException ex)
		{
			errorln(ex);
		}
		return instance;
	}


	@SuppressWarnings("unchecked")
	protected <T> T unmarshalFromString(String xml, Class<?> clazz)
	{
		T instance = null;
		try ( StringReader reader = new StringReader(xml) )
		{
			instance = (T) getUnmarshaller().unmarshal(new StreamSource(reader), clazz).getValue();
		}
		catch (JAXBException ex)
		{
			errorln(ex);
		}
		return instance;
	}

}
