package jp.co.teruuu.ec_modeling.infla.data.typehandler;

import jp.co.teruuu.ec_modeling.app.domain.order.model.OrderId;
import jp.co.teruuu.ec_modeling.app.domain.user.model.UserId;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderIdHandler extends BaseTypeHandler<OrderId> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, OrderId parameter, JdbcType jdbcType)
          throws SQLException {
    ps.setString(i, parameter.toString());
  }

  @Override
  public OrderId getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return new OrderId(rs.getInt(columnName));
  }

  @Override
  public OrderId getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return new OrderId(rs.getInt(columnIndex));
  }

  @Override
  public OrderId getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return new OrderId(cs.getInt(columnIndex));
  }
}
