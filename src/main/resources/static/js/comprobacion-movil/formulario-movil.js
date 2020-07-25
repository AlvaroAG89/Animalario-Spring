window.onload = function() {
	var movil = isMobile();
	if(movil==true){
		var URLactual = window.location.toString();
    	var dominio = URLactual.slice(0,-33);
    	var direccion = dominio+ "css/estilo-movil/";
		$("#estiloFormulario").attr("href", direccion + "formulario-movil.css");
	}
};

function isMobile() {
    try{ 
        document.createEvent("TouchEvent"); 
        return true; 
    }
    catch(e){ 
        return false;
    }
}