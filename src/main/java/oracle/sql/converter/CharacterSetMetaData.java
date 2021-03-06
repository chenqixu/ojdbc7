//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package oracle.sql.converter;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class CharacterSetMetaData {
    static final short WIDTH_SIZE = 8;
    static final short WIDTH_MASK = 255;
    static final short FLAG_FIXEDWIDTH = 256;
    public static final int ST_BADCODESET = 0;
    private static final HashMap language = new HashMap(58, 1.0F);
    private static final HashMap territory = new HashMap(134, 1.0F);
    static InternalCharacterSetMetaData metaDataImpl;
    private static final short[][] m_maxCharWidth;
    private static final String _Copyright_2007_Oracle_All_Rights_Reserved_;
    public static final String BUILD_DATE = "Thu_Apr_04_15:09:24_PDT_2013";
    public static final boolean TRACE = false;

    public CharacterSetMetaData() {
    }

    private static final void getMapProperties() {
        try {
            String[] var0 = new String[]{"oracle.jdbc.languageMap", "oracle.jdbc.territoryMap"};
            HashMap[] var1 = new HashMap[]{language, territory};

            for(int var4 = 0; var4 < var0.length; ++var4) {
                final String var5 = var0[var4];
                String var6 = (String)AccessController.doPrivileged(new PrivilegedAction<String>() {
                    public String run() {
                        return System.getProperty(var5, (String)null);
                    }
                });
                if (var6 != null) {
                    StringTokenizer var7 = new StringTokenizer(var6, "=;");
                    if (var7.countTokens() % 2 == 0) {
                        while(var7.hasMoreTokens()) {
                            String var2 = var7.nextToken();
                            String var3 = var7.nextToken();
                            var1[var4].put(var2, var3);
                        }
                    }
                }
            }
        } catch (Exception var8) {
        }

    }

    public static String getNLSLanguage(Locale var0) {
        String var1 = null;
        var1 = (String)language.get(var0.getLanguage() + "_" + var0.getCountry());
        if (var1 == null) {
            var1 = (String)language.get(var0.getLanguage());
        }

        return var1;
    }

    public static String getNLSTerritory(Locale var0) {
        String var1 = null;
        var1 = (String)territory.get(var0.getLanguage() + "_" + var0.getCountry());
        if (var1 == null) {
            var1 = (String)territory.get(var0.getCountry());
            if (var1 == null) {
                var1 = (String)territory.get(var0.getLanguage());
            }
        }

        return var1;
    }

    public static boolean isFixedWidth(int var0) throws SQLException {
        return metaDataImpl.isFixedWidth(var0);
    }

    public static int getRatio(int var0, int var1) {
        if (var1 == var0) {
            return 1;
        } else {
            int var2 = metaDataImpl.getMaxCharLength(var0);
            if (var2 == 0) {
                return 0;
            } else if (var2 == 1) {
                return 1;
            } else if (var1 == 1) {
                return var2;
            } else if (metaDataImpl.isFixedWidth(var1)) {
                return var2;
            } else {
                int var3 = metaDataImpl.getMaxCharLength(var1);
                if (var3 == 0) {
                    return 0;
                } else {
                    int var4 = var2 / var3;
                    if (var2 % var3 != 0) {
                        ++var4;
                    }

                    return var4;
                }
            }
        }
    }

    static {
        language.put("", "AMERICAN");
        language.put("ar_EG", "EGYPTIAN");
        language.put("ar", "ARABIC");
        language.put("as", "ASSAMESE");
        language.put("bg", "BULGARIAN");
        language.put("bn", "BANGLA");
        language.put("ca", "CATALAN");
        language.put("cs", "CZECH");
        language.put("da", "DANISH");
        language.put("de", "GERMAN");
        language.put("el", "GREEK");
        language.put("en", "AMERICAN");
        language.put("es_ES", "SPANISH");
        language.put("es_MX", "MEXICAN SPANISH");
        language.put("es", "LATIN AMERICAN SPANISH");
        language.put("et", "ESTONIAN");
        language.put("fi", "FINNISH");
        language.put("fr_CA", "CANADIAN FRENCH");
        language.put("fr", "FRENCH");
        language.put("ga", "IRISH");
        language.put("gu", "GUJARATI");
        language.put("he", "HEBREW");
        language.put("hi", "HINDI");
        language.put("hr", "CROATIAN");
        language.put("hu", "HUNGARIAN");
        language.put("id", "INDONESIAN");
        language.put("in", "INDONESIAN");
        language.put("is", "ICELANDIC");
        language.put("it", "ITALIAN");
        language.put("iw", "HEBREW");
        language.put("ja", "JAPANESE");
        language.put("kn", "KANNADA");
        language.put("ko", "KOREAN");
        language.put("kk", "CYRILLIC KAZAKH");
        language.put("kk_KZ", "CYRILLIC KAZAKH");
        language.put("lt", "LITHUANIAN");
        language.put("lv", "LATVIAN");
        language.put("mk", "MACEDONIAN");
        language.put("ml", "MALAYALAM");
        language.put("mr", "MARATHI");
        language.put("ms", "MALAY");
        language.put("nb", "NORWEGIAN");
        language.put("nl", "DUTCH");
        language.put("no", "NORWEGIAN");
        language.put("or", "ORIYA");
        language.put("pa", "PUNJABI");
        language.put("pl", "POLISH");
        language.put("pt_BR", "BRAZILIAN PORTUGUESE");
        language.put("pt", "PORTUGUESE");
        language.put("ro", "ROMANIAN");
        language.put("ru", "RUSSIAN");
        language.put("sk", "SLOVAK");
        language.put("sq", "ALBANIAN");
        language.put("sl", "SLOVENIAN");
        language.put("sr", "CYRILLIC SERBIAN");
        language.put("sh", "LATIN SERBIAN");
        language.put("sv", "SWEDISH");
        language.put("ta", "TAMIL");
        language.put("te", "TELUGU");
        language.put("th", "THAI");
        language.put("tr", "TURKISH");
        language.put("uk", "UKRAINIAN");
        language.put("vi", "VIETNAMESE");
        language.put("zh_HK", "TRADITIONAL CHINESE");
        language.put("zh_TW", "TRADITIONAL CHINESE");
        language.put("zh", "SIMPLIFIED CHINESE");
        territory.put("AE", "UNITED ARAB EMIRATES");
        territory.put("AL", "ALBANIA");
        territory.put("AT", "AUSTRIA");
        territory.put("AU", "AUSTRALIA");
        territory.put("BD", "BANGLADESH");
        territory.put("BE", "BELGIUM");
        territory.put("BG", "BULGARIA");
        territory.put("BH", "BAHRAIN");
        territory.put("BR", "BRAZIL");
        territory.put("CA", "CANADA");
        territory.put("CH", "SWITZERLAND");
        territory.put("CL", "CHILE");
        territory.put("CN", "CHINA");
        territory.put("CO", "COLOMBIA");
        territory.put("CR", "COSTA RICA");
        territory.put("CY", "CYPRUS");
        territory.put("CZ", "CZECH REPUBLIC");
        territory.put("DE", "GERMANY");
        territory.put("DJ", "DJIBOUTI");
        territory.put("DK", "DENMARK");
        territory.put("DZ", "ALGERIA");
        territory.put("EE", "ESTONIA");
        territory.put("EG", "EGYPT");
        territory.put("ES", "SPAIN");
        territory.put("ca_ES", "CATALONIA");
        territory.put("FI", "FINLAND");
        territory.put("FR", "FRANCE");
        territory.put("GB", "UNITED KINGDOM");
        territory.put("GR", "GREECE");
        territory.put("GT", "GUATEMALA");
        territory.put("HK", "HONG KONG");
        territory.put("HR", "CROATIA");
        territory.put("HU", "HUNGARY");
        territory.put("ID", "INDONESIA");
        territory.put("IE", "IRELAND");
        territory.put("IL", "ISRAEL");
        territory.put("IN", "INDIA");
        territory.put("IQ", "IRAQ");
        territory.put("IS", "ICELAND");
        territory.put("IT", "ITALY");
        territory.put("JO", "JORDAN");
        territory.put("JP", "JAPAN");
        territory.put("KR", "KOREA");
        territory.put("KW", "KUWAIT");
        territory.put("LB", "LEBANON");
        territory.put("LT", "LITHUANIA");
        territory.put("LU", "LUXEMBOURG");
        territory.put("LV", "LATVIA");
        territory.put("LY", "LIBYA");
        territory.put("MA", "MOROCCO");
        territory.put("MK", "FYR MACEDONIA");
        territory.put("MR", "MAURITANIA");
        territory.put("MX", "MEXICO");
        territory.put("MY", "MALAYSIA");
        territory.put("NI", "NICARAGUA");
        territory.put("NL", "THE NETHERLANDS");
        territory.put("NO", "NORWAY");
        territory.put("NZ", "NEW ZEALAND");
        territory.put("OM", "OMAN");
        territory.put("PA", "PANAMA");
        territory.put("PE", "PERU");
        territory.put("PL", "POLAND");
        territory.put("PR", "PUERTO RICO");
        territory.put("PT", "PORTUGAL");
        territory.put("QA", "QATAR");
        territory.put("RO", "ROMANIA");
        territory.put("RU", "CIS");
        territory.put("SA", "SAUDI ARABIA");
        territory.put("SD", "SUDAN");
        territory.put("SE", "SWEDEN");
        territory.put("SG", "SINGAPORE");
        territory.put("SI", "SLOVENIA");
        territory.put("SK", "SLOVAKIA");
        territory.put("SO", "SOMALIA");
        territory.put("SV", "EL SALVADOR");
        territory.put("SY", "SYRIA");
        territory.put("TH", "THAILAND");
        territory.put("TN", "TUNISIA");
        territory.put("TR", "TURKEY");
        territory.put("TW", "TAIWAN");
        territory.put("UA", "UKRAINE");
        territory.put("US", "AMERICA");
        territory.put("VE", "VENEZUELA");
        territory.put("VN", "VIETNAM");
        territory.put("YE", "YEMEN");
        territory.put("ZA", "SOUTH AFRICA");
        territory.put("ar", "SAUDI ARABIA");
        territory.put("as", "INDIA");
        territory.put("bg", "BULGARIA");
        territory.put("bn", "BANGLADESH");
        territory.put("ca", "CATALONIA");
        territory.put("cs", "CZECH REPUBLIC");
        territory.put("da", "DENMARK");
        territory.put("de", "GERMANY");
        territory.put("el", "GREECE");
        territory.put("en", "AMERICA");
        territory.put("es", "AMERICA");
        territory.put("et", "ESTONIA");
        territory.put("fi", "FINLAND");
        territory.put("fr", "FRANCE");
        territory.put("gu", "INDIA");
        territory.put("he", "ISRAEL");
        territory.put("hi", "INDIA");
        territory.put("hr", "CROATIA");
        territory.put("hu", "HUNGARY");
        territory.put("id", "INDONESIA");
        territory.put("in", "INDONESIA");
        territory.put("is", "ICELAND");
        territory.put("it", "ITALY");
        territory.put("iw", "ISRAEL");
        territory.put("ja", "JAPAN");
        territory.put("kn", "INDIA");
        territory.put("ko", "KOREA");
        territory.put("kk", "KAZAKHSTAN");
        territory.put("kk_KZ", "KAZAKHSTAN");
        territory.put("lt", "LITHUANIA");
        territory.put("lv", "LATVIA");
        territory.put("mk", "FYR MACEDONIA");
        territory.put("ml", "INDIA");
        territory.put("mr", "INDIA");
        territory.put("ms", "MALAYSIA");
        territory.put("nl", "THE NETHERLANDS");
        territory.put("no", "NORWAY");
        territory.put("or", "INDIA");
        territory.put("pa", "INDIA");
        territory.put("pl", "POLAND");
        territory.put("pt", "PORTUGAL");
        territory.put("ro", "ROMANIA");
        territory.put("ru", "CIS");
        territory.put("sk", "SLOVAKIA");
        territory.put("sl", "SLOVENIA");
        territory.put("sq", "ALBANIA");
        territory.put("sr", "SERBIA AND MONTENEGRO");
        territory.put("sh", "SERBIA AND MONTENEGRO");
        territory.put("sv", "SWEDEN");
        territory.put("ta", "INDIA");
        territory.put("te", "INDIA");
        territory.put("th", "THAILAND");
        territory.put("tr", "TURKEY");
        territory.put("uk", "UKRAINE");
        territory.put("vi", "VIETNAM");
        territory.put("zh", "CHINA");
        metaDataImpl = null;
        Orai18nCharacterSetMetaData var0 = null;

        try {
            Class.forName("oracle.i18n.text.OraBoot");
            var0 = new Orai18nCharacterSetMetaData();
        } catch (ClassNotFoundException var2) {
        }

        if (var0 != null && var0.oraBoot != null) {
            metaDataImpl = var0;
        } else {
            metaDataImpl = new CharacterSetMetaData.JdbcCharacterSetMetaData();
        }

        m_maxCharWidth = new short[][]{{1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1}, {9, 1}, {10, 1}, {11, 1}, {12, 1}, {13, 1}, {14, 1}, {15, 1}, {16, 1}, {17, 1}, {18, 1}, {19, 1}, {20, 1}, {21, 1}, {22, 1}, {23, 1}, {25, 1}, {27, 1}, {28, 1}, {31, 1}, {32, 1}, {33, 1}, {34, 1}, {35, 1}, {36, 1}, {37, 1}, {38, 1}, {39, 1}, {40, 1}, {41, 1}, {42, 1}, {43, 1}, {44, 1}, {45, 1}, {46, 1}, {47, 1}, {48, 1}, {49, 1}, {50, 1}, {51, 1}, {61, 1}, {70, 1}, {72, 1}, {81, 1}, {82, 1}, {90, 1}, {91, 1}, {92, 1}, {93, 1}, {94, 1}, {95, 1}, {96, 1}, {97, 1}, {98, 1}, {99, 1}, {100, 1}, {101, 1}, {110, 1}, {113, 1}, {114, 1}, {140, 1}, {150, 1}, {152, 1}, {153, 1}, {154, 1}, {155, 1}, {156, 1}, {158, 1}, {159, 1}, {160, 1}, {161, 1}, {162, 1}, {163, 1}, {164, 1}, {165, 1}, {166, 1}, {167, 1}, {170, 1}, {171, 1}, {172, 1}, {173, 1}, {174, 1}, {175, 1}, {176, 1}, {177, 1}, {178, 1}, {179, 1}, {180, 1}, {181, 1}, {182, 1}, {183, 1}, {184, 1}, {185, 1}, {186, 1}, {187, 1}, {188, 1}, {189, 1}, {190, 1}, {191, 1}, {192, 1}, {193, 1}, {194, 1}, {195, 1}, {196, 1}, {197, 1}, {198, 1}, {199, 1}, {200, 1}, {201, 1}, {202, 1}, {203, 1}, {204, 1}, {205, 1}, {206, 1}, {207, 1}, {208, 1}, {210, 1}, {211, 1}, {221, 1}, {222, 1}, {223, 1}, {224, 1}, {225, 1}, {226, 1}, {230, 1}, {231, 1}, {232, 1}, {233, 1}, {235, 1}, {239, 1}, {241, 1}, {251, 1}, {261, 1}, {262, 1}, {263, 1}, {264, 1}, {265, 1}, {266, 1}, {267, 1}, {277, 1}, {278, 1}, {279, 1}, {301, 1}, {311, 1}, {312, 1}, {314, 1}, {315, 1}, {316, 1}, {317, 1}, {319, 1}, {320, 1}, {322, 1}, {323, 1}, {324, 1}, {351, 1}, {352, 1}, {353, 1}, {354, 1}, {368, 1}, {380, 1}, {381, 1}, {382, 1}, {383, 1}, {384, 1}, {385, 1}, {386, 1}, {390, 1}, {401, 1}, {500, 1}, {504, 1}, {505, 1}, {506, 1}, {507, 1}, {508, 1}, {509, 1}, {511, 1}, {514, 1}, {554, 1}, {555, 1}, {556, 1}, {557, 1}, {558, 1}, {559, 1}, {560, 1}, {561, 1}, {563, 1}, {565, 1}, {566, 1}, {567, 1}, {590, 1}, {798, 1}, {799, 258}, {829, 2}, {830, 3}, {831, 3}, {832, 2}, {833, 3}, {834, 2}, {835, 3}, {836, 2}, {837, 3}, {838, 2}, {840, 2}, {842, 3}, {845, 2}, {846, 2}, {850, 2}, {851, 2}, {852, 2}, {853, 3}, {854, 4}, {860, 4}, {861, 4}, {862, 2}, {863, 4}, {864, 3}, {865, 2}, {866, 2}, {867, 2}, {868, 2}, {870, 3}, {871, 3}, {872, 4}, {873, 4}, {992, 2}, {994, 2}, {995, 2}, {996, 3}, {997, 2}, {998, 3}, {1001, 258}, {1830, 258}, {1832, 258}, {1833, 258}, {1840, 258}, {1842, 258}, {1850, 258}, {1852, 258}, {1853, 258}, {1860, 258}, {1863, 260}, {1864, 258}, {1865, 258}, {2000, 258}, {2002, 258}, {9996, 3}, {9997, 3}, {9998, 3}, {9999, 3}};
        _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
        getMapProperties();
    }

    static class JdbcCharacterSetMetaData implements InternalCharacterSetMetaData {
        JdbcCharacterSetMetaData() {
        }

        public boolean isFixedWidth(int var1) {
            if (var1 == 0) {
                return false;
            } else {
                int var2 = -1;
                int var3 = 0;
                int var4 = CharacterSetMetaData.m_maxCharWidth.length - 1;
                boolean var5 = true;

                while(var3 <= var4) {
                    int var6 = (var3 + var4) / 2;
                    if (var1 < CharacterSetMetaData.m_maxCharWidth[var6][0]) {
                        var4 = var6 - 1;
                    } else {
                        if (var1 <= CharacterSetMetaData.m_maxCharWidth[var6][0]) {
                            var2 = var6;
                            break;
                        }

                        var3 = var6 + 1;
                    }
                }

                return (CharacterSetMetaData.m_maxCharWidth[var2][1] & 256) != 0;
            }
        }

        public int getMaxCharLength(int var1) {
            int var2 = -1;
            int var3 = 0;
            int var4 = CharacterSetMetaData.m_maxCharWidth.length - 1;

            while(var3 <= var4) {
                int var5 = (var3 + var4) / 2;
                if (var1 < CharacterSetMetaData.m_maxCharWidth[var5][0]) {
                    var4 = var5 - 1;
                } else {
                    if (var1 <= CharacterSetMetaData.m_maxCharWidth[var5][0]) {
                        var2 = var5;
                        break;
                    }

                    var3 = var5 + 1;
                }
            }

            return var2 < 0 ? 0 : CharacterSetMetaData.m_maxCharWidth[var2][1] & 255;
        }
    }
}
