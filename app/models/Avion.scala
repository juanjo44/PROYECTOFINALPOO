package models
 
import io.ebean._
import com.typesafe.config.Config;
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.ManyToMany
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.CascadeType
import com.typesafe.config.Config
import scala.collection.JavaConverters._

@Entity
case class Avion() extends Model{

    //Atributos

    @Id
    @Column(name = "Id")
        private var _id :Long = 0
    @Column(name = "Nombre")
        private var _nombre : String = ""
    @Column(name = "NombreArchivo")
        private var _nombreArchivo : String = ""
    @Column(name = "TiempoAparicion")
        private var _tiempoAparicion : Int = 0
    @Column(name = "TiempoAterrizajeMasTemprano")
        private var _tiempoAterrizajeMasTemprano : Int = 0
    @Column(name = "TiempoAterrizajeMasOptimo")
        private var _tiempoAterrizajeMasOptimo : Int = 0
    @Column(name = "TiempoAterrizajeMasTarde")
        private var _tiempoAterrizajeMasTarde : Int = 0
    @Column(name = "CostoPorUnidadTiempoAntesOptimo")
        private var _costoPorUnidadTiempoAntesOptimo : Double = 0.0
    @Column(name = "CostoPorUnidadTiempoDespuesOptimo")
        private var _costoPorUnidadTiempoDespuesOptimo : Double = 0.0
    @Column(name = "TiempoAterrizajeReal")
        private var _tiempoAterrizajeReal : Int = 0
    @Column(name = "TiempoDespuesAterrizaje")
        private var _tiempoDespuesAterrizaje : List[Int] = Nil

    var find : Finder[Long, Avion] = new Finder[Long, Avion] (classOf[Avion])


    //Getters

    def id = _id
    def nombre = _nombre
    def nombreArchivo = _nombreArchivo
    def tiempoAparicion = _tiempoAparicion
    def tiempoAterrizajeMasTemprano = _tiempoAterrizajeMasTemprano
    def tiempoAterrizajeMasOptimo = _tiempoAterrizajeMasOptimo
    def tiempoAterrizajeMasTarde = _tiempoAterrizajeMasTarde
    def costoPorUnidadTiempoAntesOptimo = _costoPorUnidadTiempoAntesOptimo
    def costoPorUnidadTiempoDespuesOptimo = _costoPorUnidadTiempoDespuesOptimo
    def tiempoAterrizajeReal = _tiempoAterrizajeReal
    def tiempoDespuesAterrizaje = _tiempoDespuesAterrizaje

    //Setters

    def id_(nuevoId : Long) = _id  = nuevoId
    def nombre_(nuevoNombre : String) = _nombre = nuevoNombre
    def nombreArchivo_(nuevoNombreArchivo : String) = _nombreArchivo = nuevoNombreArchivo
    def tiempoAparicion_(nuevoTiempoAparicion : Int) = _tiempoAparicion = nuevoTiempoAparicion
    def tiempoAterrizajeMasTemprano_(nuevoTiempoAterrizajeMasTemprano : Int) = _tiempoAterrizajeMasTemprano = nuevoTiempoAterrizajeMasTemprano
    def tiempoAterrizajeMasOptimo_(nuevoTiempoAterrizajeMasOptimo : Int) = _tiempoAterrizajeMasOptimo = nuevoTiempoAterrizajeMasOptimo
    def tiempoAterrizajeMasTarde_(nuevoTiempoAterrizajeMasTarde : Int) = _tiempoAterrizajeMasTarde = nuevoTiempoAterrizajeMasTarde
    def costoPorUnidadTiempoAntesOptimo_(nuevoCostoPorUnidadTiempoAntesOptimo : Double) = _costoPorUnidadTiempoAntesOptimo = nuevoCostoPorUnidadTiempoAntesOptimo
    def costoPorUnidadTiempoDespuesOptimo_(nuevoCostoPorUnidadTiempoDespuesOptimo : Double) = _costoPorUnidadTiempoDespuesOptimo = nuevoCostoPorUnidadTiempoDespuesOptimo
    def tiempoAterrizajeReal_(nuevoTiempoAterrizajeReal : Int) = _tiempoAterrizajeReal = nuevoTiempoAterrizajeReal
    def tiempoDespuesAterrizaje_(nuevoTiempoDespuesAterrizaje : List[Int]) = _tiempoDespuesAterrizaje = nuevoTiempoDespuesAterrizaje

    //Queries
    def all() : List[Avion] = find.all().asScala.toList

}