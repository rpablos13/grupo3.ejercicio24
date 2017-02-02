package es.cic.curso.curso03.ejercicio014.dominio;

import java.io.BufferedReader;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OperacionesTienda {
	
	
	
	private static final String FICHERO = "./src/test/resources/inventario.txt";
	private List<Badulake> listaInventario = new ArrayList<>();

	
	public void aniadirProducto(Badulake almacen){
		listaInventario.add(almacen);
	}
	
	public List<Badulake> listar(){
		return listaInventario;
	}
	
	public void borrar(int indice){
		listaInventario.remove(indice);
	}
	
	
	public List<Badulake> leerInventario() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(FICHERO))) {
			String linea;
			List<Badulake> resultado = new ArrayList<>();
			while ((linea = br.readLine()) != null) {
				String[] cadena = linea.split(";");
				Badulake producto = new Badulake(	
						cadena[0],
						Double.parseDouble(cadena[1]),
						Integer.parseInt(cadena[2]),
						cadena[3]
						);
				resultado.add(producto);				
			}
			return resultado;
		}
	}

	public void guardarInventario(String fichero) throws IOException{
		boolean primero = true;
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))){
			for(Badulake f: listaInventario){
				if(!primero){
					bw.newLine();
				}else{
					primero = false;
				}
				String Inventario = f.getId()+";"
									+f.getProducto()+ ";"
									+f.getPrecio()+ ";"
									+f.getCantidad()+ ";"
									+f.getLugar();
				
				bw.write(Inventario);
			}
		}
	}
	
	
	


}
