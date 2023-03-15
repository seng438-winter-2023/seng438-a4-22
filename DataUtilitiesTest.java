package org.jfree.data;
import static org.junit.Assert.*;

import java.lang.Object;
import org.jmock.*;
import org.junit.*;
import java.security.*;
import java.util.*;

public class DataUtilitiesTest {
	DefaultKeyedValues2D nullValues2D = new DefaultKeyedValues2D();
	DefaultKeyedValues2D emptyValues2D = new DefaultKeyedValues2D();
	DefaultKeyedValues2D positiveValues2D = new DefaultKeyedValues2D();
	DefaultKeyedValues2D fullValues2D = new DefaultKeyedValues2D();
	DefaultKeyedValues nullValues = new DefaultKeyedValues();
	DefaultKeyedValues nominalValues = new DefaultKeyedValues();
	DefaultKeyedValues expectedValues = new DefaultKeyedValues();
	double[][] arrA1 = {{0, 0}, {0, 0}};
	double[][] arrA2 = {{0, 0}, {0, 0}, {0, 0}};
	double[][] arrA3 = {{1, 0}, {0, 0}, {0, 0}};
	double[][] arrB = {{0, 0}, {0, 0}, {0, 0}};
	int[] validRows = {0, 2};
	int[] invalidRows = {3};
	int[] validCols = {0, 2};
	int[] invalidCols = {3};


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setupExpectedValues() {
		// Setting up Values2D with null values
		nullValues2D.addValue(null, 0, 0);
		nullValues2D.addValue(null, 0, 1);
		nullValues2D.addValue(null, 1, 0);
		// Setting up empty Values2D
		
		// Setting up Values2D with positive values
		positiveValues2D.addValue(2, 0, 0);
		positiveValues2D.addValue(3, 0, 1);
		positiveValues2D.addValue(1, 1, 0);
		positiveValues2D.addValue(6, 1, 1);
		positiveValues2D.addValue(4, 0, 2);
		positiveValues2D.addValue(2, 2, 0);
		positiveValues2D.addValue(2, 1, 2);
		positiveValues2D.addValue(4, 2, 1);
		positiveValues2D.addValue(5, 2, 2);

		// Setting up null values 
		nullValues.addValue("0", null);
		nullValues.addValue("1", null);
		nullValues.addValue("2", null);

		// Setting up nominal values
		nominalValues.addValue("0", 5);
		nominalValues.addValue("1", 9);
		nominalValues.addValue("2", 2);

		// Setting up nominal values
		expectedValues.addValue("0", 0.3125);
		expectedValues.addValue("1", 0.875);
		expectedValues.addValue("2", 1.0);

	}


	@Test
	/**
	 *
	 */
	public void calculateColumnTotalForTwoValues() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(1, 0);
				will(returnValue(2.5));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals("Expected result is 10", 10.0, result, .000000001d);
	}

	@Test
	/**
	 *
	 */
	public void calculateColumnTotalForTwoValuesOneNegative() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(1, 0);
				will(returnValue(-2.5));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals("Expected result is 5", 5.0, result, .000000001d);
	}

	@Test
	/**
	 *
	 */
	public void calculateColumnTotalForTwoValuesBothNegative() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(-7.5));
				one(values).getValue(1, 0);
				will(returnValue(-2.5));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals("Expected result is -10", -10.0, result, .000000001d);
	}

	@Test
	/**
	 *
	 */
	public void calculateColumnTotalZeroValues() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(0));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals("Expected result is 0", 0, result, .000000001d);
	}

//	@Test (expected = InvalidParameterException.class)
//	/**
//	 *
//	 */
//	public void calculateColumnTotalTestInvalidData() {
//		Mockery mockingContext = new Mockery();
//		final Values2D values = mockingContext.mock(Values2D.class);
//		mockingContext.checking(new Expectations() {
//			{
//				one(values).getRowCount();
//				will(returnValue(2));
//				one(values).getValue(0, -1);
//				will(returnValue(2));
//				one(values).getValue(1, -1);
//				will(returnValue(2));
//			}
//		});
//		DataUtilities.calculateColumnTotal(values, -1);
//	}

	@Test
	/**
	 *
	 */
	public void calculateColumnTotalNullValuesTest() {
		double result = DataUtilities.calculateColumnTotal(nullValues2D, 0);
		assertEquals("Expected result is 0.0", 0.0, result, .000000001d);

	}

	@Test (expected = IllegalArgumentException.class)
	/**
	 *
	 */
	public void calculateColumnTotalNullTest() {
		DataUtilities.calculateColumnTotal(null, 0);
//		assertEquals("Expected result is 0.0", 0.0, result, .000000001d);
	}
	
	@Test
	/**
	 * 
	 */
	public void calculateColumnTotalValidRowTest() {
		double result = DataUtilities.calculateColumnTotal(positiveValues2D, 0, validRows);
		assertEquals("Expected result is 4.0", 4.0, result, .000000001d);
	}
	
	@Test
	/**
	 * 
	 */
	public void calculateColumnTotalInvalidRowTest() {
		double result = DataUtilities.calculateColumnTotal(positiveValues2D, 0, invalidRows);
		assertEquals("Expected result is 0.0", 0.0, result, .000000001d);
	}
	
	@Test (expected = IllegalArgumentException.class)
	/**
	 *
	 */
	public void calculateColumnTotalNullTest2() {
		 DataUtilities.calculateColumnTotal(null, 0, null);
	}
	
	@Test
	/**
	 *
	 */
	public void calculateColumnTotalEmptyValues2DTest() {
		double result = DataUtilities.calculateColumnTotal(emptyValues2D, 0);
		assertEquals("Expected result is 0.0", 0.0, result, .000000001d);
	}

	@Test
	/**
	 *
	 */
	public void calculateRowTotalForTwoValues() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(0, 1);
				will(returnValue(2.5));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals("Expected result is 10", 10.0, result, .000000001d);
	}

	@Test
	/**
	 *
	 */
	public void calculateRowTotalForTwoValuesOneNegative() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(0, 1);
				will(returnValue(-2.5));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals("Expected result is 5", 5.0, result, .000000001d);
	}

	@Test
	/**
	 *
	 */
	public void calculateRowTotalForTwoValuesBothNegative() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(-7.5));
				one(values).getValue(0, 1);
				will(returnValue(-2.5));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals("Expected result is -10", -10.0, result, .000000001d);
	}

	@Test
	/**
	 *
	 */
	public void calculateRowTotalZeroValues() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(0));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals("Expected result is 0", 0, result, .000000001d);
	}

//	@Test (expected = InvalidParameterException.class)
//	/**
//	 *
//	 */
//	public void calculateRowTotalTestInvalidData() {
//		Mockery mockingContext = new Mockery();
//		final Values2D values = mockingContext.mock(Values2D.class);
//		mockingContext.checking(new Expectations() {
//			{
//				one(values).getColumnCount();
//				will(returnValue(2));
//				one(values).getValue(-1, 0);
//				will(returnValue(2));
//				one(values).getValue(-1, 1);
//				will(returnValue(2));
//			}
//		});
//		DataUtilities.calculateRowTotal(values, -1);
//	}

	@Test
	/**
	 *
	 */
	public void calculateRowTotalNullValuesTest() {
		double result = DataUtilities.calculateRowTotal(nullValues2D, 0);
		assertEquals("Expected result is 0.0", 0.0, result, .000000001d);

	}
	
	@Test (expected = IllegalArgumentException.class)
	/**
	 *
	 */
	public void calculateRowTotalNullTest() {
		DataUtilities.calculateRowTotal(null, 0);
	}
	
	
	@Test
	/**
	 * 
	 */
	public void calculateRowTotalValidColumnTest() {
		double result = DataUtilities.calculateRowTotal(positiveValues2D, 0, validCols);
		assertEquals("Expected result is 6.0", 6.0, result, .000000001d);
	}
	
	@Test
	/**
	 * 
	 */
	public void calculateRowTotalInvalidColumnTest() {
		double result = DataUtilities.calculateRowTotal(positiveValues2D, 0, invalidCols);
		assertEquals("Expected result is 0.0", 0.0, result, .000000001d);
	}
	
	@Test (expected = IllegalArgumentException.class)
	/**
	 *
	 */
	public void calculateRowTotalNullTest2() {
		DataUtilities.calculateRowTotal(null, 0, null);
	}

	@Test
	/**
	 *
	 */
	public void createNumberArrayTestTwoValueArray() {
		final double array[] = {0,2};
		final java.lang.Number expected[] = new java.lang.Number[2];
		expected[0] = 0.0;
		expected[1] = 2.0;
		java.lang.Number[] result = DataUtilities.createNumberArray(array);
		assertArrayEquals("Expected result is [0,2]", expected, result);
	}

	@Test
	/**
	 *
	 */
	public void createNumberArrayTestZeroValueArray() {
		final double array[] = {};
		final java.lang.Number expected[] = new java.lang.Number[0];
		java.lang.Number[] result = DataUtilities.createNumberArray(array);
		assertArrayEquals("Expected result is []", expected, result);
	}

	@Test (expected = IllegalArgumentException.class)
	/**
	 *
	 */
	public void createNumberArrayNullTest() {
		DataUtilities.createNumberArray(null);
	}
	
	@Test
	/**
	 *
	 */
	public void createNumberArray2DTestTwoByTwoArray() {
		final double array[][] = {{0,2},{1,3}};
		final java.lang.Number expected[][] = new java.lang.Number[2][2];
		expected[0][0] = 0.0;
		expected[0][1] = 2.0;
		expected[1][0] = 1.0;
		expected[1][1] = 3.0;

		java.lang.Number[][] result = DataUtilities.createNumberArray2D(array);
		assertArrayEquals("Expected result is [[0,2],[1,3]]", expected, result);
	}

	@Test
	/**
	 *
	 */
	public void createNumberArray2DTestZeroValue2DArray() {
		final double array[][] = {};
		final java.lang.Number expected[][] = new java.lang.Number[0][0];
		java.lang.Number[][] result = DataUtilities.createNumberArray2D(array);
		assertArrayEquals("Expected result is [[]]", expected, result);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	/**
	 *
	 */
	public void createNumberArray2DNullTest() {
		DataUtilities.createNumberArray2D(null);
	}

	@Test (expected = IllegalArgumentException.class)
	/**
	 * Test case for exception when invalid data is provided
	 */
	public void getCumulativePercentagesNullData() {
		DataUtilities.getCumulativePercentages(null);
	}

//	@Test (expected = ArithmeticException.class)
//	/**
//	 *
//	 */
//	public void getCumulativePercentagesNullValues() {
//		DataUtilities.getCumulativePercentages(nullValues);
//	}

	@Test
	/**
	 *
	 */
	public void getCumulativePercentagesNominalValues() {
		KeyedValues result = DataUtilities.getCumulativePercentages(nominalValues);
		assertEquals("Expected to be 0.3125", expectedValues.getValue("0"),result.getValue("0"));
		assertEquals("Expected to be 0.875", expectedValues.getValue("1"),result.getValue("1"));
		assertEquals("Expected to be 1.0", expectedValues.getValue("2"),result.getValue("2"));
	}

	@Test
	/**
	 *
	 */
	public void equalNullATest() {
		assertEquals("Expected to return false", false, DataUtilities.equal(null, arrB));
	}
	
	@Test
	/**
	 *
	 */
	public void equalNullBothTest() {
		assertEquals("Expected to return true", true, DataUtilities.equal(null, null));
	}
	

	@Test
	/**
	 *
	 */
	public void equalNotNullANullBTest() {
		assertEquals("Expected to return false", false, DataUtilities.equal(arrA1, null));
	}

	@Test
	/**
	 *
	 */
	public void equalLenNotEqualTest() {
		assertEquals("Expected to return false", false, DataUtilities.equal(arrA1, arrB));
	}

	@Test
	/**
	 *
	 */
	public void equalLenEqualTest() {
		assertEquals("Expected to return false", false, DataUtilities.equal(arrA3, arrB));
	}

	@Test
	/**
	 *
	 */
	public void equalNominalTest() {
		assertEquals("Expected to return true", true, DataUtilities.equal(arrA2, arrB));
	}

	@Test
	/**
	 * 
	 * 
	 */
	public void cloneA1Test() {
		double[][] clone = DataUtilities.clone(arrA1);
		assertArrayEquals("Expected to return {{0, 0}, {0, 0}}", arrA1, clone);
	}
	
	@Test (expected = IllegalArgumentException.class)
	/**
	 *
	 */
	public void cloneNullTest() {
		DataUtilities.clone(null);
	}

	@AfterClass
	public static void tearDown() throws Exception {
	}

}
