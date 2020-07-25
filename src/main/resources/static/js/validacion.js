$("#inicioSesion").click(function(evObject){
	var todoCorrecto = true;
	
	evObject.preventDefault(); //Evita el envío del formulario hasta comprobar
	
	if($('#username').val().length ==0){
		if($('#usernameError').length ==0){
			$('#usuario').append('<small class="form-text text-danger" id="usernameError" >Campo Obligatorio</small>');
			$('#username').css('background-color', '#FCBDBD');
		}
		
		todoCorrecto = false;
	
	}else{
		$('#username').css('background-color', '#BDFCD1');
		$('#usernameError').remove();	
	
	}
	
	
	
	if($('#password').val().length ==0){
		if($('#passwordError').length ==0){
			$('#contrasena').append('<small class="form-text text-danger" id="passwordError" >Campo Obligatorio</small>');
			$('#password').css('background-color', '#FCBDBD');
		}
		todoCorrecto = false;
	}else{
		$('#password').css('background-color', '#BDFCD1');
		$('#passwordError').remove();
	}
	
	
	
	if (todoCorrecto == true) 
	{
		//si no salto fallos permitimos el submit y ya validara el resto JPA
		$("#formularioLogin").submit();

	}
	
});



$("#AñadirJaula").click(function(evObject){
	evObject.preventDefault(); //Evita el envío del formulario hasta comprobar

	todoCorrecto = true;

	var formulario = document.formularioJaulas;

	//usamos el id para buscarlo y sacamos el valor
	if (isNaN(formulario.rataHembra.value)==true  ) 
	{	//Si existe ya el small porque ya salto el error lo dejamos, si no lo crea
		if($('#RataHembraError').length==0){
			$('#bloqueRataHembra').append('<small class="form-text text-danger" id="RataHembraError" >El dato no es numerico</small>');
			$('#rataHembra').css('background-color', '#FCBDBD');
		}
		
		todoCorrecto=false;
	
	}else{//quitamos la etiqueta de error si existiera y pintamos de verde el campo como correcto
		$('#rataHembra').css('background-color', '#BDFCD1');
		$('#RataHembraError').remove();
	}

	
	//Comprobamos si es numérico el campo rataMacho
	if (isNaN(formulario.rataMacho.value)==true  ) 
	{
		if($('#RataMachoError').length==0){
			$('#bloqueRataMacho').append('<small class="form-text text-danger" id="RataMachoError" >El dato no es numerico</small>');
			$('#rataMacho').css('background-color', '#FCBDBD');
		}
		
		todoCorrecto=false;
	}else{
		$('#rataMacho').css('background-color', '#BDFCD1');
		$('#RataMachoError').remove();
	}
	
	
	
	if (todoCorrecto == true) 
	{
		//si no salto fallos permitimos el submit y ya validara el resto JPA
		$("#formularioJaulas").submit();

	}
});


