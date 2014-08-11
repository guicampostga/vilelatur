package br.com.gfc.veiculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.gfc.generic.BaseDao;
import br.com.gfc.generic.Conexao;



public class VeiculoDao extends BaseDao<Veiculo> {

	private Conexao conexao;

	private Statement stmt;

	public VeiculoDao() {
		conexao = new Conexao();
	}
	
	public void inserir(Veiculo veiculo) {
		conexao = new Conexao();
		try {

			super.inserir(veiculo);
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into veiculo (");
			sql.append(" veiculo_modelo,");
			sql.append(" veiculo_marca,");
			sql.append(" veiculo_placa,");
			sql.append(" veiculo_ano,");
			sql.append(" veiculo_quantidade_vagas");
			sql.append(") values (");
			sql.append(" ?, ?, ?, ?, ?");
			sql.append(")");

			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, veiculo.getModeloVeiculo());
			preparedStatement.setString(2, veiculo.getMarca());
			preparedStatement.setString(3, veiculo.getPlaca());
			preparedStatement.setString(4, veiculo.getAno());
			preparedStatement.setInt(5, veiculo.getQtdVagas());
			preparedStatement.executeUpdate();
			System.out.print(sql);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Salvo com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}finally{
			conexao.fecharConexao();
			this.conexao.fecharConexao();
			super.conexao.fecharConexao();
		}
	}
	
	public void alterar(Veiculo veiculo) {
		conexao = new Conexao();
		try {
			super.alterar(veiculo);
			StringBuilder sql = new StringBuilder();
			sql.append(" update veiculo set ");
			sql.append(" veiculo_modelo = ?,");
			sql.append(" veiculo_marca = ?,");
			sql.append(" veiculo_placa = ?,");
			sql.append(" veiculo_ano = ?,");
			sql.append(" veiculo_quantidade_vagas = ?");
			sql.append(" where veiculo_codigo = ?");
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, veiculo.getModeloVeiculo());
			preparedStatement.setString(2, veiculo.getMarca());
			preparedStatement.setString(3, veiculo.getPlaca());
			preparedStatement.setString(4, veiculo.getAno());
			preparedStatement.setInt(5, veiculo.getQtdVagas());
			preparedStatement.setInt(6, veiculo.getCodigo());
			preparedStatement.executeUpdate();
			System.out.print(sql);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Alterado com sucesso"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.fecharConexao();
			this.conexao.fecharConexao();
			super.conexao.fecharConexao();
		}
	}
	
	public void remover(Veiculo veiculo) {
		conexao = new Conexao();
		try {
			super.remover(veiculo);
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from veiculo where veiculo_codigo = ? ");
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setInt(1, veiculo.getCodigo());
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}finally{
			conexao.fecharConexao();
			this.conexao.fecharConexao();
			super.conexao.fecharConexao();
		}
	}
	
	public List<Veiculo> listaTudo() {
		conexao = new Conexao();
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		try {
			stmt = (Statement) conexao.getConn().createStatement();
			String sql = "select * from veiculo";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Veiculo veiculo = new Veiculo();
				veiculo.setCodigo(rs.getInt("veiculo_codigo"));
				veiculo.setModeloVeiculo(rs.getString("veiculo_modelo"));
				veiculo.setMarca(rs.getString("veiculo_marca"));
				veiculo.setPlaca(rs.getString("veiculo_placa"));
				veiculo.setAno(rs.getString("veiculo_ano"));
				veiculo.setQtdVagas(rs.getInt("veiculo_quantidade_vagas"));
				veiculos.add(veiculo);

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}finally{
			conexao.fecharConexao();
			this.conexao.fecharConexao();
			super.conexao.fecharConexao();
		}
		return veiculos;
	}
	
	public Veiculo listaPorId(Integer codigo) {
		conexao = new Conexao();
		Veiculo veiculo = new Veiculo();
		try {
			String sql = "select * from veiculo where veiculo_codigo = ?";
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setInt(1, codigo);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				veiculo.setCodigo(rs.getInt("veiculo_codigo"));
				veiculo.setModeloVeiculo(rs.getString("veiculo_modelo"));
				veiculo.setMarca(rs.getString("veiculo_marca"));
				veiculo.setPlaca(rs.getString("veiculo_placa"));
				veiculo.setAno(rs.getString("veiculo_ano"));
				veiculo.setQtdVagas(rs.getInt("veiculo_quantidade_vagas"));
			}
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}finally{
			conexao.fecharConexao();
			this.conexao.fecharConexao();
			super.conexao.fecharConexao();
		}
		return veiculo;
	}


}
