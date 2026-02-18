package org.example.swing;

import static java.lang.Math.abs;
import static org.jvnet.basicjaxb.testing.Bogus.SentenceLength.MEDIUM_SENTENCE;
import static org.jvnet.basicjaxb.testing.Bogus.SentenceLength.SHORT_SENTENCE;
import static org.slf4j.LoggerFactory.getLogger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import org.example.PurchaseOrder.model.Credit;
import org.example.PurchaseOrder.model.Item;
import org.example.PurchaseOrder.model.Items;
import org.example.PurchaseOrder.model.ObjectFactory;
import org.example.PurchaseOrder.model.Payment;
import org.example.PurchaseOrder.model.Payments;
import org.example.PurchaseOrder.model.PurchaseOrder;
import org.example.PurchaseOrder.model.USAddress;
import org.glassfish.jaxb.runtime.marshaller.NamespacePrefixMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.testing.Bogus;
import org.jvnet.basicjaxb.testing.Bogus.CatalogProduct;
import org.jvnet.basicjaxb.xml.XmlContext;
import org.slf4j.Logger;

@Order(2)
public class RunBogusDataTest
{
	private static Logger logger = getLogger(RunBogusDataTest.class);
	private static final ObjectFactory OF = new ObjectFactory();
	private static final NamespacePrefixMapper NPM = new NamespacePrefixMapper()
	{
	    @Override
	    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix)
	    {
	    	String pf = null;
	    	switch (namespaceUri)
	    	{
	    		case "\"http://example.org/PurchaseOrder\"": pf = "po"; break;
	    		default: pf = suggestion != null ? suggestion : "po"; break;
	    	}
	        return pf;
	    }
	};
	private static final XmlContext XC = new XmlContext(OF.getClass(), NPM);
	private static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	@Test
	public void testBogusPurchaseOrder() throws Exception
	{
		USAddress billTo = null;
		String stateCode = null;

		PurchaseOrder po = OF.createPurchaseOrder()
			.useOrderDate(Bogus.xmlLocalDate("P1Y", "P0Y"))
			.useComplete(Bogus.logic())
			.useBillTo(billTo = OF.createUSAddress()
				.useName(Bogus.fullName())
				.useStreet(Bogus.streetAddress())
				.useCity(Bogus.cityName())
				.useState(stateCode = Bogus.stateCode())
				.useZip(Bogus.zip(stateCode))
				.useCountry(Bogus.countryCode2()))
			.useShipTo(Bogus.probability(4, 5) ? billTo : OF.createUSAddress()
   				.useName(Bogus.fullName())
				.useStreet(Bogus.streetAddress())
				.useCity(Bogus.cityName())
				.useState(stateCode = Bogus.stateCode())
				.useZip(Bogus.zip(stateCode))
				.useCountry(Bogus.countryCode2()))
			.useComment(Bogus.sentence(MEDIUM_SENTENCE));

		// Items
		List<CatalogProduct> catalog = Bogus.catalog();
		BigDecimal totalPrice = BigDecimal.ZERO;
		Items items = OF.createItems();
		int itemsCount = Bogus.RANDOM.nextInt(3);
		while ( itemsCount-- >= 0 )
		{
			CatalogProduct product = Bogus.randomItem(catalog);
			Item item = OF.createItem()
				.usePartNum(to3D2A(product.partNum))
				.useProductName(product.description)
				.usePicture(product.picture)
				.useQuantity(Bogus.RANDOM.nextInt(3)+1)
				.useUSPrice(product.price)
				.useComment(Bogus.sentence(SHORT_SENTENCE))
				.useShipDate(Bogus.xmlLocalDate("P0Y", "P0Y1M", po.getOrderDate()))
				;
			items.getItem().add(item);
			totalPrice = totalPrice.add(item.getUSPrice());
		}
		po.setItems(items);

		// Payments
		Payments payments = OF.createPayments()
			.useTotal(totalPrice);
		int paymentsCount = Bogus.RANDOM.nextInt(3);
		BigDecimal divisor = new BigDecimal(paymentsCount+1);
		BigDecimal paymentPrice = totalPrice.divide(divisor,2,RoundingMode.DOWN);
		LocalDate previousDate = po.getOrderDate();
		while ( paymentsCount-- >= 0 )
		{
			Payment payment = OF.createPayment()
				.usePaymentDate(Bogus.xmlLocalDate("P0Y", "P0Y1M", previousDate))
				.useValue(paymentPrice);
			previousDate = payment.getPaymentDate();
			payments.getPayment().add(payment);
		}
		BigDecimal remainder = totalPrice.subtract(paymentPrice.multiply(divisor));
		if ( remainder.compareTo(BigDecimal.ZERO) > 0 )
		{
			Payment payment = payments.getPayment().get(0);
			payment.setValue(payment.getValue().add(remainder));
		}
		po.setPayments(payments);

		// Credits
		BigDecimal discount = new BigDecimal(5 * ( 1 + Bogus.RANDOM.nextInt(3)));
		BigDecimal discountPct = discount.divide(discount, 2, RoundingMode.HALF_EVEN);
		Credit credit = OF.createCredit()
			.useAmount(totalPrice.multiply(discountPct).doubleValue())
			.useReason(Bogus.sentence(SHORT_SENTENCE));
		po.useCredits(credit);

		String poXml = XC.marshalToString(OF.createPurchaseOrder(po));
		logger.debug("PO XML:\n\n{}", poXml);
	}

	private String to3D2A(String partNum)
	{
		int hash10 = abs(partNum.hashCode());
		String hash10S = Integer.toString(hash10);
		int hash10SL = hash10S.length();
		int hash26R1 = hash10 % 26;
		int hash26R2 = (hash10 - hash26R1) % 26;
		String to2A = ABC.substring(hash26R2,hash26R2+1)
					+ ABC.substring(hash26R1,hash26R1+1);
		String to3D = hash10S.substring(hash10SL-3, hash10SL);
		return to3D + "-" + to2A;
	}
}
