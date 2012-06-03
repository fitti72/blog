// @SOURCE:C:/play/blog/conf/routes
// @HASH:ad6932dcc6fa38a4043915c2301bb82822591dc6
// @DATE:Sun Jun 03 08:52:38 VET 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {


// @LINE:6
val controllers_Assets_at0 = Route("GET", PathPattern(List(StaticPart("/assets/"),DynamicPart("file", """.+"""))))
                    

// @LINE:10
val controllers_Usuario_index1 = Route("GET", PathPattern(List(StaticPart("/blog"))))
                    

// @LINE:12
val controllers_Usuario_index22 = Route("GET", PathPattern(List(StaticPart("/blog/index2"))))
                    

// @LINE:18
val controllers_Usuario_listar3 = Route("GET", PathPattern(List(StaticPart("/blog/usuario/list"))))
                    

// @LINE:21
val controllers_Usuario_insertar4 = Route("POST", PathPattern(List(StaticPart("/blog/usuario/insertar"))))
                    

// @LINE:24
val controllers_Usuario_eliminarUsuario5 = Route("DELETE", PathPattern(List(StaticPart("/blog/usuario/eliminar/"),DynamicPart("nick", """[^/]+"""))))
                    

// @LINE:27
val controllers_Usuario_modificarUsuario6 = Route("POST", PathPattern(List(StaticPart("/blog/usuario/modificar/"),DynamicPart("token", """[^/]+"""))))
                    

// @LINE:30
val controllers_Usuario_validarUsuario7 = Route("POST", PathPattern(List(StaticPart("/blog/usuario/validar"))))
                    

// @LINE:33
val controllers_Usuario_especifico8 = Route("GET", PathPattern(List(StaticPart("/blog/usuario/unico/"),DynamicPart("nick", """[^/]+"""))))
                    

// @LINE:36
val controllers_Usuario_devolverNick9 = Route("GET", PathPattern(List(StaticPart("/blog/usuario/datos/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:41
val controllers_Comentario_insertarComentario10 = Route("POST", PathPattern(List(StaticPart("/blog/comentario/insertar/"),DynamicPart("token", """[^/]+"""))))
                    

// @LINE:44
val controllers_Comentario_listart11 = Route("GET", PathPattern(List(StaticPart("/blog/comentario/listart"))))
                    

// @LINE:47
val controllers_Comentario_listarcomentarios12 = Route("GET", PathPattern(List(StaticPart("/blog/comentario/listar/"),DynamicPart("id_c", """[^/]+"""))))
                    

// @LINE:50
val controllers_Comentario_darLike13 = Route("POST", PathPattern(List(StaticPart("/blog/comentario/like/"),DynamicPart("token", """[^/]+"""))))
                    

// @LINE:53
val controllers_Comentario_eliminarComentarios14 = Route("DELETE", PathPattern(List(StaticPart("/blog/comentario/eliminar/"),DynamicPart("nick", """[^/]+"""),StaticPart("/"),DynamicPart("id_c", """[^/]+"""),StaticPart("/"),DynamicPart("token", """[^/]+"""))))
                    

// @LINE:56
val controllers_Comentario_eliminarComentariosXML15 = Route("POST", PathPattern(List(StaticPart("/blog/comentario/eliminar/"),DynamicPart("token", """[^/]+"""))))
                    

// @LINE:59
val controllers_Comentario_listarcomentariosUsuario16 = Route("GET", PathPattern(List(StaticPart("/blog/comentario/listaru/"),DynamicPart("nick", """[^/]+"""))))
                    

// @LINE:65
val controllers_Comentario_listartags17 = Route("GET", PathPattern(List(StaticPart("/blog/comentario/listar/tag/"),DynamicPart("tags", """[^/]+"""))))
                    

// @LINE:68
val controllers_Comentario_upload18 = Route("POST", PathPattern(List(StaticPart("/blog/comentario/insertarAdjunto"))))
                    

// @LINE:71
val controllers_Comentario_tagDeUnComentario19 = Route("GET", PathPattern(List(StaticPart("/blog/comentario/tags/"),DynamicPart("id_c", """[^/]+"""))))
                    

// @LINE:74
val controllers_Comentario_listarTags20 = Route("GET", PathPattern(List(StaticPart("/blog/tags/listar"))))
                    

// @LINE:77
val controllers_Comentario_insertarComentariotag21 = Route("POST", PathPattern(List(StaticPart("/blog/comentario/tag/"),DynamicPart("token", """[^/]+"""))))
                    

// @LINE:80
val controllers_Usuario_nuevaInsercionFoto22 = Route("POST", PathPattern(List(StaticPart("/blog/usuario/foto/"),DynamicPart("token", """[^/]+"""))))
                    

// @LINE:83
val controllers_Comentario_insercionAdjunto23 = Route("POST", PathPattern(List(StaticPart("/blog/comentario/insertarAdjunto/"),DynamicPart("token", """[^/]+"""))))
                    

// @LINE:86
val controllers_Usuario_obtenerFoto24 = Route("GET", PathPattern(List(StaticPart("/blog/usuario/obtenerFoto/"),DynamicPart("nick", """[^/]+"""))))
                    

// @LINE:89
val controllers_Comentario_obtenerAdjunto25 = Route("GET", PathPattern(List(StaticPart("/blog/comentario/obtenerAdjunto/"),DynamicPart("id_c", """[^/]+"""))))
                    
def documentation = List(("""GET""","""/assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""","""/blog""","""controllers.Usuario.index()"""),("""GET""","""/blog/index2""","""controllers.Usuario.index2()"""),("""GET""","""/blog/usuario/list""","""controllers.Usuario.listar()"""),("""POST""","""/blog/usuario/insertar""","""controllers.Usuario.insertar()"""),("""DELETE""","""/blog/usuario/eliminar/$nick<[^/]+>""","""controllers.Usuario.eliminarUsuario(nick:String)"""),("""POST""","""/blog/usuario/modificar/$token<[^/]+>""","""controllers.Usuario.modificarUsuario(token:String)"""),("""POST""","""/blog/usuario/validar""","""controllers.Usuario.validarUsuario()"""),("""GET""","""/blog/usuario/unico/$nick<[^/]+>""","""controllers.Usuario.especifico(nick:String)"""),("""GET""","""/blog/usuario/datos/$id<[^/]+>""","""controllers.Usuario.devolverNick(id:String)"""),("""POST""","""/blog/comentario/insertar/$token<[^/]+>""","""controllers.Comentario.insertarComentario(token:String)"""),("""GET""","""/blog/comentario/listart""","""controllers.Comentario.listart()"""),("""GET""","""/blog/comentario/listar/$id_c<[^/]+>""","""controllers.Comentario.listarcomentarios(id_c:String)"""),("""POST""","""/blog/comentario/like/$token<[^/]+>""","""controllers.Comentario.darLike(token:String)"""),("""DELETE""","""/blog/comentario/eliminar/$nick<[^/]+>/$id_c<[^/]+>/$token<[^/]+>""","""controllers.Comentario.eliminarComentarios(token:String, id_c:String, nick:String)"""),("""POST""","""/blog/comentario/eliminar/$token<[^/]+>""","""controllers.Comentario.eliminarComentariosXML(token:String)"""),("""GET""","""/blog/comentario/listaru/$nick<[^/]+>""","""controllers.Comentario.listarcomentariosUsuario(nick:String)"""),("""GET""","""/blog/comentario/listar/tag/$tags<[^/]+>""","""controllers.Comentario.listartags(tags:String)"""),("""POST""","""/blog/comentario/insertarAdjunto""","""controllers.Comentario.upload()"""),("""GET""","""/blog/comentario/tags/$id_c<[^/]+>""","""controllers.Comentario.tagDeUnComentario(id_c:String)"""),("""GET""","""/blog/tags/listar""","""controllers.Comentario.listarTags()"""),("""POST""","""/blog/comentario/tag/$token<[^/]+>""","""controllers.Comentario.insertarComentariotag(token:String)"""),("""POST""","""/blog/usuario/foto/$token<[^/]+>""","""controllers.Usuario.nuevaInsercionFoto(token:String)"""),("""POST""","""/blog/comentario/insertarAdjunto/$token<[^/]+>""","""controllers.Comentario.insercionAdjunto(token:String)"""),("""GET""","""/blog/usuario/obtenerFoto/$nick<[^/]+>""","""controllers.Usuario.obtenerFoto(nick:String)"""),("""GET""","""/blog/comentario/obtenerAdjunto/$id_c<[^/]+>""","""controllers.Comentario.obtenerAdjunto(id_c:String)"""))
             
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Assets_at0(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(_root_.controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String])))
   }
}
                    

// @LINE:10
case controllers_Usuario_index1(params) => {
   call { 
        invokeHandler(_root_.controllers.Usuario.index(), HandlerDef(this, "controllers.Usuario", "index", Nil))
   }
}
                    

// @LINE:12
case controllers_Usuario_index22(params) => {
   call { 
        invokeHandler(_root_.controllers.Usuario.index2(), HandlerDef(this, "controllers.Usuario", "index2", Nil))
   }
}
                    

// @LINE:18
case controllers_Usuario_listar3(params) => {
   call { 
        invokeHandler(_root_.controllers.Usuario.listar(), HandlerDef(this, "controllers.Usuario", "listar", Nil))
   }
}
                    

// @LINE:21
case controllers_Usuario_insertar4(params) => {
   call { 
        invokeHandler(_root_.controllers.Usuario.insertar(), HandlerDef(this, "controllers.Usuario", "insertar", Nil))
   }
}
                    

// @LINE:24
case controllers_Usuario_eliminarUsuario5(params) => {
   call(params.fromPath[String]("nick", None)) { (nick) =>
        invokeHandler(_root_.controllers.Usuario.eliminarUsuario(nick), HandlerDef(this, "controllers.Usuario", "eliminarUsuario", Seq(classOf[String])))
   }
}
                    

// @LINE:27
case controllers_Usuario_modificarUsuario6(params) => {
   call(params.fromPath[String]("token", None)) { (token) =>
        invokeHandler(_root_.controllers.Usuario.modificarUsuario(token), HandlerDef(this, "controllers.Usuario", "modificarUsuario", Seq(classOf[String])))
   }
}
                    

// @LINE:30
case controllers_Usuario_validarUsuario7(params) => {
   call { 
        invokeHandler(_root_.controllers.Usuario.validarUsuario(), HandlerDef(this, "controllers.Usuario", "validarUsuario", Nil))
   }
}
                    

// @LINE:33
case controllers_Usuario_especifico8(params) => {
   call(params.fromPath[String]("nick", None)) { (nick) =>
        invokeHandler(_root_.controllers.Usuario.especifico(nick), HandlerDef(this, "controllers.Usuario", "especifico", Seq(classOf[String])))
   }
}
                    

// @LINE:36
case controllers_Usuario_devolverNick9(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.Usuario.devolverNick(id), HandlerDef(this, "controllers.Usuario", "devolverNick", Seq(classOf[String])))
   }
}
                    

// @LINE:41
case controllers_Comentario_insertarComentario10(params) => {
   call(params.fromPath[String]("token", None)) { (token) =>
        invokeHandler(_root_.controllers.Comentario.insertarComentario(token), HandlerDef(this, "controllers.Comentario", "insertarComentario", Seq(classOf[String])))
   }
}
                    

// @LINE:44
case controllers_Comentario_listart11(params) => {
   call { 
        invokeHandler(_root_.controllers.Comentario.listart(), HandlerDef(this, "controllers.Comentario", "listart", Nil))
   }
}
                    

// @LINE:47
case controllers_Comentario_listarcomentarios12(params) => {
   call(params.fromPath[String]("id_c", None)) { (id_c) =>
        invokeHandler(_root_.controllers.Comentario.listarcomentarios(id_c), HandlerDef(this, "controllers.Comentario", "listarcomentarios", Seq(classOf[String])))
   }
}
                    

// @LINE:50
case controllers_Comentario_darLike13(params) => {
   call(params.fromPath[String]("token", None)) { (token) =>
        invokeHandler(_root_.controllers.Comentario.darLike(token), HandlerDef(this, "controllers.Comentario", "darLike", Seq(classOf[String])))
   }
}
                    

// @LINE:53
case controllers_Comentario_eliminarComentarios14(params) => {
   call(params.fromPath[String]("token", None), params.fromPath[String]("id_c", None), params.fromPath[String]("nick", None)) { (token, id_c, nick) =>
        invokeHandler(_root_.controllers.Comentario.eliminarComentarios(token, id_c, nick), HandlerDef(this, "controllers.Comentario", "eliminarComentarios", Seq(classOf[String], classOf[String], classOf[String])))
   }
}
                    

// @LINE:56
case controllers_Comentario_eliminarComentariosXML15(params) => {
   call(params.fromPath[String]("token", None)) { (token) =>
        invokeHandler(_root_.controllers.Comentario.eliminarComentariosXML(token), HandlerDef(this, "controllers.Comentario", "eliminarComentariosXML", Seq(classOf[String])))
   }
}
                    

// @LINE:59
case controllers_Comentario_listarcomentariosUsuario16(params) => {
   call(params.fromPath[String]("nick", None)) { (nick) =>
        invokeHandler(_root_.controllers.Comentario.listarcomentariosUsuario(nick), HandlerDef(this, "controllers.Comentario", "listarcomentariosUsuario", Seq(classOf[String])))
   }
}
                    

// @LINE:65
case controllers_Comentario_listartags17(params) => {
   call(params.fromPath[String]("tags", None)) { (tags) =>
        invokeHandler(_root_.controllers.Comentario.listartags(tags), HandlerDef(this, "controllers.Comentario", "listartags", Seq(classOf[String])))
   }
}
                    

// @LINE:68
case controllers_Comentario_upload18(params) => {
   call { 
        invokeHandler(_root_.controllers.Comentario.upload(), HandlerDef(this, "controllers.Comentario", "upload", Nil))
   }
}
                    

// @LINE:71
case controllers_Comentario_tagDeUnComentario19(params) => {
   call(params.fromPath[String]("id_c", None)) { (id_c) =>
        invokeHandler(_root_.controllers.Comentario.tagDeUnComentario(id_c), HandlerDef(this, "controllers.Comentario", "tagDeUnComentario", Seq(classOf[String])))
   }
}
                    

// @LINE:74
case controllers_Comentario_listarTags20(params) => {
   call { 
        invokeHandler(_root_.controllers.Comentario.listarTags(), HandlerDef(this, "controllers.Comentario", "listarTags", Nil))
   }
}
                    

// @LINE:77
case controllers_Comentario_insertarComentariotag21(params) => {
   call(params.fromPath[String]("token", None)) { (token) =>
        invokeHandler(_root_.controllers.Comentario.insertarComentariotag(token), HandlerDef(this, "controllers.Comentario", "insertarComentariotag", Seq(classOf[String])))
   }
}
                    

// @LINE:80
case controllers_Usuario_nuevaInsercionFoto22(params) => {
   call(params.fromPath[String]("token", None)) { (token) =>
        invokeHandler(_root_.controllers.Usuario.nuevaInsercionFoto(token), HandlerDef(this, "controllers.Usuario", "nuevaInsercionFoto", Seq(classOf[String])))
   }
}
                    

// @LINE:83
case controllers_Comentario_insercionAdjunto23(params) => {
   call(params.fromPath[String]("token", None)) { (token) =>
        invokeHandler(_root_.controllers.Comentario.insercionAdjunto(token), HandlerDef(this, "controllers.Comentario", "insercionAdjunto", Seq(classOf[String])))
   }
}
                    

// @LINE:86
case controllers_Usuario_obtenerFoto24(params) => {
   call(params.fromPath[String]("nick", None)) { (nick) =>
        invokeHandler(_root_.controllers.Usuario.obtenerFoto(nick), HandlerDef(this, "controllers.Usuario", "obtenerFoto", Seq(classOf[String])))
   }
}
                    

// @LINE:89
case controllers_Comentario_obtenerAdjunto25(params) => {
   call(params.fromPath[String]("id_c", None)) { (id_c) =>
        invokeHandler(_root_.controllers.Comentario.obtenerAdjunto(id_c), HandlerDef(this, "controllers.Comentario", "obtenerAdjunto", Seq(classOf[String])))
   }
}
                    
}
    
}
                