
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
object addNominee extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,List[User],Form[UserNominee],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String, allUsers: List[User], nomineeForm: Form[UserNominee]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.73*/("""

"""),_display_(/*3.2*/main("Add Nominees")/*3.22*/ {_display_(Seq[Any](format.raw/*3.24*/("""

""")))}),format.raw/*5.2*/("""
"""))}
  }

  def render(message:String,allUsers:List[User],nomineeForm:Form[UserNominee]): play.twirl.api.HtmlFormat.Appendable = apply(message,allUsers,nomineeForm)

  def f:((String,List[User],Form[UserNominee]) => play.twirl.api.HtmlFormat.Appendable) = (message,allUsers,nomineeForm) => apply(message,allUsers,nomineeForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 07 11:15:50 IST 2015
                  SOURCE: /home/ranjan/playWorkspace/endProduct/app/exceptions/addNominee.scala.html
                  HASH: 86102428a149d572798eabf47e57e8301b1d6290
                  MATRIX: 762->1|921->72|949->75|977->95|1016->97|1048->100
                  LINES: 26->1|29->1|31->3|31->3|31->3|33->5
                  -- GENERATED --
              */
          