package Services

import scala.io.Source
import models._


class archivos{

    var _listaArchivosSubidos : List[String] = Nil

    def listaArchivosSubidos = _listaArchivosSubidos
    def listaArchivosSubidos_(nuevaLista : List[String]) = _listaArchivosSubidos = nuevaLista

    def definirExistencia(nomNuevo : String, list : List[String]) : Boolean={
        var bandera : Boolean = false
        list.foreach{
            e =>
                if (e == nomNuevo) bandera = true
        }
        if(!bandera) listaArchivosSubidos_(nomNuevo::list)
        bandera
    }

    def leerArchivoTexto( nombre : String ) : (Int, List[Avion], Int) = {
        var avionTemp : Avion = new Avion
        var numeroAviones = 9999
        var tiempoCongelamiento = 0
        var aviones : List[Avion] = Nil
        var primero : Boolean = true
        var primeroAvion : Boolean = true
        var listaLinea : (List[Int], List[Double]) = (Nil,Nil)
        var listaNumeros : List[Int] = Nil
        var lines = Source.fromFile(nombre)
        var contador = 0
        var contadorAviones = 0
        lines.getLines().foreach{
        e =>
            if(contadorAviones < numeroAviones){
                listaLinea = tomarNumeros(e)
                if(primero){
                numeroAviones = listaLinea._1(1)
                tiempoCongelamiento = listaLinea._1(0)
                primero = false
                }
                else{
                if(primeroAvion){
                    avionTemp = new Avion
                    avionTemp.nombre_( contador.toString )
                    avionTemp.tiempoAterrizajeReal_( 0 )
                    contador += 1
                    avionTemp.tiempoAparicion_( listaLinea._1(3) )
                    avionTemp.tiempoAterrizajeMasTemprano_( listaLinea._1(2) )
                    avionTemp.tiempoAterrizajeMasOptimo_( listaLinea._1(1) )
                    avionTemp.tiempoAterrizajeMasTarde_( listaLinea._1(0) )
                    avionTemp.costoPorUnidadTiempoAntesOptimo_( listaLinea._2(1) )
                    avionTemp.costoPorUnidadTiempoDespuesOptimo_( listaLinea._2(0) )
                    primeroAvion = false
                    listaNumeros = Nil
                }
                else{
                    listaNumeros = concatenar( listaLinea._1, listaNumeros)
                    if(listaNumeros.length == (numeroAviones) ){
                    avionTemp.tiempoDespuesAterrizaje_( listaNumeros )
                    avionTemp.nombreArchivo_(nombre)
                    primeroAvion = true
                    aviones = avionTemp :: aviones
                    contadorAviones += 1
                    }
                }
                }
            }
        }
        (numeroAviones, aviones, tiempoCongelamiento)
    }

    def concatenar(l1 : List[Int], l2 : List[Int]): List[Int] = {
        l1 match {
            case Nil => l2
            case x :: t => x :: concatenar(t, l2)
        }
    }

    def tomarNumeros(linea : String) : (List[Int], List[Double]) = {
        var numero : String = ""
        var vacio :String = ""
        var listaNumeros : List[Int] = Nil
        var listaDouble : List[Double] = Nil
        var largo = linea.length
        var contador = 0
        var double = false
        while( contador < largo ){
            if( !(linea(contador) >= 48 && linea(contador) <= 57 || linea(contador) == 46 ) ){
                if(numero != ""){
                    if(double){
                        double = false
                        listaDouble = numero.toDouble :: listaDouble  
                    }
                    else{
                        listaNumeros = numero.toInt :: listaNumeros
                    }
                    numero = ""
                }
                numero = ""
            }
            else{
                if(linea(contador) == 46){
                    double = true
                }
                numero = numero + linea(contador)
            }
            contador += 1
        }
        if(numero != ""){
            listaNumeros = numero.toInt  :: listaNumeros
        }
        (listaNumeros,listaDouble)
    }
}