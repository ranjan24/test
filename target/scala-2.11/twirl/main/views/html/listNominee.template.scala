
package views.html

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
"""),format.raw/*132.11*/("""

  """),format.raw/*138.7*/(""" 
""")))}))}
  }

  def render(message:String,allUsers:List[User],nominees:List[UserNominee]): play.twirl.api.HtmlFormat.Appendable = apply(message,allUsers,nominees)

  def f:((String,List[User],List[UserNominee]) => play.twirl.api.HtmlFormat.Appendable) = (message,allUsers,nominees) => apply(message,allUsers,nominees)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 07 11:35:19 IST 2015
                  SOURCE: /home/ranjan/playWorkspace/endProduct/app/views/listNominee.scala.html
                  HASH: cd475d6bbc1261c9a9c359d913ee2455f39c2023
                  MATRIX: 758->1|914->69|942->72|963->85|1002->87|1032->3966|1064->4177
                  LINES: 26->1|29->1|31->3|31->3|31->3|32->132|34->138
                  -- GENERATED --
              */
          