import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;


/**
 *  serverController.
 */
public class serverController {

	/**
	 * Server main - creates server, declares handler, adds servlets and sets handler - starts and joins server
	 *
	 * @throws Exception 
	 */
	public  void serverMain() throws Exception{
		Server server = new Server(8005); //listen on port 8005
		//setup handler
		WebAppContext ctx = new WebAppContext();
		ctx.setResourceBase("webapp");
		ctx.setContextPath("/vehicles");
		ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",  ".*/[^/]*jstl.*\\.jar$");
		ClassList classlist = ClassList.setServerDefault(server);
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");

		//mappings
		ctx.addServlet("servlets.ServletApi","/servletApi");
		ctx.addServlet("servlets.GenerateAPIKeyServlet","/generateAPI");
		ctx.addServlet("servlets.RetrieveAPIKeyServlet","/retrieveAPI");

		ctx.addServlet("servlets.ServletHome","/home");
		ctx.addServlet("servlets.ServletAdminHome","/adminIndex");
		ctx.addServlet("servlets.DeleteVehicleServlet","/delete");
		ctx.addServlet("servlets.UpdateVehicleServlet","/update");
		ctx.addServlet("servlets.AddNewVehicleServlet","/addnew");
		ctx.addServlet("servlets.LoginServlet","/login");
		ctx.addServlet("servlets.ServletSalesHome","/salesindex");
		ctx.addServlet("servlets.AddNewSaleServlet","/addnewsale");
		ctx.addServlet("servlets.DeleteSaleServlet","/deletesale");
		ctx.addServlet("servlets.UpdateSaleServlet","/updatesale");
		ctx.addServlet("servlets.SearchVehicleServlet","/search");
		ctx.addServlet("servlets.ForgotPasswordServlet","/forgotpassword");
		ctx.addServlet("servlets.ChangePasswordServlet","/changepassword");

		//setting handler and starting the server
		server.setHandler(ctx);
		server.start();
		server.join();
	}
}
