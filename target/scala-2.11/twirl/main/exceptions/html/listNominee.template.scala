
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
object listNominee extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,List[User],List[UserNominee],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String, allUsers: List[User], nominees: List[UserNominee]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.70*/("""

"""),_display_(/*3.2*/main(message)/*3.15*/ {_display_(Seq[Any](format.raw/*3.17*/("""


""")))}),format.raw/*6.2*/("""
"""))}
  }

  def render(message:String,allUsers:List[User],nominees:List[UserNominee]): play.twirl.api.HtmlFormat.Appendable = apply(message,allUsers,nominees)

  def f:((String,List[User],List[UserNominee]) => play.twirl.api.HtmlFormat.Appendable) = (message,allUsers,nominees) => apply(message,allUsers,nominees)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 07 11:15:50 IST 2015
                  SOURCE: /home/ranjan/playWorkspace/endProduct/app/exceptions/listNominee.scala.html
                  HASH: fbf5deafec11ff302e1918a1bb450c89c2c5da8d
                  MATRIX: 763->1|919->69|947->72|968->85|1007->87|1040->91
                  LINES: 26->1|29->1|31->3|31->3|31->3|34->6
                  -- GENERATED --
              */
          