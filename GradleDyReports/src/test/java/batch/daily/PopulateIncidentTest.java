package batch.daily;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.vdi.batch.mds.helper.PopulateIncident;
import com.vdi.configuration.AppConfig;

public class PopulateIncidentTest {
	
	private static final Logger logger = LogManager.getLogger(PopulateIncidentTest.class);
	
	public static void main(String args[]) throws Throwable {
			
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		
		PopulateIncident populate = ctx.getBean(PopulateIncident.class);
		
//		try {
			populate.populate();
//		} catch (Exception e) {
//			throw e;
//		}
		System.out.println("finished...");
		
	}

}
