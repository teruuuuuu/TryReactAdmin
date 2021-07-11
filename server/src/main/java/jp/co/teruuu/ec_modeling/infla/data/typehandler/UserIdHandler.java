package jp.co.teruuu.ec_modeling.infla.data.typehandler;

import jp.co.teruuu.ec_modeling.app.domain.user.model.UserId;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIdHandler extends BaseTypeHandler<UserId> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, UserId parameter, JdbcType jdbcType)
          throws SQLException {
    ps.setString(i, parameter.toString());
  }

  @Override
  public UserId getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return new UserId(rs.getInt(columnName));
  }

  @Override
  public UserId getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return new UserId(rs.getInt(columnIndex));
  }

  @Override
  public UserId getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return new UserId(cs.getInt(columnIndex));
  }
}
