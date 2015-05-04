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
			totalNumberOfClaims++;
			return serializeClaims(mitchellClaims);
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
				return listOfClaimsByDateRange;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
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
		return null;
	}
	
	private Date convertXmlGregorianCalendarToDate(XMLGregorianCalendar lossDate) {
		// TODO Auto-generated method stub
		if(lossDate != null){
			return lossDate.toGregorianCalendar().getTime();
		}
		return null;
	}

	public boolean serializeClaims(ArrayList<MitchellClaimType> mitchellClaims2) {
		// TODO Auto-generated method stub
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("claims.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(mitchellClaims2);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in claims.ser");
	         return true;
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		return false;
	}
	
	public ArrayList<MitchellClaimType> deserializeClaims(){
		ArrayList<MitchellClaimType> claims1 = new ArrayList<MitchellClaimType>();
		try
	      {
	         FileInputStream fileIn = new FileInputStream("claims.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         claims1 = (ArrayList<MitchellClaimType>) in.readObject();
	         in.close();
	         fileIn.close();
	         return claims1;
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	      }
		return null;
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
