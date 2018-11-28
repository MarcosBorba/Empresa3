package controlador;

import java.util.ArrayList;
import java.util.Date;

import modelo.CargosDAO;
import modelo.ClienteDAO;
import modelo.EnderecosDAO;
import modelo.FinanceiroEntradasDAO;
import modelo.FuncionarioDAO;
import modelo.ProdutosDAO;
import modelo.VendasDAO;
import modelo.VendasItensDAO;

public class Main {
	public static void main(String[] args) {
//		ArrayList<Cargo> teste = CargosDAO.retreaveAll();

		/*Funcionario f2 = new Funcionario("Marcos", new Cpf("888.888.888-83"), new Cargo("diret geral", ""));
		CargosDAO.create(f2.getCargo());
		FuncionarioDAO.create(f2);
		
		Funcionario f3 = FuncionarioDAO.retreave(30);
		
		
		System.out.println(CargosDAO.retreave(8));*/
		
		/*VendasItens vendaItem1 = new VendasItens(new Produtos(),5,20.00);
		VendasItens vendaItem2 = new VendasItens(new Produtos(),2,10.00);
		VendasItens vendaItem3 = new VendasItens(new Produtos(),1,200.00);
		
		@SuppressWarnings("deprecation")
		FinanceiroEntradas pagar1 = new FinanceiroEntradas(new Date(),new Date(2018,10,17),new Date(),23.00,"Boleto");
		@SuppressWarnings("deprecation")
		FinanceiroEntradas pagar2 = new FinanceiroEntradas(new Date(),new Date(2018,11,17),new Date(),23.00,"Boleto");
		Vendas venda1 = new Vendas(new Cliente(),
				                   new Funcionario(),
				                   new Date(),
				                   200,
				                   new ArrayList<VendasItens>(),
				                   new ArrayList<FinanceiroEntradas>());

		venda1.getFinanceiroEntradas().add(pagar1);
		venda1.getFinanceiroEntradas().add(pagar2);
		venda1.getVendasItens().add(vendaItem1);
		venda1.getVendasItens().add(vendaItem2);
		venda1.getVendasItens().add(vendaItem3);
		
		venda1.getCliente().setPk_Cliente(5);
		venda1.getFuncionario().setPk_funcionario(8);
		VendasDAO.create(venda1);
	
		vendaItem1.getProduto().setPk_Produto(1);
		vendaItem2.getProduto().setPk_Produto(2);
		vendaItem3.getProduto().setPk_Produto(3);
		VendasItensDAO.create(vendaItem1);
		VendasItensDAO.create(vendaItem2);
		VendasItensDAO.create(vendaItem3);
		
		FinanceiroEntradasDAO.create(pagar1);
		FinanceiroEntradasDAO.create(pagar2);*/
		
		/*System.out.println(VendasItensDAO.retreaveAll(11));
		System.out.println(FinanceiroEntradasDAO.retreaveAll(34));
		System.out.println(VendasDAO.retreave(34));*/
		//FuncionarioDAO.delete(FuncionarioDAO.retreave(11));
		
		/*Funcionario meu = new Funcionario();
		meu.setNome("George");
		meu.setPk(43);
		meu.setCpf(new Cpf("700.800.900-10"));
		FuncionarioDAO.update(meu);*/
		//System.out.println(ClienteDAO.retreaveAll());
                
//                Cargo c = new Cargo("aaa","dddd");
//                
//                System.out.println("Field - > "+c.getClass().getDeclaredField(string));
//                System.out.println("ddddd");
	}

}
