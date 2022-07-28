package mx.gob.sedatu.dgdusv.sniiv.utilities;

import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class Utils {
	
	public String getFileNameUpload(String prefix){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd_HHmmss");
        fmt.setCalendar(calendar);
        return prefix + "_" + fmt.format(calendar.getTime());
    }
	
	public String getTimestamp() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd_HHmmss");
        fmt.setCalendar(calendar);
        return fmt.format(calendar.getTime());
    }

   public boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    public boolean checkFolder(File folder){
        if(!folder.exists()) {
            if(!folder.mkdir()) {
                return false;
            }
        }
        return true;
    }


}
