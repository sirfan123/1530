import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

interface Algorithm {
    String encrypt(String data);
}

// Concrete Strategy: AES Encryption
class AESEncryption implements Algorithm {
    @Override
    public String encrypt(String data) {
        // Implement AES encryption logic
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

// Concrete Strategy: DES Encryption
class DESEncryption implements Algorithm {
    @Override
    public String encrypt(String data) {
        // Implement DES encryption logic
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
        // Implement BlowFish encryption logic
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

// Context
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

        // Encrypt patient data with AES
        PatientData patientData = new PatientData("patient data");
        String encryptedPatientData = context.encryptData(patientData);
        System.out.println(encryptedPatientData);

        // Encrypt physician notes with DES
        PhysicianNotes physicianNotes = new PhysicianNotes("physician notes");
        String encryptedPhysicianNotes = context.encryptData(physicianNotes);
        System.out.println(encryptedPhysicianNotes);

        // Encrypt appointment schedule with BlowFish
        AppointmentSchedule appointmentSchedule = new AppointmentSchedule("appointment schedule");
        String encryptedAppointmentSchedule = context.encryptData(appointmentSchedule);
        System.out.println(encryptedAppointmentSchedule);
    }
}
