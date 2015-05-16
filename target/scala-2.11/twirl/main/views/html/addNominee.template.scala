
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
object addNominee extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,List[User],Form[UserNominee],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String, allUsers: List[User], nomineeForm: Form[UserNominee]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.73*/("""

"""),_display_(/*3.2*/main("Add Nominees")/*3.22*/ {_display_(Seq[Any](format.raw/*3.24*/("""


"""),format.raw/*6.1*/("""<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Add Nominee</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" media="screen" href=""""),_display_(/*13.50*/routes/*13.56*/.Assets.at("stylesheets/bootstrap.min.css")),format.raw/*13.99*/("""">
    <link rel="stylesheet" type="text/css" href=""""),_display_(/*14.51*/routes/*14.57*/.Assets.at("stylesheets/font-awesome.min.css")),format.raw/*14.103*/("""" />

    <script src=""""),_display_(/*16.19*/routes/*16.25*/.Assets.at("javascripts/jquery.min.js")),format.raw/*16.64*/(""""> language="javascript" type="text/javascript"></script>
    <script src=""""),_display_(/*17.19*/routes/*17.25*/.Assets.at("javascripts/bootstrap.min.js")),format.raw/*17.67*/(""""> language="javascript" type="text/javascript"></script>
    <script src=""""),_display_(/*18.19*/routes/*18.25*/.Assets.at("javascripts/add-nominee-related.js")),format.raw/*18.73*/(""""> language="javascript" type="text/javascript"></script>
 
 
    <style>
    
   td """),format.raw/*23.7*/("""{"""),format.raw/*23.8*/("""border: 1px #DDD solid; padding: 5px; cursor: pointer;"""),format.raw/*23.62*/("""}"""),format.raw/*23.63*/("""

"""),format.raw/*25.1*/(""".selected """),format.raw/*25.11*/("""{"""),format.raw/*25.12*/("""
    """),format.raw/*26.5*/("""background-color: brown;
    color: #FFF;
"""),format.raw/*28.1*/("""}"""),format.raw/*28.2*/("""
    
    
    """),format.raw/*31.5*/("""</style>

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
                        <a href="#">Add Nominees</a>
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
    
<div class="container">

    <div class="page-header">
        <h2></h2>
    </div>
 
    
    """),_display_(/*75.6*/helper/*75.12*/.form(action = routes.Application.addNomineePost(), 'id -> "UserNomineeForm")/*75.89*/ {_display_(Seq[Any](format.raw/*75.91*/("""
    """),format.raw/*76.5*/("""<!-- User select box -->
    <div class="col-lg-6">

     <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>ADD NOMINEES</strong></div>    
            
        <div class="bfh-selectbox">
            <label for="users">Select Users: </label>
            <select class="form-control" name="addNomineeUserSelect">
                <option value="default">Default</option>
                
             """),_display_(/*86.15*/for(data <- allUsers) yield /*86.36*/  {_display_(Seq[Any](format.raw/*86.39*/("""
                
                    """),format.raw/*88.21*/("""<option value = """),_display_(/*88.38*/data/*88.42*/.getId()),format.raw/*88.50*/(""">"""),_display_(/*88.52*/data/*88.56*/.getName()),format.raw/*88.66*/("""</option> 
                    
                """)))}),format.raw/*90.18*/("""
            """),format.raw/*91.13*/("""</select>
        </div>
     
       <ol style="visibility:hidden;"></ol>
     
                <div class="form-group">
                    <label for="InputName">Enter Nominee Name</label>
                    <div class="input-group">
                        <input type="text" class="form-control" name="nomineeName" id="InputName" placeholder="Enter Name" > 
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="InputEmail">Enter Nominee Phone</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="InputNomineePhone" name="nomineeContactNumber" placeholder="Enter Phone" >
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div> 
    
    <div class="page-header">
        <h2>Select Users as nominees</h2>
    </div>
    
    <!--  Table to select users as nominees -->
    
   <table class="table table-bordered" value= "" id="userSelectTable"> 
    <thead>
      <tr>
       <th><strong>Id</strong></th>
       <th><strong>UserName</strong></th>
       <th><strong>Email Id</strong></th>       
       <th><strong>Phone</strong></th>
      </tr>
    </thead>
    
    <tbody>
   
      """),_display_(/*129.8*/for(data <- allUsers) yield /*129.29*/  {_display_(Seq[Any](format.raw/*129.32*/("""
          """),format.raw/*130.11*/("""<tr>
            <td>"""),_display_(/*131.18*/data/*131.22*/.getId()),format.raw/*131.30*/("""</td>
            <td>"""),_display_(/*132.18*/data/*132.22*/.getName()),format.raw/*132.32*/("""</td>
            <td>"""),_display_(/*133.18*/data/*133.22*/.getEmailId()),format.raw/*133.35*/("""</td>
            <td>"""),_display_(/*134.18*/data/*134.22*/.getMobileNumber()),format.raw/*134.40*/("""</td>
           </tr>
       
       """)))}),format.raw/*137.9*/(""" 
       
       """),format.raw/*139.8*/("""<input type="hidden" name="userNomineeTable" id= "table" value="" />
       
     </tbody>
</table>
<input type="submit" name="submit" id="submit" value="Add Nominee" class="btn btn-info pull-left">
</div> 

""")))}),format.raw/*146.2*/("""  """),format.raw/*146.20*/("""


"""),format.raw/*149.1*/("""</div>  <!-- End container -->

<script src=""""),_display_(/*151.15*/routes/*151.21*/.Assets.at("javascripts/jquery.min.js")),format.raw/*151.60*/(""""> language="javascript" type="text/javascript"></script>
    <script src=""""),_display_(/*152.19*/routes/*152.25*/.Assets.at("javascripts/bootstrap.min.js")),format.raw/*152.67*/(""""> language="javascript" type="text/javascript"></script>
    <script src=""""),_display_(/*153.19*/routes/*153.25*/.Assets.at("javascripts/add-nominee-related.js")),format.raw/*153.73*/(""""> language="javascript" type="text/javascript"></script>



</body>





</html>  

"""),format.raw/*171.6*/("""

""")))}),format.raw/*173.2*/("""
"""))}
  }

  def render(message:String,allUsers:List[User],nomineeForm:Form[UserNominee]): play.twirl.api.HtmlFormat.Appendable = apply(message,allUsers,nomineeForm)

  def f:((String,List[User],Form[UserNominee]) => play.twirl.api.HtmlFormat.Appendable) = (message,allUsers,nomineeForm) => apply(message,allUsers,nomineeForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 07 11:35:19 IST 2015
                  SOURCE: /home/ranjan/playWorkspace/endProduct/app/views/addNominee.scala.html
                  HASH: 8b897f42c57d7945692f7bc006e48d2ee019bd9a
                  MATRIX: 757->1|916->72|944->75|972->95|1011->97|1040->100|1284->317|1299->323|1363->366|1443->419|1458->425|1526->471|1577->495|1592->501|1652->540|1755->616|1770->622|1833->664|1936->740|1951->746|2020->794|2132->879|2160->880|2242->934|2271->935|2300->937|2338->947|2367->948|2399->953|2468->995|2496->996|2538->1011|4051->2498|4066->2504|4152->2581|4192->2583|4224->2588|4686->3023|4723->3044|4764->3047|4830->3085|4874->3102|4887->3106|4916->3114|4945->3116|4958->3120|4989->3130|5069->3179|5110->3192|6592->4647|6630->4668|6672->4671|6712->4682|6762->4704|6776->4708|6806->4716|6857->4739|6871->4743|6903->4753|6954->4776|6968->4780|7003->4793|7054->4816|7068->4820|7108->4838|7178->4877|7223->4894|7463->5103|7494->5121|7525->5124|7599->5170|7615->5176|7676->5215|7780->5291|7796->5297|7860->5339|7964->5415|7980->5421|8050->5469|8163->5666|8197->5669
                  LINES: 26->1|29->1|31->3|31->3|31->3|34->6|41->13|41->13|41->13|42->14|42->14|42->14|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|51->23|51->23|51->23|51->23|53->25|53->25|53->25|54->26|56->28|56->28|59->31|103->75|103->75|103->75|103->75|104->76|114->86|114->86|114->86|116->88|116->88|116->88|116->88|116->88|116->88|116->88|118->90|119->91|157->129|157->129|157->129|158->130|159->131|159->131|159->131|160->132|160->132|160->132|161->133|161->133|161->133|162->134|162->134|162->134|165->137|167->139|174->146|174->146|177->149|179->151|179->151|179->151|180->152|180->152|180->152|181->153|181->153|181->153|193->171|195->173
                  -- GENERATED --
              */
          