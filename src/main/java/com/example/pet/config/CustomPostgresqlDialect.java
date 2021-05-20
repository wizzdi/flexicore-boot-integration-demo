package com.example.pet.config;

import org.hibernate.dialect.PostgreSQL92Dialect;
import org.hibernate.type.WrappedMaterializedBlobType;
import org.hibernate.type.descriptor.sql.BinaryTypeDescriptor;
import org.hibernate.type.descriptor.sql.LongVarcharTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

import java.sql.Types;


public class CustomPostgresqlDialect extends PostgreSQL92Dialect {

	@Override
	public SqlTypeDescriptor remapSqlTypeDescriptor(SqlTypeDescriptor sqlTypeDescriptor) {
		if (Types.BLOB == sqlTypeDescriptor.getSqlType()) {
			return BinaryTypeDescriptor.INSTANCE;
		}
		if (Types.CLOB == sqlTypeDescriptor.getSqlType()) {
			return LongVarcharTypeDescriptor.INSTANCE;
		}
		if (Types.VARCHAR == sqlTypeDescriptor.getSqlType()) {
			return LongVarcharTypeDescriptor.INSTANCE;
		}
		return super.remapSqlTypeDescriptor(sqlTypeDescriptor);
	}

}
