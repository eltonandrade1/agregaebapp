package br.com.sysagrega.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.imp.Proposta;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class testeRelatorio {

	public static void main(String[] args) throws FileNotFoundException {
		List<IProposta> lista = new ArrayList<>();
		
		System.out.println("Gerando relatório AGrega");

		Proposta p1 = new Proposta();
		p1.setNumeroProposta("AC1");
		p1.setDataEnvioAoCliente(new Date());
		p1.setDataInclusao(new Date());
		p1.setCliente("Elton");
		p1.setObjeto("teste1");
		
		Proposta p2 = new Proposta();
		p2.setNumeroProposta("AC2");
		p2.setDataEnvioAoCliente(new Date());
		p2.setDataInclusao(new Date());
		p2.setCliente("Elton2");
		p2.setObjeto("teste2");
		
		lista.add(p1);
		lista.add(p2);
		
		geraRelatorioProposta("PropostaTeste.jasper", lista);
		

	}
	
	private static void preenchePdf(JasperPrint print) throws JRException {
		//TODO implementar outFilename (apontando a saida para um diretorio no servidor)
		//saida = getDiretorioReal("/pdf/relatorio.pdf");
		String saida = "C:\\TempReport\\";
		// Exporto para PDF
		JasperExportManager.exportReportToPdfFile(print, saida);
		/*
		 * Jogo na variável saída o nome da aplicação mais o 
		 * caminho para o PDF. Essa variável será utilizada pela view 
		 */
		//saida = getContextPath() + "/pdf/relatorio.pdf";
	}
	
	public static void geraRelatorioProposta(String nomeRelatorio, List<IProposta> proposta) {
		
		String jasper = nomeRelatorio;
		
		try {
			// Conexão com o banco para o segundo relatório
			//conexao = new Conexao().getConexao();

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(proposta);
			/*
			 * Mando o jasper gerar o relatório. Nesse caso passo o map, 
			 * já que ele tem dois parâmetros que serão utilizados
			 */ 
			JasperPrint print = JasperFillManager.fillReport(jasper, null, ds);
			// Gero o PDF
			preenchePdf(print);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getDiretorioReal(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}

}
