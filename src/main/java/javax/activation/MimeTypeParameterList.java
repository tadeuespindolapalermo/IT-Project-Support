/*     */ package javax.activation;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MimeTypeParameterList
/*     */ {
/*     */   private Hashtable parameters;
/*     */   private static final String TSPECIALS = "()<>@,;:/[]?=\\\"";
/*     */   
/*     */   public MimeTypeParameterList() {
/*  37 */     this.parameters = new Hashtable<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MimeTypeParameterList(String parameterList) throws MimeTypeParseException {
/*  48 */     this.parameters = new Hashtable<>();
/*     */ 
/*     */     
/*  51 */     parse(parameterList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parse(String parameterList) throws MimeTypeParseException {
/*  61 */     if (parameterList == null) {
/*     */       return;
/*     */     }
/*  64 */     int length = parameterList.length();
/*  65 */     if (length <= 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  70 */     int i = skipWhiteSpace(parameterList, 0); char c;
/*  71 */     for (; i < length && (c = parameterList.charAt(i)) == ';'; 
/*  72 */       i = skipWhiteSpace(parameterList, i)) {
/*     */       String value;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  78 */       i++;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  83 */       i = skipWhiteSpace(parameterList, i);
/*     */ 
/*     */       
/*  86 */       if (i >= length) {
/*     */         return;
/*     */       }
/*     */       
/*  90 */       int lastIndex = i;
/*  91 */       while (i < length && isTokenChar(parameterList.charAt(i))) {
/*  92 */         i++;
/*     */       }
/*     */       
/*  95 */       String name = parameterList.substring(lastIndex, i).toLowerCase(Locale.ENGLISH);
/*     */ 
/*     */       
/*  98 */       i = skipWhiteSpace(parameterList, i);
/*     */       
/* 100 */       if (i >= length || parameterList.charAt(i) != '=') {
/* 101 */         throw new MimeTypeParseException("Couldn't find the '=' that separates a parameter name from its value.");
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 106 */       i++;
/* 107 */       i = skipWhiteSpace(parameterList, i);
/*     */       
/* 109 */       if (i >= length) {
/* 110 */         throw new MimeTypeParseException("Couldn't find a value for parameter named " + name);
/*     */       }
/*     */ 
/*     */       
/* 114 */       c = parameterList.charAt(i);
/* 115 */       if (c == '"') {
/*     */         
/* 117 */         i++;
/* 118 */         if (i >= length) {
/* 119 */           throw new MimeTypeParseException("Encountered unterminated quoted parameter value.");
/*     */         }
/*     */         
/* 122 */         lastIndex = i;
/*     */ 
/*     */         
/* 125 */         while (i < length) {
/* 126 */           c = parameterList.charAt(i);
/* 127 */           if (c == '"')
/*     */             break; 
/* 129 */           if (c == '\\')
/*     */           {
/*     */ 
/*     */             
/* 133 */             i++;
/*     */           }
/* 135 */           i++;
/*     */         } 
/* 137 */         if (c != '"') {
/* 138 */           throw new MimeTypeParseException("Encountered unterminated quoted parameter value.");
/*     */         }
/*     */         
/* 141 */         value = unquote(parameterList.substring(lastIndex, i));
/*     */         
/* 143 */         i++;
/* 144 */       } else if (isTokenChar(c)) {
/*     */ 
/*     */         
/* 147 */         lastIndex = i;
/* 148 */         while (i < length && isTokenChar(parameterList.charAt(i)))
/* 149 */           i++; 
/* 150 */         value = parameterList.substring(lastIndex, i);
/*     */       } else {
/*     */         
/* 153 */         throw new MimeTypeParseException("Unexpected character encountered at index " + i);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 158 */       this.parameters.put(name, value);
/*     */     } 
/* 160 */     if (i < length) {
/* 161 */       throw new MimeTypeParseException("More characters encountered in input than expected.");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 172 */     return this.parameters.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 181 */     return this.parameters.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String get(String name) {
/* 192 */     return (String)this.parameters.get(name.trim().toLowerCase(Locale.ENGLISH));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, String value) {
/* 203 */     this.parameters.put(name.trim().toLowerCase(Locale.ENGLISH), value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(String name) {
/* 212 */     this.parameters.remove(name.trim().toLowerCase(Locale.ENGLISH));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration getNames() {
/* 221 */     return this.parameters.keys();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 228 */     StringBuffer buffer = new StringBuffer();
/* 229 */     buffer.ensureCapacity(this.parameters.size() * 16);
/*     */ 
/*     */     
/* 232 */     Enumeration<String> keys = this.parameters.keys();
/* 233 */     while (keys.hasMoreElements()) {
/* 234 */       String key = keys.nextElement();
/* 235 */       buffer.append("; ");
/* 236 */       buffer.append(key);
/* 237 */       buffer.append('=');
/* 238 */       buffer.append(quote((String)this.parameters.get(key)));
/*     */     } 
/*     */     
/* 241 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isTokenChar(char c) {
/* 250 */     return (c > ' ' && c < '' && "()<>@,;:/[]?=\\\"".indexOf(c) < 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int skipWhiteSpace(String rawdata, int i) {
/* 258 */     int length = rawdata.length();
/* 259 */     while (i < length && Character.isWhitespace(rawdata.charAt(i)))
/* 260 */       i++; 
/* 261 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String quote(String value) {
/* 268 */     boolean needsQuotes = false;
/*     */ 
/*     */     
/* 271 */     int length = value.length();
/* 272 */     for (int i = 0; i < length && !needsQuotes; i++) {
/* 273 */       needsQuotes = !isTokenChar(value.charAt(i));
/*     */     }
/*     */     
/* 276 */     if (needsQuotes) {
/* 277 */       StringBuffer buffer = new StringBuffer();
/* 278 */       buffer.ensureCapacity((int)(length * 1.5D));
/*     */ 
/*     */       
/* 281 */       buffer.append('"');
/*     */ 
/*     */       
/* 284 */       for (int j = 0; j < length; j++) {
/* 285 */         char c = value.charAt(j);
/* 286 */         if (c == '\\' || c == '"')
/* 287 */           buffer.append('\\'); 
/* 288 */         buffer.append(c);
/*     */       } 
/*     */ 
/*     */       
/* 292 */       buffer.append('"');
/*     */       
/* 294 */       return buffer.toString();
/*     */     } 
/* 296 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String unquote(String value) {
/* 305 */     int valueLength = value.length();
/* 306 */     StringBuffer buffer = new StringBuffer();
/* 307 */     buffer.ensureCapacity(valueLength);
/*     */     
/* 309 */     boolean escaped = false;
/* 310 */     for (int i = 0; i < valueLength; i++) {
/* 311 */       char currentChar = value.charAt(i);
/* 312 */       if (!escaped && currentChar != '\\') {
/* 313 */         buffer.append(currentChar);
/* 314 */       } else if (escaped) {
/* 315 */         buffer.append(currentChar);
/* 316 */         escaped = false;
/*     */       } else {
/* 318 */         escaped = true;
/*     */       } 
/*     */     } 
/*     */     
/* 322 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\javax\activation\MimeTypeParameterList.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */