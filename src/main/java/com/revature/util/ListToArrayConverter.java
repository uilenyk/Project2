//package com.revature.util;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import org.postgresql.jdbc4.Jdbc4Array;
//
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//
//@Converter(autoApply = true)
//public class ListToArrayConverter implements AttributeConverter<List<String>, Object> {
//	@Override
//	public PostgreSQLTextArray convertToDatabaseColumn(List<String> attribute) {
//		if (attribute == null || attribute.isEmpty()) {
//			return null;
//		}
//		String[] rst = new String[attribute.size()];
//		return new PostgreSQLTextArray(attribute.toArray(rst));
//	}
//
//	@Override
//	public List<String> convertToEntityAttribute(Object dbData) {
//
//		List<String> rst = new ArrayList<>();
//		try {
//			String[] elements = (String[]) ((Jdbc4Array) dbData).getArray();
//			for (String element : elements) {
//				rst.add(element);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return rst;
//	}
//}
