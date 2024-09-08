package com.systex.demo.dao;


import com.systex.demo.model.DataContent;
import com.systex.demo.model.SourceUrl;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.sqlobject.SqlObject;

import java.util.List;


public interface DataContentInterface extends SqlObject {

	default List<DataContent> findALl() {
		try (Handle handle = getHandle()) {
			handle.registerRowMapper(BeanMapper.factory(DataContent.class));
			String sql = "SELECT * FROM DATACONTENT";
			Query query = handle.createQuery(sql);
			return query.mapTo(DataContent.class).list();
		}
	}

	default Integer create(DataContent dataContent) {
		try (Handle handle = getHandle()) {

			String sql = "INSERT INTO DATACONTENT (TITLE, DATACONTENTDETAIL, CREATEDATETIME, SOURCE) "
					+ "VALUES (:title, :dataContentDetail, :createDateTime, :source)";

			return handle.createUpdate(sql).bindBean(dataContent).execute();
		}
	}
	
//	default List<QclassMenuTableModel> findQclass(String table) {
//		try (Handle handle = getHandle()) {
//			handle.registerRowMapper(BeanMapper.factory(QclassMenuTableModel.class));
//			String sql = String.format("SELECT SERNO,CLASSNAME FROM %s ", table);
//			Query query = handle.createQuery(sql);
//			return query.mapTo(QclassMenuTableModel.class).list();
//		}
//	}
//
//	default List<Map<String, Object>> findUpload(String table, String serno, String flag) {
//		try (Handle handle = getHandle()) {
//
//			StringBuilder sb = new StringBuilder();
//			sb.append(" select * ");
//			sb.append(" from %s ");
//			sb.append(" where serno = :serno ");
//			sb.append(" and flag = :flag ");
//			sb.append(" order by UpdateDate desc,detailno ");
//
//			String sql = String.format(sb.toString(), table);
//
//			Query query = handle.createQuery(sql);
//			query.bind("serno", serno);
//			query.bind("flag", flag);
//
//			return query.mapToMap().list();
//		}
//	}
//
//	default List<Map<String, Object>> findByday(String table, String serno) {
//		try (Handle handle = getHandle()) {
//
//			StringBuilder sb = new StringBuilder();
//			sb.append(" select * ");
//			sb.append(" from %s ");
//			sb.append(" where serno = :serno ");
//			sb.append(" order by UpdateDate desc,detailno ");
//
//			String sql = String.format(sb.toString(), table);
//
//			Query query = handle.createQuery(sql);
//			query.bind("serno", serno);
//
//			return query.mapToMap().list();
//		}
//	}
//
//	default List<MaintainunitModel> findMaintainunit(String table) {
//		try (Handle handle = getHandle()) {
//			handle.registerRowMapper(BeanMapper.factory(MaintainunitModel.class));
//			String sql = String.format("SELECT DN,CHINESETITLE FROM %s WHERE DN LIKE :DN ORDER BY OU", table);
//			Query query = handle.createQuery(sql);
//			query.bind("DN", "%" + "ou=315000000H,ou=organization");
//			return query.mapTo(MaintainunitModel.class).list();
//		}
//	}
//
//	default Long count(String table, String module, String pubUnitID, String keyword, String qclass, String startDate,
//			String endDate, UserModel user, HttpSession session, AptreeModel aptree) {
//		try (Handle handle = getHandle()) {
//			String sql = String.format("SELECT COUNT(*) FROM %s " + " WHERE LANGUAGETYPE = :languageType", table);
//
////			if (pubUnitID != null && !StringUtils.isEmpty(pubUnitID)) {
////				sql = String.format(" %s AND PUBUNITDN LIKE :pubUnitID", sql);
////			}
//
//			// 發布單位查詢條件
//    		if(!"".equals(pubUnitID) && pubUnitID != null) {
//    			String PunitSQL = DataBrowSupport.getPunitSQL(pubUnitID, user, session, aptree);
//    			sql += PunitSQL;
//    		}
//
//			if (keyword != null && !StringUtils.isEmpty(keyword)) {
//				sql = String.format(" %s AND SUBJECT LIKE :keyword", sql);
//			}
//			if (qclass != null && !StringUtils.isEmpty(qclass)) {
//				sql = String.format(" %s AND MSERNO LIKE :qclass", sql);
//			}
//			if (startDate != null && endDate != null) {
//				sql = String.format(" %s AND POSTERDATE BETWEEN :startDate AND :endDate", sql);
//			} else if (startDate != null) {
//				sql = String.format(" %s AND POSTERDATE >= :startDate", sql);
//			} else if (endDate != null) {
//				sql = String.format(" %s AND POSTERDATE <= :endDate", sql);
//			}
//
//			sql += " AND ISDELETE = 'N' ";
//			Query query = handle.createQuery(sql);
//			query.bind("languageType", "chinese");
////			if (pubUnitID != null && !StringUtils.isEmpty(pubUnitID)) {
////				query.bind("pubUnitID", "%" + pubUnitID + "%");
////			}
//
//			// 發布單位查詢條件
//    		if(!"".equals(pubUnitID) && pubUnitID != null) {
//    			query = DataBrowSupport.getPunitquery(pubUnitID, query, user, session, aptree);
//    		}
//
//			if (keyword != null && !StringUtils.isEmpty(keyword)) {
//				query.bind("keyword", "%" + keyword + "%");
//			}
//			if (qclass != null && !StringUtils.isEmpty(qclass)) {
//				query.bind("qclass", "%" + qclass + "%");
//			}
//			if (startDate != null && endDate != null) {
//				query.bind("startDate", LocalDateTimeSupport.parse(startDate + " 00:00:00"));
//				query.bind("endDate", LocalDateTimeSupport.parse(endDate + " 23:59:59"));
//			} else if (startDate != null) {
//				query.bind("startDate", LocalDateTimeSupport.parse(startDate + " 00:00:00"));
//			} else if (endDate != null) {
//				query.bind("endDate", LocalDateTimeSupport.parse(endDate + " 23:59:59"));
//			}
//
//			return query.mapTo(Long.class).one();
//		}
//	}
//
//	default List<ArgueModel> queryPage(String table, String module, String pubUnitID, String keyword, String qclass,
//			String startDate, String endDate, String fetchClosure, UserModel user, HttpSession session, AptreeModel aptree) {
//		try (Handle handle = getHandle()) {
//
//			handle.registerRowMapper(BeanMapper.factory(ArgueModel.class));
//
//			String sql = String.format("SELECT * FROM %s " + " WHERE LANGUAGETYPE = :languageType", table);
//
////			if (pubUnitID != null && !StringUtils.isEmpty(pubUnitID)) {
////				sql = String.format(" %s AND PUBUNITDN LIKE :pubUnitID", sql);
////			}
//
//			// 發布單位查詢條件
//    		if(!"".equals(pubUnitID) && pubUnitID != null) {
//    			String PunitSQL = DataBrowSupport.getPunitSQL(pubUnitID, user, session, aptree);
//    			sql += PunitSQL;
//    		}
//
//			if (keyword != null && !StringUtils.isEmpty(keyword)) {
//				sql = String.format(" %s AND SUBJECT LIKE :keyword", sql);
//			}
//			if (qclass != null && !StringUtils.isEmpty(qclass)) {
//				sql = String.format(" %s AND MSERNO LIKE :qclass", sql);
//			}
//			if (startDate != null && endDate != null) {
//				sql = String.format(" %s AND POSTERDATE BETWEEN :startDate AND :endDate", sql);
//			} else if (startDate != null) {
//				sql = String.format(" %s AND POSTERDATE >= :startDate", sql);
//			} else if (endDate != null) {
//				sql = String.format(" %s AND POSTERDATE <= :endDate", sql);
//			}
//
//			sql += " AND ISDELETE = 'N' ORDER BY UPDATEDATE DESC,SERNO DESC,PUBUNITDN";
//			sql += " " + fetchClosure;
//
//			Query query = handle.createQuery(sql);
//			query.bind("languageType", "chinese");
//
////			if (pubUnitID != null && !StringUtils.isEmpty(pubUnitID)) {
////				query.bind("pubUnitID", "%" + pubUnitID + "%");
////				System.out.println("pubUnitID:"+pubUnitID);
////			}
//
//			// 發布單位查詢條件
//    		if(!"".equals(pubUnitID) && pubUnitID != null) {
//    			query = DataBrowSupport.getPunitquery(pubUnitID, query, user, session, aptree);
//    		}
//
//			if (keyword != null && !StringUtils.isEmpty(keyword)) {
//				query.bind("keyword", "%" + keyword + "%");
//				System.out.println("keyword:"+keyword);
//			}
//			if (qclass != null && !StringUtils.isEmpty(qclass)) {
//				query.bind("qclass", "%" + qclass + "%");
//				System.out.println("qclass:"+qclass);
//			}
//			if (startDate != null && endDate != null) {
//				query.bind("startDate", LocalDateTimeSupport.parse(startDate + " 00:00:00"));
//				query.bind("endDate", LocalDateTimeSupport.parse(endDate + " 23:59:59"));
//				System.out.println("startDate:"+LocalDateTimeSupport.parse(startDate + " 00:00:00"));
//				System.out.println("endDate:"+LocalDateTimeSupport.parse(endDate + " 23:59:59"));
//			} else if (startDate != null) {
//				query.bind("startDate", LocalDateTimeSupport.parse(startDate + " 00:00:00"));
//				System.out.println("startDate:"+LocalDateTimeSupport.parse(startDate + " 00:00:00"));
//			} else if (endDate != null) {
//				query.bind("endDate", LocalDateTimeSupport.parse(endDate + " 23:59:59"));
//				System.out.println("endDate:"+LocalDateTimeSupport.parse(endDate + " 23:59:59"));
//			}
//
//			List<ArgueModel> dataClasses = query.mapTo(ArgueModel.class).list();
//
//			System.out.println("sql:"+sql.toString());
//
//			return dataClasses;
//		}
//	}
//
//	default List<ConsortiumAssignUnitDataModel> queryName(String unitdn) {
//		try (Handle handle = getHandle()) {
//
//			handle.registerRowMapper(BeanMapper.factory(ConsortiumAssignUnitDataModel.class));
//			String sql = String.format("SELECT CHINESETITLE FROM %s WHERE DN = :DN", "DEPARTMENT");
//			Query query = handle.createQuery(sql);
//			query.bind("DN", unitdn);
//
//			return query.mapTo(ConsortiumAssignUnitDataModel.class).list();
//		}
//	}
//
//	default List<ArgueModel> selectSerno(String today) {
//		try (Handle handle = getHandle()) {
//			handle.registerRowMapper(BeanMapper.factory(ArgueModel.class));
//			String sql = " SELECT max(serno) AS SERNO FROM ARGUES WHERE SUBSTRING(serno,1,8) = :today ";
//			Query query = handle.createQuery(sql);
//			query.bind("today", today);
//			return query.mapTo(ArgueModel.class).list();
//		}
//	}
//
//	default Integer create(String table, ArgueModel dataClass) {
//		try (Handle handle = getHandle()) {
//			String sql = String.format("INSERT INTO %s "
//					+ "(SERNO,PUBUNITDN,MSERNO,SUBJECT,SECSUBJECT,DETAILCONTENT,POSTERDATE,CLOSEDATE,STARTUSING,BUSINESSONE,BUSINESSTWO,BUSINESSTHREE,ISTOP,TOPDATE,FSORT,LIAISONPER,LIAISONTEL,LIAISONFAX,LIAISONEMAIL,ISEXAMINE,READNUMBER,SUBJECTCLASS,SUBJECTID,ADMINISTATIONCLASS,ADMINISTATIONID,SERVICECLASS,SERVICEID,CREATEUID,CREATENAME,CREATEDATE,POSTUID,POSTNAME,UPDATEDATE,DSID,LANGUAGETYPE,DATASOURCEDN,ISDELETE,CLOSED,KEYWORDS,LINK, NEWSSERNO)"
//					+ "VALUES (:SERNO, :PUBUNITDN, :MSERNO, :SUBJECT, :SECSUBJECT, :DETAILCONTENT, :POSTERDATE, :CLOSEDATE, :STARTUSING, :BUSINESSONE, :BUSINESSTWO, :BUSINESSTHREE, :ISTOP, :TOPDATE, :FSORT, :LIAISONPER, :LIAISONTEL, :LIAISONFAX, :LIAISONEMAIL, :ISEXAMINE, :READNUMBER, :SUBJECTCLASS, :SUBJECTID, :ADMINISTATIONCLASS, :ADMINISTATIONID, :SERVICECLASS, :SERVICEID, :CREATEUID, :CREATENAME, :CREATEDATE, :POSTUID, :POSTNAME, :UPDATEDATE, :DSID, :LANGUAGETYPE, :DATASOURCEDN, :ISDELETE, :CLOSED, :KEYWORDS, :LINK, :NEWSSERNO)",
//					table);
//
//			return handle.createUpdate(sql).bindBean(dataClass).execute();
//		}
//	}
//
//	default List<BusinessClassModel> getclass(String layer) {
//		try (Handle handle = getHandle()) {
//			handle.registerRowMapper(BeanMapper.factory(BusinessClassModel.class));
//			String sql = " select * from BusinessClass where STARTUSING = :using and LAYER = :layer ";
//			Query query = handle.createQuery(sql);
//			query.bind("layer", layer);
//			query.bind("using", "Y");
//
//			return query.mapTo(BusinessClassModel.class).list();
//		}
//	}
//
//	default List<BusinessClassModel> getclassByPID(String pid) {
//		try (Handle handle = getHandle()) {
//			handle.registerRowMapper(BeanMapper.factory(BusinessClassModel.class));
//			String sql = " select * from BusinessClass where STARTUSING = :using and PARENTID = :pid ";
//			Query query = handle.createQuery(sql);
//			query.bind("pid", pid);
//			query.bind("using", "Y");
//
//			return query.mapTo(BusinessClassModel.class).list();
//		}
//	}
//
//	default List<ArgueModel> getData(String languageType, String keyword, String classname, String dsid, String sdate1, String sdate2){
//		try (Handle handle = getHandle()) {
//			handle.registerRowMapper(BeanMapper.factory(ArgueModel.class));
//			String sql = " select * from ARGUES where languageType = :languageType ";
//			if ( !StringUtils.isEmpty(keyword) )  {
//    			sql += " and Subject like :keyword ";
//    		}
//			if ( !StringUtils.isEmpty(classname) )  {
//				sql += " and Mserno = :classname ";
//			}
//			if ( !StringUtils.isEmpty(dsid) )  {
//				sql += " and DsID = :dsid ";
//			}
//			if ( !StringUtils.isEmpty(sdate1) )  {
//				sql += " and PosterDate >= :sdate1 ";
//			}
//			if ( !StringUtils.isEmpty(sdate2) )  {
//				sql += " and PosterDate <= :sdate2 ";
//			}
//			sql += " and ISDELETE = 'N' order by UpdateDate desc,Serno desc,PubUnitDN ";
//			Query query = handle.createQuery(sql);
//			query.bind("languageType", languageType);
//			if ( !StringUtils.isEmpty(keyword) )  {
//				query.bind("keyword", keyword);
//			}
//			if ( !StringUtils.isEmpty(classname) )  {
//				query.bind("classname", classname);
//			}
//			if ( !StringUtils.isEmpty(dsid) )  {
//				query.bind("dsid", dsid);
//			}
//			if ( !StringUtils.isEmpty(sdate1) )  {
//				query.bind("sdate1", sdate1);
//			}
//			if ( !StringUtils.isEmpty(sdate2) )  {
//				query.bind("sdate2", sdate2);
//			}
//
//			return query.mapTo(ArgueModel.class).list();
//		}
//	}
//
//	default Optional<ArgueModel> findBySerno(String table, String serno) {
//		try (Handle handle = getHandle()) {
//			handle.registerRowMapper(BeanMapper.factory(ArgueModel.class));
//
//			String sql = String.format(
//					"SELECT * FROM %s WHERE SERNO = :serno",
//					table);
//			Query query = handle.createQuery(sql);
//			query.bind("serno", serno);
//			return query.mapTo(ArgueModel.class).findFirst();
//		}
//	}
//
//	default Integer update(String table, ArgueModel dataClass) {
//		try (Handle handle = getHandle()) {
//			String sql = String.format("UPDATE %s SET "
//					+ "SERNO = :SERNO, PUBUNITDN = :PUBUNITDN, MSERNO = :MSERNO, SUBJECT = :SUBJECT, SECSUBJECT = :SECSUBJECT, DETAILCONTENT = :DETAILCONTENT, "
//					+ "POSTERDATE = :POSTERDATE, CLOSEDATE = :CLOSEDATE, STARTUSING = :STARTUSING, BUSINESSONE = :BUSINESSONE, BUSINESSTWO = :BUSINESSTWO, "
//					+ "BUSINESSTHREE = :BUSINESSTHREE, ISTOP = :ISTOP, TOPDATE = :TOPDATE, FSORT = :FSORT, LIAISONPER = :LIAISONPER, LIAISONTEL = :LIAISONTEL, "
//					+ "LIAISONFAX = :LIAISONFAX, LIAISONEMAIL =  :LIAISONEMAIL, ISEXAMINE = :ISEXAMINE, READNUMBER = :READNUMBER, SUBJECTCLASS = :SUBJECTCLASS, "
//					+ "ADMINISTATIONCLASS = :ADMINISTATIONCLASS, SERVICECLASS = :SERVICECLASS, POSTUID =  :POSTUID, POSTNAME = :POSTNAME, UPDATEDATE = :UPDATEDATE, "
//					+ "DSID = :DSID, LANGUAGETYPE = :LANGUAGETYPE, DATASOURCEDN = :DATASOURCEDN, ISDELETE = :ISDELETE, CLOSED = :CLOSED, KEYWORDS = :KEYWORDS, LINK = :LINK "
//					+ "WHERE SERNO = :SERNO", table);
//			return handle.createUpdate(sql).bindBean(dataClass).execute();
//		}
//	}
//
//	default int[] updateAfterDays(List<String> sernos) {
//      try (Handle handle = getHandle()) {
//
//          String sql = "UPDATE ARGUES SET UPDATEDATE = :updatedate, ISDELETE = 'Y' WHERE SERNO = :serno";
//
//          String updateDate = LocalDateTimeSupport.parse(LocalDate.now().toString());
//
//          PreparedBatch batch = handle.prepareBatch(sql);
//          batch.bind("updatedate", updateDate);
//          for (String serno : sernos) {
//              serno = DataValidator.returnValidatorUUID(serno);
//              batch.bind("serno", serno).add();
//          }
//
//          return batch.execute();
//      }
//    }
//
//	default List<String> removeAfterDaysBySearch(){
//      try (Handle handle = getHandle()) {
//          handle.registerRowMapper(BeanMapper.factory(ArgueModel.class));
//
//          String sql = " SELECT SERNO FROM ARGUES WHERE ISDELETE = 'Y' AND UPDATEDATE < DATEADD(DAY, -30, (CONVERT(CHAR(10),NOW(), 20)+' 00:00:00')) ";
//
//          Query query = handle.createQuery(sql);
//
//          return query.mapTo(String.class).list();
//      }
//    }
//
//	default int[] delafterdays(List<String> sernos) {
//		try (Handle handle = getHandle()) {
//
//			String sql = "DELETE FROM ARGUES WHERE ISDELETE = 'Y' AND SERNO = :serno";
//
//			PreparedBatch batch = handle.prepareBatch(sql);
//			for (String serno : sernos) {
//				serno = DataValidator.returnValidatorUUID(serno);
//				batch.bind("serno", serno).add();
//			}
//
//			return batch.execute();
//		}
//	}
//
//	default List<ArgueModel> XmlBySearch(String classname, String keyword, String sdate1, String sdate2, String dsid, String language){
//      try (Handle handle = getHandle()) {
//          handle.registerRowMapper(BeanMapper.factory(ArgueModel.class));
//
//          String sql = " SELECT * FROM ARGUES WHERE LANGUAGETYPE = :languageType ";
//
//          if ( !StringUtils.isEmpty(keyword) )  {
//              sql += " AND SUBJECT like :keyword ";
//          }
//
//          if ( !StringUtils.isEmpty(classname) )  {
//              sql += " AND MSERNO = :classname ";
//          }
//          if ( !StringUtils.isEmpty(dsid) )  {
//              sql += " AND DSID = :dsid ";
//          }
//
//          if ( !StringUtils.isEmpty(sdate1) )  {
//              sql += " AND POSTERDATE >= :sdate1 ";
//          }
//          if ( !StringUtils.isEmpty(sdate2) )  {
//              sql += " AND POSTERDATE <= :sdate2 ";
//          }
//
//          sql += " AND UPDATEDATE >= DATEADD(DAY, -30, (CONVERT(CHAR(10),NOW(), 20)+' 00:00:00')) ";
//          sql += " ORDER BY UPDATEDATE DESC,SERNO DESC,PUBUNITDN ";
//
//          Query query = handle.createQuery(sql);
//
//          query.bind("languageType", language);
//
//          if ( !StringUtils.isEmpty(keyword) )  {
//              query.bind("keyword", '%' + keyword.trim() + '%');
//          }
//          if ( !StringUtils.isEmpty(classname) )  {
//              query.bind("classname", classname);
//          }
//          if ( !StringUtils.isEmpty(dsid) )  {
//              query.bind("dsid", dsid);
//          }
//          if ( !StringUtils.isEmpty(sdate1) )  {
//              query.bind("sdate1", sdate1);
//          }
//          if ( !StringUtils.isEmpty(sdate2) )  {
//              query.bind("sdate2", sdate2);
//          }
//
//          return query.mapTo(ArgueModel.class).list();
//      }
//  }
//
//  default String GetOID(final String DN) {
//      try (Handle handle = getHandle()) {
//
//          String sql = String.format("SELECT OID FROM DEPARTMENT WHERE DN = :dn ");
//
//          Query query = handle.createQuery(sql);
//
//          String returnString = query.bind("dn", DN).mapTo(String.class).one();
//          returnString = ((returnString != null) ? returnString : "");
//
//          return returnString;
//      }
//  }
//
//  default Optional<ArgueModel> findByNewsSerno(String table, String newsSerno) {
//      try (Handle handle = getHandle()) {
//          handle.registerRowMapper(BeanMapper.factory(ArgueModel.class));
//
//          String sql = String.format("SELECT * FROM %s WHERE NEWSSERNO = :newsSerno", table);
//
//          Query query = handle.createQuery(sql);
//          query.bind("newsSerno", newsSerno);
//          return query.mapTo(ArgueModel.class).findFirst();
//      }
//  }
//
//  default String getMaxNewsSerno(String table, String newsSernoDate) {
//      try (Handle handle = getHandle()) {
//
//          String sql = String.format("SELECT max(NEWSSERNO) as NEWSSERNO FROM %s WHERE NEWSSERNO LIKE :newsSernoDate ", table);
//
//          Query query = handle.createQuery(sql);
//          query.bind("newsSernoDate", newsSernoDate + '%');
//
//          String maxSerno = query.mapTo(String.class).one();
//
//          return maxSerno;
//      }
//  }
  
}
