package juno.application;

public class ColoredString
{
    private String plainText;
    private String coloredText;

    public ColoredString(String text, String colorCode)
    {
        this.plainText = text;
        this.coloredText = colorCode + text + ConsoleColors.RESET; // Reset code
    }

    public String getPlainText()
    {
        return plainText;
    }

    public String getColoredText()
    {
        return coloredText;
    }

    // Example method: Get length of plain text
    public int length()
    {
        return plainText.length();
    }

    // Example method: Get a substring of the plain text
    public String substring(int beginIndex, int endIndex)
    {
        return plainText.substring(beginIndex, endIndex);
    }
}