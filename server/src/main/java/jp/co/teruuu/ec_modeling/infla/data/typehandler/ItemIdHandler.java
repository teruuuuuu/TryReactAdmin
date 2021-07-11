package jp.co.teruuu.ec_modeling.infla.data.typehandler;

import jp.co.teruuu.ec_modeling.app.domain.order.model.ItemId;
import jp.co.teruuu.ec_modeling.app.domain.order.model.OrderId;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemIdHandler extends BaseTypeHandler<ItemId> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, ItemId parameter, JdbcType jdbcType)
          throws SQLException {
    ps.setString(i, parameter.toString());
  }

  @Override
  public ItemId getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return new ItemId(rs.getInt(columnName));
  }

  @Override
  public ItemId getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return new ItemId(rs.getInt(columnIndex));
  }

  @Override
  public ItemId getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return new ItemId(cs.getInt(columnIndex));
  }
}
