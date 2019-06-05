//Elementos del DIV
var myButton = document.getElementById("CM");
var detalleUsuario = document.getElementById("detalleUsuario");
var mostrarCaso = document.getElementById("mostrarCaso");




//Eventos
myButton.addEventListener("click", function(event){
    console.log("Entre al JS");
    alert("Hola a todos");
})

// var myDiv= document.getElementById("Etiqueta");
// myDiv.innerHTML = "Este es el nuevo mensaje"

function detalle(idUsuario)
{
    var path = jsRoutes.controllers.HomeController.detalleUsuario(idUsuario)

    $.ajax({
        url: path.url,
        type: 'GET',
        success: function(result){
            detalleUsuario.innerHTML = result
        },
        error: function(){
            alert("Error")
        }
    });
}

function avion(archivoAvion, avion)
{
    //archivoAvion="archivosAvion/"+archivoAvion
    var path = jsRoutes.controllers.HomeController.mostrarCaso(archivoAvion)
    //var path2 = jsRoutes.controllers.HomeController.detalleUsuario(avion)

    $.ajax({
        url: path.url,
        type: 'GET',
        success: function(result){
            mostrarCaso.innerHTML = result
        },
        error: function(){
            alert("Error")
        }
    });
/*
    $.ajax({
        url:path2.url,
        type: 'GET',
        success: function(result){
            detalleUsuario.innetHTML = result
        },
        error: function(){
            alert("Error")
        }
    });
*/

}