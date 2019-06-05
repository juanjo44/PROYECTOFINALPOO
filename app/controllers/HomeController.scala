package controllers

import javax.inject._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import play.api.libs.json._
import play.api.data.validation.Constraints._
import java.io._
import play.api.http.MimeTypes
import play.api.routing._
import play.Application
import play.api.libs.Files
import play.api.mvc.MultipartFormData
import java.io.File
import java.nio.file.attribute.PosixFilePermission._
import javax.inject.Inject
import javax.inject.Singleton
import java.io.File
import java.io.IOException
import java.nio.file.{Files, Paths, Path}
import play.libs.Files.DelegateTemporaryFile
import scala.io.Source
import Services._
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */

@Singleton
class HomeController @Inject()(components: MessagesControllerComponents)
    extends MessagesAbstractController(components) {
      var archivo : archivos = new archivos
      var torre1 : Torre = new Torre

  def inicio() = Action {  
    Ok(views.html.inicio())
  }

  /*def javascriptRoutesAvion = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.HomeController.detalleUsuario
      )
    ).as(MimeTypes.JAVASCRIPT)
  }*/

  def javascriptRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.HomeController.mostrarCaso
      )
    ).as(MimeTypes.JAVASCRIPT)
  }

  def detalleUsuario(idUsuario : Long) = Action {
    var avion : Avion = Avion().find.byId(idUsuario);
    var detailViewUser = views.html.ajaxViews.detallesAvion(avion).toString
    Ok(detailViewUser)
  }

  def upload() = Action(parse.multipartFormData) { request =>
  request.body
    .file("picture")
    .map { picture =>
      // only get the last part of the filename
      // otherwise someone can send a path like ../../home/foo/bar.txt to write to other files on the system
      val filename    = Paths.get(picture.filename).getFileName
      //val fileSize    = picture.fileSize
      //val contentType = picture.contentType

      picture.ref.moveTo(Paths.get(s"archivosAvion/$filename"), replace = true)
      var listAviones : List[Avion] = Avion().all;
      def aux(nombre : String, l : List[Avion]) : Boolean = {
        var bandera : Boolean = false
        l.foreach{
          e=>
          if(e.nombreArchivo == ("archivosAvion/" + nombre)) bandera = true
        }
        bandera
      }
      if(!aux((filename.toString), listAviones)){
        archivo.listaArchivosSubidos_(filename.toString::archivo.listaArchivosSubidos)
        torre1.aviones_(archivo.leerArchivoTexto(("archivosAvion/" + filename.toString))._2)
        torre1.determinarAterrizaje(torre1.mergeSortAviones(torre1.aviones))._1.foreach{
          e=>
          torre1.agregarBaseDatos(e)
        }
      }
      
      Redirect(routes.HomeController.inicio())
    }
    .getOrElse {
      Redirect(routes.HomeController.inicio()).flashing("error" -> "Missing file")
    }
  }

  def verInfo() = Action{
    Ok(views.html.verInfo(archivo.listaArchivosSubidos))
  }

  

  def mostrarCaso(nombre : String) = Action{

     def aux(nombre : String, l : List[Avion]) : List[Avion] = {
      l match{
        case Nil => Nil
        case x :: t => if(x.nombreArchivo == ("archivosAvion/" + nombre)) x :: aux(nombre,t)
                       else aux(nombre,t)
      }
    }
    var listAviones : List[Avion] = Avion().all;
    Ok(views.html.verAviones(aux(nombre,listAviones)))
  }



}
