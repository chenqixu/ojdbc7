package oracle.net.jdbc.nl.mesg;

import java.util.ListResourceBundle;

public class NLSR_el extends ListResourceBundle {
   static final Object[][] contents = new Object[][]{{"NoFile-04600", "TNS-04600: Δεν βρέθηκε το αρχείο: {0}"}, {"FileReadError-04601", "TNS-04601: Σφάλμα κατά την ανάγνωση του αρχείου παραμέτρων: {0}, σφάλμα: {1}"}, {"SyntaxError-04602", "TNS-04602: Σφάλμα μη αποδεκτής σύνταξης: Αναμένεται \"{0}\" πριν ή στο {1}"}, {"UnexpectedChar-04603", "TNS-04603: Σφάλμα μη αποδεκτής σύνταξης: Μη αναμενόμενος χαρακτήρας \"{0}\" κατά την ανάλυση του {1}"}, {"ParseError-04604", "TNS-04604: Δεν έγινε αρχικοποίηση του αντικειμένου ανάλυσης"}, {"UnexpectedChar-04605", "TNS-04605: Σφάλμα μη αποδεκτής σύνταξης: Μη αναμενόμενος χαρακτήρας ή LITERAL \"{0}\" πριν ή στο {1}"}, {"NoLiterals-04610", "TNS-04610: Δεν υπάρχουν άλλες αλφαριθμητικές σταθερές, βρέθηκε το τέλος για το ζεύγος NV"}, {"InvalidChar-04611", "TNS-04611: Μη αποδεκτός χαρακτήρας συνέχισης μετά το σχόλιο"}, {"NullRHS-04612", "TNS-04612: Μη ορισμένη τιμή RHS για \"{0}\""}, {"Internal-04613", "TNS-04613: Εσωτερικό σφάλμα: {0}"}, {"NoNVPair-04614", "TNS-04614: Το ζεύγος NV {0} δεν βρέθηκε"}, {"InvalidRHS-04615", "TNS-04615: Μη αποδεκτό RHS για {0}"}};

   public Object[][] getContents() {
      return contents;
   }
}
