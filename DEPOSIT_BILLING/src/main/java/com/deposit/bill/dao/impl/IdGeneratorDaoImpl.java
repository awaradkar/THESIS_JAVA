package com.deposit.bill.dao.impl;

import org.springframework.stereotype.Repository;

import com.deposit.bill.config.DBConnection;
import com.deposit.bill.dao.IdGeneratorDao;
import com.deposit.bill.model.dto.IdGeneratorDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class IdGeneratorDaoImpl implements IdGeneratorDao {

	@Override
	public IdGeneratorDTO getNewId(String key) {

		IdGeneratorDTO idGeneratorDTO = new IdGeneratorDTO();
		Connection conn = DBConnection.getDBConnection();
		StringBuilder selectSql = new StringBuilder("");
		try {
			selectSql.append("SELECT idKey, idValue from ID_GENERATOR where idKey = ?");

			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(selectSql.toString());

				ps.setString(1, key);

				ResultSet resultSet = ps.executeQuery();

				if (resultSet.next()) {
					idGeneratorDTO.setIdKey(resultSet.getString("idKey"));
					idGeneratorDTO.setIdValue(resultSet.getBigDecimal("idValue").add(new BigDecimal(1)));

					String updateSql = "update ID_GENERATOR set idValue = ?, modifiedDate = ? where idKey = ?";

					ps.clearParameters();
					ps.close();
					ps = conn.prepareStatement(updateSql);
					ps.setBigDecimal(1, idGeneratorDTO.getIdValue());
					ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
					ps.setString(3, key);

					ps.executeUpdate();
				} else {
					idGeneratorDTO.setIdKey(key);
					idGeneratorDTO.setIdValue(new BigDecimal(1));

					String insertSql = "insert into ID_GENERATOR (idKey, idValue, createdDate) values (?,?,?)";

					ps.clearParameters();
					ps.close();

					ps = conn.prepareStatement(insertSql);
					ps.setString(1, key);
					ps.setBigDecimal(2, idGeneratorDTO.getIdValue());
					ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

					ps.executeUpdate();
				}

				ps.clearParameters();
				ps.close();
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return idGeneratorDTO;

	}
}
