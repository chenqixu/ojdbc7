package oracle.net.ano;

public interface AnoServices {
   int AUTHENTICATION = 1;
   int ENCRYPTION = 2;
   int DATAINTEGRITY = 3;
   int SUPERVISOR = 4;
   int ACCEPTED = 0;
   int REJECTED = 1;
   int REQUESTED = 2;
   int REQUIRED = 3;
   String ENCRYPTION_RC4_40 = "RC4_40";
   String ENCRYPTION_RC4_56 = "RC4_56";
   String ENCRYPTION_RC4_128 = "RC4_128";
   String ENCRYPTION_RC4_256 = "RC4_256";
   String ENCRYPTION_DES40C = "DES40C";
   String ENCRYPTION_DES56C = "DES56C";
   String ENCRYPTION_3DES112 = "3DES112";
   String ENCRYPTION_3DES168 = "3DES168";
   String ENCRYPTION_AES128 = "AES128";
   String ENCRYPTION_AES192 = "AES192";
   String ENCRYPTION_AES256 = "AES256";
   String CHECKSUM_MD5 = "MD5";
   String CHECKSUM_SHA1 = "SHA1";
   String CHECKSUM_SHA256 = "SHA256";
   String CHECKSUM_SHA384 = "SHA384";
   String CHECKSUM_SHA512 = "SHA512";
   String AUTHENTICATION_RADIUS = "RADIUS";
   String AUTHENTICATION_KERBEROS5 = "KERBEROS5";
   String AUTHENTICATION_TCPS = "TCPS";
   String ANO_ACCEPTED = "ACCEPTED";
   String ANO_REJECTED = "REJECTED";
   String ANO_REQUESTED = "REQUESTED";
   String ANO_REQUIRED = "REQUIRED";
   String ENCRYPTION_PROPERTY_TYPES = "oracle.net.encryption_types_client";
   String ENCRYPTION_PROPERTY_LEVEL = "oracle.net.encryption_client";
   String CHECKSUM_PROPERTY_TYPES = "oracle.net.crypto_checksum_types_client";
   String CHECKSUM_PROPERTY_LEVEL = "oracle.net.crypto_checksum_client";
   String AUTHENTICATION_PROPERTY_SERVICES = "oracle.net.authentication_services";
   String AUTHENTICATION_PROPERTY_KRB5_MUTUAL_AUTH = "oracle.net.kerberos5_mutual_authentication";
   String AUTHENTICATION_PROPERTY_KRB5_CC_NAME = "oracle.net.kerberos5_cc_name";
}
