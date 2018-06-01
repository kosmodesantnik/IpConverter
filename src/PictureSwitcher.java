import javax.swing.*;
import java.io.File;
import java.util.Random;

/**
 * Created by administrator on 16.03.2018.
 */
public class PictureSwitcher {
    public static File file = null;
    Random rand = new Random();

    public PictureSwitcher() {

    }

    public File check(){
        if(file == null){file=new File("src/showes");}
        File[] fileDir = file.listFiles();
        File res = fileDir[rand.nextInt(fileDir.length)];
        return res;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
