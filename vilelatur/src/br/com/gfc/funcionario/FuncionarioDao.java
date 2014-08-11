package br.com.gfc.funcionario;

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

public class FuncionarioDao extends BaseDao<Funcionario> {

	private Conexao conexao;

	private Statement stmt;

	public FuncionarioDao() {
		conexao = new Conexao();

	}

	public void inserir(Funcionario funcionario) {
		try {
			conexao = new Conexao();
			super.inserir(funcionario);
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into funcionario (");
			sql.append(" funcionario_nome,");
			sql.append(" funcionario_cpf,");
			sql.append(" funcionario_rg,");
			sql.append(" funcionario_data_nascimento,");
			sql.append(" funcionario_endereco,");
			sql.append(" funcionario_numero_casa,");
			sql.append(" funcionario_complemento,");
			sql.append(" funcionario_bairro,");
			sql.append(" funcionario_cidade,");
			sql.append(" estado_codigo,");
			sql.append(" funcionario_telefone,");
			sql.append(" funcionario_email,");
			sql.append(" funcao_codigo");
			sql.append(") values (");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
			sql.append(")");
			System.out.print(sql);
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, funcionario.getNome());
			preparedStatement.setString(2, funcionario.getCpf());
			preparedStatement.setString(3, funcionario.getRg());
			preparedStatement.setString(4,
					LibUtils.date2dataMYSQL(funcionario.getDataNasc()));
			preparedStatement.setString(5, funcionario.getEndereco());
			preparedStatement.setString(6, funcionario.getNumeroCasa());
			preparedStatement.setString(7, funcionario.getComplemento());
			preparedStatement.setString(8, funcionario.getBairro());
			preparedStatement.setString(9, funcionario.getCidade());
			preparedStatement.setInt(10, funcionario.getUf());
			preparedStatement.setString(11, funcionario.getTelefone());
			preparedStatement.setString(12, funcionario.getEmail());
			preparedStatement.setInt(13, funcionario.getFuncao());
			preparedStatement.executeUpdate();
			System.out.print(sql);
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

	public void alterar(Funcionario funcionario) {
		try {
			conexao = new Conexao();
			super.alterar(funcionario);
			StringBuilder sql = new StringBuilder();
			sql.append(" update funcionario set");
			sql.append(" funcionario_nome = ?,");
			sql.append(" funcionario_cpf = ?,");
			sql.append(" funcionario_rg = ?,");
			sql.append(" funcionario_data_nascimento = ?,");
			sql.append(" funcionario_endereco = ?,");
			sql.append(" funcionario_numero_casa = ?,");
			sql.append(" funcionario_complemento = ?,");
			sql.append(" funcionario_bairro = ?,");
			sql.append(" funcionario_cidade = ?,");
			sql.append(" estado_codigo = ?,");
			sql.append(" funcionario_telefone = ?,");
			sql.append(" funcionario_email = ?,");
			sql.append(" funcao_codigo = ?");
			sql.append(" where funcionario_codigo = ?");
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setString(1, funcionario.getNome());
			preparedStatement.setString(2, funcionario.getCpf());
			preparedStatement.setString(3, funcionario.getRg());
			preparedStatement.setString(4,
					LibUtils.date2dataMYSQL(funcionario.getDataNasc()));
			preparedStatement.setString(5, funcionario.getEndereco());
			preparedStatement.setString(6, funcionario.getNumeroCasa());
			preparedStatement.setString(7, funcionario.getComplemento());
			preparedStatement.setString(8, funcionario.getBairro());
			preparedStatement.setString(9, funcionario.getCidade());
			preparedStatement.setInt(10, funcionario.getUf());
			preparedStatement.setString(11, funcionario.getTelefone());
			preparedStatement.setString(12, funcionario.getEmail());
			preparedStatement.setInt(13, funcionario.getFuncao());
			preparedStatement.setInt(14, funcionario.getCodigo());
			preparedStatement.executeUpdate();
			System.out.print(sql);
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

	public void remover(Funcionario funcionario) {
		try {
			conexao = new Conexao();
			super.remover(funcionario);
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from funcionario where funcionario_codigo = ? ");
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setInt(1, funcionario.getCodigo());
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

	public List<Funcionario> listaTudo() {
		conexao = new Conexao();
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		String sql = "select * from funcionario " +
				"left join estado  on funcionario.estado_codigo = estado.estado_codigo" +
				" left join funcao  on funcionario.funcao_codigo = funcao.funcao_codigo";
		if (super.condicaoFixa != "" && super.condicaoFixa != null) {
			sql += " where funcionario." + super.condicaoFixa + " group by funcionario.funcionario_codigo";
			super.condicaoFixa = null;
		}
		try {
			stmt = (Statement) conexao.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setCodigo(rs.getInt("funcionario_codigo"));
				funcionario.setNome(rs.getString("funcionario_nome"));
				funcionario.setCpf(rs.getString("funcionario_cpf"));
				funcionario.setRg(rs.getString("funcionario_rg"));
				try {
					funcionario.setDataNasc(LibUtils.dateMYSQL2Date(rs
							.getString("funcionario_data_nascimento")));
				} catch (Exception e) {
					funcionario.setDataNasc(null);
				}
				funcionario.setEndereco(rs.getString("funcionario_endereco"));
				funcionario.setNumeroCasa(rs
						.getString("funcionario_numero_casa"));
				funcionario.setComplemento(rs
						.getString("funcionario_complemento"));
				funcionario.setBairro(rs.getString("funcionario_bairro"));
				funcionario.setCidade(rs.getString("funcionario_cidade"));
				funcionario.setUf(rs.getInt("estado_codigo"));
				funcionario.setTelefone(rs.getString("funcionario_telefone"));
				funcionario.setEmail(rs.getString("funcionario_email"));
				funcionario.setFuncao(rs.getInt("funcao_codigo"));
				funcionario.setFuncaoNome(rs.getString("funcao_descricao"));
				funcionario.setUfNome(rs.getString("estado_descricao"));
				funcionarios.add(funcionario);

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
		return funcionarios;
	}

	public Funcionario listaPorId(Integer codigo) {
		conexao = new Conexao();
		Funcionario funcionario = new Funcionario();
		try {
			String sql = "select * from funcionario where funcionario_codigo = ?";
			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql);
			preparedStatement.setInt(1, codigo);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				funcionario.setCodigo(rs.getInt("funcionario_codigo"));
				funcionario.setNome(rs.getString("funcionario_nome"));
				funcionario.setCpf(rs.getString("funcionario_cpf"));
				funcionario.setRg(rs.getString("funcionario_rg"));
				try {
					funcionario.setDataNasc(LibUtils.dateMYSQL2Date(rs
							.getString("funcionario_data_nascimento")));
				} catch (Exception e) {
					funcionario.setDataNasc(null);
				}
				funcionario.setEndereco(rs.getString("funcionario_endereco"));
				funcionario.setNumeroCasa(rs
						.getString("funcionario_numero_casa"));
				funcionario.setComplemento(rs
						.getString("funcionario_complemento"));
				funcionario.setBairro(rs.getString("funcionario_bairro"));
				funcionario.setCidade(rs.getString("funcionario_cidade"));
				funcionario.setUf(rs.getInt("estado_codigo"));
				funcionario.setTelefone(rs.getString("funcionario_telefone"));
				funcionario.setEmail(rs.getString("funcionario_email"));
				funcionario.setFuncao(rs.getInt("funcao_codigo"));
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
		return funcionario;
	}

}
