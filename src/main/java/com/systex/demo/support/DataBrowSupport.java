package com.systex.support;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import jakarta.servlet.http.HttpSession;
import org.jdbi.v3.core.statement.Query;

import com.systex.form.PubunitdnsForm;
import com.systex.model.AptreeModel;
import com.systex.model.UserModel;

import com.systex.support.core.SvString;

public final class DataBrowSupport {
	
    public static String getPunitSQL(String pubunitdn, UserModel user, HttpSession session, AptreeModel aptree) {
    	
    	
    	
    	String punitsql = "";
    	
    	String userrole = user.getRole();
    	
    	String brows = aptree.getDATABROWS();
    	
    	System.out.println("pubunitdn: " + pubunitdn);
    	
    	PubunitdnsForm pubunitdns = getpubunitdns(pubunitdn);
    	
    	String punit1 = pubunitdns.getPunit1();
    	String punit2 = pubunitdns.getPunit2();
    	
    	System.out.println("punit1 getPunitSQL: " + punit1);
    	System.out.println("punit2 getPunitSQL: " + punit2);
    	
    	
    	
    	if ("super".equals(userrole) || "admin".equals(userrole)) {
//			if (punit2 != null && !"null".equals(punit2) && !"".equals(punit2)) {
//
//				punitsql = " and PubUnitDN like :ary_punit2";
//
//			} else 
				
			if (punit1 != null && !"null".equals(punit1) && !"".equals(punit1)) {

				punitsql = " and PubUnitDN like :ary_punit1";

			}
		} else {
			if (brows.equals("1")) {
				if (punit1 != null && !"null".equals(punit1) && !"".equals(punit1)) {

					punitsql = " and PubUnitDN like :ary_punit1";

				}
			} else if (brows.equals("2")) {
				if (punit2 != null && !"null".equals(punit2) && !"".equals(punit2)) {

					punitsql = " and PubUnitDN like :ary_punit2";

				}
			} else if (brows.equals("3")) {

				punitsql = " and CreateUid = :muid";

			}
		}
    	
    	
    	
    	
    	
        return punitsql;
    }
    
    
    
    
    public static Query getPunitquery(String pubunitdn, Query query, UserModel user, HttpSession session, AptreeModel aptree) {
    	
    	
    	
    	String userrole = user.getRole();
    	String muid = user.getMUID();
    	
    	String brows = aptree.getDATABROWS();
    	
    	System.out.println("pubunitdn: " + pubunitdn);
    	
    	PubunitdnsForm pubunitdns = getpubunitdns(pubunitdn);
    	
    	String punit1 = pubunitdns.getPunit1();
    	String punit2 = pubunitdns.getPunit2();
    	
    	System.out.println("punit1 getPunitSQL: " + punit1);
    	System.out.println("punit2 getPunitSQL: " + punit2);
    	
    	
    	
    	if ("super".equals(userrole) || "admin".equals(userrole)) {
    	
//			if (punit2 != null && !"null".equals(punit2) && !"".equals(punit2)) {
//
//				String[] ary_punit2 = SvString.split(punit2, ",");	
//				
//    			query.bind("ary_punit2", "%" + ary_punit2[0] + "%");
//    	
//
//			} else 
				
			if (punit1 != null && !"null".equals(punit1) && !"".equals(punit1)) {

				String[] ary_punit1 = SvString.split(punit1, ",");	
				
    			query.bind("ary_punit1", "%" + ary_punit1[0] + "%");

			}
		} else {
			if (brows.equals("1")) {
				if (punit1 != null && !"null".equals(punit1) && !"".equals(punit1)) {

					String[] ary_punit1 = SvString.split(punit1, ",");
					
					query.bind("ary_punit1", "%" + ary_punit1[0] + "%");

				}
			} else if (brows.equals("2")) {
				if (punit2 != null && !punit2.equals("null") && !punit2.equals("")) {

				String[] ary_punit2 = SvString.split(punit2, ",");	
				
    			query.bind("ary_punit2", "%" + ary_punit2[0] + "%");
				

				}
			} else if (brows.equals("3")) {

				query.bind("muid", "%" + muid + "%");

			}
		}
    	
    	
    	
    	
    	
        return query;
    }
    
    
    
    public static PubunitdnsForm getpubunitdns(String pubunitdn) {
    	
    	PubunitdnsForm punits = new PubunitdnsForm();
    	
    	String punit1 = "";
    	String punit2 = "";
    	
    	String[] ary_dsroot = SvString.split("ou=organization", ",");
    	String[] ary_logindn = SvString.split(pubunitdn, ",");
    	if ( ary_logindn.length == ary_dsroot.length+1 ) {
    			punit1 = pubunitdn;
    			
    			//brows選擇二，如果沒有值會查到全部
    	    	if("".equals(punit2)) {
    	    		punit2 = punit1;
    	    	}
    	}
    	
    	
    	if ( ary_logindn.length > ary_dsroot.length+2 ) {
    		String pubunitdn2 = "";
			for ( int j=ary_dsroot.length; j< ary_dsroot.length+1; j++ ) {
				pubunitdn2 = SvString.right(pubunitdn, ",");
			}
			punit2 = pubunitdn2;
				
			for ( int j=ary_dsroot.length; j< ary_dsroot.length+2; j++ ) {
				pubunitdn = SvString.right(pubunitdn, ",");
			}
			punit1 = pubunitdn;
    	}else if ( ary_logindn.length > ary_dsroot.length+1 ) {
    		
    			punit2 = pubunitdn;
    		for ( int j=ary_dsroot.length; j< ary_dsroot.length+1; j++ ) {
    			pubunitdn = SvString.right(pubunitdn, ",");
    		}
    		punit1 = pubunitdn;
    	}
    	
    	
    	System.out.println("punit1 getpubunitdns: " + punit1);
    	System.out.println("punit2 getpubunitdns: " + punit2);
    	
    	punits.setPunit1(punit1);
    	punits.setPunit2(punit2);
    	
    	
    	
    	return punits;
    }
    
    
    
public static String getmPunitSQL(String pubunitdn, UserModel user, HttpSession session, AptreeModel aptree) {
    	
    	
    	
    	String punitsql = "";
    	
    	String userrole = user.getRole();
    	
    	String brows = aptree.getDATABROWS();
    	
    	System.out.println("pubunitdn: " + pubunitdn);
    	
    	PubunitdnsForm pubunitdns = getpubunitdns(pubunitdn);
    	
    	String punit1 = pubunitdns.getPunit1();
    	String punit2 = pubunitdns.getPunit2();
    	
    	System.out.println("punit1 getPunitSQL: " + punit1);
    	System.out.println("punit2 getPunitSQL: " + punit2);
    	
    	
    	
    	if ("super".equals(userrole) || "admin".equals(userrole)) {
//			if (punit2 != null && !"null".equals(punit2) && !"".equals(punit2)) {
//
//				punitsql = " and m.PubUnitDN like :ary_punit2";
//
//			} else 
			if (punit1 != null && !"null".equals(punit1) && !"".equals(punit1)) {

				punitsql = " and m.PubUnitDN like :ary_punit1";

			}
		} else {
			if (brows.equals("1")) {
				if (punit1 != null && !"null".equals(punit1) && !"".equals(punit1)) {

					punitsql = " and m.PubUnitDN like :ary_punit1";
				}
			} else if (brows.equals("2")) {
				if (punit2 != null && !"null".equals(punit2) && !"".equals(punit2)) {
					punitsql = " and m.PubUnitDN like :ary_punit2";
				}
			} else if (brows.equals("3")) {
				punitsql = " and CreateUid = :muid";
			}
		}
    	
        return punitsql;
    }

}