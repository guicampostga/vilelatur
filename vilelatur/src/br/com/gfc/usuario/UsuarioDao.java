package br.com.gfc.usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.gfc.generic.BaseDao;
import br.com.gfc.generic.Conexao;

public class UsuarioDao extends BaseDao<Usuario> {

	private Conexao conexao;

	private Statement stmt;

	public UsuarioDao() {
		conexao = new Conexao();
	}

	public void inserir(Usuario usuario) {
		try {

			super.inserir(usuario);
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into users (");
			sql.append(" username,");
			sql.append(" password,");
			sql.append(" enabled,");
			sql.append(" funcionario_codigo");
			sql.append(") values (");
			sql.append(" ?, ?, ?, ?");
			sql.append(")");

			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, usuario.getUsername());
			preparedStatement.setString(2, usuario.getPassword());
			preparedStatement.setString(3, usuario.getEnabled());
			preparedStatement.setInt(4, usuario.getFuncionario());
			preparedStatement.executeUpdate();
			System.out.print(sql);

			super.inserir(usuario);
			StringBuilder sql2 = new StringBuilder();
			sql2.append(" insert into authorities (");
			sql2.append(" username,");
			sql2.append(" authority");
			sql2.append(") values (");
			sql2.append(" ?, ?");
			sql2.append(")");

			PreparedStatement preparedStatement2 = conexao.getConn()
					.prepareStatement(sql2.toString());
			preparedStatement2.setString(1, usuario.getUsername());
			preparedStatement2.setString(2, usuario.getAuthority());
			preparedStatement2.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Salvo com sucesso"));
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

	public void alterar(Usuario usuario) {
		conexao = new Conexao();
		super.alterar(usuario);
		StringBuilder sql = new StringBuilder();
		sql.append(" update users set ");
		sql.append(" username = ?,");
		sql.append(" password = ?,");
		sql.append(" enabled = ?,");
		sql.append(" funcionario_codigo = ?");
		sql.append(" where ");
		sql.append(" users_codigo = ? ");
		try {
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, usuario.getUsername());
			preparedStatement.setString(2, usuario.getPassword());
			preparedStatement.setString(3, usuario.getEnabled());
			preparedStatement.setInt(4, usuario.getFuncionario());
			preparedStatement.setInt(5, usuario.getCodigo());
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

	public void remover(Usuario usuario) {
		super.remover(usuario);
		conexao = new Conexao();
		String sql = "delete from users where users_codigo = ?";

		try {
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setInt(1, usuario.getCodigo());
			preparedStatement.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Registro excluido com sucesso"));
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

	public List<Usuario> listaTudo() {
		conexao = new Conexao();
		super.listaTudo();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuario;
		String sql = "select * from users left join authorities on authorities.username = users.username group by users.username";
		try {
			stmt = conexao.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				usuario = new Usuario();
				usuario.setCodigo(rs.getInt("users_codigo"));
				usuario.setUsername(rs.getString("username"));
				usuario.setPassword(rs.getString("password"));
				usuario.setEnabled(rs.getString("enabled"));
				usuario.setFuncionario(rs.getInt("funcionario_codigo"));
				usuario.setAuthority(rs.getString("authority"));
				usuarios.add(usuario);
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

		return usuarios;
	}

}
