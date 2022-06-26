package org.jvnet.jaxb2_commons.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Utility to generate bogus data for testing.
 *
 * @author Rick O'Sullivan
 */
public class Bogus
{
	public static final Random RANDOM = new Random();
	private static final String[] FIRST_NAMES = {
	    "James", "Mary",
	    "Robert", "Patricia",
	    "John", "Jennifer",
	    "Michael", "Linda",
	    "William", "Elizabeth",
	    "David", "Barbara",
	    "Richard", "Susan",
	    "Joseph", "Jessica",
	    "Thomas", "Sarah",
	    "Charles", "Karen",
	    "Christopher", "Nancy",
	    "Daniel", "Lisa",
	    "Matthew", "Betty",
	    "Anthony", "Margaret",
	    "Mark", "Sandra",
	    "Donald", "Ashley",
	    "Steven", "Kimberly",
	    "Paul", "Emily",
	    "Andrew", "Donna",
	    "Joshua", "Michelle"
	};
	private static final String[] LAST_NAMES = {
		"Smith", "Johnson", "Williams", "Brown", "Jones",
		"Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
		"Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
		"Thomas", "Taylor", "Moore", "Jackson", "Martin",
		"Lee", "Perez", "Thompson", "White", "Harris",
		"Sanchez", "Clark", "Ramirez", "Lewis", "Robinson",
		"Walker", "Young", "Allen", "King", "Wright",
		"Scott", "Torres", "Nguyen", "Hill", "Flores",
		"Green", "Adams", "Nelson", "Baker", "Hall",
		"Rivera", "Campbell", "Mitchell", "Carter", "Roberts",
		"D'Amato", "D'Aragon", "D'Entremont", "D'Hemecourt", "D'Orange",
		"D'Angelo", "D'Elia", "D'Gravio", "D'Ippolito", "D'Orazio",
		"O'Brien", "O'Donnell", "O'Neill", "O'Reilly", "O'Shea"
	};
	private static final String[] STREET_NAMES = {
		"Second", "Third", "First", "Fourth", "Park",
		"Fifth", "Main", "Sixth", "Oak", "Seventh",
		"Pine", "Maple", "Cedar", "Eighth", "Elm",
		"View", "Washington", "Ninth", "Lake", "Hill"
	};
	private static final String[] STREET_TYPES = {
	    "Ave Avenue", "Dr Drive", "Rd Road", "St Street", "Terr Terrace"
	};
	private static final String[] CITY_NAMES = {
		"Franklin", "Fairview", "Clinton", "Greenville", "Madison",
		"Marion", "Salem", "Arlington", "Georgetown", "Clayton",
		"Manchester", "Springfield", "Milton", "Ashland", "Oxford",
		"Riverside", "Chester", "Newport", "Milford", "Burlington",
		"Dayton", "Waverly", "Washington", "Oakland", "Kingston"
	};
	private static final String[] STATE_NAMES = {
		"AL Alabama", "AK Alaska", "AZ Arizona", "AR Arkansas", "CA California",
		"CO Colorado", "CT Connecticut", "DE Delaware", "DC District of Columbia",
		"FL Florida", "GA Georgia", "GU Guam", "HI Hawaii", "ID Idaho",
		"IL Illinois", "IN Indiana", "IA Iowa", "KS Kansas", "KY Kentucky",
		"LA Louisiana", "ME Maine", "MD Maryland", "MA Massachusetts", "MI Michigan",
		"MN Minnesota", "MS Mississippi", "MO Missouri", "MT Montana", "NE Nebraska",
		"NV Nevada", "NH New Hampshire", "NJ New Jersey", "NM New Mexico", "NY New York",
		"NC North Carolina", "ND North Dakota", "OH Ohio", "OK Oklahoma", "OR Oregon",
		"PA Pennsylvania", "PR Puerto Rico", "RI Rhode Island", "SC South Carolina", "SD South Dakota",
		"TN Tennessee", "TX Texas", "UT Utah", "VT Vermont", "VI Virgin Islands",
		"VA Virginia", "WA Washington", "WV West Virginia", "WI Wisconsin", "WY Wyoming"
	};
	private static final String[][] ZIP_INDEX = {
		{ "CT", "MA", "ME", "NH", "NJ", "PR", "RI", "VT", "VI", "AE" }, // NY
		{ "DE", "NY", "PA" },
		{ "DC", "MD", "NC", "SC", "VA", "WV" },
		{ "AL", "FL", "GA", "MS", "TN", "AA" },
		{ "IN", "KY", "MI", "OH" },
		{ "IA", "MN", "MT", "ND", "SD", "WI" },
		{ "IL", "KS", "MO", "NE" },
		{ "AR", "LA", "OK", "TX" },
		{ "AZ", "CO", "ID", "NM", "NV", "UT", "WY" },
		{ "AK", "AS", "CA", "GU", "HI", "MH", "FM", "MP", "OR", "PW", "WA", "AP" }
	};
	private static final Map<String, String> ZIP_MAP = new HashMap<>();
	static {
		for ( int index=0; index < 10; ++index )
		{
			for ( int state=0; state < ZIP_INDEX[index].length; ++state )
				ZIP_MAP.put(ZIP_INDEX[index][state], String.valueOf(index));
		}
	}

	private static class Product
	{
		public String partNum;
		public String picture;
		public String description;

		private Product(String partNum, String picture, String description)
		{
			this.partNum = partNum;
			this.picture = picture;
			this.description = description;
		}
	}

	public static class CatalogProduct extends Product
	{
		public String catalog;
		public BigDecimal price;

		public CatalogProduct(Product product, String catalog, BigDecimal price)
		{
			super(product.partNum, product.picture, product.description);
			this.catalog = catalog;
			this.price = price;
		}
	}

	private static Product[] PRODUCTS =
	{
		new Product("U2615", "☕","hot beverage"),
		new Product("U1F33D","🌽","ear of maize"),
		new Product("U1F33E","🌾","ear of rice"),
		new Product("U1F33F","🌿","herb"),
		new Product("U1F344","🍄","mushroom"),
		new Product("U1F345","🍅","tomato"),
		new Product("U1F346","🍆","aubergine"),
		new Product("U1F347","🍇","grapes"),
		new Product("U1F348","🍈","melon"),
		new Product("U1F349","🍉","watermelon"),
		new Product("U1F34A","🍊","tangerine"),
		new Product("U1F34B","🍋","lemon"),
		new Product("U1F34C","🍌","banana"),
		new Product("U1F34D","🍍","pineapple"),
		new Product("U1F34E","🍎","red apple"),
		new Product("U1F34F","🍏","green apple"),
		new Product("U1F350","🍐","pear"),
		new Product("U1F351","🍑","peach"),
		new Product("U1F352","🍒","cherries"),
		new Product("U1F353","🍓","strawberry"),
		new Product("U1F354","🍔","hamburger"),
		new Product("U1F355","🍕","slice of pizza"),
		new Product("U1F356","🍖","meat on bone"),
		new Product("U1F357","🍗","poultry leg"),
		new Product("U1F358","🍘","rice cracker"),
		new Product("U1F359","🍙","rice ball"),
		new Product("U1F35A","🍚","cooked rice"),
		new Product("U1F35B","🍛","curry and rice"),
		new Product("U1F35C","🍜","steaming bowl"),
		new Product("U1F35D","🍝","spaghetti"),
		new Product("U1F35E","🍞","bread"),
		new Product("U1F35F","🍟","french fries"),
		new Product("U1F360","🍠","roasted sweet potato"),
		new Product("U1F361","🍡","dango"),
		new Product("U1F362","🍢","oden"),
		new Product("U1F363","🍣","sushi"),
		new Product("U1F364","🍤","fried shrimp"),
		new Product("U1F365","🍥","fish cake with swirl design"),
		new Product("U1F366","🍦","soft ice cream"),
		new Product("U1F367","🍧","shaved ice"),
		new Product("U1F368","🍨","ice cream"),
		new Product("U1F369","🍩","doughnut"),
		new Product("U1F36A","🍪","cookie"),
		new Product("U1F36B","🍫","chocolate bar"),
		new Product("U1F36C","🍬","candy"),
		new Product("U1F36D","🍭","lollipop"),
		new Product("U1F36E","🍮","custard"),
		new Product("U1F36F","🍯","honey pot"),
		new Product("U1F370","🍰","shortcake"),
		new Product("U1F371","🍱","bento box"),
		new Product("U1F372","🍲","pot of food"),
		new Product("U1F373","🍳","cooking"),
		new Product("U1F374","🍴","fork and knife"),
		new Product("U1F375","🍵","teacup without handle"),
		new Product("U1F437","🐷","pork")
	};

	
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz)
    {
        int index = RANDOM.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[index];
    }
	
    public static <T> T randomItem(List<T> list)
    {
        int index = RANDOM.nextInt(list.size());
        return list.get(index);
    }
	
	public static String firstName()
	{
		return FIRST_NAMES[ RANDOM.nextInt(FIRST_NAMES.length) ];
	}

	public static String lastName()
	{
		return LAST_NAMES[ RANDOM.nextInt(LAST_NAMES.length) ];
	}

	public static String alpha()
	{
		int rindex = RANDOM.nextInt(26);
		return"abcdefghijklmnopqrstuvwxyz".substring(rindex, rindex+1);
	}

	public static String alpha(int size)
	{
		StringBuilder alphas = new StringBuilder();
		for ( int index=0; index < size; ++index )
			alphas.append(alpha());
		return alphas.toString();
	}
	
	public static int digit(int size)
	{
		int lo = 1;
		for ( int index = 0; index < (size-1); ++index )
			lo *= 10;
		int hi = 9 * lo;
		return lo + RANDOM.nextInt(hi);
	}

	public static String fullName()
	{
		return firstName() + " " + alpha().toUpperCase() + ". " + lastName();
	}

	public static String streetName()
	{
		return STREET_NAMES[ RANDOM.nextInt(STREET_NAMES.length) ];
	}

	public static String streetTypeCode()
	{
		String street = STREET_TYPES[ RANDOM.nextInt(STREET_TYPES.length) ];
		return street.split(" ")[0];
	}

	public static String streetTypeName()
	{
		String street = STREET_TYPES[ RANDOM.nextInt(STREET_TYPES.length) ];
		return street.split(" ")[1];
	}

	public static String streetAddress()
	{
		return RANDOM.nextInt(1000) + " " + streetName() + " " + streetTypeCode();
	}

	public static String cityName()
	{
		return CITY_NAMES[ RANDOM.nextInt(CITY_NAMES.length) ];
	}

	public static String stateCode()
	{
		String state = STATE_NAMES[ RANDOM.nextInt(STATE_NAMES.length) ];
		return state.substring(0,2);
	}

	public static String stateName()
	{
		String state = STATE_NAMES[ RANDOM.nextInt(STATE_NAMES.length) ];
		return state.substring(3);
	}

	public static String stateName(String code)
	{
		String name = null;
		for (String state : STATE_NAMES )
		{
			if ( state.startsWith(code) )
			{
				name = state.substring(3);
				break;
			}
		}
		return name;
	}

	public static String countyCode2()
	{
		return "US";
	}

	public static String countyCode3()
	{
		return "USA";
	}

	public static String countyName()
	{
		return "United States of America";
	}

	public static String countyName(String code)
	{
		return "United States of America";
	}

	public static String zip(String stateCode)
	{
		return String.format("%s%04d", ZIP_MAP.get(stateCode), RANDOM.nextInt(10000));
	}

	public static List<CatalogProduct> catalog()
	{
		List<CatalogProduct> catalog = new ArrayList<>();
		String catalogId = (alpha()+alpha()+alpha()+alpha()).toUpperCase();
		for ( Product product : PRODUCTS )
		{
			String value = String.format("%.2f", 1.00 + 99.00*RANDOM.nextDouble());
			BigDecimal price = new BigDecimal(value);
			catalog.add(new CatalogProduct(product, catalogId, price));
		}
		return catalog;
	}
}
