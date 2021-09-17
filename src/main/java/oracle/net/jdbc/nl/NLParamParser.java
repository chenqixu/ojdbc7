//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package oracle.net.jdbc.nl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

public class NLParamParser {
    private String filename;
    private Hashtable ht;
    private Vector linebuffer;
    private int filePermissions;
    private int Commentcnt;
    private int nvStringcnt;
    private int Groupcnt;
    private boolean hasComments;
    private boolean hasGroups;
    private String[] errstr;
    private int errstrcnt;
    public static final byte IGNORE_NONE = 0;
    public static final byte IGNORE_NL_EXCEPTION = 1;
    public static final byte IGNORE_FILE_EXCEPTION = 2;
    public static final byte NLPASUCC = 1;
    public static final byte NLPAOVWR = 2;
    public static final byte NLPAFAIL = -1;
    private static boolean DEBUG = false;

    public static NLParamParser createEmptyParamParser() {
        return new NLParamParser();
    }

    private NLParamParser() {
        this.filePermissions = 0;
        this.Commentcnt = 0;
        this.nvStringcnt = 0;
        this.Groupcnt = 0;
        this.hasComments = false;
        this.hasGroups = false;
        this.filename = null;
        this.ht = new Hashtable(128);
    }

    public NLParamParser(String var1) throws IOException, NLException {
        this((String)var1, (byte)2);
    }

    public NLParamParser(String var1, byte var2) throws NLException, IOException {
        this.filePermissions = 0;
        this.Commentcnt = 0;
        this.nvStringcnt = 0;
        this.Groupcnt = 0;
        this.hasComments = false;
        this.hasGroups = false;
        this.filename = var1;
        this.ht = new Hashtable(128);
        FileReader var3 = null;
        BufferedReader var4 = null;

        try {
            var3 = new FileReader(var1);
            var4 = new BufferedReader(var3);
            this.initializeNlpa(var4, var2);
        } catch (FileNotFoundException var9) {
            if ((var2 & 2) == 0) {
                throw new FileNotFoundException(var1);
            }
        } finally {
            if (var3 != null) {
                var3.close();
            }

            if (var4 != null) {
                var4.close();
            }

        }

    }

    public NLParamParser(Reader var1) throws IOException, NLException {
        this((Reader)var1, (byte)0);
    }

    public NLParamParser(Reader var1, byte var2) throws IOException, NLException {
        this.filePermissions = 0;
        this.Commentcnt = 0;
        this.nvStringcnt = 0;
        this.Groupcnt = 0;
        this.hasComments = false;
        this.hasGroups = false;
        BufferedReader var3 = new BufferedReader(var1);
        this.filename = null;
        this.ht = new Hashtable(128);
        this.initializeNlpa(var3, var2);
    }

    private void initializeNlpa(BufferedReader var1, byte var2) throws IOException, NLException {
        this.linebuffer = new Vector(100, 50);
        this.errstr = new String[50];

        String var3;
        try {
            while(true) {
                var3 = var1.readLine();
                if (var3 == null) {
                    break;
                }

                this.linebuffer.addElement(var3);
            }
        } catch (IOException var16) {
            if ((var2 & 2) == 0) {
                throw new IOException("Unable to read a line from : " + this.filename);
            }
        }

        var3 = "";
        String var5 = System.getProperty("line.separator");
        String var6 = "";
        String var7 = "";

        for(int var8 = 0; var8 < this.linebuffer.size(); ++var8) {
            String var4 = (String)this.linebuffer.elementAt(var8);
            if (var4.length() != 0) {
                if (var4.charAt(0) == '#') {
                    if (var4.indexOf(".ORA Configuration ") == -1 && var4.indexOf(" Network Configuration File: ") == -1 && var4.indexOf("Generated by") == -1) {
                        if (var6.length() != 0) {
                            var7 = var7 + var4 + var5;
                        } else {
                            var6 = "COMMENT#" + this.Commentcnt;
                            var7 = var4 + var5;
                            if (!this.hasComments) {
                                this.hasComments = true;
                            }
                        }
                    } else if (DEBUG) {
                        System.out.println(var4 + ": this comment ignored");
                    }
                } else if (var4.charAt(0) != ' ' && var4.charAt(0) != '\t' && var4.charAt(0) != ')') {
                    if (var3.length() == 0 && var7.length() == 0) {
                        var4 = this.checkNLPforComments(var4);
                        var3 = var3 + var4 + var5;
                    } else if (var3.length() == 0 && var7.length() != 0) {
                        var7 = this.modifyCommentString(var7);

                        try {
                            this.addNLPListElement(var6 + "=" + var7);
                        } catch (NLException var12) {
                            this.errstr[this.errstrcnt++] = var3;
                            if ((var2 & 1) == 0) {
                                throw var12;
                            }
                        }

                        var6 = "";
                        var7 = "";
                        ++this.Commentcnt;
                        var4 = this.checkNLPforComments(var4);
                        var3 = var3 + var4 + var5;
                    } else if (var3.length() != 0 && var7.length() == 0) {
                        try {
                            this.addNLPListElement(var3);
                        } catch (NLException var13) {
                            this.errstr[this.errstrcnt++] = var3;
                            if ((var2 & 1) == 0) {
                                throw var13;
                            }
                        }

                        var3 = "";
                        var4 = this.checkNLPforComments(var4);
                        var3 = var3 + var4 + var5;
                    } else if (var3.length() != 0 && var7.length() != 0) {
                        try {
                            this.addNLPListElement(var3);
                        } catch (NLException var15) {
                            this.errstr[this.errstrcnt++] = var3;
                            if ((var2 & 1) == 0) {
                                throw var15;
                            }
                        }

                        var3 = "";
                        var4 = this.checkNLPforComments(var4);
                        var3 = var3 + var4 + var5;
                        var7 = this.modifyCommentString(var7);

                        try {
                            this.addNLPListElement(var6 + "=" + var7);
                        } catch (NLException var14) {
                            this.errstr[this.errstrcnt++] = var3;
                            if ((var2 & 1) == 0) {
                                throw var14;
                            }
                        }

                        var6 = "";
                        var7 = "";
                        ++this.Commentcnt;
                    }
                } else if (var7.length() == 0) {
                    if (var3.length() == 0) {
                        var4 = this.eatNLPWS(var4);
                    }

                    var4 = this.checkNLPforComments(var4);
                    if (var4.length() != 0) {
                        var3 = var3 + var4 + var5;
                    }
                } else if (var3.length() == 0 && var7.length() != 0) {
                    var4 = this.eatNLPWS(var4);
                    var4 = this.checkNLPforComments(var4);
                    if (var4.length() != 0 && (var2 & 1) == 0) {
                        throw new NLException("InvalidChar-04611", "");
                    }
                } else if (var3.length() != 0 && var7.length() != 0) {
                    var6 = "";
                    var7 = "";
                    var4 = this.checkNLPforComments(var4);
                    var3 = var3 + var4 + var5;
                }
            }
        }

        if (var3.length() != 0) {
            try {
                this.addNLPListElement(var3);
            } catch (NLException var11) {
                this.errstr[this.errstrcnt++] = var3;
                if ((var2 & 1) == 0) {
                    throw var11;
                }
            }

            var3 = "";
        }

        if (var7.length() != 0) {
            var7 = this.modifyCommentString(var7);

            try {
                this.addNLPListElement(var6 + "=" + var7);
            } catch (NLException var10) {
                this.errstr[this.errstrcnt++] = var3;
                if ((var2 & 1) == 0) {
                    throw var10;
                }
            }

            var6 = "";
            var7 = "";
            ++this.Commentcnt;
        }

    }

    private String modifyCommentString(String var1) {
        String var2 = "";
        boolean var3 = false;

        for(int var4 = 0; var4 < var1.length(); ++var4) {
            char var5 = var1.charAt(var4);
            switch(var5) {
                case '(':
                    var2 = var2 + "\\(";
                    break;
                case ')':
                    var2 = var2 + "\\)";
                    break;
                case ',':
                    var2 = var2 + "\\,";
                    break;
                case '=':
                    var2 = var2 + "\\=";
                    break;
                case '\\':
                    var2 = var2 + "\\\\";
                    break;
                default:
                    var2 = var2 + var1.charAt(var4);
            }
        }

        return var2;
    }

    private String checkNLPforComments(String var1) {
        StringBuffer var2 = new StringBuffer(var1.length());
        boolean var3 = false;

        for(int var4 = 0; var4 < var1.length(); ++var4) {
            char var5 = var1.charAt(var4);
            if (var5 == '#') {
                if (var4 == 0) {
                    return "";
                }

                if (var1.charAt(var4 - 1) != '\\') {
                    break;
                }

                var2.append(var5);
            } else {
                var2.append(var5);
            }
        }

        return var2.toString();
    }

    private String eatNLPWS(String var1) {
        StringBuffer var2 = new StringBuffer(var1.length());
        int var3 = 0;
        boolean var4 = false;

        char var5;
        label29:
        do {
            while(!var4) {
                var5 = var1.charAt(var3++);
                if (var5 == ' ' && var5 == '\t') {
                    continue label29;
                }

                var4 = true;

                for(int var6 = var3 - 1; var1.charAt(var6) == '\n'; ++var6) {
                    var2.append(var1.charAt(var6));
                }
            }

            return var2.toString();
        } while(var5 != '\n');

        return "";
    }

    public void saveNLParams() throws IOException {
        if (this.filename != null) {
            FileWriter var1 = null;

            try {
                var1 = new FileWriter(this.filename);
                String var2 = "unknown";

                for(StringTokenizer var3 = new StringTokenizer(this.filename, File.separator); var3.hasMoreTokens(); var2 = var3.nextToken()) {
                }

                this.writeToStream(var1, var2, this.filename);
            } finally {
                if (var1 != null) {
                    var1.close();
                }

            }
        }
    }

    public void writeToStream(Writer var1, String var2, String var3) {
        PrintWriter var4 = null;
        var4 = new PrintWriter(new BufferedWriter(var1));
        var4.println("# " + var2 + " Network Configuration File: " + var3 + "");
        var4.println("# Generated by Oracle configuration tools.");
        var4.println("");
        if (this.hasGroups) {
            this.saveNLPGroups(var4);
        }

        Enumeration var7 = this.ht.elements();

        while(var7.hasMoreElements()) {
            NVPair var8 = (NVPair)var7.nextElement();
            String var9 = var8.toString(0, true);
            if (DEBUG) {
                System.out.println("The initial stringified NVPair is:\n" + var9);
            }

            if (!var9.equals("")) {
                char[] var5 = new char[var9.length() - 2];
                var9.getChars(1, var9.length() - 1, var5, 0);
                String var6 = new String(var5);
                if (DEBUG) {
                    System.out.println("The modified NV String is:\n" + var6);
                }

                var4.println(var6);
                var4.println("");
                Object var10 = null;
            }
        }

        var4.close();
    }

    public void saveNLParams(String var1) throws FileNotFoundException, IOException {
        String var2 = this.filename;
        this.filename = var1;
        this.saveNLParams();
        this.filename = var2;
    }

    public String getFilename() {
        return this.filename;
    }

    public boolean configuredInFile() {
        return this.filename != null;
    }

    public int getNLPListSize() {
        this.nvStringcnt = 0;
        Enumeration var1 = this.ht.keys();

        while(var1.hasMoreElements()) {
            String var2 = (String)var1.nextElement();
            if (var2.indexOf("COMMENT") == -1) {
                ++this.nvStringcnt;
            }
        }

        return this.nvStringcnt;
    }

    public boolean inErrorList(String var1) {
        boolean var2 = false;
        if (DEBUG) {
            System.out.println("Entering inErrorList():");
        }

        for(int var3 = 0; (!var2 || var3 < this.errstrcnt) && this.errstrcnt != 0; ++var3) {
            if (this.errstr[var3].indexOf(var1) != -1) {
                var2 = true;
            }
        }

        return var2;
    }

    public NVPair getNLPListElement(String var1) {
        String var2 = var1.toUpperCase();
        return (NVPair)((NVPair)this.ht.get(var2));
    }

    public String[] getNLPAllNames() {
        int var1 = this.getNLPListSize();
        String[] var2 = new String[var1];
        int var3 = 0;
        Enumeration var4 = this.ht.keys();

        while(var4.hasMoreElements()) {
            String var5 = (String)var4.nextElement();
            if (var5.indexOf("COMMENT") == -1) {
                var2[var3++] = var5;
            }
        }

        return var2;
    }

    public String[] getNLPAllElements() {
        int var1 = this.getNLPListSize();
        String[] var2 = new String[var1];
        int var3 = 0;
        Enumeration var4 = this.ht.elements();

        while(var4.hasMoreElements()) {
            NVPair var5 = (NVPair)var4.nextElement();
            if (var5.getName().indexOf("COMMENT") == -1) {
                String var6 = var5.toString();
                var2[var3++] = var6;
            }
        }

        return var2;
    }

    public byte addNLPListElement(String var1, Object var2) {
        try {
            Object var3 = this.ht.put(var1, var2);
            return (byte)(var3 != null ? 2 : 1);
        } catch (NullPointerException var4) {
            if (DEBUG) {
                System.out.println(var4.getMessage());
            }

            return -1;
        }
    }

    public void addNLPGroupProfile(String[] var1) {
        String var2 = new String("GROUP#" + this.Groupcnt++);
        if (!this.hasGroups) {
            this.hasGroups = true;
        }

        this.addNLPListElement(var2, var1);
    }

    private String[] getNLPGroupProfile(String var1) {
        String var2 = var1.toUpperCase();
        return (String[])((String[])this.ht.get(var2));
    }

    private void saveNLPGroups(PrintWriter var1) {
        for(int var3 = 0; var3 < this.Groupcnt; ++var3) {
            String var4 = new String("GROUP#" + var3);
            String[] var2 = this.getNLPGroupProfile(var4);

            for(int var5 = 0; var5 < var2.length; ++var5) {
                String var7 = null;
                String var8 = null;
                NVPair var9 = null;
                if (DEBUG) {
                    System.out.println("Current Value in Group Profile: " + var2[var5]);
                }

                if (var2[var5] != null) {
                    var9 = this.getNLPListElement(var2[var5]);
                    if (var9 != null) {
                        var7 = var9.toString(0, true);
                        if (DEBUG) {
                            System.out.println("Parameter Value = " + var7);
                        }

                        char[] var6 = new char[var7.length() - 2];
                        var7.getChars(1, var7.length() - 1, var6, 0);
                        var8 = new String(var6);
                        var1.println(var8);
                        var1.println("");
                        NVPair var10 = this.removeNLPListElement(var2[var5]);
                        if (var10 == null && DEBUG) {
                            System.out.println("saveNLPGroups(): Could notremove param from Hashtable");
                        }

                        Object var11 = null;
                        var8 = null;
                    } else if (DEBUG) {
                        System.out.println("No such Parameter in the Table");
                    }
                }
            }

            this.removeNLPGroupProfile(var4);
        }

    }

    public void addNLPListElement(String var1) throws NLException {
        char[] var2 = new char[var1.length() + 2];
        String var3 = "";
        if (DEBUG) {
            System.out.println("Entering Method addNLPListElement\n");
            System.out.println("String to add is: " + var1 + "");
        }

        var1.getChars(0, var1.length(), var2, 1);
        if (var2[1] == '(') {
            var3 = var1;
        } else {
            var2[0] = '(';
            String var4 = System.getProperty("os.name");
            if (!var4.equals("Windows NT") && !var4.equals("Windows 95")) {
                if (var2[var2.length - 2] == '\\') {
                    var2[var2.length - 2] = ')';
                } else {
                    var2[var2.length - 1] = ')';
                }
            } else if (var2[var2.length - 2] != '/' && var2[var2.length - 2] != '\\') {
                var2[var2.length - 1] = ')';
            } else {
                var2[var2.length - 2] = ')';
            }

            var3 = new String(var2);
            if (DEBUG) {
                System.out.println("The modified NV String is: " + var3 + "");
            }
        }

        NVFactory var9 = new NVFactory();
        NVPair var5 = var9.createNVPair(var3);
        if (var5.getRHSType() == NVPair.RHS_NONE) {
            throw new NLException("NullRHS-04612", var5.getName());
        } else {
            String var6 = var5.getName();
            String var7 = var6.toUpperCase();
            var5.setName(var7);
            if (DEBUG) {
                System.out.println("The final NV String is: " + var5.toString() + "");
            }

            byte var8 = this.addNLPListElement(var7, var5);
            switch(var8) {
                case -1:
                    if (DEBUG) {
                        System.out.println("The value for the Name: " + var6 + " could not be inserted\n");
                    }
                    break;
                case 2:
                    if (DEBUG) {
                        System.out.println("The value for the Name: " + var6 + " was overwritten\n");
                    }
            }

        }
    }

    public NVPair removeNLPListElement(String var1) {
        String var2 = var1.toUpperCase();
        if (DEBUG) {
            System.out.println("Trying to remove: " + var2 + " from Table");
        }

        Object var3 = this.ht.remove(var2);
        return var3 != null ? (NVPair)var3 : null;
    }

    public void removeNLPGroupProfile(String var1) {
        String var2 = var1.toUpperCase();
        if (DEBUG) {
            System.out.println("Trying to remove: " + var2 + " GroupName from Table");
        }

        this.ht.remove(var2);
    }

    public void removeNLPAllElements() {
        this.ht.clear();
    }

    public String toString() {
        String var1 = "";

        String var4;
        for(Enumeration var2 = this.ht.elements(); var2.hasMoreElements(); var1 = var1 + var4 + "\n") {
            NVPair var3 = (NVPair)var2.nextElement();
            var4 = var3.toString();
        }

        return var1;
    }

    public boolean fileHasComments() {
        return this.hasComments;
    }

    public void println() {
        System.out.println(this.toString());
    }

    public void setFilePermissions(int var1) {
        this.filePermissions = var1;
    }
}
