package es.cic.curso.grupo7.ejercicio020.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import es.cic.curso.grupo7.ejercicio020.dto.Sala;
import es.cic.curso.grupo7.ejercicio020.dto.Sesion;
import es.cic.curso.grupo7.ejercicio020.dto.Taquilla;
import es.cic.curso.grupo7.ejercicio020.repository.CreadorCine;

@Repository
public class LecturaEscrituraFicheros {
	
	
	private static final Logger LOGGER = Logger.getLogger(GestionTaquillaRepositoryImpl.class.getName() );
	private Taquilla taquilla;
	private static final String OUTPUT_FILE = "src/test/resources/taquilla.txt";
	private static final String INPUT_FILE = "src/test/resources/cine.txt";
	private SerializadorCine serial;
	
	
	
	
	public void guardaFichero() throws IOException{
		try(PrintWriter writer = new PrintWriter(OUTPUT_FILE, "UTF-8")){
		    for(int i=0;i<taquilla.getListaSalas().size();i++){
		    	Sala s = taquilla.getListaSalas().get(i);
		    	for(int j=0;j<s.getSesiones().size();j++){
		    		String cadena = serial.serializarSala(s,j);
		    		writer.println(cadena);
		    	}
		    }
		} catch (IOException e) {

			LOGGER.log(Level.FINE, "Error", e.getMessage() );
			throw e;
		   
		}
	}
	
	public Taquilla leeFichero() throws  IOException{
		taquilla = new Taquilla();
		List<String> salasDTO = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
			
			StringBuilder sb = new StringBuilder();
			String linea = br.readLine();

			while (linea != null) {
				sb.append(linea);
				salasDTO.add(linea);
				linea = br.readLine();
			}
			almacenaSala(salasDTO, taquilla);
		}
		return taquilla;
	}
	
	private Taquilla almacenaSala(List<String> lista, Taquilla taquilla){
		for(int i=0;i<lista.size();i++){
			List<String> listaAux = new ArrayList<>();
			listaAux.addAll(Arrays.asList(lista.get(i).split(",")));
			generaSala(listaAux, taquilla);
		}
		return taquilla;
	}
	
	private void generaSala(List<String> lista, Taquilla taquilla){
		CreadorCine creador = new CreadorCine();
		int sala = Integer.parseInt(lista.get(0));
		int sesion = Integer.parseInt(lista.get(3));
		sesion--;
		int indexSala = sala - 1;
		Sesion s = creador.creaDTO(lista);
		if(!taquilla.getListaSalas().isEmpty() && taquilla.getListaSalas().size()> indexSala){
			taquilla.getListaSalas().get(indexSala).getSesiones().add(sesion,s);
		}else{
			int aforo = Integer.parseInt(lista.get(1));
			double recaudacion = Double.parseDouble(lista.get(2));
			Sala sa = new Sala();
			sa.setAforo(aforo);
			sa.setRecaudacion(recaudacion);
			sa.setNumeroSala(sala);
			sa.getSesiones().add(sesion, s);
			taquilla.getListaSalas().add(indexSala,sa);
		}
		
		
	}

}
