package jp.co.teruuu.ec_modeling.infla.data.typehandler;

import jp.co.teruuu.ec_modeling.app.domain.order.model.PaymentId;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentInfoIdHandler extends BaseTypeHandler<PaymentId> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, PaymentId parameter, JdbcType jdbcType)
          throws SQLException {
    ps.setString(i, parameter.toString());
  }

  @Override
  public PaymentId getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return new PaymentId(rs.getInt(columnName));
  }

  @Override
  public PaymentId getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return new PaymentId(rs.getInt(columnIndex));
  }

  @Override
  public PaymentId getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return new PaymentId(cs.getInt(columnIndex));
  }
}
