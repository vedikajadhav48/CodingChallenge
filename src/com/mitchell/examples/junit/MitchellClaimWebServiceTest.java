/**
 * 
 */
package com.mitchell.examples.junit;

import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.ObjectFactory;
import com.mitchell.examples.services.MitchellClaimBackingStore;

/**
 * @author Vedika Jadhav
 *
 */
public class MitchellClaimWebServiceTest {
	File createFile = new File("create-claim.xml");
	File updateFile = new File("update-claim.xml");
	File outputFile = new File("claims.ser");
	MitchellClaimBackingStore mitchellClaimBackingStore;
	MitchellClaimType newMitchellClaimType;
	JAXBContext jaxbContext;
	Unmarshaller jaxbUnmarshaller;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mitchellClaimBackingStore = new MitchellClaimBackingStore();
		jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		jaxbUnmarshaller = jaxbContext.createUnmarshaller();		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		try{
		}catch(Throwable t){
			t.printStackTrace();
		}
	}

	@Test
	public void testAddClaim() {
		try{
			JAXBElement<MitchellClaimType> jaxbElement = (JAXBElement<MitchellClaimType>)jaxbUnmarshaller.unmarshal(createFile);
			newMitchellClaimType = jaxbElement.getValue();
			assertTrue(mitchellClaimBackingStore.addClaim(newMitchellClaimType));
			//fail("Not yet implemented");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
