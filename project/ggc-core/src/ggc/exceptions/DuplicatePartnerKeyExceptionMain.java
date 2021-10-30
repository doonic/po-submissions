package ggc.exceptions;

/**
 * Exception for duplicated partner keys.
 */
public class DuplicatePartnerKeyExceptionMain extends Exception{
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202110260040L;

   /** Partner key. */
    private String _key;

   /** @param key the duplicated key */
    public DuplicatePartnerKeyExceptionMain(String key) {
        _key = key;
    }
  
    /**
     * @return the invalid partner key.
     */
    public String getPartnerKey() {
        return _key;
    }

}
