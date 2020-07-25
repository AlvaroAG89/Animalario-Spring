window.onload = function() {
	var movil = isMobile();
	if(movil==true){
		var URLactual = window.location.toString();
		
		//var dominio = URLactual.slice(0,-5);
		diseñoWeb
		var cssOriginal = $("#diseñoWeb").attr("href");
		
		var dominio = cssOriginal.slice(0,-12);
		var direccion = dominio + "estilo-movil/login-movil.css";
		
		$("#diseñoWeb").attr("href", direccion);
		
//		var dominio = window.location.pathname;
//    	var direccion = dominio+ "css/estilo-movil/";

//		$("#diseñoWeb").attr("href", "login-movil.css");
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