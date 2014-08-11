package br.com.gfc.viagem;

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

public class ViagemDao extends BaseDao<Viagem> {

	private Statement stmt;
	private Conexao conexao;

	public ViagemDao() {
		conexao = new Conexao();
	}

	public void inserir(Viagem viagem) {
		try {
			conexao = new Conexao();
			super.inserir(viagem);
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into viagem (");
			sql.append(" cliente_codigo,");
			sql.append(" destino_codigo,");
			sql.append(" origem_codigo,");
			sql.append(" viagem_data_saida,");
			sql.append(" viagem_data_emissao,");
			sql.append(" viagem_hora_saida,");
			sql.append(" motorista_codigo,");
			sql.append(" veiculo_codigo,");
			sql.append(" viagem_endereco_entrega,");
			sql.append(" viagem_bairro_entrega,");
			sql.append(" viagem_numero_entrega,");
			sql.append(" viagem_complemento_entrega");
			sql.append(") values (");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
			sql.append(")");

			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setInt(1, viagem.getPassageiroId());
			preparedStatement.setInt(2, viagem.getDestinoId());
			preparedStatement.setInt(3, viagem.getOrigemId());
			preparedStatement.setString(4,
					LibUtils.date2dataMYSQL(viagem.getViagemData()));
			preparedStatement.setString(5,
					LibUtils.DataAtual(viagem.getDataEmissao()));
			preparedStatement.setInt(6, viagem.getHoraSaida());
			preparedStatement.setInt(7, viagem.getMotoristaId());
			preparedStatement.setInt(8, viagem.getVeiculoId());
			preparedStatement.setString(9, viagem.getEnderecoEntrega());
			preparedStatement.setString(10, viagem.getBairroEntrega());
			preparedStatement.setString(11, viagem.getNumeroEntrega());
			preparedStatement.setString(12, viagem.getComplementoEntrega());
			preparedStatement.executeUpdate();
			System.out.print(sql);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Passagem emitida com sucesso"));
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
	
	public void alterar(Viagem viagem) {
		try {
			conexao = new Conexao();
			super.inserir(viagem);
			StringBuilder sql = new StringBuilder();
			sql.append(" iupdate viagem viagem ");
			sql.append(" cliente_codigo = ?,");
			sql.append(" destino_codigo = ?,");
			sql.append(" origem_codigo = ?,");
			sql.append(" viagem_data_saida = ?,");
			sql.append(" viagem_hora_saida = ?");
			sql.append(" motorista_codigo = ?,");
			sql.append(" veiculo_codigo = ?,");
			sql.append(" viagem_endereco_entrega = ?,");
			sql.append(" viagem_bairro_entrega = ?,");
			sql.append(" viagem_numero_entrega = ?,");
			sql.append(" viagem_complemento_entrega = ?");
			sql.append(" where viagem_codigo = ?");

			PreparedStatement preparedStatement = conexao.getConn()
					.prepareStatement(sql.toString());
			preparedStatement.setInt(1, viagem.getPassageiroId());
			preparedStatement.setInt(2, viagem.getDestinoId());
			preparedStatement.setInt(3, viagem.getOrigemId());
			preparedStatement.setString(4,
					LibUtils.date2dataMYSQL(viagem.getViagemData()));
			preparedStatement.setInt(5, viagem.getHoraSaida());
			preparedStatement.setInt(6, viagem.getMotoristaId());
			preparedStatement.setInt(7, viagem.getVeiculoId());
			preparedStatement.setString(8, viagem.getEnderecoEntrega());
			preparedStatement.setString(9, viagem.getBairroEntrega());
			preparedStatement.setString(10, viagem.getNumeroEntrega());
			preparedStatement.setString(11, viagem.getComplementoEntrega());
			preparedStatement.executeUpdate();
			System.out.print(sql);
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

	public List<Viagem> listaTudo() {
		List<Viagem> viagens = new ArrayList<Viagem>();
		Viagem viagem;
		String sql = "select * from viagem";
		sql += " left join motorista on viagem.motorista_codigo = motorista.motorista_codigo "
				+ "left join funcionario on motorista.funcionario_codigo = funcionario.funcionario_codigo"
				+ " left join cidade c on origem_codigo = c.cidade_codigo"
				+ " left join cidade c2 on destino_codigo = c2.cidade_codigo"
				+ " left join cliente cl on viagem.cliente_codigo = cl.cliente_codigo"
				+ " left join veiculo v on viagem.veiculo_codigo = v.veiculo_codigo"
				+ " left join horario on viagem.viagem_hora_saida = horario.horario_codigo";
		if (super.condicaoFixa != "" && super.condicaoFixa != null) {
			sql += " where viagem." + super.condicaoFixa + " group by viagem.cliente_codigo";
			super.condicaoFixa = null;
		}
		conexao = new Conexao();

		try {
			stmt = conexao.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				viagem = new Viagem();
				viagem.setCodigo(rs.getInt("viagem_codigo"));
				viagem.setOrigemId(rs.getInt("origem_codigo"));
				viagem.setDestinoId(rs.getInt("destino_codigo"));
				viagem.setOrigem(rs.getString("c.cidade_descricao"));
				viagem.setDestino(rs.getString("c2.cidade_descricao"));
				viagem.complementoId.setClienteNome(rs.getString("cliente_nome"));
				viagem.complementoId.setOrigem(rs.getString("c.cidade_descricao"));
				viagem.complementoId.setDestino(rs.getString("c2.cidade_descricao"));
				viagem.complementoId.setMotoristaNome(rs.getString("funcionario_nome"));
				viagem.complementoId.setVeiculoNome(rs.getString("veiculo_modelo"));
				viagem.setMotoristaId(rs.getInt("motorista_codigo"));
				viagem.setVeiculoId(rs.getInt("veiculo_codigo"));
				viagem.setHoraSaida(rs.getInt("viagem_hora_saida"));
				viagem.setHoraSaidaVlr(rs.getString("horario_saida"));
				viagem.setViagemData(rs.getDate("viagem_data_saida"));
				viagem.setDataEmissao(rs.getDate("viagem_data_emissao"));
				viagem.setEnderecoEntrega(rs
						.getString("viagem_endereco_entrega"));
				viagem.setBairroEntrega(rs.getString("viagem_bairro_entrega"));
				viagem.setNumeroEntrega(rs.getString("viagem_numero_entrega"));
				viagem.setComplementoEntrega(rs
						.getString("viagem_complemento_entrega"));
				viagens.add(viagem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.fecharConexao();
			this.conexao.fecharConexao();
			super.conexao.fecharConexao();
		}

		return viagens;
	}

}
