package mx.com.twg;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import mx.com.twg.conexion.Conexion;
import mx.com.twg.dao.PersonaDao;
import mx.com.twg.entity.Persona;

public class Main {

	private static Scanner ent = new Scanner(System.in);
	private static boolean x;
	
	public static void main(String[] args) {
		
		Connection conn=null;
		
		try {
			conn = Conexion.getConnection();
			
			if(conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			
			PersonaDao pd = new PersonaDao(conn);
			pd.insert("Noe", "Vargas");
			select(pd.select());
			pd.update(20, "Carlos", "Perez");
			select(pd.select());
			pd.insert("Pedro", 
					//"Cortez123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123");
					"Cortez");
			select(pd.select());
				System.out.println("Realizar Commit?Yes/No");
			String yes = ent.nextLine();
			if(yes.equalsIgnoreCase("yes")) {
				conn.commit();
				select(pd.select());
			}else {
					System.out.println("Rollback");
				conn.rollback();
				select(pd.select());
			}
			
		}catch(SQLException e) {
			try {
				System.out.println("RollBack");
				e.printStackTrace();
				conn.rollback();
			}catch(SQLException f) {
				f.printStackTrace();
			}
		}
		
	}
	
	private static void select(List<Persona> listaPersonas) {
		for(Persona p:listaPersonas) {
			System.out.println(p);
		}
	}
	

}
