package aspectj;

import java.util.HashMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Logging{
	private static final HashMap<Object, StackTraceElement[]> logStoringStruct = new HashMap<Object, StackTraceElement[]>();
	private static final HashMap<Object, String> printErrorStatement = new HashMap<Object, String>();

	private static final Logging logger = new Logging();
	
	private Logging(){}

	public static Logging getInstance(){
		return logger;
	}

	public void formatter(Logger logger){
	    logger.setUseParentHandlers(false);
	    Handler conHdlr = new ConsoleHandler();
	    conHdlr.setFormatter(new Formatter() {
	      public String format(LogRecord record) {
	        return "\n" + record.getLevel() + "-:- "
	            + record.getMessage() + "\n";
	      }
	    });
	    logger.addHandler(conHdlr);
	}

	public void printLog(String error, Object arg, StackTraceElement[] stacktrace, String className) {
		final Logger logger = Logger.getLogger(className); 
		formatter(logger);
		//StringBuilder stackTraceArray = buildStackTraceString(stacktrace);
	    logger.warning("*******************************************\n" + error + "\n\nHere's the StackTrace...\n");
	    // for (int i=0; i <stackTraceArray.length; i++) {
	    // 	logger.info("\tat " + i +"\n");
	    // }
	    for (StackTraceElement element : stacktrace) {
    		logger.info("\tat " + element.toString() +"\n");
		}
	                    
	 }

	// private static String buildStackTraceString(final StackTraceElement[] elements) {
	//     StringBuilder sb = new StringBuilder();
	//     if (elements != null && elements.length > 0) {
	// 	for (StackTraceElement element : elements) {
	// 	    sb.append(element.toString());
	// 	}
	//     }
	//     return sb.toString();
	// }

	public void insertToHashMap(StackTraceElement[] stacktrace, Object arg[], int comparingParameter){
		if(logStoringStruct == null){
        logStoringStruct.put(arg[comparingParameter], stacktrace);
	    }else if(!logStoringStruct.containsKey(arg[comparingParameter])){
	        logStoringStruct.put(arg[comparingParameter], stacktrace);
	    }
	}

	public void insertToHashMap(StackTraceElement[] stacktrace, Object arg){
		if(logStoringStruct == null){
        logStoringStruct.put(arg, stacktrace);
	    }else if(!logStoringStruct.containsKey(arg)){
	        logStoringStruct.put(arg, stacktrace);
	    }
	}

	public void insertToErrorHashMap(Object arg[], int comparingParameter, String error){
		if(printErrorStatement == null){
        printErrorStatement.put(arg[comparingParameter], error);
	    }else if(!printErrorStatement.containsKey(arg[comparingParameter])){
	        printErrorStatement.put(arg[comparingParameter], error);
	    }
	}

	public void insertToErrorHashMap(Object arg, String error){
		if(printErrorStatement == null){
        printErrorStatement.put(arg, error);
	    }else if(!printErrorStatement.containsKey(arg)){
	        printErrorStatement.put(arg, error);
	    }
	}

	public void removeFromHashMap(Object arg[], int comparingParameter){
		if (logStoringStruct.containsKey(arg[comparingParameter])){
        	logStoringStruct.remove(arg[comparingParameter]);
    	}
	}

	public void removeFromHashMap(Object arg){
		if (logStoringStruct.containsKey(arg)){
        	logStoringStruct.remove(arg);
    	}
	}

	public void printingLog(String className){
		final Logger logger = Logger.getLogger(className); 
		formatter(logger);
	 if (!logStoringStruct.isEmpty()){
	    //logger.warning("Violating best practice. ");
	    for(Object listener : logStoringStruct.keySet()){
	        StackTraceElement[] element = logStoringStruct.get(listener);
	        logger.warning(printErrorStatement.get(listener));
	        for(int i = 3; i<element.length; i++){
	            logger.info("\tat " + element[i]+"\n");
	        }
	    }                
	 }else{
	    logger.info("Followed all the best practices.");
	 }
	 //Remember emptying both the HashMaps.
	 logStoringStruct.clear();
	 //printErrorStatement.clear();
	}
}

