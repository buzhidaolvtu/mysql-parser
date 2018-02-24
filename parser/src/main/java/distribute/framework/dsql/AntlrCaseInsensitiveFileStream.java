package distribute.framework.dsql;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.IntStream;

import java.io.IOException;

public class AntlrCaseInsensitiveFileStream extends ANTLRFileStream {
   protected char[] lookaheadData;

   public AntlrCaseInsensitiveFileStream(String fileName, String encoding, CaseInsensitiveType caseInsensitiveType) throws IOException {
      super(fileName, encoding);
      lookaheadData = new char[data.length];
      for (int i = 0; i < data.length; i++) {
         lookaheadData[i] = caseInsensitiveType == CaseInsensitiveType.None
            ? data[i]
            : caseInsensitiveType == CaseInsensitiveType.UPPER
            ? Character.toUpperCase(data[i])
            : Character.toLowerCase(data[i]);
      }
   }

   @Override
   public int LA(int i) {
      if (i == 0) {
         return 0; // undefined
      }
      if (i < 0) {
         i++; // e.g., translate LA(-1) to use offset i=0; then data[p+0-1]
         if (((p + i) - 1) < 0) {
            return IntStream.EOF; // invalid; no char before first char
         }
      }
      if (((p + i) - 1) >= n) {
         return IntStream.EOF;
      }
      return lookaheadData[(p + i) - 1];
   }
}
