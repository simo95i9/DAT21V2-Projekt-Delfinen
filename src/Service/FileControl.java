/*
 * @author Mark "Massive Legend" Larsen
 * 19/05/2021 11.24
 *
 * DAT21V2-Projekt-Delfinen
 *
 */

package Service;

import java.io.*;

public class FileControl {
    public static <T extends Serializable> void writeSerializableToFile(T serializableObject, String filename){
        try {
            //WRITE FILE
            FileOutputStream write = new FileOutputStream(new File(filename + ".ser"));
            ObjectOutputStream o = new ObjectOutputStream(write);

            try {
                //WRITE & PRINT LOADED
                o.writeObject(serializableObject);
                System.out.println("Serializable added to file:");
                System.out.println(serializableObject);
            } finally {
                //CLEANUP
                o.close();
                write.close();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static <T extends Serializable> T readSerializableFromFile(String filename, T defaultReturn) {
        try {
            //READ FILE
            FileInputStream f = new FileInputStream(new File(filename + ".ser"));
            ObjectInputStream in = new ObjectInputStream(f);
            T serializableObject = defaultReturn;

            try {
                //READ AND PASTE OBJECT
                serializableObject = (T) in.readObject();
                System.out.println("Serializable loaded from file:");
                System.out.println(serializableObject);
            } finally {
                //CLEAN UP
                f.close();
                in.close();
            }

            return serializableObject;

        } catch(IOException | ClassCastException | ClassNotFoundException e) {
            e.printStackTrace();
            return defaultReturn;
        }
    }
}
