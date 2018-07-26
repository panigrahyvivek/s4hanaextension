package com.s4hanaextension.dataadjustments.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.s4hanaextension.dataadjustments.models.FileRecord;
import com.sap.cloud.sdk.odatav2.connectivity.ODataCreateRequestBuilder;
import com.sap.cloud.sdk.odatav2.connectivity.ODataCreateResult;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryBuilder;
import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;


@RestController
@RequestMapping("/s4connectodata")
public class s4connectorodata {
	
	private static Logger log = LoggerFactory.getLogger(s4connectorodata.class);
	
	
	/*public s4connectorodata(){
		String destinationName = "glob-s4fin-sandbox_basic";
		configContext = new ErpConfigContext(destinationName); 
	}*/
	
	/*@GetMapping
	public Object checkODATAConnection() throws ODataException{
		private final ErpConfigContext configContext;
		String destinationName = "glob-s4fin-sandbox_basic";
		configContext = new ErpConfigContext(destinationName); 
		
		final List<Object> fileRecords =
				ODataQueryBuilder.withEntity("/sap/opu/odata/sap/ZFXX_FLATFILE_TO_ACDOCA_UPLOAD_SRV", "ZFXX_FLATFILE_TO_ACDOCASet('filepath')")
						.select("IFilepath")
						.build()
                        .execute(configContext)
                        .asList(Object.class);
		return fileRecords;
		
	}*/
	
	@PostMapping
	@RequestMapping(path="triggerFlatFlieWorkflow")
	public Map<String, Object> triggerFlatFileWorkflow(@RequestBody Map<String, Object> request) throws ODataException{
		
		
		String destinationName = "glob-s4fin-sandbox_basic";
		final ErpConfigContext configContext = new ErpConfigContext(destinationName); 
		
		Map<String, Object> body = new HashMap<>();
		body.put("IFilePath", request.get("IFilePath"));
		body.put("IRestId", request.get("IRestId"));
		
		

		ODataCreateResult result = ODataCreateRequestBuilder.withEntity("/sap/opu/odata/sap/ZFXX_SERVER_TO_ACDOCA_SRV", "FILEPATHSet")
						.withBodyAsMap(body)
						.build()
                        .execute(configContext);
         
		return result.asMap();
		
	}
	
	
	@PostMapping
	@RequestMapping(path="triggerRestatementsWorkflow")
	public Map<String, Object> triggerRestatementsWorkflow(@RequestBody Map<String, Object> request) throws ODataException{
		
		
		String destinationName = "glob-s4fin-sandbox_basic";
		final ErpConfigContext configContext = new ErpConfigContext(destinationName); 
		
		Map<String, Object> body = new HashMap<>();	
		body.put("BusId", request.get("BusId"));
		body.put("RestId", request.get("RestId"));
		body.put("SourceGeo", request.get("SourceGeo"));
		body.put("RuleId", request.get("RuleId"));
		
		/*FileRecord fr = new FileRecord(request.get("IFilepath").toString(), request.get("Type").toString());*/
		

		ODataCreateResult result = ODataCreateRequestBuilder.withEntity("/sap/opu/odata/sap/ZFXX_BWRES_CALL_AIF_SRV", "IMPORTSet")
						.withBodyAsMap(body)
						.build()
                        .execute(configContext);
         
		return result.asMap();
		
	}
}
