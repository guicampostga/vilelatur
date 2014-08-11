package br.com.gfc.cidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.gfc.generic.BaseDao;
import br.com.gfc.generic.Conexao;
public class CidadeDao extends BaseDao<Cidade>{

	private Conexao conexao;

	private Statement stmt;

	public CidadeDao(){
		conexao = new Conexao();
	}

	public void inserir(Cidade cidade) {
		try {

			conexao = new Conexao();
			super.inserir(cidade);
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into cidade (");
			sql.append(" cidade_descricao,");
			sql.append(" estado_codigo");
			sql.append(") values (");
			sql.append(" ?, ?");
			sql.append(")");

			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, cidade.getDescricao());
			preparedStatement.setInt(2, cidade.getUf());
			preparedStatement.executeUpdate();
			System.out.print(sql);
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}finally{
			conexao.fecharConexao();
			this.conexao.fecharConexao();
			super.conexao.fecharConexao();		}
	}
	
	public void alterar(Cidade cidade) {
		try {
			conexao = new Conexao();
			super.inserir(cidade);
			StringBuilder sql = new StringBuilder();
			sql.append(" update cidade set ");
			sql.append(" cidade_descricao = ?,");
			sql.append(" estado_codigo = ? ");
			sql.append(" where cidade_codigo = ?");
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, cidade.getDescricao());
			preparedStatement.setInt(2, cidade.getUf());
			preparedStatement.setInt(3, cidade.getCodigo());
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
	
	public void remover(Cidade cidade){
		try {
			conexao = new Conexao();
			String sql = "delete from cidade where cidade_codigo = ?";
			PreparedStatement preparedStatement = conexao.getConn().
					prepareStatement(sql);
			preparedStatement.setInt(1, cidade.getCodigo());
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
	
	public List<Cidade> listaTudo(){
		conexao = new Conexao();
		List<Cidade> cidades = new ArrayList<Cidade>();
		try {
			stmt = (Statement) conexao.getConn().createStatement();
			String sql = "select * from cidade left join estado on cidade.estado_codigo = estado.estado_codigo";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Cidade cidade = new Cidade();
				cidade.setCodigo(rs.getInt("cidade_codigo"));
				cidade.setDescricao(rs.getString("cidade_descricao"));
				cidade.setUf(rs.getInt("estado_codigo"));
				cidade.setUfValor(rs.getString("estado_descricao"));
				cidades.add(cidade);
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
		return cidades;
	}
	
	public Cidade listaPorId(Integer codigo){
		conexao = new Conexao();
		Cidade cidade = new Cidade();
		try {
			String sql = "select * from cidade where cidade_codigo = ?";
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setInt(1, codigo);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				cidade.setCodigo(rs.getInt("cidade_codigo"));
				cidade.setDescricao(rs.getString("cidade_descricao"));
				cidade.setUf(rs.getInt("estado_codigo"));
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
		return cidade;
	}
	
}