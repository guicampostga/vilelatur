package br.com.gfc.motorista;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.gfc.generic.BaseDao;
import br.com.gfc.generic.Conexao;
import br.com.gfc.generic.LibUtils;

public class MotoristaDao extends BaseDao<Motorista> {

	private Conexao conexao;

	private Statement stmt;

	public MotoristaDao() {
		conexao = new Conexao();
	}

	public void inserir(Motorista motorista) {
		try {

			conexao = new Conexao();
			super.inserir(motorista);
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into motorista (");
			sql.append(" funcionario_codigo,");
			sql.append(" motorista_tipo_cnh,");
			sql.append(" motorista_codigo_cnh,");
			sql.append(" motorista_data_venc_cnh,");
			sql.append(" veiculo_codigo");
			sql.append(") values (");
			sql.append(" ?, ?, ?, ?, ?");
			sql.append(")");

			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setInt(1, motorista.getFuncionario());
			preparedStatement.setString(2, motorista.getCnh());
			preparedStatement.setString(3, motorista.getCnhCodigo());
			preparedStatement.setString(4,
					LibUtils.date2dataMYSQL(motorista.getCnhValidade()));
			preparedStatement.setInt(5, motorista.getVeiculo());
			preparedStatement.executeUpdate();
			System.out.print(sql);
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

	public void alterar(Motorista motorista) {
		try {
			conexao = new Conexao();
			super.inserir(motorista);
			StringBuilder sql = new StringBuilder();
			sql.append(" update motorista set ");
			sql.append(" funcionario_codigo = ?,");
			sql.append(" motorista_tipo_cnh = ? ");
			sql.append(" motorista_codigo_cnh = ? ");
			sql.append(" motorista_data_venc_cnh = ? ");
			sql.append(" veiculo_codigo = ? ");
			sql.append(" where motorista_codigo = ?");
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setInt(1, motorista.getFuncionario());
			preparedStatement.setString(2, motorista.getCnh());
			preparedStatement.setString(3, motorista.getCnhCodigo());
			preparedStatement.setString(4,
					LibUtils.date2dataMYSQL(motorista.getCnhValidade()));
			preparedStatement.setInt(5, motorista.getVeiculo());
			preparedStatement.setInt(6, motorista.getCodigo());
			preparedStatement.executeUpdate();
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

	public void remover(Motorista motorista) {
		try {
			conexao = new Conexao();
			String sql = "delete from motorista where motorista_codigo = ?";
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setInt(1, motorista.getCodigo());
			preparedStatement.executeUpdate();
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

	public List<Motorista> listaTudo() {
		conexao = new Conexao();
		List<Motorista> motoristas = new ArrayList<Motorista>();
		try {
			stmt = (Statement) conexao.getConn().createStatement();
			String sql = "select * from motorista left join veiculo on motorista.veiculo_codigo = veiculo.veiculo_codigo " +
					"left join funcionario on motorista.funcionario_codigo = funcionario.funcionario_codigo";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Motorista motorista = new Motorista();
				motorista.setCodigo(rs.getInt("motorista_codigo"));
				motorista.setFuncionario(rs.getInt("funcionario_codigo"));
				motorista.setFuncionarioNome(rs.getString("funcionario_nome"));
				motorista.setCnh(rs.getString("motorista_tipo_cnh"));
				motorista.setCnhCodigo(rs.getString("motorista_codigo_cnh"));
				try {

					motorista.setCnhValidade(LibUtils.dateMYSQL2Date(rs
							.getString("motorista_data_venc_cnh")));
				} catch (Exception e) {
					motorista.setCnhValidade(null);
				}
				motorista.setVeiculo(rs.getInt("veiculo_codigo"));
				motorista.setVeiculoNome(rs.getString("veiculo_modelo"));
				motoristas.add(motorista);
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
		return motoristas;
	}

	public Motorista listaPorId(Integer codigo) {
		conexao = new Conexao();
		Motorista motorista = new Motorista();
		try {
			String sql = "select * from motorista where motorista_codigo = ?";
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setInt(1, codigo);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				motorista.setCodigo(rs.getInt("motorista_codigo"));
				motorista.setFuncionario(rs.getInt("funcionario_codigo"));
				motorista.setCnh(rs.getString("motorista_tipo_cnh"));
				motorista.setCnhCodigo(rs.getString("motorista_codigo_cnh"));
				try {

					motorista.setCnhValidade(LibUtils.dateMYSQL2Date(rs
							.getString("motorista_data_venc_cnh")));
				} catch (Exception e) {
					motorista.setCnhValidade(null);
				}
				motorista.setVeiculo(rs.getInt("veiculo_codigo"));
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
		return motorista;
	}

}