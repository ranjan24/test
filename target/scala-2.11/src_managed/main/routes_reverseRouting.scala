// @SOURCE:/home/ranjan/playWorkspace/endProduct/conf/routes
// @HASH:1e8fa1469e382b9e88f1deba3fe028f1de8afb02
// @DATE:Sat May 09 13:50:28 IST 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:27
// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:14
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:6
package controllers {

// @LINE:27
class ReverseAssets {


// @LINE:27
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:14
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:6
class ReverseApplication {


// @LINE:12
def addNomineePost(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "addNomineePost")
}
                        

// @LINE:10
def addNominee(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "addNominee")
}
                        

// @LINE:16
def relationship(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "relationship")
}
                        

// @LINE:8
def signUp(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "signUp")
}
                        

// @LINE:14
def listNominees(id:Int): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "listNominees/" + implicitly[PathBindable[Int]].unbind("id", id))
}
                        

// @LINE:18
def relationshipPost(user1:String, user2:String): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "relationshipPost/" + implicitly[PathBindable[String]].unbind("user1", dynamicString(user1)) + "/" + implicitly[PathBindable[String]].unbind("user2", dynamicString(user2)))
}
                        

// @LINE:20
def asyncRelationship(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "asyncRelationship")
}
                        

// @LINE:6
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

// @LINE:22
def asyncRelationships(source:String, destination:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "asyncRelationships/" + implicitly[PathBindable[String]].unbind("source", dynamicString(source)) + "/" + implicitly[PathBindable[String]].unbind("destination", dynamicString(destination)))
}
                        

}
                          
}
                  


// @LINE:27
// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:14
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:27
class ReverseAssets {


// @LINE:27
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:14
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:6
class ReverseApplication {


// @LINE:12
def addNomineePost : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addNomineePost",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addNomineePost"})
      }
   """
)
                        

// @LINE:10
def addNominee : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addNominee",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "addNominee"})
      }
   """
)
                        

// @LINE:16
def relationship : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.relationship",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "relationship"})
      }
   """
)
                        

// @LINE:8
def signUp : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.signUp",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "signUp"})
      }
   """
)
                        

// @LINE:14
def listNominees : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.listNominees",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "listNominees/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:18
def relationshipPost : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.relationshipPost",
   """
      function(user1,user2) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "relationshipPost/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("user1", encodeURIComponent(user1)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("user2", encodeURIComponent(user2))})
      }
   """
)
                        

// @LINE:20
def asyncRelationship : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.asyncRelationship",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "asyncRelationship"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:22
def asyncRelationships : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.asyncRelationships",
   """
      function(source,destination) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "asyncRelationships/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("source", encodeURIComponent(source)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("destination", encodeURIComponent(destination))})
      }
   """
)
                        

}
              
}
        


// @LINE:27
// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:14
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:6
package controllers.ref {


// @LINE:27
class ReverseAssets {


// @LINE:27
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:22
// @LINE:20
// @LINE:18
// @LINE:16
// @LINE:14
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:6
class ReverseApplication {


// @LINE:12
def addNomineePost(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addNomineePost(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "addNomineePost", Seq(), "POST", """""", _prefix + """addNomineePost""")
)
                      

// @LINE:10
def addNominee(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addNominee(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "addNominee", Seq(), "GET", """""", _prefix + """addNominee""")
)
                      

// @LINE:16
def relationship(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.relationship(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "relationship", Seq(), "GET", """""", _prefix + """relationship""")
)
                      

// @LINE:8
def signUp(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.signUp(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "signUp", Seq(), "POST", """""", _prefix + """signUp""")
)
                      

// @LINE:14
def listNominees(id:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.listNominees(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "listNominees", Seq(classOf[Int]), "GET", """""", _prefix + """listNominees/$id<[^/]+>""")
)
                      

// @LINE:18
def relationshipPost(user1:String, user2:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.relationshipPost(user1, user2), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "relationshipPost", Seq(classOf[String], classOf[String]), "POST", """""", _prefix + """relationshipPost/$user1<[^/]+>/$user2<[^/]+>""")
)
                      

// @LINE:20
def asyncRelationship(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.asyncRelationship(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "asyncRelationship", Seq(), "POST", """""", _prefix + """asyncRelationship""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:22
def asyncRelationships(source:String, destination:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.asyncRelationships(source, destination), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "asyncRelationships", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """asyncRelationships/$source<[^/]+>/$destination<[^/]+>""")
)
                      

}
                          
}
        
    