package jp.co.teruuu.ec_modeling.infla.data.typehandler;

import jp.co.teruuu.ec_modeling.app.domain.product.model.ProductId;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductIdHandler extends BaseTypeHandler<ProductId> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, ProductId parameter, JdbcType jdbcType)
          throws SQLException {
    ps.setString(i, parameter.toString());
  }

  @Override
  public ProductId getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return new ProductId(rs.getInt(columnName));
  }

  @Override
  public ProductId getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return new ProductId(rs.getInt(columnIndex));
  }

  @Override
  public ProductId getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return new ProductId(cs.getInt(columnIndex));
  }
}
