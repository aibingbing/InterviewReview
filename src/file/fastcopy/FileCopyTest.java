package file.fastcopy;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class FileCopyTest {

    public static void main(String[] args) throws IOException {
        File source = new File("/Users/aibb/Downloads/自驾中国P1.mp4");
        File dest = new File("/Users/aibb/Downloads/自驾中国P1_copy.mp4");
        if (dest.exists()) {
            dest.delete();
        }
        useFileStream(source, dest);
        dest.delete();
        useFileChannel(source, dest);
        dest.delete();
        useJdk7Api(source, dest);
    }

    private static void useFileStream(File source, File dest) throws IOException {
        long start = System.nanoTime();
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
        long end = System.nanoTime();
        System.out.println("Time taken by FileStreams Copy = " + (end - start) / 1000000 / 1000);
    }

    private static void useFileChannel(File source, File dest) throws IOException {
        long start = System.nanoTime();
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
        long end = System.nanoTime();
        System.out.println("Time taken by FileChannels Copy = " + (end - start) / 1000000 / 1000);
    }

    private static void useJdk7Api(File source, File dest) throws IOException {
        long start = System.nanoTime();
        Files.copy(source.toPath(), dest.toPath());
        long end = System.nanoTime();
        System.out.println("Time taken by FileChannels Copy = " + (end - start) / 1000000 / 1000);
    }
}
