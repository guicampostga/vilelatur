package br.com.gfc.cliente;

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

public class ClienteDao extends BaseDao<Cliente> {

	private Conexao conexao;

	private Statement stmt;

	public ClienteDao() {
		conexao = new Conexao();

	}

	public void inserir(Cliente cliente) {
		try {
			conexao = new Conexao();
			super.inserir(cliente);
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO CLIENTE (");
			sql.append(" CLIENTE_NOME,");
			sql.append(" CLIENTE_RG,");
			sql.append(" CLIENTE_CPF,");
			sql.append(" CLIENTE_DATA_NASCIMENTO,");
			sql.append(" CLIENTE_ENDERECO,");
			sql.append(" CLIENTE_BAIRRO,");
			sql.append(" CLIENTE_NUMERO,");
			sql.append(" CLIENTE_COMPLEMENTO,");
			sql.append(" CLIENTE_TELEFONE,");
			sql.append(" CLIENTE_CIDADE,");
			sql.append(" ESTADO_CODIGO");
			sql.append(") values (");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
			sql.append(")");

			PreparedStatement preparedStatement = super.conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setString(2, cliente.getRg());
			preparedStatement.setString(3, cliente.getCpf());
			preparedStatement.setString(4,
					LibUtils.date2dataMYSQL(cliente.getDataNasc()));
			preparedStatement.setString(5, cliente.getEndereco());
			preparedStatement.setString(6, cliente.getBairro());
			preparedStatement.setString(7, cliente.getNumero());
			preparedStatement.setString(8, cliente.getComplemento());
			preparedStatement.setString(9, cliente.getTelefone());
			preparedStatement.setString(10, cliente.getCidade());
			preparedStatement.setInt(11, cliente.getUf());
			preparedStatement.executeUpdate();
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

	public void alterar(Cliente cliente) {
		try {
			conexao = new Conexao();
			super.inserir(cliente);
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE CLIENTE SET ");
			sql.append(" CLIENTE_NOME = ?,");
			sql.append(" CLIENTE_RG = ?,");
			sql.append(" CLIENTE_CPF = ?,");
			sql.append(" CLIENTE_DATA_NASCIMENTO = ?,");
			sql.append(" CLIENTE_ENDERECO = ?,");
			sql.append(" CLIENTE_BAIRRO = ?,");
			sql.append(" CLIENTE_NUMERO = ?,");
			sql.append(" CLIENTE_COMPLEMENTO = ?,");
			sql.append(" CLIENTE_TELEFONE = ?,");
			sql.append(" CLIENTE_CIDADE = ?,");
			sql.append(" ESTADO_CODIGO = ?");
			sql.append(" WHERE CLIENTE_CODIGO = ?");
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setString(2, cliente.getRg());
			preparedStatement.setString(3, cliente.getCpf());
			preparedStatement.setString(4,
					LibUtils.date2dataMYSQL(cliente.getDataNasc()));
			preparedStatement.setString(5, cliente.getEndereco());
			preparedStatement.setString(6, cliente.getBairro());
			preparedStatement.setString(7, cliente.getNumero());
			preparedStatement.setString(8, cliente.getComplemento());
			preparedStatement.setString(9, cliente.getTelefone());
			preparedStatement.setString(10, cliente.getCidade());
			preparedStatement.setInt(11, cliente.getUf());
			preparedStatement.setInt(12, cliente.getCodigo());
			preparedStatement.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Alterado com sucesso"));
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

	public void remover(Cliente cliente) {
		try {
			conexao = new Conexao();
			super.inserir(cliente);
			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE FROM CLIENTE WHERE CLIENTE_CODIGO = ? ");
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setInt(1, cliente.getCodigo());
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

	public List<Cliente> listaTudo() {
		conexao = new Conexao();
		List<Cliente> clientes = new ArrayList<Cliente>();
		String sql = "select * from cliente left join estado on cliente.estado_codigo = estado.estado_codigo";
		if (super.condicaoFixa != "" && super.condicaoFixa != null) {
			sql += " where cliente." + super.condicaoFixa + " group by cliente.cliente_codigo";
			super.condicaoFixa = null;
		}
		try {
			stmt = (Statement) conexao.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodigo(rs.getInt("CLIENTE_CODIGO"));
				cliente.setNome(rs.getString("CLIENTE_NOME"));
				cliente.setRg(rs.getString("CLIENTE_RG"));
				cliente.setCpf(rs.getString("CLIENTE_CPF"));
				try {

					cliente.setDataNasc(LibUtils.dateMYSQL2Date(rs
							.getString("CLIENTE_DATA_NASCIMENTO")));
				} catch (Exception e) {
					cliente.setDataNasc(null);
				}

				cliente.setEndereco(rs.getString("CLIENTE_ENDERECO"));
				cliente.setBairro(rs.getString("CLIENTE_BAIRRO"));
				cliente.setNumero(rs.getString("CLIENTE_NUMERO"));
				cliente.setComplemento(rs.getString("CLIENTE_COMPLEMENTO"));
				cliente.setTelefone(rs.getString("CLIENTE_TELEFONE"));
				cliente.setCidade(rs.getString("CLIENTE_CIDADE"));
				cliente.setUf(rs.getInt("ESTADO_CODIGO"));
				cliente.setUfNome(rs.getString("estado_descricao"));
				clientes.add(cliente);

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
		return clientes;
	}

	public Cliente listaPorId(Integer codigo) {
		conexao = new Conexao();
		Cliente cliente = new Cliente();
		try {
			String sql = "SELECT * FROM CLIENTE WHERE CLIENTE_CODIGO = ?";
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setInt(1, codigo);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				cliente.setCodigo(rs.getInt("CLIENTE_CODIGO"));
				cliente.setNome(rs.getString("CLIENTE_NOME"));
				cliente.setRg(rs.getString("CLIENTE_RG"));
				cliente.setCpf(rs.getString("CLIENTE_CPF"));
				try {

					cliente.setDataNasc(LibUtils.dateMYSQL2Date(rs
							.getString("CLIENTE_DATA_NASCIMENTO")));
				} catch (Exception e) {
					cliente.setDataNasc(null);
				}

				cliente.setEndereco(rs.getString("CLIENTE_ENDERECO"));
				cliente.setBairro(rs.getString("CLIENTE_BAIRRO"));
				cliente.setNumero(rs.getString("CLIENTE_NUMERO"));
				cliente.setComplemento(rs.getString("CLIENTE_COMPLEMENTO"));
				cliente.setTelefone(rs.getString("CLIENTE_TELEFONE"));
				cliente.setCidade(rs.getString("CLIENTE_CIDADE"));
				cliente.setUf(rs.getInt("ESTADO_CODIGO"));

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
		return cliente;
	}

}