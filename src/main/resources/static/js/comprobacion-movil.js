//Al cargar la pagina comprobamos si lo hacemos desde un movil u ordenador
//Para ello usaremos el elemento tactil touch. Podrian usarse otros mejores, de momento este

window.onload = function() {
	var movil = isMobile();
	if(movil==true){
		var URLactual = window.location.toString();
    	var dominio = URLactual.slice(0,-5);
    	var direccion = dominio+ "css/";
    	
		$("#diseñoWeb").attr("href", direccion + "login-movil.css");
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