package org.jfree.data;


import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.*;

public class RangeTest {
	Range testRangeDifferent;
	Range testRangeEqual;
	Range testRangeEqualScaledUp;
	Range testRangeExpandExpectedBLB;
	Range testRangeExpandExpectedAUB;
	Range testLowerNotEqual;
	Range testUpperNotEqual;
	Range testEqualExpected;
	Range normalRangeOne;
	Range normalRangeTwo;
	Range combinedRange;
	Range testRangeNegativeShift;
	Range doubledNormalRange;
	Range expandLowerGreaterThanHigher;
	Double constraintValue;

	@BeforeClass public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setupExpectedRanges() throws Exception {
		testRangeDifferent = new Range(-5, 5);
		testRangeEqual = new Range(5, 5);
		testRangeEqualScaledUp = new Range(10, 10);
		testRangeExpandExpectedBLB = new Range(-6, 5);
		testRangeExpandExpectedAUB = new Range(-5, 6);
		testLowerNotEqual = new Range (-6, 5);
		testUpperNotEqual = new Range(-5, 6);
		testEqualExpected = new Range(-5 ,5);
		normalRangeOne = new Range(5, 10);
		normalRangeTwo = new Range(-10, 5);
		combinedRange = new Range(-10, 10);
		testRangeNegativeShift = new Range(-5, 0);
		doubledNormalRange = new Range(-5, 20);
		expandLowerGreaterThanHigher = new Range(17.5, 17.5);
		constraintValue = 1.00;
	}

	@Test (expected = IllegalArgumentException.class)
	/**
	 *
	 */
	public void constructorTest() {
		Range range = new Range(10, 5);
	}
	
	@Test
	/**
	 * 
	 */
	public void containsMutations() throws Exception
	{
		Range newRange = new Range(3,4);
		boolean[] actual = new boolean[4];
		boolean[] expect = {true,true,false,false};
		actual[0] = newRange.contains(3);
		actual[1] = newRange.contains(4);
		actual[2] = newRange.contains(2);
		actual[3] = newRange.contains(5);
		
		for(int i = 0; i < 4; i++)
		{
			assertEquals(expect[i],actual[i]);
		}
		
	}
	
	@Test
	/**
	 * 
	 */
	public void intersectMutants() {
		Range newRange = new Range(3,4);
		boolean[] actual = new boolean[4];
		boolean[] expect = {true,true,false,false};
		actual[0] = newRange.intersects(3, 4);
		actual[1] = newRange.intersects(2,4);
		actual[2] = newRange.intersects(3, 5);
		actual[2] = newRange.intersects(4, 5);
		
		for (int i = 0; i < 4; i++) 
		{
			assertEquals(expect[i],actual[i]);
		}
		
	}
	
	@Test
	/**
	 * getLengthTestDifferentValues tests the getLength() method to see if returns the correct length of the range when lower != upper
	 */
	public void getLengthTestDifferentValues(){
		assertEquals("Expected length is 10.0", 10.0, testRangeDifferent.getLength(), 0.00000000d);
	}

	@Test
	/**
	 * getLengthTestEqualValues tests the getLength() method to see if returns the correct length of the range when lower == upper
	 */
	public void getLengthTestEqualValues(){
		assertEquals("Expected length is 0", 0, testRangeEqual.getLength(), 0.00000000d);
	}


	@Test
	/**
	 * getUpperBoundTestDifferentValues tests the the getUpperBound() method to see if it returns the correct upper bound of the range
	 * when lower != upper
	 */
	public void getUpperBoundTestDifferentValues() {
		assertEquals("Expected upper bound is 5.0", 5.0, testRangeDifferent.getUpperBound(), 0.00000000d);
	}

	@Test
	/**
	 * getUpperBoundTestEqualValues tests the the getUpperBound() method to see if it returns the correct upper bound of the range
	 * when lower == upper
	 */
	public void getUpperBoundTestEqualValues() {
		assertEquals("Expected upper bound is 5.0", 5.0, testRangeEqual.getUpperBound(), 0.00000000d);
	}

	@Test
	/**
	 * getLowerBoundTestDifferentValues tests the the getLowerBound() method to see if it returns the correct lower bound of the range
	 * when lower != upper
	 */
	public void getLowerBoundTestDifferentValues() {
		assertEquals("Expected lower bound is -5.0", -5.0, testRangeDifferent.getLowerBound(), 0.00000000d);
	}

	@Test
	/**
	 * getLowerBoundTestEqualValues tests the the getUpperBound() method to see if it returns the correct upper bound of the range
	 * when lower == upper
	 */
	public void getLowerBoundTestEqualValues() {
		assertEquals("Expected lower bound is 5.0", 5.0, testRangeEqual.getLowerBound(), 0.00000000d);
	}

	@Test
	/**
	 * containsTestBLB tests the constraint() method when the given value is below the lower bound
	 */
	public void containsTestBLB() {
		assertEquals("Expected to return false", false, testRangeDifferent.contains(-7));
	}

	@Test
	/**
	 * containsTestBLB tests the constraint() method when the given value is at the lower bound
	 */
	public void containsTestLB() {
		assertEquals("Expected to return true", true, testRangeDifferent.contains(-5));
	}

	@Test
	/**
	 * containsTestBLB tests the constraint() method when the given value is within the given range
	 */
	public void containsTestNOM() {
		assertEquals("Expected to return true", true, testRangeDifferent.contains(0));
	}

	@Test
	/**
	 * containsTestBLB tests the constraint() method when the given value at the upper bound
	 */
	public void containsTestUB() {
		assertEquals("Expected to return true", true, testRangeDifferent.contains(5));
	}

	@Test
	/**
	 * containsTestBLB tests the constraint() method when the given value above the upper bound
	 */
	public void containsTestAUB() {
		assertEquals("Expected to return false", false, testRangeDifferent.contains(7));
	}

	@Test (expected = IllegalArgumentException.class)
    /**
     *
     */
    public void expandNormalTestNull() {
        Range.expand(null, 2, 2);
    }
	
	@Test
    /**
     * containsTestBLB tests the constraint() method when the given value is below the lower bound
     */
    public void containsTestBLBMutant() {
        assertEquals("Expected to return false", false, testRangeDifferent.contains(-6));
    }
	
	@Test
    /**
     * containsTestBLB tests the constraint() method when the given value above the upper bound
     */
    public void containsTestAUBMutant() {
        assertEquals("Expected to return false", false, testRangeDifferent.contains(6));
    }
	
	public void intersectionTestBLBMutant() {
        assertEquals("Expected intersection result is false", false, testRangeDifferent.intersects(-10,-5));
    }
	
	@Test
	/**
	 * intersectionOutOfLowerBound tests the intersection value when the intersection range you are testing
	 * is outside of the lower bound of the original range.
	 * Expected value: False
	 */
	public void intersectionTestBLB() {
		assertEquals("Expected intersection result is false", false, testRangeDifferent.intersects(-10,-8));
	}

	@Test
	/**
	 * intersectionTestAtLB tests the intersection value when the intersection range you are testing
	 * is exactly at the upper-bound. The range is half within bounds and half outside.
	 * Expected Value: True
	 */
	public void intersectionTestLB() {
		assertEquals("Expected intersection result is true", true, testRangeDifferent.intersects(-10,0));
	}

	@Test
	/**
	 * intersectionOutOfLowerBound tests the intersection value when the intersection range you are testing
	 * is exactly within the range you are given.
	 * Expected Value: True
	 */
	public void intersectionTestNOM() {
		assertEquals("Expected intersection result is true", true, testRangeDifferent.intersects(-1,-1));
	}

	@Test
	/**
	 * intersectionTestAtUB tests the intersection value when the intersection range you are testing
	 * is exactly at the upper-bound. The range is half within bounds and half outside.
	 * Expected Value: True
	 */
	public void intersectionTestUB() {
		assertEquals("Expected intersection result is true", true, testRangeDifferent.intersects(0, 10));
	}

	@Test
	/**
	 * intersectionTestOutofUpperBound tests the intersection value when the intersection range you are testing
	 * is completely outside of the upper bound of the range you are testing.
	 * Expected Value: False
	 */
	public void intersectionTestAUB() {
		assertEquals("Expected intersection result is false", false, testRangeDifferent.intersects(10, 20));
	}

	@Test
	/**
	 * toStringTest()
	 */
	public void toStringTest() {
		assertEquals("Expected to return Range[-5,5]", "Range[5.0,5.0]", testRangeEqual.toString());
	}

	@Test (expected = IllegalArgumentException.class)
	/**
	 *
	 */
	public void scaleTestLessZero() {
		Range.scale(testRangeEqual, -1);
	}

	@Test
	/**
	 *
	 */
	public void scaleTestAboveEqualZero() {
		assertEquals("Expected to be [10,10]", testRangeEqualScaledUp, Range.scale(testRangeEqual, 2));
	}

	@Test
	/**
	 *
	 */
	public void hashcodeTest() {
		assertEquals("Expcted to be -2108162048", -2108162048, testRangeEqual.hashCode());
	}

	@Test
	/**
	 * Testing nominal value here
	 */
	public void expandToIncludeNullTest() {
		assertEquals("Expected range to be [5,5]", testRangeEqual, Range.expandToInclude(null, 5));
	}

	@Test
	/**
	 *
	 */
	public void expandToIncludeBLBTest() {
		assertEquals("Expected range to be [-6,5]", testRangeExpandExpectedBLB, Range.expandToInclude(testRangeDifferent, -6));
	}

	@Test
	/**
	 *
	 */
	public void expandToIncludeAUBTest() {
		assertEquals("Expected range to be [-5,6]", testRangeExpandExpectedAUB, Range.expandToInclude(testRangeDifferent, 6));
	}

	@Test
	/**
	 *
	 */
	public void expandToIncludeNominalTest() {
		assertEquals("Expected range to be [-5, 5]", testRangeDifferent, Range.expandToInclude(testRangeDifferent, 0));
	}


	@Test
	/**
	 *
	 */
	public void equalsNotRangeObjTest() {
		assertEquals("Expected to be false", false, testRangeDifferent.equals("String"));
	}

	@Test
	/**
	 *
	 */
	public void equalsLowerNotEqualTest() {
		assertEquals("Expected to be false", false, testRangeDifferent.equals(testLowerNotEqual));
	}

	@Test
	/**
	 *
	 */
	public void equalsUpperNotEqualTest() {
		assertEquals("Expected to be false", false, testRangeDifferent.equals(testUpperNotEqual));
	}

//	@Test
//	/**
//	 *
//	 */
//	public void equalsBoundsEqualTest() {
//		assertEquals("Expected to be false", false, testRangeDifferent.equals(testEqualExpected));
//	}

	@Test
	/**
	 *
	 */
	public void combineIgnoringNaNRange1NullTest() {
		assertEquals("Expected to be [-10, 5]", normalRangeTwo, Range.combineIgnoringNaN(null, normalRangeTwo));
	}

	@Test
	/**
	 *
	 */
	public void combineIgnoringNaNRange2NullTest() {
		assertEquals("Expected to be [5, 10]", normalRangeOne, Range.combineIgnoringNaN(normalRangeOne, null));
	}

	@Test
	/**
	 *
	 */
	public void combineIgnoringNaNValidTest() {
		assertEquals("Expected to be [-10, 10]", combinedRange, Range.combineIgnoringNaN(normalRangeOne, normalRangeTwo));
	}

	@Test
	/**
	 *
	 */
	public void getCentralValueTest() {
		assertEquals("Expected to be 7.5", 7.5, normalRangeOne.getCentralValue(), 0.00000000d);
	}

	@Test
	/**
	 * 
	 */
	public void getCentralValueTestMutants() {
		double[] actual = new double[4];
		double[] expect = {4.0,4.0,3.0,3.0};
		Range newRange = new Range(3,4);
		actual[0] = newRange.constrain(5);
		actual[1] = newRange.constrain(4);
		actual[2] = newRange.constrain(3);
		actual[3] = newRange.constrain(2);
		assertArrayEquals(expect,actual,0.00000d);

	}
	@Test
	/**
	 *
	 */
	public void shiftEqualRangeTest() {
		assertEquals("Expected to be [10, 10]", testRangeEqualScaledUp, Range.shift(testRangeEqual, 5.0));
	}

	@Test
	/**
	 *
	 */
	public void shiftZeroCrossingTest() {
		assertEquals("Expected to be [-5, 0]", testRangeNegativeShift, Range.shift(normalRangeOne, -10, true));
	}

	@Test
	/**
	 *
	 */
	public void expandNormalTest() {
		assertEquals("Expected to be [-5, 20]", doubledNormalRange, Range.expand(normalRangeOne, 2, 2));
	}

	@Test
	/**
	 *
	 *
	 */
	public void expandLowerGreaterThanHigherTest() {
		assertEquals("Expected to be [17.5, 17.5]", expandLowerGreaterThanHigher, Range.expand(normalRangeOne, -3, 1));
	}
	@Test
	/**
	 * intersectionTestOutofUpperBound tests the intersection value when the intersection range you are testing
	 * is completely outside of the upper bound of the range you are testing.
	 * Expected Value: False
	 */
	public void intersectionRangeTestAUB() {
		Range testAUB = new Range(10,20);
		assertEquals("Expected intersection result is false", false, testRangeDifferent.intersects(testAUB));
	}

	@Test
	/**
	 * combineTestRangeNull
	 * This test will attempt to combine two ranges however, Range2 will equal Null, the test should return
	 * the other range (range1) as the range.
	 */
	public void combineRangeOneNullTest()
	{
		assertEquals("Expected to be [5,10]",normalRangeOne,Range.combine(normalRangeOne, null));
	}

	@Test
	/*
	 * 
	 */
	public void combineRangeTwoNullTest() {
		assertEquals("Expected to be [5,10]",normalRangeOne,Range.combine(null, normalRangeOne));
	}
	
	@Test
	/*
	 * 
	 */
	public void combineRangeBothNotNull()
	{
		Range new1 = new Range(3,5);
		Range new2 = new Range(1,3);
		Range expected = new Range(1,5);
		assertEquals("Value should be [1,5]",expected,Range.combine(new1, new2));
	}
	
	@Test
	/**
	 * constrainValueWithin
	 * Value passed through to constraint is within the range that is set, constraint will return the value that we are
	 * sending in.
	 */
	public void constraintValue() throws Exception
	{	
		double[] actual = new double[4];
		double[] expect = {4.0,4.0,3.0,3.0};
		Range newRange = new Range(3,4);
		actual[0] = newRange.constrain(5);
		actual[1] = newRange.constrain(4);
		actual[2] = newRange.constrain(3);
		actual[3] = newRange.constrain(2);
		assertArrayEquals(expect,actual,0.00000d);
	}

	@Test
	/**
	 * constrainValueWithin
	 * Value passed through to constraint is above the upper-bound range that is set, constraint will return the upper-bound that we are
	 * sending in.
	 */
	public void constraintValueAUB()
	{
		assertEquals("Expected to be 5",testRangeDifferent.getUpperBound(),testRangeDifferent.constrain(10),0.00000000d);
	}

//	@Test
//	/**
//	 * constrainValueWithin
//	 * Value passed through to constraint is below the lower-bound range that is set, constraint will return the lower-bound that we are
//	 * sending in.
//	 */
//	public void constraintValueBLB()
//	{
//		assertEquals("Expected to be -5",testRangeDifferent.getUpperBound(),testRangeDifferent.constrain(-10),0.00000000d);
//	}
	
	@Test
	/**
	 * 
	 * @throws Exception
	 */
	public void getCentralValueMutants()
	{
		double[] actual = new double[4];
		double[] expect = {4.0,4.0,3.0,3.0};
		Range newRange = new Range(3,4);
		actual[0] = newRange.constrain(5);
		actual[1] = newRange.constrain(4);
		actual[2] = newRange.constrain(3);
		actual[3] = newRange.constrain(2);
		assertArrayEquals(expect,actual,0.00000d);
	}
	
	@After
	public void tearDown() throws Exception {}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}
