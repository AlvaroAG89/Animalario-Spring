window.onload = function() {
	var movil = isMobile();
	if(movil==true){
		var URLactual = window.location.toString();
    	var dominio = URLactual.slice(0,-16);
    	var direccion = dominio+ "css/estilo-movil/";
    	
		$("#estiloEstante").attr("href", direccion + "estante-movil.css");
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