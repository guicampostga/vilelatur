package br.com.gfc.empresa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.gfc.generic.BaseDao;
import br.com.gfc.generic.Conexao;

public class EmpresaDao extends BaseDao<Empresa>{

	private Conexao conexao;

	private Statement stmt;

	public EmpresaDao(){
		conexao = new Conexao();
	}

	public void inserir(Empresa empresa) {
		try {

			conexao = new Conexao();
			stmt = (Statement) conexao.getConn().createStatement();
			String sql = "INSERT INTO EMPRESA(EMPRESA_NOME_FANTASIA, " +
					"EMPRESA_RAZAO_SOCIAL, EMPRESA_CNPJ, EMPRESA_ENDERECO, EMPRESA_BAIRRO, " +
					"EMPRESA_NUMERO, EMPRESA_COMPLEMENTO, EMPRESA_CIDADE, estado_codigo, EMPRESA_TELEFONE, " +
					"EMPRESA_EMAIL) ";
			sql += " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setString(1, empresa.getNomeFantazia());
			preparedStatement.setString(2, empresa.getRazaoSocial());
			preparedStatement.setString(3, empresa.getCnpj());
			preparedStatement.setString(4, empresa.getEndereco());
			preparedStatement.setString(5, empresa.getBairro());
			preparedStatement.setString(6, empresa.getNumero());
			preparedStatement.setString(7, empresa.getComplemento());
			preparedStatement.setString(8, empresa.getCidade());
			preparedStatement.setInt(9, empresa.getEstado());
			preparedStatement.setString(10, empresa.getTelefone());
			preparedStatement.setString(11, empresa.getEmail());
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
	
	public void alterar(Empresa empresa) {
		try {
			conexao = new Conexao();
			String sql = "UPDATE EMPRESA SET EMPRESA_NOME_FANTASIA = ?, " +
					"EMPRESA_RAZAO_SOCIAL = ?, EMPRESA_CNPJ = ?, EMPRESA_ENDERECO = ?, " +
					"EMPRESA_BAIRRO = ?, EMPRESA_NUMERO = ?, EMPRESA_COMPLEMENTO = ?, " +
					"EMPRESA_CIDADE = ?, estado_codigo = ?, EMPRESA_TELEFONE = ?, " +
					"EMPRESA_EMAIL = ?"
					+ " WHERE EMPRESA_CODIGO = ?";
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setString(1, empresa.getNomeFantazia());
			preparedStatement.setString(2, empresa.getRazaoSocial());
			preparedStatement.setString(3, empresa.getCnpj());
			preparedStatement.setString(4, empresa.getEndereco());
			preparedStatement.setString(5, empresa.getBairro());
			preparedStatement.setString(6, empresa.getNumero());
			preparedStatement.setString(7, empresa.getComplemento());
			preparedStatement.setString(8, empresa.getCidade());
			preparedStatement.setInt(9, empresa.getEstado());
			preparedStatement.setString(10, empresa.getTelefone());
			preparedStatement.setString(11, empresa.getEmail());
			preparedStatement.setInt(12, empresa.getCodigo());
			preparedStatement.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Alterado com sucesso"));
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
	
	public void remover(Empresa empresa){
		try {
			conexao = new Conexao();
			String sql = "DELETE FROM EMPRESA WHERE EMPRESA_CODIGO = ?";
			PreparedStatement preparedStatement = conexao.getConn().
					prepareStatement(sql);
			preparedStatement.setInt(1, empresa.getCodigo());
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
	
	public List<Empresa> listaTudo(){
		conexao = new Conexao();
		List<Empresa> empresas = new ArrayList<Empresa>();
		try {
			stmt = (Statement) conexao.getConn().createStatement();
			String sql = "SELECT * FROM EMPRESA";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Empresa empresa = new Empresa();
				empresa.setCodigo(rs.getInt("EMPRESA_CODIGO"));
				empresa.setNomeFantazia(rs.getString("EMPRESA_NOME_FANTASIA"));
				empresa.setRazaoSocial(rs.getString("EMPRESA_RAZAO_SOCIAL"));
				empresa.setCnpj(rs.getString("EMPRESA_CNPJ"));
				empresa.setEndereco(rs.getString("EMPRESA_ENDERECO"));
				empresa.setBairro(rs.getString("EMPRESA_BAIRRO"));
				empresa.setNumero(rs.getString("EMPRESA_NUMERO"));
				empresa.setComplemento(rs.getString("EMPRESA_COMPLEMENTO"));
				empresa.setCidade(rs.getString("EMPRESA_CIDADE"));
				empresa.setEstado(rs.getInt("estado_codigo"));
				empresa.setTelefone(rs.getString("EMPRESA_TELEFONE"));
				empresa.setEmail(rs.getString("EMPRESA_EMAIL"));
				empresas.add(empresa);
				
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
		return empresas;
	}
	
	public Empresa listaPorId(Integer codigo){
		conexao = new Conexao();
		Empresa empresa = new Empresa();
		try {
			String sql = "SELECT * FROM EMPRESA WHERE EMPRESA_CODIGO = ?";
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setInt(1, codigo);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				empresa.setCodigo(rs.getInt("EMPRESA_CODIGO"));
				empresa.setNomeFantazia(rs.getString("EMPRESA_NOME_FANTASIA"));
				empresa.setRazaoSocial(rs.getString("EMPRESA_RAZAO_SOCIAL"));
				empresa.setCnpj(rs.getString("EMPRESA_CNPJ"));
				empresa.setEndereco(rs.getString("EMPRESA_ENDERECO"));
				empresa.setBairro(rs.getString("EMPRESA_BAIRRO"));
				empresa.setNumero(rs.getString("EMPRESA_NUMERO"));
				empresa.setComplemento(rs.getString("EMPRESA_COMPLEMENTO"));
				empresa.setCidade(rs.getString("EMPRESA_CIDADE"));
				empresa.setEstado(rs.getInt("estado_codigo"));
				empresa.setTelefone(rs.getString("EMPRESA_TELEFONE"));
				empresa.setEmail(rs.getString("EMPRESA_EMAIL"));
				
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
		return empresa;
	}
	
}