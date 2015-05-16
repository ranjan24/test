// @SOURCE:/home/ranjan/playWorkspace/endProduct/conf/routes
// @HASH:1e8fa1469e382b9e88f1deba3fe028f1de8afb02
// @DATE:Sat May 09 13:50:28 IST 2015


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String): Unit = {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
controllers.Application.index(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
        

// @LINE:8
private[this] lazy val controllers_Application_signUp1_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signUp"))))
private[this] lazy val controllers_Application_signUp1_invoker = createInvoker(
controllers.Application.signUp(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "signUp", Nil,"POST", """""", Routes.prefix + """signUp"""))
        

// @LINE:10
private[this] lazy val controllers_Application_addNominee2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addNominee"))))
private[this] lazy val controllers_Application_addNominee2_invoker = createInvoker(
controllers.Application.addNominee(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "addNominee", Nil,"GET", """""", Routes.prefix + """addNominee"""))
        

// @LINE:12
private[this] lazy val controllers_Application_addNomineePost3_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addNomineePost"))))
private[this] lazy val controllers_Application_addNomineePost3_invoker = createInvoker(
controllers.Application.addNomineePost(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "addNomineePost", Nil,"POST", """""", Routes.prefix + """addNomineePost"""))
        

// @LINE:14
private[this] lazy val controllers_Application_listNominees4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("listNominees/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_Application_listNominees4_invoker = createInvoker(
controllers.Application.listNominees(fakeValue[Int]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "listNominees", Seq(classOf[Int]),"GET", """""", Routes.prefix + """listNominees/$id<[^/]+>"""))
        

// @LINE:16
private[this] lazy val controllers_Application_relationship5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("relationship"))))
private[this] lazy val controllers_Application_relationship5_invoker = createInvoker(
controllers.Application.relationship(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "relationship", Nil,"GET", """""", Routes.prefix + """relationship"""))
        

// @LINE:18
private[this] lazy val controllers_Application_relationshipPost6_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("relationshipPost/"),DynamicPart("user1", """[^/]+""",true),StaticPart("/"),DynamicPart("user2", """[^/]+""",true))))
private[this] lazy val controllers_Application_relationshipPost6_invoker = createInvoker(
controllers.Application.relationshipPost(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "relationshipPost", Seq(classOf[String], classOf[String]),"POST", """""", Routes.prefix + """relationshipPost/$user1<[^/]+>/$user2<[^/]+>"""))
        

// @LINE:20
private[this] lazy val controllers_Application_asyncRelationship7_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("asyncRelationship"))))
private[this] lazy val controllers_Application_asyncRelationship7_invoker = createInvoker(
controllers.Application.asyncRelationship(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "asyncRelationship", Nil,"POST", """""", Routes.prefix + """asyncRelationship"""))
        

// @LINE:22
private[this] lazy val controllers_Application_asyncRelationships8_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("asyncRelationships/"),DynamicPart("source", """[^/]+""",true),StaticPart("/"),DynamicPart("destination", """[^/]+""",true))))
private[this] lazy val controllers_Application_asyncRelationships8_invoker = createInvoker(
controllers.Application.asyncRelationships(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "asyncRelationships", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """asyncRelationships/$source<[^/]+>/$destination<[^/]+>"""))
        

// @LINE:27
private[this] lazy val controllers_Assets_at9_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at9_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signUp""","""controllers.Application.signUp()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addNominee""","""controllers.Application.addNominee()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addNomineePost""","""controllers.Application.addNomineePost()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """listNominees/$id<[^/]+>""","""controllers.Application.listNominees(id:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """relationship""","""controllers.Application.relationship()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """relationshipPost/$user1<[^/]+>/$user2<[^/]+>""","""controllers.Application.relationshipPost(user1:String, user2:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """asyncRelationship""","""controllers.Application.asyncRelationship()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """asyncRelationships/$source<[^/]+>/$destination<[^/]+>""","""controllers.Application.asyncRelationships(source:String, destination:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0_route(params) => {
   call { 
        controllers_Application_index0_invoker.call(controllers.Application.index())
   }
}
        

// @LINE:8
case controllers_Application_signUp1_route(params) => {
   call { 
        controllers_Application_signUp1_invoker.call(controllers.Application.signUp())
   }
}
        

// @LINE:10
case controllers_Application_addNominee2_route(params) => {
   call { 
        controllers_Application_addNominee2_invoker.call(controllers.Application.addNominee())
   }
}
        

// @LINE:12
case controllers_Application_addNomineePost3_route(params) => {
   call { 
        controllers_Application_addNomineePost3_invoker.call(controllers.Application.addNomineePost())
   }
}
        

// @LINE:14
case controllers_Application_listNominees4_route(params) => {
   call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_Application_listNominees4_invoker.call(controllers.Application.listNominees(id))
   }
}
        

// @LINE:16
case controllers_Application_relationship5_route(params) => {
   call { 
        controllers_Application_relationship5_invoker.call(controllers.Application.relationship())
   }
}
        

// @LINE:18
case controllers_Application_relationshipPost6_route(params) => {
   call(params.fromPath[String]("user1", None), params.fromPath[String]("user2", None)) { (user1, user2) =>
        controllers_Application_relationshipPost6_invoker.call(controllers.Application.relationshipPost(user1, user2))
   }
}
        

// @LINE:20
case controllers_Application_asyncRelationship7_route(params) => {
   call { 
        controllers_Application_asyncRelationship7_invoker.call(controllers.Application.asyncRelationship())
   }
}
        

// @LINE:22
case controllers_Application_asyncRelationships8_route(params) => {
   call(params.fromPath[String]("source", None), params.fromPath[String]("destination", None)) { (source, destination) =>
        controllers_Application_asyncRelationships8_invoker.call(controllers.Application.asyncRelationships(source, destination))
   }
}
        

// @LINE:27
case controllers_Assets_at9_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at9_invoker.call(controllers.Assets.at(path, file))
   }
}
        
}

}
     