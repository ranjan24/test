
package exceptions.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object relationship extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[String,List[User],Map[String, String],Map[String, String],List[String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String, allUsers: List[User], userExceptNominee: Map[String, String], nomineeExceptUsers: Map[String, String], relation: List[String] ):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.147*/("""

"""),_display_(/*3.2*/main(message)/*3.15*/ {_display_(Seq[Any](format.raw/*3.17*/("""

 
""")))}),format.raw/*6.2*/("""
"""))}
  }

  def render(message:String,allUsers:List[User],userExceptNominee:Map[String, String],nomineeExceptUsers:Map[String, String],relation:List[String]): play.twirl.api.HtmlFormat.Appendable = apply(message,allUsers,userExceptNominee,nomineeExceptUsers,relation)

  def f:((String,List[User],Map[String, String],Map[String, String],List[String]) => play.twirl.api.HtmlFormat.Appendable) = (message,allUsers,userExceptNominee,nomineeExceptUsers,relation) => apply(message,allUsers,userExceptNominee,nomineeExceptUsers,relation)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 07 11:15:50 IST 2015
                  SOURCE: /home/ranjan/playWorkspace/endProduct/app/exceptions/relationship.scala.html
                  HASH: a0a7ce0667c0b7e3985c572593960e8e041a6fc6
                  MATRIX: 799->1|1033->146|1061->149|1082->162|1121->164|1155->169
                  LINES: 26->1|29->1|31->3|31->3|31->3|34->6
                  -- GENERATED --
              */
          