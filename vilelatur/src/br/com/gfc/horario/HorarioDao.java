package br.com.gfc.horario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.gfc.generic.BaseDao;
import br.com.gfc.generic.Conexao;
public class HorarioDao extends BaseDao<Horario>{

	private Conexao conexao;

	private Statement stmt;

	public HorarioDao(){
		conexao = new Conexao();
	}

	public void inserir(Horario horario) {
		try {

			conexao = new Conexao();
			super.inserir(horario);
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into horario (");
			sql.append(" horario_saida,");
			sql.append(" horario_chegada,");
			sql.append(" cidade_codigo_origem,");
			sql.append(" cidade_codigo_destino");
			sql.append(") values (");
			sql.append(" ?, ?, ?, ?");
			sql.append(")");

			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, horario.getHoraSaida());
			preparedStatement.setString(2, horario.getHoraChegada());
			preparedStatement.setInt(3, horario.getCidadeOrigem());
			preparedStatement.setInt(4, horario.getCidadeDestino());
			preparedStatement.executeUpdate();
			System.out.print(sql);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Horario emitida com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		} finally {
			conexao.fecharConexao();
			this.conexao.fecharConexao();
			super.conexao.fecharConexao();
		}
	}
	
	public void alterar(Horario horario) {
		try {
			conexao = new Conexao();
			super.inserir(horario);
			StringBuilder sql = new StringBuilder();
			sql.append(" update horario set ");
			sql.append(" horario_saida = ?,");
			sql.append(" horario_chegada = ?,");
			sql.append(" cidade_codigo_origem = ?,");
			sql.append(" cidade_codigo_destino = ?");
			sql.append(" where horario_codigo = ?");
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, horario.getHoraSaida());
			preparedStatement.setString(2, horario.getHoraChegada());
			preparedStatement.setInt(3, horario.getCidadeOrigem());
			preparedStatement.setInt(4, horario.getCidadeDestino());
			preparedStatement.setInt(5, horario.getCodigo());
			preparedStatement.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Alterado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		} finally {
			conexao.fecharConexao();
			this.conexao.fecharConexao();
			super.conexao.fecharConexao();
		}
	}
	
	public void remover(Horario horario){
		try {
			conexao = new Conexao();
			String sql = "delete from horario where horario_codigo = ?";
			PreparedStatement preparedStatement = conexao.getConn().
					prepareStatement(sql);
			preparedStatement.setInt(1, horario.getCodigo());
			preparedStatement.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Removido com sucesso"));
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
	
	public List<Horario> listaTudo(){
		conexao = new Conexao();
		List<Horario> horarios = new ArrayList<Horario>();
		try {
			stmt = (Statement) conexao.getConn().createStatement();
			String sql = "select * from horario left join cidade on horario.cidade_codigo_origem = cidade.cidade_codigo" +
					" left join cidade c on horario.cidade_codigo_destino = c.cidade_codigo";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Horario horario = new Horario();
				horario.setCodigo(rs.getInt("horario_codigo"));
				horario.setHoraSaida(rs.getString("horario_saida"));
				horario.setHoraChegada(rs.getString("horario_chegada"));
				horario.setCidadeOrigem(rs.getInt("cidade_codigo_origem"));
				horario.setCidadeDestino(rs.getInt("cidade_codigo_destino"));
				horario.setCidadeDestinoVlr(rs.getString("cidade_descricao"));
				horario.setCidadeOrigemVlr(rs.getString("c.cidade_descricao"));
				horarios.add(horario);
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
		return horarios;
	}
	
	public Horario listaPorId(Integer codigo){
		conexao = new Conexao();
		Horario horario = new Horario();
		try {
			String sql = "select * from horario where horario_codigo = ?";
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setInt(1, codigo);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				horario.setCodigo(rs.getInt("horario_codigo"));
				horario.setHoraSaida(rs.getString("horario_saida"));
				horario.setHoraChegada(rs.getString("horario_chegada"));
				horario.setCidadeOrigem(rs.getInt("cidade_codigo_origem"));
				horario.setCidadeDestino(rs.getInt("cidade_codigo_destino"));
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
		return horario;
	}
	
}