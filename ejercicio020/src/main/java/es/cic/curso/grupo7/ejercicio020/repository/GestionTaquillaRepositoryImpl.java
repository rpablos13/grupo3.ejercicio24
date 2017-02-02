package es.cic.curso.grupo7.ejercicio020.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.grupo7.ejercicio020.dto.Sala;
import es.cic.curso.grupo7.ejercicio020.dto.Sesion;
import es.cic.curso.grupo7.ejercicio020.dto.Taquilla;
import es.cic.curso.grupo7.ejercicio020.exception.MyException;

@Repository
public class GestionTaquillaRepositoryImpl implements GestionTaquillaRepository {
	


	private Taquilla taquilla;
	
	public double precioEntradas(int numeroEntradas, int sala){
		int index = sala-1;
		return numeroEntradas * taquilla.getListaSalas().get(index).getPrecioEntrada();
		
	}
	
	
	
	@Override
	public boolean hayEntradas(int numeroEntradas,int sala, int sesion) throws MyException{
		boolean permitir=false;
		int indexSala = sala - 1;
		int indexSesion = sesion-1;
		Sala sa = taquilla.getListaSalas().get(indexSala);
		Sesion s = sa.getSesiones().get(indexSesion);
		if(sa.isCerrado()){
			throw new MyException("Cine cerrado");
		}else{
			if(s.isCerrado()){
				throw new MyException("Sesion cerrada");
			}else{
				int disponibles = sa.obtenLibres(indexSesion);
				if(disponibles > numeroEntradas)
					permitir = true;
			}
		}
		
		return permitir;
		
	}
	
	
	@Override
	public void vendeEntrada(int numEntradas,int sala, int sesion) throws MyException{
		int indexS = sala-1;
		int indexSesion = sesion-1;
		Sala sa = taquilla.getListaSalas().get(indexS);
		Sesion s = sa.getSesiones().get(indexSesion);
		if(sa.isCerrado())
			throw new MyException("Cine cerrado");
		if(s.isCerrado())
			throw new MyException("Sesion cerrada");
		double precio = numEntradas * taquilla.getListaSalas().get(indexS).getPrecioEntrada();
		s.vendeEntrada(numEntradas);
		taquilla.getListaSalas().get(indexS).sumaRecaudacion(precio);
	}
	
	
	
	
	public double recaudacionTotal(){
		double res=0;
		for(int i =0;i<taquilla.getListaSalas().size();i++){
			res+= taquilla.getListaSalas().get(i).getRecaudacion();
		}
		return res;
	}
	public void cierraCine(){
		for(Sala sala: taquilla.getListaSalas()){
			sala.setCerrado(true);
		}
	}
	public void cierraSesion(int sala, int sesion){
		int indexS = sala-1;
		int indexSesion = sesion-1;
		taquilla.getListaSalas().get(indexS).getSesiones().get(indexSesion).setCerrado(true);
	}
	public void abreCine(){
		for(Sala sala: taquilla.getListaSalas()){
			sala.setCerrado(false);
		}
	}
	public void abreSesion(int sala, int sesion){
		int indexS = sala-1;
		int indexSesion = sesion-1;
		taquilla.getListaSalas().get(indexS).getSesiones().get(indexSesion).setCerrado(false);
		
	}
	
	public Taquilla getTaquilla() {
		return taquilla;
	}
	
}
