package br.com.gfc.funcao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.gfc.generic.BaseDao;
import br.com.gfc.generic.Conexao;

public class FuncaoDao extends BaseDao<Funcao> {

	private Conexao conexao;

	private Statement stmt;

	public FuncaoDao() {
		conexao = new Conexao();
	}

	public void inserir(Funcao funcao) {
		try {

			conexao = new Conexao();
			stmt = (Statement) conexao.getConn().createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into funcao (");
			sql.append(" funcao_descricao");
			sql.append(") values (");
			sql.append(" ? ");
			sql.append(")");
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, funcao.getDescricao());
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

	public void alterar(Funcao funcao) {
		try {
			conexao = new Conexao();
			StringBuilder sql = new StringBuilder();
			sql.append(" update funcao set ");
			sql.append(" where funcao_codigo = ?");
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, funcao.getDescricao());
			preparedStatement.setInt(2, funcao.getCodigo());
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

	public void remover(Funcao funcao) {
		try {
			conexao = new Conexao();
			String sql = "delete from funcao where funcao_codigo = ?";
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setInt(1, funcao.getCodigo());
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

	public List<Funcao> listaTudo() {
		conexao = new Conexao();
		List<Funcao> funcoes = new ArrayList<Funcao>();
		try {
			stmt = (Statement) conexao.getConn().createStatement();
			String sql = "select * from funcao";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Funcao funcao = new Funcao();
				funcao.setCodigo(rs.getInt("funcao_codigo"));
				funcao.setDescricao(rs.getString("funcao_descricao"));
				funcoes.add(funcao);

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
		return funcoes;
	}

	public Funcao listaPorId(Integer codigo) {
		conexao = new Conexao();
		Funcao funcao = new Funcao();
		try {
			String sql = "select * from funcao where funcao_codigo = ?";
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setInt(1, codigo);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				funcao.setCodigo(rs.getInt("funcao_codigo"));
				funcao.setDescricao(rs.getString("funcao_descricao"));
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
		return funcao;
	}

}