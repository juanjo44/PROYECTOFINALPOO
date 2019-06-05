package Services

import scala.io.Source
import models._

class Torre {
    private var _aviones : List[Avion] = Nil

    def aviones = _aviones
    def aviones_(nuevoAvion : List[Avion]) = _aviones = nuevoAvion

    
    def agregarBaseDatos(avion : Avion) = {
        avion.save()
    }

    def merge(left: List[Avion], right: List[Avion]): List[Avion] = {
        (left, right) match {
		case(left, Nil) => left
		case(Nil, right) => right
		case(leftHead :: leftTail, rightHead :: rightTail) =>
			if (leftHead.tiempoAterrizajeMasTemprano < rightHead.tiempoAterrizajeMasTemprano ) leftHead::merge(leftTail, right)
			else rightHead :: merge(left, rightTail)
        }    
    }

    def mergeSortAviones(list: List[Avion]): List[Avion] = {
	    var n = list.length / 2
	    if (n == 0) list
	    else {
		    val (left, right) = list.splitAt(n)
		    merge(mergeSortAviones(left), mergeSortAviones(right))
	    }
    }      

def determinarAterrizaje(list : List[Avion]) : (List[Avion], Double) = {
	var suma : Double = 0.0
	var tamanoLista = list.length
	var siguiente = 1
	var tiempoEspera = 0
	var pistaAterrizaje = 0
	list.foreach{
		e =>
			if(siguiente < tamanoLista){
				if( (pistaAterrizaje + tiempoEspera) <= e.tiempoAterrizajeMasTemprano ){
					e.tiempoAterrizajeReal_(e.tiempoAterrizajeMasTemprano)
				}
				else{
					e.tiempoAterrizajeReal_(pistaAterrizaje + tiempoEspera)
				}
				tiempoEspera = e.tiempoDespuesAterrizaje(e.tiempoDespuesAterrizaje.length - 1 - list(siguiente).nombre.toInt )
				pistaAterrizaje = e.tiempoAterrizajeReal
			}
			else{
				if(e.tiempoAterrizajeMasOptimo >= (pistaAterrizaje + tiempoEspera) ){
					e.tiempoAterrizajeReal_( e.tiempoAterrizajeMasOptimo)
				}
				else{
					e.tiempoAterrizajeReal_(pistaAterrizaje + tiempoEspera)
				}
			}
			if(e.tiempoAterrizajeReal < e.tiempoAterrizajeMasOptimo){
				suma += e.costoPorUnidadTiempoAntesOptimo * (e.tiempoAterrizajeMasOptimo - e.tiempoAterrizajeReal)
			}
			else if(e.tiempoAterrizajeReal > e.tiempoAterrizajeMasOptimo){
				suma += e.costoPorUnidadTiempoDespuesOptimo * (e.tiempoAterrizajeReal - e.tiempoAterrizajeMasOptimo)
			}
			siguiente += 1
 
	}
	(list, suma)
    }
}