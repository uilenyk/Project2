//package com.revature.util;
//
//import java.sql.Array;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Types;
//
//import org.hibernate.HibernateException;
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//
//public class StringList {
//
//	private static final int[] SQL_TYPES = { Types.ARRAY };
//
//	public int[] sqlTypes() {
//		return SQL_TYPES;
//	}
//
//	public Class<String[]> returnedClass() {
//		return String[].class;
//	}
//
//	public boolean equals(Object x, Object y) throws HibernateException {
//		if (x == y)
//			return true;
//		else if (x == null || y == null)
//			return false;
//		else
//			return x.equals(y);
//	}
//
//	@Override
//	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
//			throws HibernateException, SQLException {
//		if (names != null && names.length > 0 && rs != null && rs.getArray(names[0]) != null) {
//			String[] results = (String[]) rs.getArray(names[0]).getArray();
//			return results;
//		}
//		return null;
//	}
//
//	@Override
//	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
//			throws HibernateException, SQLException {
//		if (value != null && st != null) {
//			String[] castObject = (String[]) value;
//			Array array = session.connection().createArrayOf("text", castObject);
//			st.setArray(index, array);
//		} else {
//			st.setNull(index, arrayTypes[0]);
//		}
//
//	}
//
//	public Object deepCopy(Object value) throws HibernateException {
//		return value;
//	}
//
//	public boolean isMutable() {
//		return false;
//	}
//
//}
