package br.com.gfc.generic;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.context.FacesContext;


import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;



public class LibUtils {

	public static Date dateBR2Date(String data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return dateFormat.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date dateMYSQL2Date(String data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String date2dataBR(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(data);
	}

	public static String date2dataMYSQL(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(data);
	}

	public static String DataAtual(Date data)     
	{    
		Date hoje = new Date(); 
		SimpleDateFormat df; 
		df = new SimpleDateFormat("yyyy-MM-dd");     
	    return df.format(hoje);   
	}  
	
	public static int calcIdade(Date dataNasc){  
        Date hoje = new Date();  
        Calendar cal = Calendar.getInstance();  
          
        cal.setTime(hoje);  
        int day1 = cal.get(Calendar.DAY_OF_YEAR);  
        int ano1 = cal.get(Calendar.YEAR);  
          
        cal.setTime(dataNasc);  
        int day2 = cal.get(Calendar.DAY_OF_YEAR);  
        int ano2 = cal.get(Calendar.YEAR);  
          
        int nAno = ano1 - ano2;  
          
        if(day1 < day2)  
            nAno--; //Ainda não completou aniversario esse ano.  
              
        return nAno;  
    }  
	
	
	 private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	   private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	 
	   private static int calcularDigito(String str, int[] peso) {
	      int soma = 0;
	      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
	         digito = Integer.parseInt(str.substring(indice,indice+1));
	         soma += digito*peso[peso.length-str.length()+indice];
	      }
	      soma = 11 - soma % 11;
	      return soma > 9 ? 0 : soma;
	   }
	 
	   public static boolean isValidCPF(String cpf) {
	      if ((cpf==null) || (cpf.length()!=11)) return false;
	 
	      Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
	      Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
	      return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	   }
	 
	   public static boolean isValidCNPJ(String cnpj) {
	      if ((cnpj==null)||(cnpj.length()!=14)) return false;
	 
	      Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
	      Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
	      return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
	   }
	   
	   public static String getParameter(String parameter){
			return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parameter);
		}
	   
	   public static String userLogado(String user){
		   user = (((SecurityContext) SecurityContextHolder.getContext()).getAuthentication().getName());
		   return  user;
		   
	   }
	   
		public static String usuarioLogado() { 
			String user;
			 user = (((SecurityContext) SecurityContextHolder.getContext()).getAuthentication().getName());
			 System.out.print(user);
			   return  user;

			} 
}
