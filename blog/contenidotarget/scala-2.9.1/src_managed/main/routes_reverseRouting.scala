// @SOURCE:C:/play/blog/conf/routes
// @HASH:ad6932dcc6fa38a4043915c2301bb82822591dc6
// @DATE:Sun Jun 03 08:52:38 VET 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:89
// @LINE:86
// @LINE:83
// @LINE:80
// @LINE:77
// @LINE:74
// @LINE:71
// @LINE:68
// @LINE:65
// @LINE:59
// @LINE:56
// @LINE:53
// @LINE:50
// @LINE:47
// @LINE:44
// @LINE:41
// @LINE:36
// @LINE:33
// @LINE:30
// @LINE:27
// @LINE:24
// @LINE:21
// @LINE:18
// @LINE:12
// @LINE:10
// @LINE:6
package controllers {

// @LINE:89
// @LINE:83
// @LINE:77
// @LINE:74
// @LINE:71
// @LINE:68
// @LINE:65
// @LINE:59
// @LINE:56
// @LINE:53
// @LINE:50
// @LINE:47
// @LINE:44
// @LINE:41
class ReverseComentario {
    


 
// @LINE:71
def tagDeUnComentario(id_c:String) = {
   Call("GET", "/blog/comentario/tags/" + implicitly[PathBindable[String]].unbind("id_c", id_c))
}
                                                        
 
// @LINE:41
def insertarComentario(token:String) = {
   Call("POST", "/blog/comentario/insertar/" + implicitly[PathBindable[String]].unbind("token", token))
}
                                                        
 
// @LINE:74
def listarTags() = {
   Call("GET", "/blog/tags/listar")
}
                                                        
 
// @LINE:50
def darLike(token:String) = {
   Call("POST", "/blog/comentario/like/" + implicitly[PathBindable[String]].unbind("token", token))
}
                                                        
 
// @LINE:89
def obtenerAdjunto(id_c:String) = {
   Call("GET", "/blog/comentario/obtenerAdjunto/" + implicitly[PathBindable[String]].unbind("id_c", id_c))
}
                                                        
 
// @LINE:53
def eliminarComentarios(token:String, id_c:String, nick:String) = {
   Call("DELETE", "/blog/comentario/eliminar/" + implicitly[PathBindable[String]].unbind("nick", nick) + "/" + implicitly[PathBindable[String]].unbind("id_c", id_c) + "/" + implicitly[PathBindable[String]].unbind("token", token))
}
                                                        
 
// @LINE:77
def insertarComentariotag(token:String) = {
   Call("POST", "/blog/comentario/tag/" + implicitly[PathBindable[String]].unbind("token", token))
}
                                                        
 
// @LINE:47
def listarcomentarios(id_c:String) = {
   Call("GET", "/blog/comentario/listar/" + implicitly[PathBindable[String]].unbind("id_c", id_c))
}
                                                        
 
// @LINE:68
def upload() = {
   Call("POST", "/blog/comentario/insertarAdjunto")
}
                                                        
 
// @LINE:56
def eliminarComentariosXML(token:String) = {
   Call("POST", "/blog/comentario/eliminar/" + implicitly[PathBindable[String]].unbind("token", token))
}
                                                        
 
// @LINE:44
def listart() = {
   Call("GET", "/blog/comentario/listart")
}
                                                        
 
// @LINE:65
def listartags(tags:String) = {
   Call("GET", "/blog/comentario/listar/tag/" + implicitly[PathBindable[String]].unbind("tags", tags))
}
                                                        
 
// @LINE:83
def insercionAdjunto(token:String) = {
   Call("POST", "/blog/comentario/insertarAdjunto/" + implicitly[PathBindable[String]].unbind("token", token))
}
                                                        
 
// @LINE:59
def listarcomentariosUsuario(nick:String) = {
   Call("GET", "/blog/comentario/listaru/" + implicitly[PathBindable[String]].unbind("nick", nick))
}
                                                        

                      
    
}
                            

// @LINE:6
class ReverseAssets {
    


 
// @LINE:6
def at(file:String) = {
   Call("GET", "/assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                        

                      
    
}
                            

// @LINE:86
// @LINE:80
// @LINE:36
// @LINE:33
// @LINE:30
// @LINE:27
// @LINE:24
// @LINE:21
// @LINE:18
// @LINE:12
// @LINE:10
class ReverseUsuario {
    


 
// @LINE:33
def especifico(nick:String) = {
   Call("GET", "/blog/usuario/unico/" + implicitly[PathBindable[String]].unbind("nick", nick))
}
                                                        
 
// @LINE:21
def insertar() = {
   Call("POST", "/blog/usuario/insertar")
}
                                                        
 
// @LINE:86
def obtenerFoto(nick:String) = {
   Call("GET", "/blog/usuario/obtenerFoto/" + implicitly[PathBindable[String]].unbind("nick", nick))
}
                                                        
 
// @LINE:36
def devolverNick(id:String) = {
   Call("GET", "/blog/usuario/datos/" + implicitly[PathBindable[String]].unbind("id", id))
}
                                                        
 
// @LINE:80
def nuevaInsercionFoto(token:String) = {
   Call("POST", "/blog/usuario/foto/" + implicitly[PathBindable[String]].unbind("token", token))
}
                                                        
 
// @LINE:30
def validarUsuario() = {
   Call("POST", "/blog/usuario/validar")
}
                                                        
 
// @LINE:24
def eliminarUsuario(nick:String) = {
   Call("DELETE", "/blog/usuario/eliminar/" + implicitly[PathBindable[String]].unbind("nick", nick))
}
                                                        
 
// @LINE:18
def listar() = {
   Call("GET", "/blog/usuario/list")
}
                                                        
 
// @LINE:10
def index() = {
   Call("GET", "/blog")
}
                                                        
 
// @LINE:12
def index2() = {
   Call("GET", "/blog/index2")
}
                                                        
 
// @LINE:27
def modificarUsuario(token:String) = {
   Call("POST", "/blog/usuario/modificar/" + implicitly[PathBindable[String]].unbind("token", token))
}
                                                        

                      
    
}
                            
}
                    


// @LINE:89
// @LINE:86
// @LINE:83
// @LINE:80
// @LINE:77
// @LINE:74
// @LINE:71
// @LINE:68
// @LINE:65
// @LINE:59
// @LINE:56
// @LINE:53
// @LINE:50
// @LINE:47
// @LINE:44
// @LINE:41
// @LINE:36
// @LINE:33
// @LINE:30
// @LINE:27
// @LINE:24
// @LINE:21
// @LINE:18
// @LINE:12
// @LINE:10
// @LINE:6
package controllers.javascript {

// @LINE:89
// @LINE:83
// @LINE:77
// @LINE:74
// @LINE:71
// @LINE:68
// @LINE:65
// @LINE:59
// @LINE:56
// @LINE:53
// @LINE:50
// @LINE:47
// @LINE:44
// @LINE:41
class ReverseComentario {
    


 
// @LINE:71
def tagDeUnComentario = JavascriptReverseRoute(
   "controllers.Comentario.tagDeUnComentario",
   """
      function(id_c) {
      return _wA({method:"GET", url:"/blog/comentario/tags/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id_c", id_c)})
      }
   """
)
                                                        
 
// @LINE:41
def insertarComentario = JavascriptReverseRoute(
   "controllers.Comentario.insertarComentario",
   """
      function(token) {
      return _wA({method:"POST", url:"/blog/comentario/insertar/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", token)})
      }
   """
)
                                                        
 
// @LINE:74
def listarTags = JavascriptReverseRoute(
   "controllers.Comentario.listarTags",
   """
      function() {
      return _wA({method:"GET", url:"/blog/tags/listar"})
      }
   """
)
                                                        
 
// @LINE:50
def darLike = JavascriptReverseRoute(
   "controllers.Comentario.darLike",
   """
      function(token) {
      return _wA({method:"POST", url:"/blog/comentario/like/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", token)})
      }
   """
)
                                                        
 
// @LINE:89
def obtenerAdjunto = JavascriptReverseRoute(
   "controllers.Comentario.obtenerAdjunto",
   """
      function(id_c) {
      return _wA({method:"GET", url:"/blog/comentario/obtenerAdjunto/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id_c", id_c)})
      }
   """
)
                                                        
 
// @LINE:53
def eliminarComentarios = JavascriptReverseRoute(
   "controllers.Comentario.eliminarComentarios",
   """
      function(token,id_c,nick) {
      return _wA({method:"DELETE", url:"/blog/comentario/eliminar/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("nick", nick) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id_c", id_c) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", token)})
      }
   """
)
                                                        
 
// @LINE:77
def insertarComentariotag = JavascriptReverseRoute(
   "controllers.Comentario.insertarComentariotag",
   """
      function(token) {
      return _wA({method:"POST", url:"/blog/comentario/tag/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", token)})
      }
   """
)
                                                        
 
// @LINE:47
def listarcomentarios = JavascriptReverseRoute(
   "controllers.Comentario.listarcomentarios",
   """
      function(id_c) {
      return _wA({method:"GET", url:"/blog/comentario/listar/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id_c", id_c)})
      }
   """
)
                                                        
 
// @LINE:68
def upload = JavascriptReverseRoute(
   "controllers.Comentario.upload",
   """
      function() {
      return _wA({method:"POST", url:"/blog/comentario/insertarAdjunto"})
      }
   """
)
                                                        
 
// @LINE:56
def eliminarComentariosXML = JavascriptReverseRoute(
   "controllers.Comentario.eliminarComentariosXML",
   """
      function(token) {
      return _wA({method:"POST", url:"/blog/comentario/eliminar/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", token)})
      }
   """
)
                                                        
 
// @LINE:44
def listart = JavascriptReverseRoute(
   "controllers.Comentario.listart",
   """
      function() {
      return _wA({method:"GET", url:"/blog/comentario/listart"})
      }
   """
)
                                                        
 
// @LINE:65
def listartags = JavascriptReverseRoute(
   "controllers.Comentario.listartags",
   """
      function(tags) {
      return _wA({method:"GET", url:"/blog/comentario/listar/tag/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("tags", tags)})
      }
   """
)
                                                        
 
// @LINE:83
def insercionAdjunto = JavascriptReverseRoute(
   "controllers.Comentario.insercionAdjunto",
   """
      function(token) {
      return _wA({method:"POST", url:"/blog/comentario/insertarAdjunto/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", token)})
      }
   """
)
                                                        
 
// @LINE:59
def listarcomentariosUsuario = JavascriptReverseRoute(
   "controllers.Comentario.listarcomentariosUsuario",
   """
      function(nick) {
      return _wA({method:"GET", url:"/blog/comentario/listaru/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("nick", nick)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:6
class ReverseAssets {
    


 
// @LINE:6
def at = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"/assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:86
// @LINE:80
// @LINE:36
// @LINE:33
// @LINE:30
// @LINE:27
// @LINE:24
// @LINE:21
// @LINE:18
// @LINE:12
// @LINE:10
class ReverseUsuario {
    


 
// @LINE:33
def especifico = JavascriptReverseRoute(
   "controllers.Usuario.especifico",
   """
      function(nick) {
      return _wA({method:"GET", url:"/blog/usuario/unico/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("nick", nick)})
      }
   """
)
                                                        
 
// @LINE:21
def insertar = JavascriptReverseRoute(
   "controllers.Usuario.insertar",
   """
      function() {
      return _wA({method:"POST", url:"/blog/usuario/insertar"})
      }
   """
)
                                                        
 
// @LINE:86
def obtenerFoto = JavascriptReverseRoute(
   "controllers.Usuario.obtenerFoto",
   """
      function(nick) {
      return _wA({method:"GET", url:"/blog/usuario/obtenerFoto/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("nick", nick)})
      }
   """
)
                                                        
 
// @LINE:36
def devolverNick = JavascriptReverseRoute(
   "controllers.Usuario.devolverNick",
   """
      function(id) {
      return _wA({method:"GET", url:"/blog/usuario/datos/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:80
def nuevaInsercionFoto = JavascriptReverseRoute(
   "controllers.Usuario.nuevaInsercionFoto",
   """
      function(token) {
      return _wA({method:"POST", url:"/blog/usuario/foto/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", token)})
      }
   """
)
                                                        
 
// @LINE:30
def validarUsuario = JavascriptReverseRoute(
   "controllers.Usuario.validarUsuario",
   """
      function() {
      return _wA({method:"POST", url:"/blog/usuario/validar"})
      }
   """
)
                                                        
 
// @LINE:24
def eliminarUsuario = JavascriptReverseRoute(
   "controllers.Usuario.eliminarUsuario",
   """
      function(nick) {
      return _wA({method:"DELETE", url:"/blog/usuario/eliminar/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("nick", nick)})
      }
   """
)
                                                        
 
// @LINE:18
def listar = JavascriptReverseRoute(
   "controllers.Usuario.listar",
   """
      function() {
      return _wA({method:"GET", url:"/blog/usuario/list"})
      }
   """
)
                                                        
 
// @LINE:10
def index = JavascriptReverseRoute(
   "controllers.Usuario.index",
   """
      function() {
      return _wA({method:"GET", url:"/blog"})
      }
   """
)
                                                        
 
// @LINE:12
def index2 = JavascriptReverseRoute(
   "controllers.Usuario.index2",
   """
      function() {
      return _wA({method:"GET", url:"/blog/index2"})
      }
   """
)
                                                        
 
// @LINE:27
def modificarUsuario = JavascriptReverseRoute(
   "controllers.Usuario.modificarUsuario",
   """
      function(token) {
      return _wA({method:"POST", url:"/blog/usuario/modificar/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", token)})
      }
   """
)
                                                        

                      
    
}
                            
}
                    


// @LINE:89
// @LINE:86
// @LINE:83
// @LINE:80
// @LINE:77
// @LINE:74
// @LINE:71
// @LINE:68
// @LINE:65
// @LINE:59
// @LINE:56
// @LINE:53
// @LINE:50
// @LINE:47
// @LINE:44
// @LINE:41
// @LINE:36
// @LINE:33
// @LINE:30
// @LINE:27
// @LINE:24
// @LINE:21
// @LINE:18
// @LINE:12
// @LINE:10
// @LINE:6
package controllers.ref {

// @LINE:89
// @LINE:83
// @LINE:77
// @LINE:74
// @LINE:71
// @LINE:68
// @LINE:65
// @LINE:59
// @LINE:56
// @LINE:53
// @LINE:50
// @LINE:47
// @LINE:44
// @LINE:41
class ReverseComentario {
    


 
// @LINE:71
def tagDeUnComentario(id_c:String) = new play.api.mvc.HandlerRef(
   controllers.Comentario.tagDeUnComentario(id_c), HandlerDef(this, "controllers.Comentario", "tagDeUnComentario", Seq(classOf[String]))
)
                              
 
// @LINE:41
def insertarComentario(token:String) = new play.api.mvc.HandlerRef(
   controllers.Comentario.insertarComentario(token), HandlerDef(this, "controllers.Comentario", "insertarComentario", Seq(classOf[String]))
)
                              
 
// @LINE:74
def listarTags() = new play.api.mvc.HandlerRef(
   controllers.Comentario.listarTags(), HandlerDef(this, "controllers.Comentario", "listarTags", Seq())
)
                              
 
// @LINE:50
def darLike(token:String) = new play.api.mvc.HandlerRef(
   controllers.Comentario.darLike(token), HandlerDef(this, "controllers.Comentario", "darLike", Seq(classOf[String]))
)
                              
 
// @LINE:89
def obtenerAdjunto(id_c:String) = new play.api.mvc.HandlerRef(
   controllers.Comentario.obtenerAdjunto(id_c), HandlerDef(this, "controllers.Comentario", "obtenerAdjunto", Seq(classOf[String]))
)
                              
 
// @LINE:53
def eliminarComentarios(token:String, id_c:String, nick:String) = new play.api.mvc.HandlerRef(
   controllers.Comentario.eliminarComentarios(token, id_c, nick), HandlerDef(this, "controllers.Comentario", "eliminarComentarios", Seq(classOf[String], classOf[String], classOf[String]))
)
                              
 
// @LINE:77
def insertarComentariotag(token:String) = new play.api.mvc.HandlerRef(
   controllers.Comentario.insertarComentariotag(token), HandlerDef(this, "controllers.Comentario", "insertarComentariotag", Seq(classOf[String]))
)
                              
 
// @LINE:47
def listarcomentarios(id_c:String) = new play.api.mvc.HandlerRef(
   controllers.Comentario.listarcomentarios(id_c), HandlerDef(this, "controllers.Comentario", "listarcomentarios", Seq(classOf[String]))
)
                              
 
// @LINE:68
def upload() = new play.api.mvc.HandlerRef(
   controllers.Comentario.upload(), HandlerDef(this, "controllers.Comentario", "upload", Seq())
)
                              
 
// @LINE:56
def eliminarComentariosXML(token:String) = new play.api.mvc.HandlerRef(
   controllers.Comentario.eliminarComentariosXML(token), HandlerDef(this, "controllers.Comentario", "eliminarComentariosXML", Seq(classOf[String]))
)
                              
 
// @LINE:44
def listart() = new play.api.mvc.HandlerRef(
   controllers.Comentario.listart(), HandlerDef(this, "controllers.Comentario", "listart", Seq())
)
                              
 
// @LINE:65
def listartags(tags:String) = new play.api.mvc.HandlerRef(
   controllers.Comentario.listartags(tags), HandlerDef(this, "controllers.Comentario", "listartags", Seq(classOf[String]))
)
                              
 
// @LINE:83
def insercionAdjunto(token:String) = new play.api.mvc.HandlerRef(
   controllers.Comentario.insercionAdjunto(token), HandlerDef(this, "controllers.Comentario", "insercionAdjunto", Seq(classOf[String]))
)
                              
 
// @LINE:59
def listarcomentariosUsuario(nick:String) = new play.api.mvc.HandlerRef(
   controllers.Comentario.listarcomentariosUsuario(nick), HandlerDef(this, "controllers.Comentario", "listarcomentariosUsuario", Seq(classOf[String]))
)
                              

                      
    
}
                            

// @LINE:6
class ReverseAssets {
    


 
// @LINE:6
def at(path:String, file:String) = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]))
)
                              

                      
    
}
                            

// @LINE:86
// @LINE:80
// @LINE:36
// @LINE:33
// @LINE:30
// @LINE:27
// @LINE:24
// @LINE:21
// @LINE:18
// @LINE:12
// @LINE:10
class ReverseUsuario {
    


 
// @LINE:33
def especifico(nick:String) = new play.api.mvc.HandlerRef(
   controllers.Usuario.especifico(nick), HandlerDef(this, "controllers.Usuario", "especifico", Seq(classOf[String]))
)
                              
 
// @LINE:21
def insertar() = new play.api.mvc.HandlerRef(
   controllers.Usuario.insertar(), HandlerDef(this, "controllers.Usuario", "insertar", Seq())
)
                              
 
// @LINE:86
def obtenerFoto(nick:String) = new play.api.mvc.HandlerRef(
   controllers.Usuario.obtenerFoto(nick), HandlerDef(this, "controllers.Usuario", "obtenerFoto", Seq(classOf[String]))
)
                              
 
// @LINE:36
def devolverNick(id:String) = new play.api.mvc.HandlerRef(
   controllers.Usuario.devolverNick(id), HandlerDef(this, "controllers.Usuario", "devolverNick", Seq(classOf[String]))
)
                              
 
// @LINE:80
def nuevaInsercionFoto(token:String) = new play.api.mvc.HandlerRef(
   controllers.Usuario.nuevaInsercionFoto(token), HandlerDef(this, "controllers.Usuario", "nuevaInsercionFoto", Seq(classOf[String]))
)
                              
 
// @LINE:30
def validarUsuario() = new play.api.mvc.HandlerRef(
   controllers.Usuario.validarUsuario(), HandlerDef(this, "controllers.Usuario", "validarUsuario", Seq())
)
                              
 
// @LINE:24
def eliminarUsuario(nick:String) = new play.api.mvc.HandlerRef(
   controllers.Usuario.eliminarUsuario(nick), HandlerDef(this, "controllers.Usuario", "eliminarUsuario", Seq(classOf[String]))
)
                              
 
// @LINE:18
def listar() = new play.api.mvc.HandlerRef(
   controllers.Usuario.listar(), HandlerDef(this, "controllers.Usuario", "listar", Seq())
)
                              
 
// @LINE:10
def index() = new play.api.mvc.HandlerRef(
   controllers.Usuario.index(), HandlerDef(this, "controllers.Usuario", "index", Seq())
)
                              
 
// @LINE:12
def index2() = new play.api.mvc.HandlerRef(
   controllers.Usuario.index2(), HandlerDef(this, "controllers.Usuario", "index2", Seq())
)
                              
 
// @LINE:27
def modificarUsuario(token:String) = new play.api.mvc.HandlerRef(
   controllers.Usuario.modificarUsuario(token), HandlerDef(this, "controllers.Usuario", "modificarUsuario", Seq(classOf[String]))
)
                              

                      
    
}
                            
}
                    
                