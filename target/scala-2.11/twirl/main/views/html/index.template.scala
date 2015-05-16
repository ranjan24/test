
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
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Form[User],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String, myForm: Form[User]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.39*/("""

"""),_display_(/*3.2*/main("Registration Form")/*3.27*/ {_display_(Seq[Any](format.raw/*3.29*/("""

"""),format.raw/*5.1*/("""<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Registration form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" media="screen" href=""""),_display_(/*12.50*/routes/*12.56*/.Assets.at("stylesheets/bootstrap.min.css")),format.raw/*12.99*/("""">
    <link rel="stylesheet" type="text/css" href=""""),_display_(/*13.51*/routes/*13.57*/.Assets.at("stylesheets/font-awesome.min.css")),format.raw/*13.103*/("""" />

    <script src=""""),_display_(/*15.19*/routes/*15.25*/.Assets.at("javascripts/jquery.min.js")),format.raw/*15.64*/(""""> language="javascript" type="text/javascript"></script>
    <script src=""""),_display_(/*16.19*/routes/*16.25*/.Assets.at("javascripts/bootstrap.min.js")),format.raw/*16.67*/(""""> language="javascript" type="text/javascript"></script>

</head>
<body>

<!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Project Nominees</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/">Sign Up</a>
                    </li>
                    <li>
                        <a href="/addNominee">Add Nominees</a>
                    </li>
                    <li>
                        <a href="/listNominees/0">List Nominees</a>
                    </li>
                    <li>
                        <a href="/relationship">Relationship</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>


<div class="container">

<div class="page-header">
    <h2></h2>
</div>

<!-- Registration form - START -->
<div class="container">
    <div class="row">
        """),_display_(/*66.10*/helper/*66.16*/.form(action = routes.Application.signUp(), 'id -> "UserIndexForm")/*66.83*/ {_display_(Seq[Any](format.raw/*66.85*/("""
            """),format.raw/*67.13*/("""<div class="col-lg-6">
                <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Registration Form</strong></div>
                <div class="form-group">
                    <label for="InputName">Enter Name</label>
                    <div class="input-group">
                        <input type="text" class="form-control" name="name" id="InputName" placeholder="Enter Name" required> 
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="InputEmail">Enter Email</label>
                    <div class="input-group">
                        <input type="email" class="form-control" id="InputEmailFirst" name="emailId" placeholder="Enter Email" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="InputEmail">Confirm Email</label>
                    <div class="input-group">
                        <input type="email" class="form-control" id="InputEmailSecond" name="InputEmail" placeholder="Confirm Email" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="bfh-selectbox">
                    <label for="PatientGender">GENDER: </label>
                    <select class="form-control" name="gender">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                        <option value="other">Other</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="InputEmail">Phone Number</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="InputPhoneId" name="mobileNumber" placeholder="Phone number" required>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                
                <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info pull-left">
              <!--  <input type="submit" name="submit" onclick=""""),_display_(/*107.66*/routes/*107.72*/.Application.addNominee()),format.raw/*107.97*/("""" id="submit" value="Skip" class="btn btn-info pull-right">  -->
            </div>
        """)))}),format.raw/*109.10*/("""
    """),format.raw/*110.5*/("""</div>
</div>
<!-- Registration form - END -->

</div>

</body>
</html>









""")))}),format.raw/*127.2*/("""
"""))}
  }

  def render(message:String,myForm:Form[User]): play.twirl.api.HtmlFormat.Appendable = apply(message,myForm)

  def f:((String,Form[User]) => play.twirl.api.HtmlFormat.Appendable) = (message,myForm) => apply(message,myForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 07 11:13:24 IST 2015
                  SOURCE: /home/ranjan/playWorkspace/endProduct/app/views/index.scala.html
                  HASH: 297627803632ff5486aff85a675d211a1f1cb824
                  MATRIX: 734->1|859->38|887->41|920->66|959->68|987->70|1237->293|1252->299|1316->342|1396->395|1411->401|1479->447|1530->471|1545->477|1605->516|1708->592|1723->598|1786->640|3553->2380|3568->2386|3644->2453|3684->2455|3725->2468|6316->5031|6332->5037|6379->5062|6504->5155|6537->5160|6650->5242
                  LINES: 26->1|29->1|31->3|31->3|31->3|33->5|40->12|40->12|40->12|41->13|41->13|41->13|43->15|43->15|43->15|44->16|44->16|44->16|94->66|94->66|94->66|94->66|95->67|135->107|135->107|135->107|137->109|138->110|155->127
                  -- GENERATED --
              */
          