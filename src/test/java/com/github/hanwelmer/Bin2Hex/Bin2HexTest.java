package com.github.hanwelmer.Bin2Hex;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests.
 */
public class Bin2HexTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Bin2HexTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( Bin2HexTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testUsage()
    {
        assertTrue( true );
    }
}
