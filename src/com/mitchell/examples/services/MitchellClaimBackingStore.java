/**
 * 
 */
package com.mitchell.examples.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.xml.datatype.XMLGregorianCalendar;

import com.mitchell.examples.claim.MitchellClaimType;

/**
 * @author Vedika Jadhav
 *
 */
public class MitchellClaimBackingStore {
	private ArrayList<MitchellClaimType> mitchellClaims = new ArrayList<MitchellClaimType>();
	private static int totalNumberOfClaims;

	public boolean addClaim(MitchellClaimType newMitchellClaimType) {
		// TODO Auto-generated method stub
		if(newMitchellClaimType != null){
			mitchellClaims.add(newMitchellClaimType);
			serializeClaims(mitchellClaims);
			totalNumberOfClaims++;
			return true;
			//System.out.println(mitchellClaims.toString() + "total number of claims" + totalNumberOfClaims);			
		}
		return false;
	}
	
	public MitchellClaimType readClaim(String claimNumber){
		try{
			mitchellClaims = deserializeClaims();
			if(mitchellClaims != null){
				for(MitchellClaimType mitchellClaim: mitchellClaims){
					if(mitchellClaim.getClaimNumber().equals(claimNumber)){
						return mitchellClaim;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<MitchellClaimType> findClaimsByDateRange(Date startDate, Date endDate){
		Date mitchellClaimLossDate;
		ArrayList<MitchellClaimType> listOfClaimsByDateRange = new ArrayList<MitchellClaimType>();
		try{
			mitchellClaims = deserializeClaims();
			if(mitchellClaims != null){
				for(MitchellClaimType mitchellClaim: mitchellClaims){
					mitchellClaimLossDate = convertXmlGregorianCalendarToDate(mitchellClaim.getLossDate());
					if(mitchellClaimLossDate.after(startDate) && mitchellClaimLossDate.before(endDate)){
						listOfClaimsByDateRange.add(mitchellClaim);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listOfClaimsByDateRange;
	}
	
	public ArrayList<MitchellClaimType> deleteClaim(String claimNumber){
		try{
			mitchellClaims = deserializeClaims();
			if(mitchellClaims != null){
				for(MitchellClaimType mitchellClaim: mitchellClaims){
					if(mitchellClaim.getClaimNumber().equals(claimNumber)){
						mitchellClaims.remove(mitchellClaim);
						serializeClaims(mitchellClaims);
						totalNumberOfClaims--;
						return mitchellClaims;
					}
				}
				System.out.println("MitchellClaim with claimNumber: " + claimNumber + " not found");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		/*Iterator<MitchellClaimType> claimsIt = mitchellClaims.iterator();
		while(claimsIt.hasNext())
		{
			if(claimsIt.next().getClaimNumber().equals(claimNumber)){
				claimsIt.remove();
				try {
					serializeClaims(claimsIt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				totalNumberOfClaims--;
				break;
			}
		}*/
		return null;
	}
	
	private Date convertXmlGregorianCalendarToDate(XMLGregorianCalendar lossDate) {
		// TODO Auto-generated method stub
		if(lossDate != null){
			return lossDate.toGregorianCalendar().getTime();
		}
		return null;
	}

	public void serializeClaims(Object mitchellClaims2) {
		// TODO Auto-generated method stub
		try
	      {
	         FileOutputStream fileOut = new FileOutputStream("claims.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(mitchellClaims2);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in claims.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public ArrayList<MitchellClaimType> deserializeClaims(){
		ArrayList<MitchellClaimType> deserializedClaims = new ArrayList<MitchellClaimType>();
		try
	      {
	         FileInputStream fileIn = new FileInputStream("claims.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         deserializedClaims = (ArrayList<MitchellClaimType>) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	      }
        return deserializedClaims;
	}

	public void updateClaim(MitchellClaimType mitchellClaimType) {
		// TODO Auto-generated method stub
		try{
			mitchellClaims = deserializeClaims();
			if(mitchellClaims != null){
				for(MitchellClaimType mitchellClaim: mitchellClaims){
					if(mitchellClaim.getClaimNumber().equals(mitchellClaimType.getClaimNumber())){
						//update mitchellClaim and serialize the mitchellClaims back to the file
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
