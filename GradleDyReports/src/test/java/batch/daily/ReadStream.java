package batch.daily;


import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vdi.batch.mds.repository.dao.StagingServiceDeskDAOService;
import com.vdi.batch.mds.service.ItopMDSDataLoaderService;
import com.vdi.configuration.AppConfig;
import com.vdi.model.staging.StagingServiceDesk;
import com.vdi.tools.IOToolsService;

public class ReadStream {
	
	public static final Logger logger = LogManager.getLogger(ReadStream.class);
	private static final Double bufferSize = (Math.pow(1024, 2));
	
	public static void main(String args[]) throws IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		IOToolsService io = ctx.getBean("ioTools",IOToolsService.class);
		
//		String readLine = io.readUrl("http://172.17.6.21/itop/web/api/Query1_8b09fc98eb98edcff9700ee747064cd6.php");
//		System.out.println("sa: "+readLine.length());
		
//		String read = io.readUrl("http://172.17.6.21/itop/web/api/Query2_796e92b9df3e0fc7b9ee0c37a462cec8.php");
//		System.out.println("sd: "+read.length());
		
		ItopMDSDataLoaderService loader = ctx.getBean("serviceDeskDataLoaderService",ItopMDSDataLoaderService.class);
		List<StagingServiceDesk> stagingList = loader.getStagingAllByURL();
		
		
		StagingServiceDeskDAOService stagingService = ctx.getBean("stagingServiceDeskDAO",StagingServiceDeskDAOService.class);
		stagingService.addAll(stagingList);
		
		logger.debug(""+stagingList.size());
	}
	
	

}
