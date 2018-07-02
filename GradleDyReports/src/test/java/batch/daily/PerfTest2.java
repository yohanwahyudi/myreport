package batch.daily;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vdi.batch.mds.helper.weekly.PopulatePerformance;
import com.vdi.configuration.AppConfig;

public class PerfTest2 {
	
	private final static Logger logger = LogManager.getLogger(PerfTest2.class.getName());
	
	public static void main (String args[]) {
		
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		PopulatePerformance populatePerf = ctx.getBean(PopulatePerformance.class);
		logger.debug("begin");
		populatePerf.populatePerformance();
		
	}

}
