package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import DAO.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;


public class TesteBancoJdbc  {
	
	@Test
	public void initBanco() {
		UserPosDAO userposdao = new UserPosDAO();
		Userposjava userposjava = new Userposjava();
		
		userposjava.setNome("Ralf Moreira Leite");
		userposjava.setEmail("Ralf_moreira7@hotmail.com");
		
		userposdao.salvar(userposjava);
	}
	
	@Test
	public void initInsertTel() {
		UserPosDAO dao = new UserPosDAO();
		Telefone telefone = new Telefone();
		
		telefone.setNumero("11 4002-8922");
		telefone.setTipo("telefone");
		telefone.setUsuario(50L);
		dao.salvar(telefone);
	}
	
	@Test
	public void initBuscarBeanUserFone() {
		UserPosDAO dao = new UserPosDAO();
		List<BeanUserFone> beanuserfone = dao.buscarInnerJoin(2L);
		for (BeanUserFone user : beanuserfone) {
			System.out.println(user);
		}		
	}
	
	@Test
	public void initDeletarBeanUserFone() {
		UserPosDAO dao = new UserPosDAO();
		dao.deleteInnerJoin(3L);
	}
	
	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		List<Userposjava> lista = dao.listar();
		
		for (Userposjava userposjava : lista) {
			System.out.println("id: "+userposjava.getId()+" / "+ " nome: "+ userposjava.getNome()+" / "+ userposjava.getEmail()+" /");
			System.out.println("---------------------------------------------------");
		}
		
	}
	
	@Test
	public void initBuscar() {
		UserPosDAO dao = new UserPosDAO();

		try {
			Userposjava userposjava = dao.buscar(2L);
			System.out.println("id: "+userposjava.getId()+" / "+ " nome: "+ userposjava.getNome()+" / "+ userposjava.getEmail()+" /");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void initAtualizar() {
		
		try {
			UserPosDAO dao = new UserPosDAO();
			Userposjava objetoBanco = dao.buscar(1l);
			objetoBanco.setNome("Caique Moreira Leite");
			dao.atualizar(objetoBanco);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initRemover() {
		UserPosDAO dao = new UserPosDAO();
		dao.remover(3L);
	}
}
