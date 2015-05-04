/**
 * 
 */
package com.mitchell.examples.services;

import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.mitchell.examples.claim.MitchellClaimType;

/**
 * @author Vedika Jadhav
 *
 */
@WebService
public class MitchellClaimWebService {
	
	private static MitchellClaimBackingStore mitchellClaimBackingStore = new MitchellClaimBackingStore();
	
	@WebMethod
	public void addClaim(MitchellClaimType newMitchellClaimType){
		mitchellClaimBackingStore.addClaim(newMitchellClaimType);
	}
	
	@WebMethod
	public MitchellClaimType readClaim(String claimNumber){
		return (mitchellClaimBackingStore.readClaim(claimNumber));
	}
	
	@WebMethod
	public ArrayList<MitchellClaimType> findClaimsByDateRange(Date startDate, Date endDate){
		return (mitchellClaimBackingStore.findClaimsByDateRange(startDate, endDate));
	}
	
	@WebMethod
	public ArrayList<MitchellClaimType> deleteClaim(String claimNumber){
		return (mitchellClaimBackingStore.deleteClaim(claimNumber));
	}
	
	@WebMethod
	public void updateClaim(MitchellClaimType mitchellClaimType){
		mitchellClaimBackingStore.updateClaim(mitchellClaimType);
	}
}
