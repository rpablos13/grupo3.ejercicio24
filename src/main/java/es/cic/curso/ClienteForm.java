package es.cic.curso;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class ClienteForm extends FormLayout{
	
	private Grid maestro;
	private Button comprar;
	private TextField cuantas;
	private Notification alerta;
	
	private MyUI padre;
	
	public ClienteForm(MyUI padre){
		this.padre = padre;
		
		final HorizontalLayout arriba = new HorizontalLayout();
		arriba.setSpacing(true);
		maestro = new Grid();
		
		maestro.setColumns("Sala","Asientos","Sesión","Abierto");
        maestro.setSizeFull();
        maestro.setFrozenColumnCount(1);
        maestro.setSelectionMode(SelectionMode.SINGLE);
        
        final HorizontalLayout abajo = new HorizontalLayout();
        abajo.setSpacing(true);
        
        comprar = new Button("Comprar");
        comprar.setIcon(FontAwesome.TICKET);
        
        cuantas = new TextField();
        
        alerta = new Notification("Precio de entradas", "5 Euretes");
        alerta.show(Page.getCurrent());
        alerta.setDelayMsec(5000);
        
        
        arriba.addComponents(maestro);
        abajo.addComponents(comprar, cuantas);
        addComponents(arriba, abajo);
		
	}
	
	
	
}
