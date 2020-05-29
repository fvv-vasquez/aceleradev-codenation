package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        return this.handleCaesarCipher(texto, true);
    }

    @Override
    public String descriptografar(String texto) {
        return this.handleCaesarCipher(texto, false);
    }

    private String handleCaesarCipher(String text, final boolean isEncrypt) {        
        final StringBuffer result = new StringBuffer();
        if (text == null ) {
            throw new NullPointerException("Text must not be null. Please provide a valid value");
        } else if (text.equals("")) {
            throw new IllegalArgumentException("Text must not be empty. Please provide a valid value");
        } else {
            text = text.toLowerCase();
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) >= 97 && text.charAt(i) <= 122) {
                    final char ch;
                    if (isEncrypt) {
                        ch = (char)(((int)text.charAt(i) + 3 - 97) % 26 + 97);
                    } else {
                        ch = (char)(((int)text.charAt(i) - 3 - 97) % 26 + 97);
                    }                    
                    result.append(ch);
                } else {
                    result.append(text.charAt(i));
                }
            }
            return result.toString();
        } 
    }
}
