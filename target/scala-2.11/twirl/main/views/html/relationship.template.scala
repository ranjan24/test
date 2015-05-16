
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
object relationship extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[String,List[User],Map[String, String],Map[String, String],List[Int],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String, allUsers: List[User], userExceptNominee: Map[String, String], nomineeExceptUsers: Map[String, String], relation: List[Int] ):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.144*/("""

"""),_display_(/*3.2*/main(message)/*3.15*/ {_display_(Seq[Any](format.raw/*3.17*/("""


"""),format.raw/*6.1*/("""<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Registration form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" media="screen" href=""""),_display_(/*13.50*/routes/*13.56*/.Assets.at("stylesheets/bootstrap.min.css")),format.raw/*13.99*/("""">
    <link rel="stylesheet" type="text/css" href=""""),_display_(/*14.51*/routes/*14.57*/.Assets.at("stylesheets/font-awesome.min.css")),format.raw/*14.103*/("""" />

    <script src=""""),_display_(/*16.19*/routes/*16.25*/.Assets.at("javascripts/jquery.min.js")),format.raw/*16.64*/(""""> language="javascript" type="text/javascript"></script>
    <script src=""""),_display_(/*17.19*/routes/*17.25*/.Assets.at("javascripts/bootstrap.min.js")),format.raw/*17.67*/(""""> language="javascript" type="text/javascript"></script>
    <script src=""""),_display_(/*18.19*/routes/*18.25*/.Assets.at("javascripts/add-nominee-related.js")),format.raw/*18.73*/(""""> language="javascript" type="text/javascript"></script>
 

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
                        <a href="#">List Nominees</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
<body>    

<div class="container">

    <div class="page-header">
        <h2></h2>
    </div>
    
    
    
    """),_display_(/*65.6*/helper/*65.12*/.form(action = routes.Application.asyncRelationship(), 'id -> "relationshipSubmit")/*65.95*/ {_display_(Seq[Any](format.raw/*65.97*/("""
    
    """),format.raw/*67.5*/("""<div class= "col-md-8" id="formBegin">  
            
        <div class="bfh-selectbox">
            <label for="users">Select Source: </label>
            <select class="form-control" name="source">
                <option value="default">Default</option>
                
               """),_display_(/*74.17*/for(data <- allUsers) yield /*74.38*/  {_display_(Seq[Any](format.raw/*74.41*/("""
                
                    """),format.raw/*76.21*/("""<option value = """),_display_(/*76.38*/data/*76.42*/.getId().toString().concat("U")),format.raw/*76.73*/(""" """),format.raw/*76.74*/(""">"""),_display_(/*76.76*/data/*76.80*/.getName()),format.raw/*76.90*/("""</option>
                
                """)))}),format.raw/*78.18*/(""" 
            """),format.raw/*79.13*/("""</select>
        </div>
        <ol style="visibility:hidden;"></ol>
        <div class="bfh-selectbox">
            <label for="users">Select Destination: </label>
            <select class="form-control" name="destination">
                <option value="default">Default</option>
                
                <optgroup label="Users">
                """),_display_(/*88.18*/for((key, value) <- userExceptNominee) yield /*88.56*/ {_display_(Seq[Any](format.raw/*88.58*/("""
                
                    """),format.raw/*90.21*/("""<option value = """),_display_(/*90.38*/key/*90.41*/.concat("U")),format.raw/*90.53*/(""" """),format.raw/*90.54*/(""">"""),_display_(/*90.56*/value),format.raw/*90.61*/("""</option>
                
                """)))}),format.raw/*92.18*/("""
                """),format.raw/*93.17*/("""</optgroup>
                
                <optgroup label="Nominees">
                
                """),_display_(/*97.18*/for((key, value) <- nomineeExceptUsers) yield /*97.57*/ {_display_(Seq[Any](format.raw/*97.59*/("""
                
                    """),format.raw/*99.21*/("""<option value = """),_display_(/*99.38*/key/*99.41*/.concat("N")),format.raw/*99.53*/(""" """),format.raw/*99.54*/(""">"""),_display_(/*99.56*/value),format.raw/*99.61*/("""</option>
                
                """)))}),format.raw/*101.18*/("""
                """),format.raw/*102.17*/("""</optgroup>
            </select>
        </div>
        
        <ol style="visibility:hidden;"></ol>
        <ol style="visibility:hidden;"></ol>
        
        <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info pull-left">
        
        """)))}),format.raw/*111.10*/("""  
        
    """),format.raw/*113.5*/("""</div>
    
    
</div> <!-- End container -->

<ol style="visibility:hidden;"></ol>
<ol style="visibility:hidden;"></ol>
<ol style="visibility:hidden;"></ol>
<ol style="visibility:hidden;"></ol>
    
    <div class="row">
        <div class="col-md-2 col-md-offset-1">
        
        <h2>Shortest path</h2>
    
    
    <h2><font size="3" color="black" class="btn btn-info pull-left">"""),_display_(/*129.70*/relation/*129.78*/.toString()),format.raw/*129.89*/("""</font></h2><br>
        
        
        </div>
    </div>
    

    <script src=""""),_display_(/*136.19*/routes/*136.25*/.Assets.at("javascripts/jquery.min.js")),format.raw/*136.64*/(""""> language="javascript" type="text/javascript"></script>
    <script src=""""),_display_(/*137.19*/routes/*137.25*/.Assets.at("javascripts/bootstrap.min.js")),format.raw/*137.67*/(""""> language="javascript" type="text/javascript"></script>
    <script src=""""),_display_(/*138.19*/routes/*138.25*/.Assets.at("javascripts/relationship.js")),format.raw/*138.66*/(""""> language="javascript" type="text/javascript"></script>
    
</body>

</html>
 
""")))}))}
  }

  def render(message:String,allUsers:List[User],userExceptNominee:Map[String, String],nomineeExceptUsers:Map[String, String],relation:List[Int]): play.twirl.api.HtmlFormat.Appendable = apply(message,allUsers,userExceptNominee,nomineeExceptUsers,relation)

  def f:((String,List[User],Map[String, String],Map[String, String],List[Int]) => play.twirl.api.HtmlFormat.Appendable) = (message,allUsers,userExceptNominee,nomineeExceptUsers,relation) => apply(message,allUsers,userExceptNominee,nomineeExceptUsers,relation)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri May 08 14:27:48 IST 2015
                  SOURCE: /home/ranjan/playWorkspace/endProduct/app/views/relationship.scala.html
                  HASH: 40aaa86c75945ac4134b86fb611afff785c1fbd8
                  MATRIX: 791->1|1022->143|1050->146|1071->159|1110->161|1139->164|1389->387|1404->393|1468->436|1548->489|1563->495|1631->541|1682->565|1697->571|1757->610|1860->686|1875->692|1938->734|2041->810|2056->816|2125->864|3714->2427|3729->2433|3821->2516|3861->2518|3898->2528|4216->2819|4253->2840|4294->2843|4360->2881|4404->2898|4417->2902|4469->2933|4498->2934|4527->2936|4540->2940|4571->2950|4646->2994|4688->3008|5074->3367|5128->3405|5168->3407|5234->3445|5278->3462|5290->3465|5323->3477|5352->3478|5381->3480|5407->3485|5482->3529|5527->3546|5661->3653|5716->3692|5756->3694|5822->3732|5866->3749|5878->3752|5911->3764|5940->3765|5969->3767|5995->3772|6071->3816|6117->3833|6426->4110|6470->4126|6887->4515|6905->4523|6938->4534|7051->4619|7067->4625|7128->4664|7232->4740|7248->4746|7312->4788|7416->4864|7432->4870|7495->4911
                  LINES: 26->1|29->1|31->3|31->3|31->3|34->6|41->13|41->13|41->13|42->14|42->14|42->14|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|93->65|93->65|93->65|93->65|95->67|102->74|102->74|102->74|104->76|104->76|104->76|104->76|104->76|104->76|104->76|104->76|106->78|107->79|116->88|116->88|116->88|118->90|118->90|118->90|118->90|118->90|118->90|118->90|120->92|121->93|125->97|125->97|125->97|127->99|127->99|127->99|127->99|127->99|127->99|127->99|129->101|130->102|139->111|141->113|157->129|157->129|157->129|164->136|164->136|164->136|165->137|165->137|165->137|166->138|166->138|166->138
                  -- GENERATED --
              */
          