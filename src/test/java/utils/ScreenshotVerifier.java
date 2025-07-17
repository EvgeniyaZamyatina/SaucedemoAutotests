package utils;

import com.codeborne.selenide.Selenide;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import io.qameta.allure.Attachment;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotVerifier {
    private static final String REFERENCE_DIR =
            "src\\test\\resources\\referenceScreenshots\\"; // эталонный скриншот
    private static final String CURRENT_DIR = "build\\reports\\tests\\"; // текущий скриншот
    private static final String DIFF_DIR =
            "src\\test\\resources\\diffScreenshots\\"; // скриншот изменений

    // Методы для прикрепления скриншотов в отчет Allure
    @Attachment(value = "Reference Screenshot", type = "image/png")
    public static byte[] attachReference(byte[] bytes) {
        return bytes;
    }

    @Attachment(value = "Current Screenshot", type = "image/png")
    public static byte[] attachCurrent(byte[] bytes) {
        return bytes;
    }

    @Attachment(value = "Diff Screenshot", type = "image/png")
    public static byte[] attachDiff(byte[] bytes) {
        return bytes;
    }

    // Преобразование изображения BufferedImage в массив байт PNG-формата
    private static byte[] imageToBytes(BufferedImage img) throws IOException {
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream()) {
            ImageIO.write(img, "PNG", byteStream);
            return byteStream.toByteArray();
        }
    }

    public static void verifyScreenshot(String name) throws IOException {
        Selenide.screenshot(name);
        String fullPath = CURRENT_DIR + name + ".png";
        System.out.println("Screenshot saved at: " + fullPath);
        BufferedImage currentImage = ImageIO.read(new File(fullPath));
        File referenceFile = new File(REFERENCE_DIR + name + ".png");

        if (!referenceFile.exists()) {
            ImageIO.write(currentImage, "PNG", referenceFile);
            attachReference(Files.readAllBytes(referenceFile.toPath()));
            return;
        }

        BufferedImage referenceImage = ImageIO.read(referenceFile);
        ImageComparison imageComparison = new ImageComparison(referenceFile.getPath(),
                CURRENT_DIR + name + ".png");
        ImageComparisonResult result = imageComparison.compareImages();

        if (result.getImageComparisonState() != ImageComparisonState.MATCH) {
            BufferedImage diffImage = result.getResult();
            File diffFile = new File(DIFF_DIR + name + "_diff.png");
            ImageIO.write(diffImage, "PNG", diffFile);

            attachReference(imageToBytes(referenceImage));
            attachCurrent(imageToBytes(currentImage));
            attachDiff(imageToBytes(diffImage));

            throw new AssertionError("Скриншоты не совпадают! См. diff снимок: " + diffFile.getPath());
        }
    }
}
