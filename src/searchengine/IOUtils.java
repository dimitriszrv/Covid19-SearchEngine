package searchengine;

import java.io.File;

public class IOUtils
{
   public static void makeSureDirectoriesExist(String baseDir)
   {
      File dir = new File(baseDir);
      if (!dir.exists())
      {
         dir.mkdirs();
      }
   }
}
