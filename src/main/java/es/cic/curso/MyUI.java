package es.cic.curso;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.ContextLoader;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.AbstractErrorMessage.ContentMode;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	private ClienteForm clienteForm;
	private AdminForm adminForm;
	private TabSheet tab;


	
	@Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalLayout base = new HorizontalLayout();
        
        tab = new TabSheet();
        tab.setHeight(100.0f, Unit.PERCENTAGE);
        tab.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tab.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        
        final VerticalLayout tab1 = new VerticalLayout(new Label());
        
        tab1.setMargin(true);
        clienteForm = new ClienteForm(this);
        tab1.addComponent(clienteForm);
        tab.addTab(tab1, "Ciente");
        
        
        final HorizontalLayout tab2 = new HorizontalLayout(new Label());
        
        tab2.setMargin(true);    
        adminForm = new AdminForm(this);
        tab2.addComponent(adminForm);
        tab.addTab(tab2, "Admin");
        tab.setWidth("100%");
        
        base.addComponents(tab);
        base.setMargin(true);
        base.setSpacing(true);
        base.setWidth("100%");
        setContent(base);
           
	}
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
