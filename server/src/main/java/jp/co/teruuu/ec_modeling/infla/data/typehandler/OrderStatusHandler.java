package jp.co.teruuu.ec_modeling.infla.data.typehandler;

import jp.co.teruuu.ec_modeling.app.domain.order.model.OrderId;
import jp.co.teruuu.ec_modeling.app.domain.order.model.OrderStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStatusHandler extends BaseTypeHandler<OrderStatus> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, OrderStatus parameter, JdbcType jdbcType)
          throws SQLException {
    ps.setString(i, parameter.toString());
  }

  @Override
  public OrderStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return OrderStatus.byCode(rs.getInt(columnName));
  }

  @Override
  public OrderStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return OrderStatus.byCode(rs.getInt(columnIndex));
  }

  @Override
  public OrderStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return OrderStatus.byCode(cs.getInt(columnIndex));
  }
}
