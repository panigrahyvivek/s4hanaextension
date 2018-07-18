package com.s4hanaextension.dataadjustments.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s4hanaextension.dataadjustments.models.Response;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRepository;

@RestController
@RequestMapping("/s4connect")
public class s4connector {
	
	//private static Logger log = LoggerFactory.getLogger(HelloWorldController.class);

	@GetMapping
	public Response checkRFCConnection(){
		try
        {
			// access the RFC Destination "JCoDemoSystem"
            JCoDestination destination=JCoDestinationManager.getDestination("JE2");

            // make an invocation of STFC_CONNECTION in the backend;
            JCoRepository repo=destination.getRepository();
            JCoFunction stfcConnection=repo.getFunction("Z_CALL_WORKFLOW");
			
			JCoParameterList imports=stfcConnection.getImportParameterList();
            
            imports.setValue("P_REQUEST_ID", "");
            imports.setValue("P_TASK_ID", "");
            imports.setValue("P_BUKRS", "PSM1");
            imports.setValue("P_BELNR", 11315);
            imports.setValue("P_GJAHR", 2018);
            imports.setValue("P_ACTION", "RECLA");
            imports.setValue("G_ERKRS", "");
            imports.setValue("G_CHGRUN", 0);
            imports.setValue("G_TEST", "");
            imports.setValue("G_PKGSIZ", 0);
            imports.setValue("G_MAXMEM", 0);
            
            stfcConnection.execute(destination);
            JCoParameterList exports=stfcConnection.getExportParameterList();
            String echotext=exports.getString("P_OK");
            
            Response response = new Response(echotext);
            
            return response;
            
        }
        catch (AbapException ae)
        {
            //just for completeness: As this function module does not have an exception
            //in its signature, this exception cannot occur. However,you should always
            //take care of AbapExceptions
        	return new Response("AbapException");
        }
        catch (JCoException e)
        {
            /*response.addHeader("Content-type", "text/html");
            responseWriter.println("<html><body>");
            responseWriter.println("<h1>Exception occurred while executing ZFXX_FLATFILE_TO_ACDOCA in system HGE</h1>");
            responseWriter.println("<pre>");
            e.printStackTrace(responseWriter);
            responseWriter.println("</pre>");
            responseWriter.println("</body></html>");*/
        	
        	return new Response("JcoException");
        }

	}
}
