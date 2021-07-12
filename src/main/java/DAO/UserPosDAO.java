package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDAO {
	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConection();
	}

	public void salvar(Userposjava userposjava) {
		try {
			String sql = "insert into userposjava (nome, email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			connection.commit();// salva no banco

		} catch (SQLException e) {
			try {
				connection.rollback(); // reverte opereção
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void salvar(Telefone telefone) {
		try {
			String sql = "insert into telefoneuser (numero, tipo, usuariopessoa) values (?,?,?);";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	public List<BeanUserFone> buscarInnerJoin(Long id) {
		List<BeanUserFone> beanuserfone = new ArrayList<BeanUserFone>();
		try {
			String sql = "select nome, numero, email from telefoneuser as fone ";
			sql += "inner join userposjava as userp ";
			sql += "on fone.usuariopessoa = userp.id ";
			sql += "where userp.id=" + id;

			PreparedStatement buscar = connection.prepareStatement(sql);
			ResultSet resultado = buscar.executeQuery();
			while (resultado.next()) {
				BeanUserFone userBeanUserFone = new BeanUserFone();
				userBeanUserFone.setNome(resultado.getString("nome"));
				userBeanUserFone.setNumero(resultado.getString("numero"));
				userBeanUserFone.setEmail(resultado.getString("email"));
				beanuserfone.add(userBeanUserFone);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanuserfone;
	}

	public void deleteInnerJoin(Long id) {
		try {
			String sql = "delete from telefoneuser where usuariopessoa =" + id;		
			String sql1 = "delete from userposjava where id =" + id;
			
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.executeUpdate();
			connection.commit();
			
			delete = connection.prepareStatement(sql1);
			delete.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public List<Userposjava> listar() {
		List<Userposjava> lista = new ArrayList<Userposjava>();

		try {
			String sql = "SELECT * FROM userposjava";
			PreparedStatement select = connection.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();

			while (resultado.next()) {
				Userposjava userposjava = new Userposjava();
				userposjava.setId(resultado.getLong("id"));
				userposjava.setNome(resultado.getString("nome"));
				userposjava.setEmail(resultado.getString("email"));

				lista.add(userposjava);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;

	}

	public Userposjava buscar(Long id) {
		Userposjava retorno = new Userposjava();

		try {
			String sql = "select id, nome, email FROM userposjava WHERE id = " + id;
			PreparedStatement select = connection.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();

			while (resultado.next()) { // retorna apenas um ou nenhum
				retorno.setId(resultado.getLong("id"));
				retorno.setNome(resultado.getString("nome"));
				retorno.setEmail(resultado.getString("email"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;

	}

	public void atualizar(Userposjava userposjava) {
		try {
			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
			PreparedStatement atualizar = connection.prepareStatement(sql);
			atualizar.setString(1, userposjava.getNome());
			atualizar.execute();
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void remover(Long id) {
		try {
			String sql = "DELETE FROM public.userposjava WHERE id =" + id;
			PreparedStatement remover = connection.prepareStatement(sql);
			remover.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
