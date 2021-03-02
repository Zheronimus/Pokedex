import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class Sprite {

    private enum Unicode {

        COLON("\u003A"),
        FEMALE_SYMBOL("\u2640"),
        MALE_SYMBOL("\u2642"),
        PERIOD("\u002E"),
        SPACE("\u0020");

        private final String unicode;

        Unicode(String unicode) {
            this.unicode = unicode;
        }

        @Override
        public String toString() {
            return unicode;
        }
    }


    public static FileInputStream getImgFile(String name, String folderName) {

        try {
            File imagesFolder = new File("Resources/" + folderName);
            String[] imagePath = imagesFolder.list();
            FileInputStream imgFile = null;

            name = name.replaceAll(Unicode.SPACE.toString(), "").replace(Unicode.PERIOD.toString(), "").replace(Unicode.COLON.toString(), "").
                    replace(Unicode.MALE_SYMBOL.toString(), "M").replace(Unicode.FEMALE_SYMBOL.toString(), "F");

            for(String fileName : imagePath) {
                if(name.equalsIgnoreCase(fileName.substring(0, fileName.lastIndexOf('.')))) {
                    imgFile = new FileInputStream("Resources/" + folderName + "/" + fileName);

                    break;
                }
            }

            return imgFile;
        }

        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());

            return null;
        }
    }
}