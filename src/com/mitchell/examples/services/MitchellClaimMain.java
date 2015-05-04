/**
 * 
 */
package com.mitchell.examples.services;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.ObjectFactory;

/**
 * @author Vedika Jadhav
 *
 */
public class MitchellClaimMain {
	public static void main(String[] args){
		System.out.println("1. Create Claim \n2.Read Claim \n3.FindListOfClaimByDateRange \n4.Update Claim \n5.Read a specific vehicle \n6. Delete a claim");
		System.out.println("Enter your choice");
		
		try{
			MitchellClaimBackingStore mitchellClaimStore = new MitchellClaimBackingStore();
			File file = new File("create-claim.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<MitchellClaimType> jaxbElement =(JAXBElement<MitchellClaimType>)jaxbUnmarshaller.unmarshal(file);
			
			MitchellClaimType mitchellClaimType = jaxbElement.getValue();
			System.out.println(mitchellClaimType);
			System.out.println(mitchellClaimType.getClaimantFirstName());
			
			mitchellClaimStore.addClaim(mitchellClaimType);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
