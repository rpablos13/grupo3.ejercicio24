<%@ page import="java.util.*" %>
<html>
<head></head>
<body>
    <%



        String numero = request.getParameter("num");
        int iNum = 0;
        
        if (numero != null && !numero.equals("")) {

            iNum = Integer.parseInt(numero);
        }
        Integer num = new Integer(iNum); 

        Set numerosAcertados = (Set<Integer>) session.getAttribute("acertados"); 
        Set numerosAAcertar = (Set<Integer>) session.getAttribute("aAcertar");


        if (numerosAcertados == null || numerosAcertados.size() == 5) {
            numerosAcertados = new HashSet<Integer>();
            session.setAttribute("acertados", numerosAcertados); 
        }


        if (numerosAAcertar == null || numerosAAcertar.isEmpty()) {
            numerosAAcertar = new HashSet<Integer>();
            numerosAAcertar.add(2);            
            numerosAAcertar.add(5);
            numerosAAcertar.add(8);
            numerosAAcertar.add(7);
            numerosAAcertar.add(10);
            session.setAttribute("aAcertar", numerosAAcertar); 
        }


       
        boolean acertado = numerosAAcertar.contains(num);

        if (acertado) {
            numerosAAcertar.remove(num);
            numerosAcertados.add(num);
        }

        boolean ganado = numerosAAcertar.isEmpty();
    %>
    <%
        if (ganado) {
    %>
    <div id='completado'/>
    <%
        }
    %>   
    <div id='<%=(acertado)?"correcto":"fallo"%>'/>

    Llevas acertados: <div id='resultado'><%=numerosAcertados.size()%></div> / 5
    <br/> 
    <form method='get'>
        <%
            if(!ganado) {
        %>
        N&uacute;mero: <input name='num'/><br/> 

        <%
            }
        %> 


        <input type='submit' value='<%=(ganado)?"Reset":"Enviar"%>'/>
    </form>
</body>
</html>
