package com.softicar.platform.db.sql.expressions.numeric;

import com.softicar.platform.db.sql.expressions.ISqlExpression;
import com.softicar.platform.db.sql.expressions.ISqlExpression0;
import com.softicar.platform.db.sql.expressions.ISqlExpression1;
import com.softicar.platform.db.sql.expressions.ISqlExpression2;
import com.softicar.platform.db.sql.expressions.ISqlExpression3;
import com.softicar.platform.db.sql.expressions.ISqlExpression4;
import com.softicar.platform.db.sql.expressions.bool.ISqlBooleanExpression0;
import com.softicar.platform.db.sql.expressions.bool.ISqlBooleanExpression1;
import com.softicar.platform.db.sql.expressions.bool.ISqlBooleanExpression2;
import com.softicar.platform.db.sql.expressions.bool.ISqlBooleanExpression3;
import com.softicar.platform.db.sql.expressions.bool.ISqlBooleanExpression4;
import com.softicar.platform.db.sql.expressions.bool.SqlBooleanExpression1;
import com.softicar.platform.db.sql.expressions.bool.SqlBooleanExpression2;
import com.softicar.platform.db.sql.expressions.bool.SqlBooleanExpression3;
import com.softicar.platform.db.sql.expressions.bool.SqlBooleanExpression4;
import com.softicar.platform.db.sql.expressions.string.ISqlStringExpression0;
import com.softicar.platform.db.sql.expressions.string.SqlStringExpression0;
import com.softicar.platform.db.sql.operations.SqlOperations;
import com.softicar.platform.db.sql.type.SqlValueTypes;

public interface ISqlIntegerExpression0 extends ISqlExpression0<Integer>, ISqlIntegerExpression<ISqlIntegerExpression0, ISqlBooleanExpression0> {
	@Override
	default ISqlIntegerExpression0 wrap(ISqlExpression<Integer> expression) {
		return new SqlIntegerExpression0(expression);
	}

	// -------------------------------- CASTS -------------------------------- //

	default ISqlStringExpression0 castToString() {
		return new SqlStringExpression0(SqlOperations.CAST_CHAR.create(SqlValueTypes.STRING, this));
	}

	default ISqlFloatExpression0 castToFloat() {
		return new SqlFloatExpression0(SqlOperations.CAST_DECIMAL.create(SqlValueTypes.FLOAT, this));
	}

	default ISqlDoubleExpression0 castToDouble() {
		return new SqlDoubleExpression0(SqlOperations.CAST_DECIMAL.create(SqlValueTypes.DOUBLE, this));
	}

	default ISqlBigDecimalExpression0 castToBigDecimal() {
		return new SqlBigDecimalExpression0(SqlOperations.CAST_DECIMAL.create(SqlValueTypes.BIG_DECIMAL, this));
	}

	default ISqlLongExpression0 castToLong() {
		return new SqlLongExpression0(SqlOperations.NOP.create(SqlValueTypes.LONG, this));
	}

	// -------------------------------- OPERATIONS 1 -------------------------------- //

	default <S0> ISqlBooleanExpression1<S0> isEqual(ISqlExpression1<Integer, S0> other) {
		return new SqlBooleanExpression1<>(SqlOperations.IS_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0> ISqlBooleanExpression1<S0> isNotEqual(ISqlExpression1<Integer, S0> other) {
		return new SqlBooleanExpression1<>(SqlOperations.IS_NOT_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0> ISqlBooleanExpression1<S0> isLess(ISqlExpression1<Integer, S0> other) {
		return new SqlBooleanExpression1<>(SqlOperations.IS_LESS.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0> ISqlBooleanExpression1<S0> isLessEqual(ISqlExpression1<Integer, S0> other) {
		return new SqlBooleanExpression1<>(SqlOperations.IS_LESS_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0> ISqlBooleanExpression1<S0> isGreater(ISqlExpression1<Integer, S0> other) {
		return new SqlBooleanExpression1<>(SqlOperations.IS_GREATER.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0> ISqlBooleanExpression1<S0> isGreaterEqual(ISqlExpression1<Integer, S0> other) {
		return new SqlBooleanExpression1<>(SqlOperations.IS_GREATER_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0> ISqlIntegerExpression1<S0> plus(ISqlExpression1<Integer, S0> other) {
		return new SqlIntegerExpression1<>(SqlOperations.PLUS.create(this, other));
	}

	default <S0> ISqlIntegerExpression1<S0> minus(ISqlExpression1<Integer, S0> other) {
		return new SqlIntegerExpression1<>(SqlOperations.MINUS.create(this, other));
	}

	default <S0> ISqlIntegerExpression1<S0> times(ISqlExpression1<Integer, S0> other) {
		return new SqlIntegerExpression1<>(SqlOperations.TIMES.create(this, other));
	}

	default <S0> ISqlIntegerExpression1<S0> modulo(ISqlExpression1<Integer, S0> other) {
		return new SqlIntegerExpression1<>(SqlOperations.MODULO.create(this, other));
	}

	default <S0> ISqlIntegerExpression1<S0> divided(ISqlExpression1<Integer, S0> other) {
		return new SqlIntegerExpression1<>(SqlOperations.INTEGRAL_DIVIDED.create(this, other));
	}

	// -------------------------------- OPERATIONS 2 -------------------------------- //

	default <S0, S1> ISqlBooleanExpression2<S0, S1> isEqual(ISqlExpression2<Integer, S0, S1> other) {
		return new SqlBooleanExpression2<>(SqlOperations.IS_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1> ISqlBooleanExpression2<S0, S1> isNotEqual(ISqlExpression2<Integer, S0, S1> other) {
		return new SqlBooleanExpression2<>(SqlOperations.IS_NOT_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1> ISqlBooleanExpression2<S0, S1> isLess(ISqlExpression2<Integer, S0, S1> other) {
		return new SqlBooleanExpression2<>(SqlOperations.IS_LESS.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1> ISqlBooleanExpression2<S0, S1> isLessEqual(ISqlExpression2<Integer, S0, S1> other) {
		return new SqlBooleanExpression2<>(SqlOperations.IS_LESS_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1> ISqlBooleanExpression2<S0, S1> isGreater(ISqlExpression2<Integer, S0, S1> other) {
		return new SqlBooleanExpression2<>(SqlOperations.IS_GREATER.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1> ISqlBooleanExpression2<S0, S1> isGreaterEqual(ISqlExpression2<Integer, S0, S1> other) {
		return new SqlBooleanExpression2<>(SqlOperations.IS_GREATER_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1> ISqlIntegerExpression2<S0, S1> plus(ISqlExpression2<Integer, S0, S1> other) {
		return new SqlIntegerExpression2<>(SqlOperations.PLUS.create(this, other));
	}

	default <S0, S1> ISqlIntegerExpression2<S0, S1> minus(ISqlExpression2<Integer, S0, S1> other) {
		return new SqlIntegerExpression2<>(SqlOperations.MINUS.create(this, other));
	}

	default <S0, S1> ISqlIntegerExpression2<S0, S1> times(ISqlExpression2<Integer, S0, S1> other) {
		return new SqlIntegerExpression2<>(SqlOperations.TIMES.create(this, other));
	}

	default <S0, S1> ISqlIntegerExpression2<S0, S1> modulo(ISqlExpression2<Integer, S0, S1> other) {
		return new SqlIntegerExpression2<>(SqlOperations.MODULO.create(this, other));
	}

	default <S0, S1> ISqlIntegerExpression2<S0, S1> divided(ISqlExpression2<Integer, S0, S1> other) {
		return new SqlIntegerExpression2<>(SqlOperations.INTEGRAL_DIVIDED.create(this, other));
	}

	// -------------------------------- OPERATIONS 3 -------------------------------- //

	default <S0, S1, S2> ISqlBooleanExpression3<S0, S1, S2> isEqual(ISqlExpression3<Integer, S0, S1, S2> other) {
		return new SqlBooleanExpression3<>(SqlOperations.IS_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1, S2> ISqlBooleanExpression3<S0, S1, S2> isNotEqual(ISqlExpression3<Integer, S0, S1, S2> other) {
		return new SqlBooleanExpression3<>(SqlOperations.IS_NOT_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1, S2> ISqlBooleanExpression3<S0, S1, S2> isLess(ISqlExpression3<Integer, S0, S1, S2> other) {
		return new SqlBooleanExpression3<>(SqlOperations.IS_LESS.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1, S2> ISqlBooleanExpression3<S0, S1, S2> isLessEqual(ISqlExpression3<Integer, S0, S1, S2> other) {
		return new SqlBooleanExpression3<>(SqlOperations.IS_LESS_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1, S2> ISqlBooleanExpression3<S0, S1, S2> isGreater(ISqlExpression3<Integer, S0, S1, S2> other) {
		return new SqlBooleanExpression3<>(SqlOperations.IS_GREATER.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1, S2> ISqlBooleanExpression3<S0, S1, S2> isGreaterEqual(ISqlExpression3<Integer, S0, S1, S2> other) {
		return new SqlBooleanExpression3<>(SqlOperations.IS_GREATER_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1, S2> ISqlIntegerExpression3<S0, S1, S2> plus(ISqlExpression3<Integer, S0, S1, S2> other) {
		return new SqlIntegerExpression3<>(SqlOperations.PLUS.create(this, other));
	}

	default <S0, S1, S2> ISqlIntegerExpression3<S0, S1, S2> minus(ISqlExpression3<Integer, S0, S1, S2> other) {
		return new SqlIntegerExpression3<>(SqlOperations.MINUS.create(this, other));
	}

	default <S0, S1, S2> ISqlIntegerExpression3<S0, S1, S2> times(ISqlExpression3<Integer, S0, S1, S2> other) {
		return new SqlIntegerExpression3<>(SqlOperations.TIMES.create(this, other));
	}

	default <S0, S1, S2> ISqlIntegerExpression3<S0, S1, S2> modulo(ISqlExpression3<Integer, S0, S1, S2> other) {
		return new SqlIntegerExpression3<>(SqlOperations.MODULO.create(this, other));
	}

	default <S0, S1, S2> ISqlIntegerExpression3<S0, S1, S2> divided(ISqlExpression3<Integer, S0, S1, S2> other) {
		return new SqlIntegerExpression3<>(SqlOperations.INTEGRAL_DIVIDED.create(this, other));
	}

	// -------------------------------- OPERATIONS 4 -------------------------------- //

	default <S0, S1, S2, S3> ISqlBooleanExpression4<S0, S1, S2, S3> isEqual(ISqlExpression4<Integer, S0, S1, S2, S3> other) {
		return new SqlBooleanExpression4<>(SqlOperations.IS_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1, S2, S3> ISqlBooleanExpression4<S0, S1, S2, S3> isNotEqual(ISqlExpression4<Integer, S0, S1, S2, S3> other) {
		return new SqlBooleanExpression4<>(SqlOperations.IS_NOT_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1, S2, S3> ISqlBooleanExpression4<S0, S1, S2, S3> isLess(ISqlExpression4<Integer, S0, S1, S2, S3> other) {
		return new SqlBooleanExpression4<>(SqlOperations.IS_LESS.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1, S2, S3> ISqlBooleanExpression4<S0, S1, S2, S3> isLessEqual(ISqlExpression4<Integer, S0, S1, S2, S3> other) {
		return new SqlBooleanExpression4<>(SqlOperations.IS_LESS_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1, S2, S3> ISqlBooleanExpression4<S0, S1, S2, S3> isGreater(ISqlExpression4<Integer, S0, S1, S2, S3> other) {
		return new SqlBooleanExpression4<>(SqlOperations.IS_GREATER.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1, S2, S3> ISqlBooleanExpression4<S0, S1, S2, S3> isGreaterEqual(ISqlExpression4<Integer, S0, S1, S2, S3> other) {
		return new SqlBooleanExpression4<>(SqlOperations.IS_GREATER_EQUAL.create(SqlValueTypes.BOOLEAN, this, other));
	}

	default <S0, S1, S2, S3> ISqlIntegerExpression4<S0, S1, S2, S3> plus(ISqlExpression4<Integer, S0, S1, S2, S3> other) {
		return new SqlIntegerExpression4<>(SqlOperations.PLUS.create(this, other));
	}

	default <S0, S1, S2, S3> ISqlIntegerExpression4<S0, S1, S2, S3> minus(ISqlExpression4<Integer, S0, S1, S2, S3> other) {
		return new SqlIntegerExpression4<>(SqlOperations.MINUS.create(this, other));
	}

	default <S0, S1, S2, S3> ISqlIntegerExpression4<S0, S1, S2, S3> times(ISqlExpression4<Integer, S0, S1, S2, S3> other) {
		return new SqlIntegerExpression4<>(SqlOperations.TIMES.create(this, other));
	}

	default <S0, S1, S2, S3> ISqlIntegerExpression4<S0, S1, S2, S3> modulo(ISqlExpression4<Integer, S0, S1, S2, S3> other) {
		return new SqlIntegerExpression4<>(SqlOperations.MODULO.create(this, other));
	}

	default <S0, S1, S2, S3> ISqlIntegerExpression4<S0, S1, S2, S3> divided(ISqlExpression4<Integer, S0, S1, S2, S3> other) {
		return new SqlIntegerExpression4<>(SqlOperations.INTEGRAL_DIVIDED.create(this, other));
	}
}

