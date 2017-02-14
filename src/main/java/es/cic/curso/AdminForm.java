package es.cic.curso;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;

public class AdminForm extends FormLayout{
	
	private Grid maestro;
	private MyUI padre;
	private NativeSelect lista;
	private Button recaudacion;
	private NativeSelect cerrar;
	private NativeSelect abrir;
	
	public AdminForm(MyUI padre){
		this.padre = padre;
		
		final HorizontalLayout base = new HorizontalLayout();
		final VerticalLayout izquierda = new VerticalLayout();
		final VerticalLayout derecha = new VerticalLayout();
		final HorizontalLayout arriba = new HorizontalLayout();
		arriba.setSpacing(true);
		maestro = new Grid();
		
		maestro.setColumns("Venta","Sala","Sesión","Precio Entrada", "Cantidad");
        maestro.setSizeFull();
        maestro.setFrozenColumnCount(1);
        maestro.setSelectionMode(SelectionMode.SINGLE);
        
        final HorizontalLayout abajo = new HorizontalLayout();
        abajo.setSpacing(true);
        
        lista = new NativeSelect("Selecciona Sala");
        for (int i = 0; i < 6; i++) {
            lista.addItem(i);
            lista.setItemCaption(i, "Sala " + i);
        }
 
        lista.setNullSelectionAllowed(false);
        lista.setValue(2);
        lista.setImmediate(true);
 
        lista.addValueChangeListener(e -> Notification.show("Se han obtenido:",
                String.valueOf(e.getProperty().getValue()),
                Type.TRAY_NOTIFICATION));
        
        recaudacion = new Button("Recaudacion Total");
        
        cerrar = new NativeSelect("Cerrar Sala");
        for (int i = 0; i < 6; i++) {
            cerrar.addItem(i);
            cerrar.setItemCaption(i, "Sala " + i);
        }
        
        cerrar.setNullSelectionAllowed(false);
        cerrar.setValue(2);
        cerrar.setImmediate(true);
 
        cerrar.addValueChangeListener(e -> Notification.show("Se han obtenido:",
                String.valueOf(e.getProperty().getValue()),
                Type.TRAY_NOTIFICATION));
        
        abrir = new NativeSelect("Cerrar Sala");
        for (int i = 0; i < 6; i++) {
            abrir.addItem(i);
            abrir.setItemCaption(i, "Sala " + i);
        }
        
        abrir.setNullSelectionAllowed(false);
        abrir.setValue(2);
        abrir.setImmediate(true);
 
        abrir.addValueChangeListener(e -> Notification.show("Se han obtenido:",
                String.valueOf(e.getProperty().getValue()),
                Type.TRAY_NOTIFICATION));
        
        derecha.setSpacing(true);
        derecha.setMargin(true);
        izquierda.setSpacing(true);
        izquierda.setMargin(true);
        
        arriba.addComponent(maestro);
        abajo.addComponents(lista, recaudacion);
        izquierda.addComponents(arriba, abajo);
        derecha.addComponents(cerrar, abrir);
        base.addComponents(izquierda, derecha);
        addComponents(base);
	}
}
