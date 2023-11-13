import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

interface Algorithm {
    String encrypt(String data);
}

class AESEncryption implements Algorithm {
    @Override
    public String encrypt(String data) {
        //AES encryption logic
         try {
            // Generate AES key
            // ChatGPT
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();

            // Create and initialize the cipher
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Encrypt the data
            byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // Encode the encrypted data to Base64 for better readability
            return "AES-Encrypted: " + Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during AES encryption";
        }
    }
}

class DESEncryption implements Algorithm {
    @Override
    public String encrypt(String data) {
        //DES encryption logic
         try {
            // Generate DES key
            // ChatGPT
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey secretKey = keyGenerator.generateKey();

            // Create and initialize the cipher
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Encrypt the data
            byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // Encode the encrypted data to Base64 for better readability
            return "DES-Encrypted: " + Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during DES encryption";
        }
    }
}

// Concrete Strategy: BlowFish Encryption
class BlowFishEncryption implements Algorithm {
    @Override
    public String encrypt(String data) {
        //BlowFish encryption logic
         try {
            // Generate Blowfish key
            // ChatGPT
            KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();

            // Create and initialize the cipher
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Encrypt the data
            byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // Encode the encrypted data to Base64 for better readability
            return "Blowfish-Encrypted: " + Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during Blowfish encryption";
        }
    }
}

class Context {
    private Algorithm encryptionStrategy;

    public void setEncryptionStrategy(Algorithm encryptionStrategy) {
        this.encryptionStrategy = encryptionStrategy;
    }

    public String encryptData(Object data) {
        if (data instanceof PatientData) {
            setEncryptionStrategy(new AESEncryption());
        } else if (data instanceof PhysicianNotes) {
            setEncryptionStrategy(new DESEncryption());
        } else if (data instanceof AppointmentSchedule) {
            setEncryptionStrategy(new BlowFishEncryption());
        } else {
            throw new IllegalArgumentException("Unsupported data type");
        }

        return encryptionStrategy.encrypt(data.toString());
    }
}

// Data types
class PatientData {
    private String data;

    public PatientData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}

class PhysicianNotes {
    private String notes;

    public PhysicianNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }
}

class AppointmentSchedule {
    private String schedule;

    public AppointmentSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getSchedule() {
        return schedule;
    }
}

// Client Code
public class EncryptionApp {
    public static void main(String[] args) {
        Context context = new Context();
        //AES
        PatientData patientData = new PatientData("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "+ 
        "labore et dolore magna aliqua. Aliquam malesuada bibendum arcu vitae elementum "+ 
        "curabitur vitae. Semper viverra nam libero justo. Sit amet nulla facilisi morbi tempus iaculis "+ 
        "urna id. Aliquam ut porttitor leo a diam sollicitudin tempor. Aliquam eleifend mi in nulla posuere sollicitudin aliquam ultrices. "+
        "Sed vulputate mi sit amet mauris commodo quis. Nullam vehicula ipsum a arcu cursus vitae congue. Tortor consequat id porta nibh. Nullam non nisi est sit amet "+ 
        "facilisis magna. Sodales neque sodales ut etiam sit amet nisl. Elementum curabitur vitae nunc sed velit dignissim sodales ut.\n" + //
                "\n" + //
                "");
        String encryptedPatientData = context.encryptData(patientData);
        System.out.println(encryptedPatientData);

        //DES
        PhysicianNotes physicianNotes = new PhysicianNotes("Eget mauris pharetra et ultrices neque ornare aenean euismod. Morbi tristique "+
        "senectus et netus et malesuada fames. Lectus quam id leo in. Iaculis nunc sed augue lacus viverra vitae congue eu. Vulputate mi sit amet "+
        "mauris commodo. Est ultricies integer quis auctor. Arcu dui vivamus arcu felis. Augue mauris augue neque gravida in fermentum et "+
        "sollicitudin ac. Arcu dui vivamus arcu felis bibendum ut tristique et egestas. Vehicula ipsum a arcu cursus vitae congue mauris rhoncus aenean. "+
        "Dignissim enim sit amet venenatis. Pellentesque dignissim enim sit amet venenatis urna cursus eget. Turpis tincidunt id aliquet risus feugiat in.");
        String encryptedPhysicianNotes = context.encryptData(physicianNotes);
        System.out.println(encryptedPhysicianNotes);

         //BlowFish
        AppointmentSchedule appointmentSchedule = new AppointmentSchedule("Morbi non arcu risus quis varius. Rhoncus mattis rhoncus urna neque viverra "+
        "justo nec. In nulla posuere sollicitudin aliquam. Nunc consequat interdum varius sit amet mattis. Sed cras ornare arcu dui vivamus arcu felis. "+
        "Amet volutpat consequat mauris nunc congue nisi vitae suscipit tellus. Mauris nunc congue nisi vitae suscipit tellus mauris a. Turpis in eu mi "+
        "bibendum neque egestas congue quisque egestas. Libero id faucibus nisl tincidunt eget nullam non nisi est. Sit amet nulla facilisi morbi tempus "+
        "iaculis urna id. Diam vel quam elementum pulvinar etiam non quam lacus suspendisse.");
        String encryptedAppointmentSchedule = context.encryptData(appointmentSchedule);
        System.out.println(encryptedAppointmentSchedule);
    }
}
