package es.cic.curso.curso03.ejercicio014.dominio;

import org.junit.Before;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import es.cic.curso.curso03.ejercicio014.dominio.Badulake;
import es.cic.curso.curso03.ejercicio014.repository.BadulakeRepository;
import es.cic.curso.curso03.ejercicio014.repository.IRepository;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso03/ejercicio014/applicationContext.xml"
				})
public class BadulakeRepositoryImplTest extends AbstractRepositoryImplTest<Long, Badulake> {

	
	@Autowired
	private BadulakeRepository sut;

	
	@Before
	public void setUp() throws Exception {

	}
	
	@Override
	public Badulake getInstanceDeTParaNuevo() {
		Badulake badulake = new Badulake();
		badulake.setProducto("Zumo");
		badulake.setCantidad(2);
		badulake.setPrecio(3.4);
		badulake.setLugar("Tienda");
		
		return badulake;
	}

	
	public IRepository<Long, Badulake> getRepository() {
		return sut;
	}



}
