package br.com.gfc.uf;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.gfc.generic.BaseDao;
import br.com.gfc.generic.Conexao;

public class UfDao extends BaseDao<Uf>{

	private Conexao conexao;

	private Statement stmt;

	public UfDao(){
		conexao = new Conexao();
	}

	public void inserir(Uf uf) {
		try {

			conexao = new Conexao();
			stmt = (Statement) conexao.getConn().createStatement();
			String sql = "insert into estado(estado_desccricao) ";
			sql += " VALUES (?)";

			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setString(1, uf.getUf());
			preparedStatement.executeUpdate();
			System.out.print(sql);
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
	
	public void alterar(Uf uf) {
		try {
			conexao = new Conexao();
			String sql = "update estado set estado_descricao = ?"
					+ " where estado_codigo = ?";
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setString(1, uf.getUf());
			preparedStatement.setInt(2, uf.getCodigo());
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
	
	public void remover(Uf uf){
		try {
			conexao = new Conexao();
			String sql = "delete from estado where estado_codigo = ?";
			PreparedStatement preparedStatement = conexao.getConn().
					prepareStatement(sql);
			preparedStatement.setInt(1, uf.getCodigo());
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
	
	public List<Uf> listaTudo(){
		conexao = new Conexao();
		List<Uf> ufs = new ArrayList<Uf>();
		try {
			stmt = (Statement) conexao.getConn().createStatement();
			String sql = "select * from estado";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Uf uf = new Uf();
				uf.setCodigo(rs.getInt("estado_codigo"));
				uf.setUf(rs.getString("estado_descricao"));
				ufs.add(uf);
				
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
		return ufs;
	}
	
	public Uf listaPorId(Integer codigo){
		conexao = new Conexao();
		Uf uf = new Uf();
		try {
			String sql = "select * from estado where estado_codigo = ?";
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setInt(1, codigo);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				uf.setCodigo(rs.getInt("estado_codigo"));
				uf.setUf(rs.getString("estado_descricao"));
				
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
		return uf;
	}
	
}